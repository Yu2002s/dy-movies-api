package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.common.entity.VodBanner;
import xyz.jdynb.dymovies.mapper.VodBannerMapper;
import xyz.jdynb.dymovies.service.VodBannerService;

import java.util.List;

@Service
public class VodBannerServiceImpl implements VodBannerService {

    @Resource
    private VodBannerMapper vodBannerMapper;

    @Override
    public List<VodBanner> findList() {
        return vodBannerMapper.findList();
    }
}
