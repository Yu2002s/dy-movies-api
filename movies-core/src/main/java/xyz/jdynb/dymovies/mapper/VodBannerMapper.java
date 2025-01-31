package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.entity.Vod;
import xyz.jdynb.dymovies.entity.VodBanner;
import xyz.jdynb.dymovies.entity.VodDetail;

import java.util.List;

@Mapper
public interface VodBannerMapper {

    List<VodBanner> findList();
}
