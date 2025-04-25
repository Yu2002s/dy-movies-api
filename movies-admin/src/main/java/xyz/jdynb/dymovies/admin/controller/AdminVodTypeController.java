package xyz.jdynb.dymovies.admin.controller;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.admin.service.AdminVodTypeService;
import xyz.jdynb.dymovies.common.entity.VodType;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.common.validator.UpdateGroup;

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

    @PutMapping
    public Result<String> updateVodType(@RequestBody @Validated(UpdateGroup.class) VodType vodType) {
        return adminVodTypeService.update(vodType) ? Result.success("更新成功") : Result.error("更新失败");
    }

}
