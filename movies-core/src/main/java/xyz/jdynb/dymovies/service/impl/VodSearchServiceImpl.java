package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.jdynb.dymovies.admin.service.AdminVodConfigService;
import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.common.entity.VodDetail;
import xyz.jdynb.dymovies.mapper.VodMapper;
import xyz.jdynb.dymovies.mapper.VodSearchMapper;
import xyz.jdynb.dymovies.service.VodSearchService;
import xyz.jdynb.dymovies.service.VodService;

import java.util.Collections;
import java.util.List;

@Service
public class VodSearchServiceImpl implements VodSearchService {

    @Resource
    private VodSearchMapper vodSearchMapper;

    @Resource
    private VodMapper vodMapper;

    @Resource
    private AdminVodConfigService adminVodConfigService;

    @Override
    public Page<VodDetail> findList(VodQueryParamsDto vodQueryParamsDto) {
        String year = vodQueryParamsDto.getYear();
        if (StringUtils.hasText(year)) {
            String[] years = year.split("-");
            if (years.length == 2) {
                vodQueryParamsDto.setYear(years[0]);
                vodQueryParamsDto.setYearEnd(years[1]);
            }
        }
        int total = vodMapper.count(vodQueryParamsDto);
        List<VodDetail> list = vodSearchMapper.findList(vodQueryParamsDto);
        return Page.of(vodQueryParamsDto.getPage(), total, vodQueryParamsDto.getPageSize(), list);
    }

    @Override
    public List<String> findNameByKeyword(String keyword) {
        if (keyword.isBlank()) {
            return Collections.emptyList();
        }
        String flag = adminVodConfigService.findFlag();
        return vodSearchMapper.findNameByKeyword(keyword, flag);
    }
}
