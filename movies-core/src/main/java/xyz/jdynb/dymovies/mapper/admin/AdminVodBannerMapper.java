package xyz.jdynb.dymovies.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.dto.VodBannerSaveParams;
import xyz.jdynb.dymovies.entity.VodBanner;

import java.util.List;

@Mapper
public interface AdminVodBannerMapper {

    int add(VodBannerSaveParams params);

    int update(VodBannerSaveParams params);

    int delete(List<Integer> ids);

    List<VodBanner> findList();
}
