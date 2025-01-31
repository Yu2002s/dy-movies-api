package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.dto.VodCommentQueryParamsDto;
import xyz.jdynb.dymovies.entity.VodComment;
import xyz.jdynb.dymovies.pojo.Page;

import java.util.List;

public interface VodCommentService {

    int countByDetailId(int detailId);

    Page<VodComment> findList(VodCommentQueryParamsDto vodCommentQueryParamsDto);

}
