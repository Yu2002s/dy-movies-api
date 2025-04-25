package xyz.jdynb.dymovies.admin.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.admin.entity.AdminUser;
import xyz.jdynb.dymovies.admin.mapper.AdminUserMapper;
import xyz.jdynb.dymovies.admin.service.AdminUserService;
import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.utils.JwtUtils;
import xyz.jdynb.dymovies.common.utils.MD5Utils;
import xyz.jdynb.dymovies.common.vo.LoginFromVo;
import xyz.jdynb.dymovies.common.vo.UserAuthVo;

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
    public AdminUser findByUsernameAndPassword(LoginFromVo loginFromVo) {
        loginFromVo.setPassword(getPassword(loginFromVo.getPassword()));
        return adminUserMapper.findByUsernameAndPassword(loginFromVo);
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

   /* @Override
    public Page<User> selectUsersByPage(int pageNo, int pageSize, String username) {
        return null;
    }
    */

    private String getPassword(String password) {
        return MD5Utils.encrypt(MD5Utils.encrypt(SALT) + MD5Utils.encrypt(password));
    }
}
