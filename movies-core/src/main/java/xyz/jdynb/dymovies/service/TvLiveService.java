package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.entity.TvLive;
import xyz.jdynb.dymovies.vo.TvLiveVo;

import java.util.List;

public interface TvLiveService {

    List<TvLiveVo> findList();
}
