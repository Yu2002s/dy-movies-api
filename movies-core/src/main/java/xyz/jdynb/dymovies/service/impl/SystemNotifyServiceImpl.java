package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.entity.SystemNotify;
import xyz.jdynb.dymovies.mapper.SystemNotifyMapper;
import xyz.jdynb.dymovies.service.SystemNotifyService;

import java.util.List;

@Service
public class SystemNotifyServiceImpl implements SystemNotifyService {

    @Resource
    private SystemNotifyMapper systemNotifyMapper;

    @Override
    public List<SystemNotify> findAll() {
        return systemNotifyMapper.findAll();
    }
}
