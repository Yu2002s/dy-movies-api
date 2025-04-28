package xyz.jdynb.dymovies.admin.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.admin.entity.AdminTvLive;
import xyz.jdynb.dymovies.admin.mapper.AdminTvLiveMapper;
import xyz.jdynb.dymovies.admin.service.AdminTvLiveService;

import java.util.List;

@Service
public class AdminTvLiveServiceImpl implements AdminTvLiveService {

    @Resource
    private AdminTvLiveMapper adminTvLiveMapper;

    @Override
    public List<AdminTvLive> findAll() {
        return adminTvLiveMapper.findAll();
    }

    @Override
    public boolean add(AdminTvLive tvLive) {
        return adminTvLiveMapper.add(tvLive) > 0;
    }

    @Override
    public boolean update(AdminTvLive tvLive) {
        if (tvLive.getId() == null) {
            return add(tvLive);
        }
        return adminTvLiveMapper.update(tvLive) > 0;
    }

    @Override
    public boolean delete(int id) {
        return adminTvLiveMapper.delete(id) > 0;
    }
}
