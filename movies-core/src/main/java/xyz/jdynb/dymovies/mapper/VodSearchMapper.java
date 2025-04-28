package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.common.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.common.entity.VodDetail;

import java.util.List;

@Mapper
public interface VodSearchMapper {

    List<VodDetail> findList(VodQueryParamsDto params);

    List<String> findNameByKeyword(String keyword, String flag);
}
