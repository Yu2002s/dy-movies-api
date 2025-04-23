package xyz.jdynb.dymovies.service.admin.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.entity.AdminVodProvider;
import xyz.jdynb.dymovies.mapper.admin.AdminVodProviderMapper;
import xyz.jdynb.dymovies.service.admin.AdminVodProviderService;

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
