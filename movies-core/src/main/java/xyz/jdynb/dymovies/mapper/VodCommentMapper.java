package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.dto.VodCommentAddParamsDto;
import xyz.jdynb.dymovies.dto.VodCommentQueryParamsDto;
import xyz.jdynb.dymovies.entity.VodComment;

import java.util.List;

/**
* @author Administrator
* @description 针对表【vod_comment(影片评论表)】的数据库操作Mapper
* @createDate 2025-01-26 10:52:07
* @Entity xyz.jdynb.dymovies.entity.VodComment
*/
@Mapper
public interface VodCommentMapper {

    int countByDetailId(Integer detailId);

    List<VodComment> findList(VodCommentQueryParamsDto vodCommentQueryParamsDto);

    int add(VodCommentAddParamsDto params);
}




