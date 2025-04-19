package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.vo.VodParseUrlVo;

public interface VodParserService {

    VodParseUrlVo parseByVideoId(Integer id, String flag);

    VodParseUrlVo parseByVideoIdAndUrl(Integer id, String url, String flag);
}
