package com.cthugha.controller.play;

import com.cthugha.model.play.Drama;
import com.cthugha.model.play.Performer;
import com.cthugha.service.play.IDramaService;
import com.cthugha.service.play.IPerformerService;
import com.cthugha.util.CookieUtils;
import com.cthugha.util.JsonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * @Classname PerformerController
 * @Description
 * @Date 2021/12/21 16:34
 * @Created by gaoqi
 */
@RestController
@RequestMapping("/performer")
public class PerformerController {
    final IDramaService dramaService;
    final IPerformerService performerService;

    public PerformerController(IDramaService dramaService, IPerformerService performerService) {
        this.dramaService = dramaService;
        this.performerService = performerService;
    }

    @GetMapping("/join")
    public JsonResponse join(HttpServletRequest request,
                             @RequestParam("id") Integer dramaId,
                             @RequestParam("code") String code) {
        Drama drama = dramaService.getById(dramaId);
        if (drama == null)
            return new JsonResponse(1, "剧本不存在");
        else if (!drama.getCode().equals(code))
            return new JsonResponse(2, "邀请码错误");
        else {
            Integer userId = Integer.parseInt(CookieUtils.get(request, "userId"));
            Performer performer = new Performer();
            performer.setUserId(userId);
            performer.setDramaId(dramaId);
            performer.setPosition(1);
            performerService.add(performer);
            return new JsonResponse(0,"成功加入");
        }

    }
    @GetMapping("/dramas")
    public JsonResponse dramas(HttpServletRequest request) {
        Integer userId = Integer.parseInt(CookieUtils.get(request, "userId"));
        return new JsonResponse(performerService.listByUserId(userId));
    }
}