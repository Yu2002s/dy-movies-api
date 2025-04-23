package xyz.jdynb.dymovies.service.admin.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.dto.VodBannerSaveParams;
import xyz.jdynb.dymovies.entity.VodBanner;
import xyz.jdynb.dymovies.mapper.admin.AdminVodBannerMapper;
import xyz.jdynb.dymovies.service.admin.AdminVodBannerService;

import java.util.List;

@Service
public class AdminVodBannerServiceImpl implements AdminVodBannerService {

    @Resource
    private AdminVodBannerMapper adminVodBannerMapper;

    @Override
    public List<VodBanner> findList() {
        return adminVodBannerMapper.findList();
    }

    @Override
    public boolean add(VodBannerSaveParams params) {
        return adminVodBannerMapper.add(params) > 0;
    }

    @Override
    public boolean update(VodBannerSaveParams params) {
        return adminVodBannerMapper.update(params) > 0;
    }

    @Override
    public boolean delete(List<Integer> ids) {
        return adminVodBannerMapper.delete(ids) > 0;
    }
}
