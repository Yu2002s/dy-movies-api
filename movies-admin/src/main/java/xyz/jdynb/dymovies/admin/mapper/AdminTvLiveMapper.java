package xyz.jdynb.dymovies.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.admin.entity.AdminTvLive;

import java.util.List;

@Mapper
public interface AdminTvLiveMapper {

    List<AdminTvLive> findAll();

    int update(AdminTvLive tvLive);

    int add(AdminTvLive tvLive);

    int delete(int id);
}
