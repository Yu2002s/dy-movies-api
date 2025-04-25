package xyz.jdynb.dymovies.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.common.entity.VodDetail;

import java.util.List;

@Mapper
public interface AdminVodDetailMapper {

    int count(String flag);

    int addBatch(List<VodDetail> vodDetails);

    int countByVidAndFlag(Integer vid, String flag);

    void add(VodDetail vodDetail);
}
