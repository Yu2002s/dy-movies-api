package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.VodDetail;
import xyz.jdynb.dymovies.pojo.Page;
import xyz.jdynb.dymovies.service.VodSearchService;

@RestController
@RequestMapping("/vodSearchs")
public class VodSearchController {

    @Resource
    private VodSearchService vodSearchService;

    @PostMapping
    public Result<Page<VodDetail>> search(@Validated @RequestBody VodQueryParamsDto vodQueryParamsDto) {
        if (vodQueryParamsDto.getPageSize() == null) {
            vodQueryParamsDto.setPageSize(10);
        }
        return Result.success(vodSearchService.findListByKeywordAndType(vodQueryParamsDto));
    }

}
