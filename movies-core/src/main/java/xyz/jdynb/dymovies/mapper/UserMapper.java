package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.entity.User;

/**
* @author Administrator
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2025-01-24 21:42:17
* @Entity xyz.jdynb.dymovies.entity.User
*/
@Mapper
public interface UserMapper {

    int add(User user);

    void update(User user);

    User findByEmail(String email);

    User findById(Integer id);
}




