package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.entity.VodBanner;
import xyz.jdynb.dymovies.entity.VodDetail;
import xyz.jdynb.dymovies.service.HomeVodService;
import xyz.jdynb.dymovies.service.VodBannerService;
import xyz.jdynb.dymovies.vo.HomeVodDataVo;

import java.util.List;

@Service
public class HomeVodServiceImpl implements HomeVodService {

    @Resource
    private VodBannerService vodBannerService;

    @Override
    public HomeVodDataVo getHomeVodData() {
        HomeVodDataVo homeVodDataVo = new HomeVodDataVo();
        List<VodBanner> banners = vodBannerService.findList();
        homeVodDataVo.setBanners(banners);
        return homeVodDataVo;
    }
}
