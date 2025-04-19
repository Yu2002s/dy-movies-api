package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.dto.VodLatestQueryParamsDto;
import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.Vod;
import xyz.jdynb.dymovies.entity.VodDetail;
import xyz.jdynb.dymovies.mapper.VodMapper;
import xyz.jdynb.dymovies.dto.Page;
import xyz.jdynb.dymovies.service.VodConfigService;
import xyz.jdynb.dymovies.service.VodService;
import xyz.jdynb.dymovies.service.VodTypeService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

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
    public int countByVidAndFlag(Integer id, String flag) {
        return vodMapper.countByVidAndFlag(id, flag);
    }

    @Override
    public Page<Vod> findListByType(VodQueryParamsDto vodQueryParamsDto) {
        String flag = vodConfigService.findFlag();
        vodQueryParamsDto.setFlag(flag);

        int total;
        // 有 pid 代表是有父级
        if (vodQueryParamsDto.getPid() != null && vodQueryParamsDto.getPid() != 0) {
            // 获取总记录数
            // 直接通过tid进行获取
            total = vodMapper.countByTidAndFlag(vodQueryParamsDto.getTid(), flag);
        } else {
            // 通过pid进行获取，此时是父级
            total = vodMapper.countByPidAndFlag(vodQueryParamsDto.getTid(), flag);
        }
        List<Vod> vodList = vodMapper.findListByTid(vodQueryParamsDto);
        return Page.of(vodQueryParamsDto.getPage(), total, vodQueryParamsDto.getPageSize(), vodList);
    }

    @Override
    public List<Vod> findLast(int pageSize) {
        return findLast(pageSize, null);
    }

    @Override
    public List<Vod> findLast(int pageSize, Integer typeId) {
        return findLast(VodLatestQueryParamsDto.create(typeId, pageSize));
    }

    @Override
    public List<Vod> findLast(VodLatestQueryParamsDto vodLatestQueryParamsDto) {
        String flag = vodConfigService.findFlag();
        vodLatestQueryParamsDto.setFlag(flag);
        return vodMapper.findLast(vodLatestQueryParamsDto);
    }
}
