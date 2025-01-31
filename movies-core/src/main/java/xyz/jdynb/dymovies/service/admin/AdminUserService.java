package xyz.jdynb.dymovies.service.admin;

import xyz.jdynb.dymovies.entity.AdminUser;
import xyz.jdynb.dymovies.entity.User;
import xyz.jdynb.dymovies.pojo.LoginFrom;
import xyz.jdynb.dymovies.pojo.Page;
import xyz.jdynb.dymovies.vo.UserAuthVo;

public interface AdminUserService {

    AdminUser findByUsernameAndPassword(LoginFrom loginFrom);

    UserAuthVo generateToken(Integer userId);

    AdminUser findById(Integer userId);

    Page<AdminUser> selectAdminUsersByPage(int pageNo, int pageSize);

    Page<User> selectUsersByPage(int pageNo, int pageSize, String username);


}
