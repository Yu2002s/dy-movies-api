package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.common.entity.VodDetail;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.service.VodSearchService;

import java.util.List;

@RestController
@RequestMapping("/vodSearchs")
public class VodSearchController {

    @Resource
    private VodSearchService vodSearchService;

    @PostMapping
    public Result<Page<VodDetail>> searchVodList(@Validated @RequestBody VodQueryParamsDto vodQueryParamsDto) {
        return Result.success(vodSearchService.findList(vodQueryParamsDto));
    }

    @GetMapping("/suggest")
    public Result<List<String>> getSearchSuggests(@RequestParam String keyword) {
        return Result.success(vodSearchService.findNameByKeyword(keyword));
    }
}