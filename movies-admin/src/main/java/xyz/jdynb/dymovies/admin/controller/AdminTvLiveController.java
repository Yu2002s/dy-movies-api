package xyz.jdynb.dymovies.admin.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.admin.entity.AdminTvLive;
import xyz.jdynb.dymovies.admin.service.AdminTvLiveService;
import xyz.jdynb.dymovies.common.pojo.Result;

import java.util.List;

@RestController
@RequestMapping("/admin/tvLives")
public class AdminTvLiveController {

    @Resource
    private AdminTvLiveService adminTvLiveService;

    @GetMapping
    public Result<List<AdminTvLive>> getTvLives() {
        return Result.success(adminTvLiveService.findAll());
    }

    @PostMapping
    public Result<String> addTvLive(@RequestBody AdminTvLive tvLive) {
        return adminTvLiveService.add(tvLive) ? Result.success() : Result.error("新增失败");
    }

    @PutMapping
    public Result<String> updateTvLive(@RequestBody AdminTvLive tvLive) {
        return adminTvLiveService.update(tvLive) ? Result.success() : Result.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteTvLive(@PathVariable int id) {
        return adminTvLiveService.delete(id) ? Result.success() : Result.error("删除失败");
    }

}
