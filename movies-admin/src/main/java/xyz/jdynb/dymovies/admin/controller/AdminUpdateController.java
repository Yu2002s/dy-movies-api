package xyz.jdynb.dymovies.admin.controller;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.admin.service.AdminAppUpdateService;
import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.entity.AppUpdate;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.common.validator.UpdateGroup;

@RestController
@RequestMapping("/admin/updates")
public class AdminUpdateController {

    @Resource
    private AdminAppUpdateService adminAppUpdateService;

    @GetMapping
    public Result<Page<AppUpdate>> getUpdateList(@RequestParam(required = false, defaultValue = "1") int page,
                                                 @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return Result.success(adminAppUpdateService.findListByPage(page, pageSize));
    }

    @PostMapping
    public Result<String> addUpdate(@Validated @RequestBody AppUpdate appUpdate) {
        return adminAppUpdateService.add(appUpdate) ? Result.success("添加成功") : Result.error("添加失败");
    }

    @PutMapping
    public Result<String> updateAppUpdate(@Validated(UpdateGroup.class) @RequestBody AppUpdate appUpdate) {
        return adminAppUpdateService.update(appUpdate) ? Result.success("修改成功") : Result.error("修改失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteUpdate(@PathVariable Integer id) {
        return adminAppUpdateService.delete(id) ? Result.success("删除成功") : Result.error("删除失败");
    }
}
