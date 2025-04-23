package xyz.jdynb.dymovies.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.dto.Page;
import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.Vod;
import xyz.jdynb.dymovies.service.VodService;
import xyz.jdynb.dymovies.service.admin.AdminVodService;

@RestController
@RequestMapping("/admin/vods")
public class AdminVodController {

    @Resource
    private AdminVodService adminVodService;

    @GetMapping
    public Result<Page<Vod>> getVodList(VodQueryParamsDto vodQueryParamsDto) {
        if (vodQueryParamsDto.getPageSize() == null) {
            vodQueryParamsDto.setPageSize(20);
        }
        if (vodQueryParamsDto.getPage() == null) {
            vodQueryParamsDto.setPage(1);
        }
        return Result.success(adminVodService.findList(vodQueryParamsDto));
    }

}
