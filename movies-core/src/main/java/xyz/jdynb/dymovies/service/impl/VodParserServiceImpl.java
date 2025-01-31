package xyz.jdynb.dymovies.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.jdynb.dymovies.entity.VodVideo;
import xyz.jdynb.dymovies.pojo.VodParseUrl;
import xyz.jdynb.dymovies.service.VodParserService;
import xyz.jdynb.dymovies.service.VodVideoService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class VodParserServiceImpl implements VodParserService {

    @Resource
    private VodVideoService vodVideoService;

    @Override
    public VodParseUrl parseByVideoId(Integer id) {
        VodVideo video = vodVideoService.findById(id);
        String videoUrl = video.getUrl();
        return parseByVideoIdAndUrl(id, videoUrl);
    }

    @Override
    public VodParseUrl parseByVideoIdAndUrl(Integer id, String url) {
        if (url.endsWith(".m3u8")) {
            return new VodParseUrl(url);
        }
        try (HttpResponse response = HttpRequest.get(url).execute()) {
            String body = response.body();
            Pattern pattern = Pattern.compile("const url = \"(.+)\";");
            Matcher matcher = pattern.matcher(body);
            if (matcher.find()) {
                String host = url.substring(0, url.indexOf("/", 10));
                VodParseUrl vodParseUrl = new VodParseUrl();
                String group = matcher.group(1);
                int lastIndex = group.lastIndexOf("?");
                if (lastIndex != -1) {
                    group = group.substring(0, lastIndex);
                }
                vodParseUrl.setUrl(host + group);
                if (StringUtils.hasText(group)) {
                    vodVideoService.updateUrlById(id, vodParseUrl.getUrl());
                }
                return vodParseUrl;
            }
        }
        return null;
    }
}
