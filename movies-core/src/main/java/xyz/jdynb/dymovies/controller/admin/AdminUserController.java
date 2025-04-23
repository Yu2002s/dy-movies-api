package xyz.jdynb.dymovies.controller.admin;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.anno.RequireLogin;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.entity.AdminUser;
import xyz.jdynb.dymovies.entity.User;
import xyz.jdynb.dymovies.service.admin.AdminVerifyService;
import xyz.jdynb.dymovies.vo.LoginFromVo;
import xyz.jdynb.dymovies.dto.Page;
import xyz.jdynb.dymovies.service.admin.AdminUserService;
import xyz.jdynb.dymovies.vo.UserAuthVo;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    @Resource
    private AdminUserService adminUserService;

    @Resource
    private AdminVerifyService adminVerifyService;

    @PostMapping("/login")
    public Result<UserAuthVo> login(@Validated @RequestBody LoginFromVo loginFromVo, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        String code = adminVerifyService.getCode(sessionId);
        if (loginFromVo.getCode() == null || !loginFromVo.getCode().equalsIgnoreCase(code)) {
            return Result.error("验证码错误");
        }
        adminVerifyService.deleteCode(sessionId);
        AdminUser user = adminUserService.findByUsernameAndPassword(loginFromVo);
        return user != null
                ? Result.success("登录成功", adminUserService.generateToken(user.getId()))
                : Result.error("用户名或密码错误");
    }

    @GetMapping
    @RequireLogin
    public Result<AdminUser> getUserInfo(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute(User.ID);
        AdminUser user = adminUserService.findById(userId);
        return user != null ? Result.success(user) : Result.error("获取用户信息失败");
    }

    @GetMapping("/adminList")
    public Result<Page<AdminUser>> getAdminList(@RequestParam(defaultValue = "1") int pageNo,
                                                @RequestParam(defaultValue = "10") int pageSize) {
        return Result.success(adminUserService.selectAdminUsersByPage(pageNo, pageSize));
    }

    @GetMapping("/list")
    public Result<Page<User>> getUsersByPage(int pageNo, int pageSize, @RequestParam(required = false) String nickname) {
        return Result.success(adminUserService.selectUsersByPage(pageNo, pageSize, nickname));
    }

}
