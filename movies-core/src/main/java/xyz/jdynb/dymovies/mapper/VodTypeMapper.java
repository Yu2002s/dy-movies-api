package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.common.entity.VodType;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【vod_type(影片类型表)】的数据库操作Mapper
 * @createDate 2025-01-07 11:08:37
 * @Entity xyz.jdynb.dymovies.common.entity.VodType
 */
@Mapper
public interface VodTypeMapper {

    List<VodType> findList();

    List<VodType> findListByFlag(String flag);

    List<VodType> findAllByFlag(String flag);

    List<VodType> findListByParent(String flag);

    List<VodType> findListByPid(String flag, Integer pid);
}




