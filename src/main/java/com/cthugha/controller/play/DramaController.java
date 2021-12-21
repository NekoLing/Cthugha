package com.cthugha.controller.play;

import com.cthugha.model.play.Drama;
import com.cthugha.service.play.IDramaService;
import com.cthugha.service.system.IUserService;
import com.cthugha.util.CookieUtils;
import com.cthugha.util.JsonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname DramaController
 * @Description 剧本的控制类
 * @Date 2021/12/21 15:33
 * @Created by gaoqi
 */
@Controller
@RequestMapping("/drama")
public class DramaController {

    final IDramaService dramaService;
    final IUserService userService;

    public DramaController(IDramaService dramaService, IUserService userService) {
        this.dramaService = dramaService;
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping("/create")
    public JsonResponse create(HttpServletRequest request, @RequestBody Drama drama) {
        Integer createUserId = Integer.parseInt(CookieUtils.get(request, "userId"));
        dramaService.add(drama, userService.getById(createUserId));
        return new JsonResponse(0,"创建成功");
    }
}
