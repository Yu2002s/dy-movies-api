package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.entity.AppUpdate;
import xyz.jdynb.dymovies.mapper.AppUpdateMapper;
import xyz.jdynb.dymovies.service.UpdateService;

@Service
public class UpdateServiceImpl implements UpdateService {

    @Resource
    private AppUpdateMapper appUpdateMapper;

    @Override
    public AppUpdate findLastByCode(long code) {
        return appUpdateMapper.findLastByCode(code);
    }
}
