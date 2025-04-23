package xyz.jdynb.dymovies.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.dto.Page;
import xyz.jdynb.dymovies.entity.AppUpdate;
import xyz.jdynb.dymovies.service.UpdateService;
import xyz.jdynb.dymovies.validator.UpdateGroup;

@RestController
@RequestMapping("/admin/updates")
public class AdminUpdateController {

    @Resource
    private UpdateService updateService;

    @GetMapping
    public Result<Page<AppUpdate>> getUpdateList(@RequestParam(required = false, defaultValue = "1") int page,
                                                 @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return Result.success(updateService.findListByPage(page, pageSize));
    }

    @PostMapping
    public Result<String> addUpdate(@Validated @RequestBody AppUpdate appUpdate) {
        return updateService.add(appUpdate) ? Result.success("添加成功") : Result.error("添加失败");
    }

    @PutMapping
    public Result<String> updateAppUpdate(@Validated(UpdateGroup.class) @RequestBody AppUpdate appUpdate) {
        return updateService.update(appUpdate) ? Result.success("修改成功") : Result.error("修改失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteUpdate(@PathVariable Integer id) {
        return updateService.delete(id) ? Result.success("删除成功") : Result.error("删除失败");
    }
}
