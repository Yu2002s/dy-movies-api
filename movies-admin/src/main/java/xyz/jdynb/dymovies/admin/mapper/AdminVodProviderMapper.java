package xyz.jdynb.dymovies.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.jdynb.dymovies.admin.entity.AdminVodProvider;

import java.util.List;

@Mapper
public interface AdminVodProviderMapper {

    @Select("select id, name, url, remark, weight from dy_movies.vod_provider order by weight desc")
    List<AdminVodProvider> findAll();

    int add(AdminVodProvider vodProvider);

    int update(AdminVodProvider vodProvider);

    int deleteById(Integer id);

}
