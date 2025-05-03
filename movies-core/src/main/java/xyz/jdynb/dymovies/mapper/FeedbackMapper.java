package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.common.dto.PageParams;
import xyz.jdynb.dymovies.common.entity.Feedback;

import java.util.List;

@Mapper
public interface FeedbackMapper {

    int count();

    List<Feedback> findList(PageParams pageParams);

    int add(Feedback feedback);

    int update(Feedback feedback);
}
