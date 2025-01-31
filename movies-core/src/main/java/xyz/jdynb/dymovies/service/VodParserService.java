package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.pojo.VodParseUrl;

public interface VodParserService {

    VodParseUrl parseByVideoId(Integer id);

    VodParseUrl parseByVideoIdAndUrl(Integer id, String url);
}
