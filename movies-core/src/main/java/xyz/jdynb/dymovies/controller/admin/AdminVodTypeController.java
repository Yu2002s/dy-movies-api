package xyz.jdynb.dymovies.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.entity.VodType;
import xyz.jdynb.dymovies.service.admin.AdminVodTypeService;
import xyz.jdynb.dymovies.validator.UpdateGroup;

import java.util.List;

@RestController
@RequestMapping("/admin/vodTypes")
public class AdminVodTypeController {

    @Resource
    private AdminVodTypeService adminVodTypeService;

    @GetMapping
    public Result<List<VodType>> getVodTypeList(String flag) {
        return Result.success(adminVodTypeService.findList(flag));
    }

    @PostMapping
    public Result<String> addVodType(@RequestBody @Validated VodType vodType) {
        return adminVodTypeService.add(vodType) ? Result.success("添加成功") : Result.error("添加失败");
    }

    @GetMapping("/parent")
    public Result<List<VodType>> getParentVodTypeList(String flag) {
        return Result.success(adminVodTypeService.findParentList(flag));
    }

    @PutMapping
    public Result<String> updateVodType(@RequestBody @Validated(UpdateGroup.class) VodType vodType) {
        return adminVodTypeService.update(vodType) ? Result.success("更新成功") : Result.error("更新失败");
    }

}
