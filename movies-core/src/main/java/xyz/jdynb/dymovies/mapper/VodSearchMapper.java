package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.Vod;
import xyz.jdynb.dymovies.entity.VodDetail;

import java.util.List;

@Mapper
public interface VodSearchMapper {

    List<VodDetail> findListByKeywordAndType(VodQueryParamsDto params);

    int countByKeywordAndType(String flag, Integer typeId, String keyword);
}
