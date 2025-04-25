package xyz.jdynb.dymovies.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.common.entity.VodType;

import java.util.List;

@Mapper
public interface AdminVodTypeMapper {

    int countByFlag(String flag);

    int addBatch(List<VodType> vodTypes);

    List<VodType> findList(String flag);

    int update(VodType vodType);
}
