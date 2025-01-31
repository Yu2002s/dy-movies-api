package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.service.TvLiveService;
import xyz.jdynb.dymovies.vo.TvLiveVo;

import java.util.List;

/**
 * 电视直播
 */
@RestController
@RequestMapping("/tvLives")
public class TvLiveController {

    @Resource
    private TvLiveService tvLiveService;

    @GetMapping
    public Result<List<TvLiveVo>> getTvLives() {
        return Result.success(tvLiveService.findList());
    }

}
