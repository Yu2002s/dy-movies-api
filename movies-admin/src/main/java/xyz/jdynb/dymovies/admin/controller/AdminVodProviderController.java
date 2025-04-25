package xyz.jdynb.dymovies.admin.controller;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.admin.entity.AdminVodProvider;
import xyz.jdynb.dymovies.admin.service.AdminVodProviderService;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.common.validator.UpdateGroup;

import java.util.List;

@RestController
@RequestMapping("/admin/vodProviders")
public class AdminVodProviderController {

    @Resource
    private AdminVodProviderService adminVodProviderService;

    @GetMapping
    public Result<List<AdminVodProvider>> getVodProviders() {
        return Result.success(adminVodProviderService.findAll());
    }

    @PostMapping
    public Result<String> addVodProvider(@Validated @RequestBody AdminVodProvider vodProvider) {
        return adminVodProviderService.add(vodProvider) ? Result.success("添加成功") : Result.error("添加失败");
    }

    @PutMapping
    public Result<String> updateVodProvider(@Validated(UpdateGroup.class) @RequestBody AdminVodProvider vodProvider) {
        return adminVodProviderService.update(vodProvider) ? Result.success("修改成功") : Result.error("修改失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteVodProvider(@PathVariable Integer id) {
        return adminVodProviderService.delete(id) ? Result.success("删除成功") : Result.error("删除失败");
    }
}
