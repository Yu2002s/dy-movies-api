package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.Vod;
import xyz.jdynb.dymovies.entity.VodDetail;
import xyz.jdynb.dymovies.mapper.VodSearchMapper;
import xyz.jdynb.dymovies.pojo.Page;
import xyz.jdynb.dymovies.service.VodConfigService;
import xyz.jdynb.dymovies.service.VodSearchService;

import java.util.List;

@Service
public class VodSearchServiceImpl implements VodSearchService {

    @Resource
    private VodSearchMapper vodSearchMapper;

    @Resource
    private VodConfigService vodConfigService;

    @Override
    public int countByKeywordAndType(String flag, Integer typeId, String keyword) {
        return vodSearchMapper.countByKeywordAndType(flag, typeId, keyword);
    }

    @Override
    public Page<VodDetail> findListByKeywordAndType(VodQueryParamsDto vodQueryParamsDto) {
        String flag = vodConfigService.findFlag();
        vodQueryParamsDto.setFlag(flag);
        int total = countByKeywordAndType(flag, vodQueryParamsDto.getTypeId(), vodQueryParamsDto.getKeyword());
        List<VodDetail> list = vodSearchMapper.findListByKeywordAndType(vodQueryParamsDto);
        return Page.of(vodQueryParamsDto.getPage(), total, vodQueryParamsDto.getPageSize(), list);
    }
}
