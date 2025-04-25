package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.common.entity.VodBanner;

import java.util.List;

@Mapper
public interface VodBannerMapper {

    List<VodBanner> findList();
}
