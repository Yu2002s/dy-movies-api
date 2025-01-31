package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.Vod;
import xyz.jdynb.dymovies.mapper.VodMapper;
import xyz.jdynb.dymovies.pojo.Page;
import xyz.jdynb.dymovies.service.VodConfigService;
import xyz.jdynb.dymovies.service.VodService;
import xyz.jdynb.dymovies.service.VodTypeService;

import java.util.List;

@Service
public class VodServiceImpl implements VodService {

    @Resource
    private VodMapper vodMapper;

    @Resource
    private VodConfigService vodConfigService;

    @Resource
    private VodTypeService vodTypeService;

    @Override
    public int add(Vod vod) {
        return vodMapper.add(vod);
    }

    @Override
    public int addBatch(List<Vod> vodList) {
        return vodMapper.addBatch(vodList);
    }

    @Override
    public int addOrUpdate(Vod vod) {
        return 0;
    }

    @Override
    public int count(String flag) {
        return vodMapper.count(flag);
    }

    @Override
    public int countByTidAndFlag(Integer tid, String flag) {
        return vodMapper.countByTidAndFlag(tid, flag);
    }

    @Override
    public int countByVidAndFlag(Integer id, String flag) {
        return vodMapper.countByVidAndFlag(id, flag);
    }

    @Override
    public Page<Vod> findListByTid(VodQueryParamsDto vodQueryParamsDto) {
        String flag = vodConfigService.findFlag();
        vodQueryParamsDto.setFlag(flag);
        // 获取总记录数
        int total = vodMapper.countByTidAndFlag(vodQueryParamsDto.getTypeId(), flag);
        List<Vod> vodList = vodMapper.findListByTid(vodQueryParamsDto);
        return Page.of(vodQueryParamsDto.getPage(), total, vodQueryParamsDto.getPageSize(), vodList);
    }
}
