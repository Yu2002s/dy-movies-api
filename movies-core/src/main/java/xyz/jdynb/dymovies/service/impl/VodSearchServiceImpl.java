package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.Vod;
import xyz.jdynb.dymovies.entity.VodDetail;
import xyz.jdynb.dymovies.mapper.VodSearchMapper;
import xyz.jdynb.dymovies.dto.Page;
import xyz.jdynb.dymovies.service.VodConfigService;
import xyz.jdynb.dymovies.service.VodSearchService;

import java.util.List;

@Service
public class VodSearchServiceImpl implements VodSearchService {

    @Resource
    private VodSearchMapper vodSearchMapper;

    @Override
    public int countByKeywordAndType(VodQueryParamsDto vodQueryParamsDto) {
        return vodSearchMapper.countByKeywordAndType(vodQueryParamsDto);
    }

    @Override
    public Page<VodDetail> findListByKeywordAndType(VodQueryParamsDto vodQueryParamsDto) {
        int total = countByKeywordAndType(vodQueryParamsDto);
        List<VodDetail> list = vodSearchMapper.findListByKeywordAndType(vodQueryParamsDto);
        return Page.of(vodQueryParamsDto.getPage(), total, vodQueryParamsDto.getPageSize(), list);
    }
}
