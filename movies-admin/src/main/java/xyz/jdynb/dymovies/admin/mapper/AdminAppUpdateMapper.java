package xyz.jdynb.dymovies.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.common.dto.PageParams;
import xyz.jdynb.dymovies.common.entity.AppUpdate;

import java.util.List;

@Mapper
public interface AdminAppUpdateMapper {

    int count();

    List<AppUpdate> findListByPage(PageParams params);

    int add(AppUpdate appUpdate);

    int update(AppUpdate appUpdate);

    boolean delete(Integer id);
}
