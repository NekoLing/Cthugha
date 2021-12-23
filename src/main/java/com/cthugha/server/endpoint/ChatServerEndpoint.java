package com.cthugha.server.endpoint;
import java.util.Date;

import com.cthugha.model.play.Log;
import com.cthugha.model.system.User;
import com.cthugha.model.system.UserInfo;
import com.cthugha.service.play.ILogService;
import com.cthugha.service.system.IUserService;
import com.cthugha.util.JsonResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @Classname ChatServerEndpoint
 * @Description
 * @Date 2021/12/23 8:26
 * @Created by gaoqi
 */
@Component
@ServerEndpoint("/ws/chat/{userId}")
public class ChatServerEndpoint {
    private static final int singelChatMsg = 0;

    private static IUserService userService;
    private static ILogService logService;

    @Autowired
    public void setSerivce(IUserService userService, ILogService logService) {
        ChatServerEndpoint.userService = userService;
        ChatServerEndpoint.logService = logService;
    }


    /**
     * 当前在线用户
     */
    private static CopyOnWriteArrayList<UserInfo> onlineUser = new CopyOnWriteArrayList();

    /**
     * 用来存放每个客户端对应的 Session 对象
     */
    private static ConcurrentHashMap<Integer, Session> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收 userId
     */
    private Integer userId;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String id) {
        System.out.println("连接");
        this.session = session;
        this.userId = Integer.parseInt(id);
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, session);
        } else {
            webSocketMap.put(userId, session);
            User user = userService.getById(userId);
            UserInfo userInfo = new UserInfo(user);
            addOnlineUser(userInfo);
            sendMessageToAll(new JsonResponse(onlineUser, 1).toJsonString());
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            removeOnlineUser(userId);
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        Gson gson = new Gson();
        System.out.println("用户消息:" + userId + ",报文:" + message);
        if (!ObjectUtils.isEmpty(message)) {
            JsonParser parser = new JsonParser();
            JsonObject jsonMsg  = parser.parse(message).getAsJsonObject();
            int type = jsonMsg.get("type").getAsInt();
            if (type == 0) {
                JsonObject jsonLog = jsonMsg.get("data").getAsJsonObject();
                Log log = gson.fromJson(jsonLog, Log.class);
                logService.add(log);
                sendMessageToAll(new JsonResponse(log,singelChatMsg).toJsonString());
            }
            if (type == 2) {
                Integer dramaId = jsonMsg.get("data").getAsInt();
                sendLogList(session, dramaId);
            }
            if (type == 3) {
                Integer logId = jsonMsg.get("data").getAsInt();
                logService.positionUp(logId);
                sendMessageToAll(new JsonResponse(logId, 3).toJsonString());
            }
            if (type == 4) {
                Integer logId = jsonMsg.get("data").getAsInt();
                logService.positionDown(logId);
                sendMessageToAll(new JsonResponse(logId, 4).toJsonString());
            }
        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("=============");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public void sendMessageToAll(String message) {
        List<Session> sessions = new ArrayList<>(webSocketMap.values());
        for (Session session : sessions) {
            try {
                System.out.println("发送报文");
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendLogList(Session session, Integer dramaId) {
        JsonResponse response = new JsonResponse();
        response.setType(2);
        response.setData(logService.listByDramaId(dramaId));
        try {
            session.getBasicRemote().sendText(response.toJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<UserInfo> getOnlineUser() {
        return onlineUser;
    }

    public static void addOnlineUser(UserInfo userInfo) {
        onlineUser.add(userInfo);
    }

    public static  void removeOnlineUser(Integer userId) {
        int index = 0;
        for (int i = 0; i < onlineUser.size(); i++) {
            if (onlineUser.get(i).getUserId() == userId) {
                index = i;
                break;
            }
        }
        onlineUser.remove(index);
    }
}
