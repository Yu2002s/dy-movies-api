package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.service.HomeVodService;
import xyz.jdynb.dymovies.vo.HomeVodDataVo;

@RestController
@RequestMapping("/homes")
public class HomeVodController {

    @Resource
    private HomeVodService homeVodService;

    @GetMapping
    public Result<HomeVodDataVo> getHomeVodData() {
        return Result.success(homeVodService.getHomeVodData());
    }
    
    /**
     * 清除首页数据缓存
     * 用于解决缓存序列化错误问题
     */
    @PostMapping("/cache/clear")
    public Result<String> clearHomeDataCache() {
        homeVodService.clearHomeDataCache();
        return Result.success("缓存已清除");
    }
}
