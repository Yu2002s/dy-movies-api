package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.common.entity.VodVideo;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.common.pojo.VodSource;
import xyz.jdynb.dymovies.common.vo.VodSourceVideoVo;
import xyz.jdynb.dymovies.service.VodVideoService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/vodVideos")
public class VodVideoController {

    @Resource
    private VodVideoService vodVideoService;

    /**
     * 旧版本接口，已弃用。使用影片名称搜索源列表
     * 改用 `getVodVideosByVid`
     * @param name 影片名称
     * @return 影片源列表
     */
    @Deprecated
    @GetMapping
    public Result<List<VodSource>> getVodVideosByName(String name) {
        return Result.success(vodVideoService.findSourcesByName(name));
    }

    /**
     * 通过影片ID和 flag 采集源标识来获取影片视频列表
     * @param vid 影片 id （每个视频对应该id）
     * @param flag 采集源标识
     * @return 视频列表
     */
    @GetMapping("/{vid}")
    public Result<List<VodVideo>> getVodVideosByVid(@PathVariable Integer vid, String flag) {
        return Result.success(vodVideoService.findByVid(vid, flag));
    }

    /**
     * 通过影片名称获取其他源下的视频列表
     * @param name 影片名称
     * @param flag 采集源标识
     * @return 视频列表
     */
    @GetMapping("/video")
    public Result<List<VodVideo>> getVodVideoByName(String name, String flag) {
        return Result.success(vodVideoService.findListByName(name, flag));
    }

    /**
     * 获取影片的采集源和视频列表
     * @param vid 影片 id
     * @param flag 采集源标识
     * @return 影片源和视频列表
     */
    @GetMapping("/source")
    public Result<VodSourceVideoVo> getVodSourcesAndVideosByVid(Integer vid, String flag) {
        return Result.success(vodVideoService.findSourcesAndVideos(vid, flag));
    }

    /**
     * 获取影片的代理地址，这个地址去除了视频内嵌的广告
     * @param url 原生视频播放地址
     */
    @GetMapping("/proxy")
    public void getProxyVideoUrl(String url) throws IOException {
        vodVideoService.proxy(url);
    }
}
