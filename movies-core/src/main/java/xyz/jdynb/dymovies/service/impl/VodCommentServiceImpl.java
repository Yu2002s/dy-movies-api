package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.dto.VodCommentQueryParamsDto;
import xyz.jdynb.dymovies.entity.VodComment;
import xyz.jdynb.dymovies.mapper.VodCommentMapper;
import xyz.jdynb.dymovies.pojo.Page;
import xyz.jdynb.dymovies.service.VodCommentService;

import java.util.List;

@Service
public class VodCommentServiceImpl implements VodCommentService {

    @Resource
    private VodCommentMapper vodCommentMapper;

    @Override
    public int countByDetailId(int detailId) {
        return vodCommentMapper.countByDetailId(detailId);
    }

    @Override
    public Page<VodComment> findList(VodCommentQueryParamsDto vodCommentQueryParamsDto) {
        int total = countByDetailId(vodCommentQueryParamsDto.getDetailId());
        List<VodComment> list = vodCommentMapper.findList(vodCommentQueryParamsDto);
        return Page.of(vodCommentQueryParamsDto.getPage(), total, vodCommentQueryParamsDto.getPageSize(), list);
    }
}
