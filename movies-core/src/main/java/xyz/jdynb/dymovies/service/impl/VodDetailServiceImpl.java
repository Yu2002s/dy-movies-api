package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.entity.VodDetail;
import xyz.jdynb.dymovies.entity.VodVideo;
import xyz.jdynb.dymovies.mapper.VodDetailMapper;
import xyz.jdynb.dymovies.service.VodDetailService;
import xyz.jdynb.dymovies.service.VodParserService;
import xyz.jdynb.dymovies.service.VodVideoService;

import java.util.List;

@Slf4j
@Service
public class VodDetailServiceImpl implements VodDetailService {

    @Resource
    private VodDetailMapper vodDetailMapper;

    @Resource
    private VodVideoService vodVideoService;

    @Resource
    private VodParserService vodParserService;

    @Override
    public int countFlag(String flag) {
        return vodDetailMapper.count(flag);
    }

    @Override
    public int countByVidAndFlag(Integer vid, String flag) {
        return vodDetailMapper.countByVidAndFlag(vid, flag);
    }

    @Override
    public int addBatch(List<VodDetail> vodDetails) {
        return vodDetailMapper.addBatch(vodDetails);
    }

    @Override
    public void add(VodDetail vodDetail) {
        vodDetailMapper.add(vodDetail);
    }

    @Override
    public VodDetail findById(Integer id) {
        /*VodDetail vodDetail = vodDetailMapper.findById(id);
        if (vodDetail != null) {
            List<VodVideo> vodVideos = vodVideoService.findByVid(vodDetail.getVid(), vodDetail.getFlag());
            if (!vodVideos.isEmpty()) {
                vodDetail.setVideos(vodVideos);
                VodVideo vodVideo = vodVideos.get(0);
                vodDetail.setVideoId(vodVideo.getId());
                vodDetail.setVideoUrl(vodVideo.getUrl());
            }
        }*/
        return vodDetailMapper.findById(id);
    }
}
