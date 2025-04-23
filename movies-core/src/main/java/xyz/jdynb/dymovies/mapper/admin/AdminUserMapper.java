package xyz.jdynb.dymovies.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.entity.AdminUser;
import xyz.jdynb.dymovies.vo.LoginFromVo;

/**
* @author Administrator
* @description 针对表【admin_user(管理员用户)】的数据库操作Mapper
* @createDate 2025-01-27 14:57:00
* @Entity xyz.jdynb.dymovies.entity.AdminUser
*/
@Mapper
public interface AdminUserMapper {

    AdminUser findByUsernameAndPassword(LoginFromVo loginFromVo);

    AdminUser findById(Integer id);
}




