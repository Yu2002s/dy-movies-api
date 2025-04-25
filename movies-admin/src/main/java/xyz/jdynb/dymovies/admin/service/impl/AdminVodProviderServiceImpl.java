package xyz.jdynb.dymovies.admin.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.admin.entity.AdminVodProvider;
import xyz.jdynb.dymovies.admin.mapper.AdminVodProviderMapper;
import xyz.jdynb.dymovies.admin.service.AdminVodProviderService;

import java.util.List;

@Service
public class AdminVodProviderServiceImpl implements AdminVodProviderService {

    @Resource
    private AdminVodProviderMapper adminVodProviderMapper;

    @Override
    public List<AdminVodProvider> findAll() {
        return adminVodProviderMapper.findAll();
    }

    @Override
    public boolean add(AdminVodProvider vodProvider) {
        return adminVodProviderMapper.add(vodProvider) > 0;
    }

    @Override
    public boolean update(AdminVodProvider vodProvider) {
        return adminVodProviderMapper.update(vodProvider) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null) {
            return false;
        }
        return adminVodProviderMapper.deleteById(id) > 0;
    }

}
