package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.jdynb.dymovies.entity.JobClass;

import java.util.List;

/**
* @author Administrator
* @description 针对表【job_class(job 启动类表)】的数据库操作Mapper
* @createDate 2025-01-07 20:03:34
* @Entity xyz.jdynb.dymovies.entity.JobClass
*/
@Mapper
public interface JobClassMapper {

    @Select("select id, name, remark from dy_movies.job_class")
    List<JobClass> findAll();
}




