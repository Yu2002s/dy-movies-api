package xyz.jdynb.dymovies.admin.mapper;

import org.apache.ibatis.annotations.*;
import xyz.jdynb.dymovies.admin.entity.JobClass;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【job_class(job 启动类表)】的数据库操作Mapper
 * @createDate 2025-01-07 20:03:34
 * @Entity xyz.jdynb.dymovies.admin.entity.JobClass
 */
@Mapper
public interface JobClassMapper {

    @Select("select id, name, remark from dy_movies.job_class")
    List<JobClass> findAll();

    @Insert("insert into dy_movies.job_class (name, remark) values (#{name},#{remark})")
    void add(JobClass jobClass);

    @Update("update dy_movies.job_class set name = #{name},remark = #{remark} where id = #{id}")
    void update(JobClass jobClass);

    @Delete("delete from dy_movies.job_class where id = #{id}")
    void delete(long id);
}




