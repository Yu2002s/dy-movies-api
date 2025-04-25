package xyz.jdynb.dymovies.admin.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.admin.mapper.AdminVodDetailMapper;
import xyz.jdynb.dymovies.admin.service.AdminVodDetailService;
import xyz.jdynb.dymovies.common.entity.VodDetail;

import java.util.List;

@Service
public class AdminVodDetailServiceImpl implements AdminVodDetailService {

    @Resource
    private AdminVodDetailMapper adminVodDetailMapper;


    @Override
    public int countFlag(String flag) {
        return adminVodDetailMapper.count(flag);
    }

    @Override
    public int countByVidAndFlag(Integer vid, String flag) {
        return adminVodDetailMapper.countByVidAndFlag(vid, flag);
    }

    @Override
    public int addBatch(List<VodDetail> vodDetails) {
        return adminVodDetailMapper.addBatch(vodDetails);
    }

    @Override
    public void add(VodDetail vodDetail) {
        adminVodDetailMapper.add(vodDetail);
    }
}
