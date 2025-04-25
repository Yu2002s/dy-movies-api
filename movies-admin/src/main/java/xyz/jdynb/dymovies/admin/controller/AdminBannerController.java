package xyz.jdynb.dymovies.admin.controller;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.admin.dto.VodBannerSaveParams;
import xyz.jdynb.dymovies.admin.service.AdminVodBannerService;
import xyz.jdynb.dymovies.common.entity.VodBanner;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.common.validator.UpdateGroup;

import java.util.List;

@RestController
@RequestMapping("/admin/vodBanners")
public class AdminBannerController {

    @Resource
    private AdminVodBannerService adminVodBannerService;

    @GetMapping
    public Result<List<VodBanner>> getHomeBanners() {
        return Result.success(adminVodBannerService.findList());
    }

    @PostMapping
    public Result<String> addHomeBanners(@RequestBody @Validated VodBannerSaveParams params) {
        return adminVodBannerService.add(params) ? Result.success("添加成功") : Result.error("添加失败");
    }

    @PutMapping
    public Result<String> updateHomeBanners(@RequestBody @Validated(UpdateGroup.class) VodBannerSaveParams params) {
        return adminVodBannerService.update(params) ? Result.success("更新成功") : Result.error("更新失败");
    }

    @DeleteMapping
    public Result<String> deleteHomeBanners(@RequestBody List<Integer> ids) {
        return adminVodBannerService.delete(ids) ? Result.success("删除成功") : Result.error("删除失败");
    }
}
