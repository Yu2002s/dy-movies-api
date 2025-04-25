package xyz.jdynb.dymovies.admin.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.admin.mapper.AdminVodMapper;
import xyz.jdynb.dymovies.admin.service.AdminVodService;
import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.common.entity.Vod;

import java.util.List;

@Service
public class AdminVodServiceImpl implements AdminVodService {

    @Resource
    private AdminVodMapper adminVodMapper;

    @Override
    public int count(String flag) {
        return adminVodMapper.countByFlag(flag);
    }

    @Override
    public int count(VodQueryParamsDto vodQueryParamsDto) {
        return adminVodMapper.count(vodQueryParamsDto);
    }

    @Override
    public Page<Vod> findList(VodQueryParamsDto queryParamsDto) {
        int total = count(queryParamsDto);
        return Page.of(queryParamsDto.getPage(), total, queryParamsDto.getPageSize(), adminVodMapper.findList(queryParamsDto));
    }

    @Override
    public int add(Vod vod) {
        return adminVodMapper.add(vod);
    }

    @Override
    public int addBatch(List<Vod> vodList) {
        return adminVodMapper.addBatch(vodList);
    }

    @Override
    public int countByVidAndFlag(Integer id, String flag) {
        return adminVodMapper.countByVidAndFlag(id, flag);
    }

}
