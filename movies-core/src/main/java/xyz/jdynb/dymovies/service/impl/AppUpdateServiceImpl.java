package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.common.entity.AppUpdate;
import xyz.jdynb.dymovies.mapper.AppUpdateMapper;
import xyz.jdynb.dymovies.service.AppUpdateService;

@Service
public class AppUpdateServiceImpl implements AppUpdateService {

    @Resource
    private AppUpdateMapper appUpdateMapper;

    @Override
    public AppUpdate findLastByCode(long code) {
        return appUpdateMapper.findLastByCode(code);
    }
}
