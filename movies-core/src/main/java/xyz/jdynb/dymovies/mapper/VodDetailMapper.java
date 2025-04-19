package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.entity.VodDetail;

import java.util.List;

/**
* @author Yu2002s
* @description 针对表【vod_detail(影片详情表)】的数据库操作Mapper
* @createDate 2025-01-07 11:08:25
* @Entity xyz.jdynb.dymovies.entity.VodDetail
*/
@Mapper
public interface VodDetailMapper {

    int count(String flag);

    int addBatch(List<VodDetail> vodDetails);

    int countByVidAndFlag(Integer vid, String flag);

    void add(VodDetail vodDetail);

    VodDetail findById(Integer id);
}




