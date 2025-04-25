package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.common.entity.VodDetail;
import xyz.jdynb.dymovies.mapper.VodDetailMapper;
import xyz.jdynb.dymovies.service.VodDetailService;

@Slf4j
@Service
public class VodDetailServiceImpl implements VodDetailService {

    @Resource
    private VodDetailMapper vodDetailMapper;

    @Override
    public VodDetail findById(Integer id) {
        return vodDetailMapper.findById(id);
    }
}
