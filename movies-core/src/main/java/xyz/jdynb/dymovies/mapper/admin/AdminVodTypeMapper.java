package xyz.jdynb.dymovies.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.entity.VodType;

import java.util.List;

@Mapper
public interface AdminVodTypeMapper {

    List<VodType> findList(String flag);

    int update(VodType vodType);
}
