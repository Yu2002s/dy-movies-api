package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.common.entity.AppUpdate;

/**
* @author Administrator
* @description 针对表【app_update(App 更新表)】的数据库操作Mapper
* @createDate 2025-02-02 19:58:38
 * @Entity xyz.jdynb.dymovies.common.entity.AppUpdate
*/
@Mapper
public interface AppUpdateMapper {

    AppUpdate findLastByCode(long code);
}




