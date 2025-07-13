package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.common.entity.VodBanner;
import xyz.jdynb.dymovies.dto.VodFeedDto;
import xyz.jdynb.dymovies.entity.SystemNotify;
import xyz.jdynb.dymovies.enums.HomeCate;
import xyz.jdynb.dymovies.service.HomeVodService;
import xyz.jdynb.dymovies.service.SystemNotifyService;
import xyz.jdynb.dymovies.service.VodBannerService;
import xyz.jdynb.dymovies.service.VodService;
import xyz.jdynb.dymovies.vo.HomeVodDataVo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class HomeVodServiceImpl implements HomeVodService {

    @Resource
    private VodBannerService vodBannerService;

    @Resource
    private VodService vodService;

    @Resource
    private SystemNotifyService systemNotifyService;

    // 缓存首页数据，提高访问速度，缓存时间可根据业务需求调整
    @Override
    @Cacheable(value = "homeVodData", key = "'homeData'", unless = "#result == null or #result.feeds.empty")
    public HomeVodDataVo getHomeVodData() {
        try {
            HomeVodDataVo homeVodDataVo = new HomeVodDataVo();

            // 使用CompletableFuture异步并行加载各部分数据
            CompletableFuture<List<VodBanner>> bannersFuture = CompletableFuture.supplyAsync(vodBannerService::findList);
            CompletableFuture<VodFeedDto> updateFeedFuture = CompletableFuture.supplyAsync(() ->
                    VodFeedDto.createFeed(HomeCate.UPDATE, vodService.findLast(9)));

            CompletableFuture<List<SystemNotify>> notifyFuture = CompletableFuture.supplyAsync(systemNotifyService::findAll);

            // 等待所有异步任务完成
            List<VodBanner> banners = bannersFuture.get();
            VodFeedDto updateFeed = updateFeedFuture.get();
            List<SystemNotify> notifyList = notifyFuture.get();

            // 设置结果
            homeVodDataVo.setBanners(banners);
            homeVodDataVo.setNotifyList(notifyList);
            List<VodFeedDto> feeds = new ArrayList<>();
            feeds.add(updateFeed);

            homeVodDataVo.setFeeds(feeds);
            return homeVodDataVo;
        } catch (Exception e) {
            log.error("加载首页数据失败：{}", e.toString());
            // 错误情况下返回一个基本数据，避免缓存错误
            HomeVodDataVo fallbackData = new HomeVodDataVo();
            fallbackData.setBanners(new ArrayList<>());
            fallbackData.setFeeds(new ArrayList<>());
            fallbackData.setNotifyList(new ArrayList<>());
            return fallbackData;
        }
    }

    /**
     * 清除首页数据缓存
     * 当遇到序列化错误时，可以调用此方法清除缓存
     */
    @Override
    @CacheEvict(value = "homeVodData", key = "'homeData'")
    public void clearHomeDataCache() {
        log.info("首页数据缓存已清除");
    }
}
