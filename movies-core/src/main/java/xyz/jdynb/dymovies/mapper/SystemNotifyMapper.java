package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.entity.SystemNotify;

import java.util.List;

/**
* @author Yu2002s
* @description 针对表【system_notify(系统通知)】的数据库操作Mapper
* @createDate 2025-05-16 12:20:46
* @Entity xyz.jdynb.dymovies.entity.SystemNotify
*/
@Mapper
public interface SystemNotifyMapper {

    List<SystemNotify> findAll();

}




