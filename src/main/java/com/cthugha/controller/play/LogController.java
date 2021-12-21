package com.cthugha.controller.play;

import com.cthugha.model.play.Log;
import com.cthugha.service.play.ILogService;
import com.cthugha.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname LogController
 * @Description
 * @Date 2021/12/21 21:47
 * @Created by gaoqi
 */
@RestController
@RequestMapping("/log")
public class LogController {
    final ILogService logService;

    public LogController(ILogService logService) {
        this.logService = logService;
    }

    @PostMapping("/create")
    public JsonResponse create(@RequestBody Log log) {
        logService.add(log);
        return new JsonResponse();
    }

    @GetMapping("/list")
    public JsonResponse list(@RequestParam Integer id){
        return new JsonResponse(logService.listByDramaId(id));
    }
}
