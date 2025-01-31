package xyz.jdynb.dymovies.service.admin.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.entity.AdminUser;
import xyz.jdynb.dymovies.entity.User;
import xyz.jdynb.dymovies.mapper.AdminUserMapper;
import xyz.jdynb.dymovies.pojo.LoginFrom;
import xyz.jdynb.dymovies.pojo.Page;
import xyz.jdynb.dymovies.service.admin.AdminUserService;
import xyz.jdynb.dymovies.utils.JwtUtils;
import xyz.jdynb.dymovies.utils.MD5Utils;
import xyz.jdynb.dymovies.vo.UserAuthVo;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    /**
     * 盐
     */
    @Value("${jwt.key}")
    private String SALT;

    @Resource
    private AdminUserMapper adminUserMapper;

    @Resource
    private JwtUtils jwtUtils;

    @Override
    public AdminUser findByUsernameAndPassword(LoginFrom loginFrom) {
        loginFrom.setPassword(getPassword(loginFrom.getPassword()));
        return adminUserMapper.findByUsernameAndPassword(loginFrom);
    }

    @Override
    public UserAuthVo generateToken(Integer userId) {
        return new UserAuthVo(jwtUtils.generateToken(userId));
    }

    @Override
    public AdminUser findById(Integer userId) {
        return adminUserMapper.findById(userId);
    }

    @Override
    public Page<AdminUser> selectAdminUsersByPage(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public Page<User> selectUsersByPage(int pageNo, int pageSize, String username) {
        return null;
    }
    private String getPassword(String password) {
        return MD5Utils.encrypt(MD5Utils.encrypt(SALT) + MD5Utils.encrypt(password));
    }

}
