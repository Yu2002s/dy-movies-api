package xyz.jdynb.dymovies.admin.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.admin.mapper.AdminAppUpdateMapper;
import xyz.jdynb.dymovies.admin.service.AdminAppUpdateService;
import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.dto.PageParams;
import xyz.jdynb.dymovies.common.entity.AppUpdate;

@Service
public class AdminAppUpdateServiceImpl implements AdminAppUpdateService {

    @Resource
    private AdminAppUpdateMapper adminAppUpdateMapper;

    @Override
    public int count() {
        return adminAppUpdateMapper.count();
    }

    @Override
    public boolean add(AppUpdate appUpdate) {
        return adminAppUpdateMapper.add(appUpdate) > 0;
    }

    @Override
    public boolean update(AppUpdate appUpdate) {
        return adminAppUpdateMapper.update(appUpdate) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return adminAppUpdateMapper.delete(id);
    }

    @Override
    public Page<AppUpdate> findListByPage(int page, int pageSize) {
        int count = count();
        return Page.of(page, count, pageSize, adminAppUpdateMapper.findListByPage(new PageParams(page, pageSize)));
    }
}
