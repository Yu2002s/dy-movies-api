package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.dto.VodLatestQueryParamsDto;
import xyz.jdynb.dymovies.common.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.common.entity.Vod;
import xyz.jdynb.dymovies.common.entity.VodDetail;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.service.VodDetailService;
import xyz.jdynb.dymovies.service.VodService;

import java.util.List;

@RestController
@RequestMapping("/vods")
public class VodController {

    @Resource
    private VodService vodService;

    @Resource
    private VodDetailService vodDetailService;

    /**
     * 获取某个类型的视频列表
     * @param tid 类型 id
     * @param page 页码
     * @param pageSize 每页数量
     * @return 每页数据
     */
    @GetMapping("/type/{tid}")
    public Result<Page<Vod>> getVodListByType(@PathVariable("tid") Integer tid,
                                              Integer pid,
                                              @RequestParam(defaultValue = "1", required = false) Integer page,
                                              @RequestParam(defaultValue = "20", required = false) Integer pageSize) {

        VodQueryParamsDto vodQueryParamsDto = new VodQueryParamsDto();
        vodQueryParamsDto.setPage(page);
        vodQueryParamsDto.setPageSize(pageSize);
        vodQueryParamsDto.setTid(tid);
        vodQueryParamsDto.setPid(pid);
        return Result.success(vodService.findListByType(vodQueryParamsDto));
    }

    @PostMapping
    public Result<Page<Vod>> getVodList(@Validated @RequestBody VodQueryParamsDto vodQueryParamsDto) {
        return Result.success(vodService.findList(vodQueryParamsDto));
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

    @GetMapping("/latest")
    public Result<List<Vod>> getLatestVods(@RequestParam(defaultValue = "1", required = false) int page,
                                      @RequestParam(defaultValue = "20", required = false) int pageSize) {
        return Result.success(vodService.findLast(VodLatestQueryParamsDto.create(null, page, pageSize)));
    }
}
