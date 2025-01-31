package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.entity.VodType;
import xyz.jdynb.dymovies.mapper.VodTypeMapper;
import xyz.jdynb.dymovies.service.VodConfigService;
import xyz.jdynb.dymovies.service.VodTypeService;

import java.util.List;

@Service
@Slf4j
public class VodTypeServiceImpl implements VodTypeService {

    @Resource
    private VodTypeMapper vodTypeMapper;

    @Resource
    private VodConfigService vodConfigService;

    @Override
    public int countByFlag(String flag) {
        return vodTypeMapper.countByFlag(flag);
    }

    @Override
    public int addBatch(List<VodType> vodList) {
        return vodTypeMapper.addBatch(vodList);
    }

    @Override
    public List<VodType> findList() {
        String flag = vodConfigService.findFlag();
        return vodTypeMapper.findListByFlag(flag);
    }

    @Override
    public List<VodType> findAll() {
        String flag = vodConfigService.findFlag();
        return vodTypeMapper.findAllByFlag(flag);
    }
}
