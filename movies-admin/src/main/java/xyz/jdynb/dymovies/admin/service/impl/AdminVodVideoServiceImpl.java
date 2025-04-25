package xyz.jdynb.dymovies.admin.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.admin.mapper.AdminVodVideoMapper;
import xyz.jdynb.dymovies.admin.service.AdminVodVideoService;
import xyz.jdynb.dymovies.common.entity.VodVideo;

import java.util.List;

@Service
public class AdminVodVideoServiceImpl implements AdminVodVideoService {

    @Resource
    private AdminVodVideoMapper adminVodVideoMapper;

    @Override
    public int countByVid(Integer vid) {
        return adminVodVideoMapper.countByVid(vid);
    }

    @Override
    public int countByVidAndFlag(Integer vid, String flag) {
        return adminVodVideoMapper.countByVidAndFlag(vid, flag);
    }


    @Override
    public void createTable(String flag) {
        adminVodVideoMapper.createTable(flag);
    }

    @Override
    public boolean existTable(String flag) {
        return adminVodVideoMapper.existTable(flag) > 0;
    }

    @Override
    public int addBatch(List<VodVideo> vodVideos, String flag) {
        if (vodVideos.isEmpty()) {
            return 0;
        }
        return adminVodVideoMapper.addBatch(vodVideos, flag);
    }

    @Override
    public void add(VodVideo vodVideo, String flag) {
        adminVodVideoMapper.add(vodVideo, flag);
    }
}
