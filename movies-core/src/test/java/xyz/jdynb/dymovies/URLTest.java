package xyz.jdynb.dymovies;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;
import xyz.jdynb.dymovies.service.VodParserService;
import xyz.jdynb.dymovies.vo.VodParseUrlVo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class URLTest {

    @Resource
    private VodParserService vodParserService;

    @Test
    public void test() {
        /*String lzm3u8 = vodParserService.parseByVideoId(2513539);
        System.out.println(lzm3u8);*/

        String url = "https://vip.ffzy-video.com/share/a4267159aa970aa5a6542bcbb7ef575e";

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
                    System.out.println(vodParseUrlVo);
                }
            }
        }
    }

}
