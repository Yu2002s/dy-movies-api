package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.dto.Page;
import xyz.jdynb.dymovies.dto.PageParams;
import xyz.jdynb.dymovies.entity.AppUpdate;
import xyz.jdynb.dymovies.mapper.AppUpdateMapper;
import xyz.jdynb.dymovies.service.UpdateService;

@Service
public class UpdateServiceImpl implements UpdateService {

    @Resource
    private AppUpdateMapper appUpdateMapper;

    @Override
    public int count() {
        return appUpdateMapper.count();
    }

    @Override
    public boolean add(AppUpdate appUpdate) {
        return appUpdateMapper.add(appUpdate) > 0;
    }

    @Override
    public boolean update(AppUpdate appUpdate) {
        return appUpdateMapper.update(appUpdate) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return appUpdateMapper.delete(id);
    }

    @Override
    public AppUpdate findLastByCode(long code) {
        return appUpdateMapper.findLastByCode(code);
    }

    @Override
    public Page<AppUpdate> findListByPage(int page, int pageSize) {
        int count = count();
        return Page.of(page, count, pageSize, appUpdateMapper.findListByPage(new PageParams(page, pageSize)));
    }
}
