package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.dto.VodCommentAddParamsDto;
import xyz.jdynb.dymovies.dto.VodCommentQueryParamsDto;
import xyz.jdynb.dymovies.entity.VodComment;

public interface VodCommentService {

    int countByDetailId(int detailId);

    Page<VodComment> findList(VodCommentQueryParamsDto vodCommentQueryParamsDto);

    int add(VodCommentAddParamsDto params);
}
