package xyz.jdynb.dymovies.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.service.VodConfigService;

@RestController
@RequestMapping("/admin/vodConfigs")
public class VodConfigController {

    @Resource
    private VodConfigService vodConfigService;

    @GetMapping("/flag")
    public Result<String> getVodFlagConfig() {
        String flag = vodConfigService.findFlag();
        return StringUtils.hasText(flag) ? Result.success("获取配置成功", flag) : Result.error("获取配置失败");
    }

    @PutMapping("/flag")
    public Result<String> updateVodFlagConfig(String flag) {
        if (vodConfigService.updateFlag(flag)) {
            return Result.success("更新配置成功", flag);
        }
        return Result.error("更新配置失败");
    }
}
