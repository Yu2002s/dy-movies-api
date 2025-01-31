package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.mapper.VodConfigMapper;
import xyz.jdynb.dymovies.service.VodConfigService;

@Service
public class VodConfigServiceImpl implements VodConfigService {

    @Resource
    private VodConfigMapper vodConfigMapper;

    @Override
    public String findFlag() {
        return vodConfigMapper.findFlag();
    }
}
