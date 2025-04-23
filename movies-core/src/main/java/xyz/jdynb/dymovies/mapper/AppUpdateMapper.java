package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.dto.PageParams;
import xyz.jdynb.dymovies.entity.AppUpdate;

import java.util.List;

/**
* @author Administrator
* @description 针对表【app_update(App 更新表)】的数据库操作Mapper
* @createDate 2025-02-02 19:58:38
* @Entity xyz.jdynb.dymovies.entity.AppUpdate
*/
@Mapper
public interface AppUpdateMapper {

    int count();

    AppUpdate findLastByCode(long code);

    List<AppUpdate> findListByPage(PageParams params);

    int add(AppUpdate appUpdate);

    int update(AppUpdate appUpdate);

    boolean delete(Integer id);
}




