package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.jdynb.dymovies.common.utils.JwtUtils;
import xyz.jdynb.dymovies.entity.User;
import xyz.jdynb.dymovies.mapper.UserMapper;
import xyz.jdynb.dymovies.service.MailService;
import xyz.jdynb.dymovies.service.UserService;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private MailService mailService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtUtils jwtUtils;

    private static final String[] AVATARS = {
            "https://img0.baidu.com/it/u=1069774655,179634375&fm=253&fmt=auto&app=120&f=JPEG?w=200&h=200",
            "https://img0.baidu.com/it/u=3437576064,956753245&fm=253&fmt=auto&app=120&f=JPEG?w=200&h=200",
            "https://img0.baidu.com/it/u=907518174,2775661127&fm=253&fmt=auto&app=120&f=JPEG?w=200&h=200",
            "https://img1.baidu.com/it/u=773681673,1944990074&fm=253&fmt=auto&app=120&f=JPEG?w=200&h=200",
            "https://img2.baidu.com/it/u=2874337484,3995335822&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200",
            "https://img0.baidu.com/it/u=4081520873,1539092309&fm=253&fmt=auto&app=120&f=JPEG?w=200&h=200"
    };

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Transactional
    @Override
    public String getOrInsertUserByEmail(String email, String code) {
        User user = findByEmail(email);
        LocalDateTime now = LocalDateTime.now();
        String ipAddress = request.getRemoteAddr();
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setUsername(email);
            user.setCreateAt(now);
            user.setUpdateAt(now);
            user.setIpAddr(ipAddress);
            user.setAvatar(AVATARS[(int) (Math.random() * AVATARS.length)]);
            if (userMapper.add(user) == 0) {
                return null;
            }
        } else {
            user.setIpAddr(ipAddress);
            user.setUpdateAt(now);
            userMapper.update(user);
        }
        return jwtUtils.generateToken(user.getId());
    }

    @Override
    public int add(String email, String code) {
        return 0;
    }

    @Override
    @Cacheable(key = "#email", value = "code", unless = "#result == null", condition = "#email != null")
    public String sendCode(String email) {
        // 生成验证码
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        // 这里执行具体发送验证码的操作
        if (!mailService.sendMail(email, "冬雨影视登录注册", "你的验证码是: " + code)) {
            return null;
        }
        return String.valueOf(code);
    }

    @Override
    @Cacheable(key = "#email", value = "code", unless = "#result == null")
    public String getCode(String email) {
        return null;
    }
}
