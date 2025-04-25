package xyz.jdynb.dymovies.admin.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.admin.mapper.AdminVodConfigMapper;
import xyz.jdynb.dymovies.admin.service.AdminVodConfigService;

@Service
public class AdminVodConfigServiceImpl implements AdminVodConfigService {

    @Resource
    private AdminVodConfigMapper adminVodConfigMapper;

    @Override
    public String findFlag() {
        return adminVodConfigMapper.findFlag();
    }

    @Override
    public boolean updateFlag(String flag) {
        return adminVodConfigMapper.updateFlag(flag) > 0;
    }
}
