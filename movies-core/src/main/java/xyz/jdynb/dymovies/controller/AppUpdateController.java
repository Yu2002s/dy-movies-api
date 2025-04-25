package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jdynb.dymovies.common.entity.AppUpdate;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.service.AppUpdateService;

/**
 * App 更新
 */
@RestController
@RequestMapping("/updates")
public class AppUpdateController {

    @Resource
    private AppUpdateService appUpdateService;

    @GetMapping("/{code}")
    public Result<AppUpdate> checkUpdate(@PathVariable("code") long versionCode) {
        AppUpdate appUpdate = appUpdateService.findLastByCode(versionCode);
        if (appUpdate == null) {
           return Result.success("已是最新版本");
        }
        return Result.success(appUpdate);
    }

}
