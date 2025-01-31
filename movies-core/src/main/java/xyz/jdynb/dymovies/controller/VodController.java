package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.Vod;
import xyz.jdynb.dymovies.entity.VodDetail;
import xyz.jdynb.dymovies.pojo.Page;
import xyz.jdynb.dymovies.service.VodDetailService;
import xyz.jdynb.dymovies.service.VodService;

@RestController
@RequestMapping("/vods")
public class VodController {

    @Resource
    private VodService vodService;

    @Resource
    private VodDetailService vodDetailService;

    /**
     * 获取某个类型的视频列表
     * @param tid 类型id
     * @param page 页码
     * @param pageSize 每页数量
     * @return 每页数据
     */
    @GetMapping("/type/{tid}")
    public Result<Page<Vod>> getVodListByType(@PathVariable("tid") Integer tid,
                                              @RequestParam(defaultValue = "1", required = false) Integer page,
                                              @RequestParam(defaultValue = "20", required = false) Integer pageSize) {

        VodQueryParamsDto vodQueryParamsDto = new VodQueryParamsDto();
        vodQueryParamsDto.setPage(page);
        vodQueryParamsDto.setPageSize(pageSize);
        vodQueryParamsDto.setTypeId(tid);
        return Result.success(vodService.findListByTid(vodQueryParamsDto));
    }

    /**
     * 获取某个影片的详情
     * @param id 详情id
     * @return 影片详情
     */
    @GetMapping("/{id}")
    public Result<VodDetail> getVodById(@PathVariable("id") Integer id) {
        return Result.success(vodDetailService.findById(id));
    }
}
