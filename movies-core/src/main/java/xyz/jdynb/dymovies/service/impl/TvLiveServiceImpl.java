package xyz.jdynb.dymovies.service.impl;

import cn.hutool.core.bean.BeanUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.mapper.TvLiveMapper;
import xyz.jdynb.dymovies.service.TvLiveService;
import xyz.jdynb.dymovies.vo.TvLiveVo;

import java.util.List;

@Service
public class TvLiveServiceImpl implements TvLiveService {

    @Resource
    private TvLiveMapper tvLiveMapper;

    @Override
    public List<TvLiveVo> findList() {
        return tvLiveMapper.findList().stream().map(tvLive -> {
            TvLiveVo tvLiveVo = new TvLiveVo();
            BeanUtil.copyProperties(tvLive, tvLiveVo);
            return tvLiveVo;
        }).toList();
    }
}
