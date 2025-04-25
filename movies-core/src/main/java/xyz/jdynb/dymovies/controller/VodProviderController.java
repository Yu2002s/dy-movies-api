package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jdynb.dymovies.common.entity.VodProvider;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.service.VodProviderService;

import java.util.List;

@RestController
@RequestMapping("/vodProviders")
public class VodProviderController {

    @Resource
    private VodProviderService vodProviderService;

    @GetMapping
    public Result<List<VodProvider>> getVodProviders() {
        return Result.success(vodProviderService.findAll());
    }

}
