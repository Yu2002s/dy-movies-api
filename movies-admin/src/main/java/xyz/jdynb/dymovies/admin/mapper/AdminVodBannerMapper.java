package xyz.jdynb.dymovies.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.admin.dto.VodBannerSaveParams;
import xyz.jdynb.dymovies.common.entity.VodBanner;

import java.util.List;

@Mapper
public interface AdminVodBannerMapper {

    int add(VodBannerSaveParams params);

    int update(VodBannerSaveParams params);

    int delete(List<Integer> ids);

    List<VodBanner> findList();
}
