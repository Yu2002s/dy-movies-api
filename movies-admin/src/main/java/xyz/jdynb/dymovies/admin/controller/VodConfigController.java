package xyz.jdynb.dymovies.admin.controller;

import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jdynb.dymovies.admin.service.AdminVodConfigService;
import xyz.jdynb.dymovies.common.pojo.Result;

@RestController
@RequestMapping("/admin/vodConfigs")
public class VodConfigController {

    @Resource
    private AdminVodConfigService adminVodConfigService;

    @GetMapping("/flag")
    public Result<String> getVodFlagConfig() {
        String flag = adminVodConfigService.findFlag();
        return StringUtils.hasText(flag) ? Result.success("获取配置成功", flag) : Result.error("获取配置失败");
    }

    @PutMapping("/flag")
    public Result<String> updateVodFlagConfig(String flag) {
        if (adminVodConfigService.updateFlag(flag)) {
            return Result.success("更新配置成功", flag);
        }
        return Result.error("更新配置失败");
    }
}
