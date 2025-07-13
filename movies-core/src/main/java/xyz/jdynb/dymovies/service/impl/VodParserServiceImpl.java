package xyz.jdynb.dymovies.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.jdynb.dymovies.common.entity.VodVideo;
import xyz.jdynb.dymovies.service.VodParserService;
import xyz.jdynb.dymovies.service.VodVideoService;
import xyz.jdynb.dymovies.vo.VodParseUrlVo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class VodParserServiceImpl implements VodParserService {

    @Resource
    private VodVideoService vodVideoService;

    @Override
    public VodParseUrlVo parseByVideoId(Integer id, String flag) {
        VodVideo video = vodVideoService.findById(id, flag);
        String videoUrl = video.getUrl();
        return parseByVideoIdAndUrl(id, videoUrl, flag);
    }

    @Override
    public VodParseUrlVo parseByVideoIdAndUrl(Integer id, String url, String flag) {
        if (url.endsWith(".m3u8")) {
            return new VodParseUrlVo(url);
        }
        try (HttpResponse response = HttpRequest.get(url).execute()) {
            String body = response.body();
            String[] regexArr = new String[]{"const url = \"(.+)\";", "url: ['\"](.+)['\"]"};
            for (String regex : regexArr) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(body);
                if (matcher.find()) {
                    VodParseUrlVo vodParseUrlVo = new VodParseUrlVo();
                    String group = matcher.group(1);
                    int lastIndex = group.lastIndexOf("?");
                    if (lastIndex != -1) {
                        group = group.substring(0, lastIndex);
                    }
                    String videoUrl = group;
                    if (!group.startsWith("http")) {
                        String host = url.substring(0, url.indexOf("/", 10));
                        videoUrl = host + videoUrl;
                    }
                    vodParseUrlVo.setUrl(videoUrl);
                    if (StringUtils.hasText(group)) {
                        vodVideoService.updateUrlById(id, vodParseUrlVo.getUrl(), flag);
                    }
                    return vodParseUrlVo;
                }
            }
        }
        return null;
    }
}
