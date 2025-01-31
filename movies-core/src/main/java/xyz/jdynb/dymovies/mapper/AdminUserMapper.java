package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.entity.AdminUser;
import xyz.jdynb.dymovies.pojo.LoginFrom;

/**
* @author Administrator
* @description 针对表【admin_user(管理员用户)】的数据库操作Mapper
* @createDate 2025-01-27 14:57:00
* @Entity xyz.jdynb.dymovies.entity.AdminUser
*/
@Mapper
public interface AdminUserMapper {

    AdminUser findByUsernameAndPassword(LoginFrom loginFrom);

    AdminUser findById(Integer id);
}




