package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.vo.VodParseUrlVo;

public interface VodParserService {

    VodParseUrlVo parseByVideoId(Integer id);

    VodParseUrlVo parseByVideoIdAndUrl(Integer id, String url);
}
