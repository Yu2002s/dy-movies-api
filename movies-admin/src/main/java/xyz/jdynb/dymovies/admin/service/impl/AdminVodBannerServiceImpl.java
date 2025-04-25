package xyz.jdynb.dymovies.admin.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.admin.dto.VodBannerSaveParams;
import xyz.jdynb.dymovies.admin.mapper.AdminVodBannerMapper;
import xyz.jdynb.dymovies.admin.service.AdminVodBannerService;
import xyz.jdynb.dymovies.common.entity.VodBanner;

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
