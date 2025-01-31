package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.pojo.VodSource;
import xyz.jdynb.dymovies.service.VodVideoService;

import java.util.List;

@RestController
@RequestMapping("/vodVideos")
public class VodVideoController {

    @Resource
    private VodVideoService vodVideoService;

    @GetMapping
    public Result<List<VodSource>> getVodVideos(String name) {
        return Result.success(vodVideoService.findSourcesByName(name));
    }

}
