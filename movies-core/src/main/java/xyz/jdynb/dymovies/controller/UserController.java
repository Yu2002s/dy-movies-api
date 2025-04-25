package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jdynb.dymovies.common.anno.RequireLogin;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.common.vo.UserAuthVo;
import xyz.jdynb.dymovies.entity.User;
import xyz.jdynb.dymovies.service.UserService;

@Slf4j
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Value("${admin-verify-code}")
    private String adminVerifyCode;

    @Resource
    private UserService userService;

    /**
     * 登录
     *
     * @param email 邮箱
     * @param code  验证码
     * @return 登录信息
     */
    @PostMapping
    public Result<UserAuthVo> login(@Email(message = "请输入正确邮箱") String email,
                                    @Size(max = 6, message = "验证码不能超过6位") String code) {
        String cacheCode = userService.getCode(email);
        log.info("requestCode: {}, cacheCode: {}", code, cacheCode);
        if (!adminVerifyCode.equals(code) && (cacheCode == null || !cacheCode.equals(code))) {
            return Result.error("验证码错误");
        }
        String token = userService.getOrInsertUserByEmail(email, code);
        if (token == null) {
            return Result.error("登录失败");
        }
        return Result.success("登录成功", new UserAuthVo(token));
    }

    /**
     * 发送验证码
     *
     * @param email 邮箱
     * @return 发送信息
     */
    @PostMapping("/code")
    public Result<String> sendCode(@Email(message = "请输入正确邮箱") String email) {
        String code = userService.sendCode(email);
        return code != null ? Result.success("验证码发送成功")
                : Result.error("验证码发送失败");
    }

    /**
     * 获取登录的用户
     * @return 用户信息
     */
    @GetMapping
    @RequireLogin
    public Result<User> getLoginUser(HttpServletRequest request, HttpServletResponse response) {
        Integer userId = (Integer) request.getAttribute(User.ID);
        if (userId == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return Result.error("用户未登录");
        }
        User user = userService.findById(userId);
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return Result.error("用户不存在");
        }
        return Result.success(user);
    }
}
