package xyz.jdynb.dymovies.admin.service;

import xyz.jdynb.dymovies.admin.entity.AdminUser;
import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.vo.LoginFromVo;
import xyz.jdynb.dymovies.common.vo.UserAuthVo;

public interface AdminUserService {

    AdminUser findByUsernameAndPassword(LoginFromVo loginFromVo);

    UserAuthVo generateToken(Integer userId);

    AdminUser findById(Integer userId);

    Page<AdminUser> selectAdminUsersByPage(int pageNo, int pageSize);

    //  Page<User> selectUsersByPage(int pageNo, int pageSize, String username);


}
