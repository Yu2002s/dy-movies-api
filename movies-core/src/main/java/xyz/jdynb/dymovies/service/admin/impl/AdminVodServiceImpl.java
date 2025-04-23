package xyz.jdynb.dymovies.service.admin.impl;

import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.dto.Page;
import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.Vod;
import xyz.jdynb.dymovies.mapper.VodMapper;
import xyz.jdynb.dymovies.mapper.admin.AdminVodMapper;
import xyz.jdynb.dymovies.service.admin.AdminVodService;

@Service
public class AdminVodServiceImpl implements AdminVodService {

    @Resource
    private AdminVodMapper adminVodMapper;

    @Override
    public int count(VodQueryParamsDto vodQueryParamsDto) {
        return adminVodMapper.count(vodQueryParamsDto);
    }

    @Override
    public Page<Vod> findList(VodQueryParamsDto queryParamsDto) {
        int total = count(queryParamsDto);
        return Page.of(queryParamsDto.getPage(), total, queryParamsDto.getPageSize(), adminVodMapper.findList(queryParamsDto));
    }
}
