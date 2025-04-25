package xyz.jdynb.dymovies;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;
import xyz.jdynb.dymovies.common.entity.VodVideo;
import xyz.jdynb.dymovies.common.utils.MD5Utils;
import xyz.jdynb.dymovies.service.VodService;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@SpringBootTest
@Slf4j
class DyMoviesApplicationTests {

    // https://api.wujinapi.me/api.php/provide/vod/from/wjm3u8/at/xml/

    // https://cj.lziapi.com/api.php/provide/vod/from/lzm3u8/at/xml/

    private static final String URL_LIST = "https://api.wujinapi.me/api.php/provide/vod/from/wjm3u8/at/xml/?ac=list";

    @Resource
    private Scheduler scheduler;

    @Resource
    private VodService vodService;

    @Value("${jwt.key}")
    private String SALT;

    public final List<VodVideo> parseVodVideo(String videosUrl, Integer vid, String flag) {
        String[] v = videosUrl.split("#");
        return Arrays.stream(v).map(new Function<String, VodVideo>() {

            private int index = 1;

            @Override
            public VodVideo apply(String s) {
                String[] strings = s.split("\\$+");
                System.out.println(Arrays.toString(strings));
                if (strings.length == 0) {
                    // 需要过滤的
                    return new VodVideo();
                }
                VodVideo vodVideo = new VodVideo();
                if (strings.length == 1) {
                    vodVideo.setName(String.valueOf(index));
                    vodVideo.setUrl(strings[0]);
                } else {
                    for (String string : strings) {
                        if (string.isBlank()) {
                            continue;
                        }
                        if (string.startsWith("http")) {
                            vodVideo.setUrl(string);
                        } else {
                            vodVideo.setName(string);
                        }
                    }
                }
                index++;
                vodVideo.setVid(vid);
                vodVideo.setFlag(flag);
                return vodVideo;
            }
        }).filter(vodVideo -> StringUtils.hasText(vodVideo.getUrl())
                && StringUtils.hasText(vodVideo.getName())).toList();
    }

    @Test
    void testCollect() throws Exception {

        String url = "正片$https://ukzy.ukubf4.com/share/8iBBun8iaJQEXc9q#$$$正片$https://ukzy.ukubf4.com/20220415/nEkk92gf/index.m3u8";

        List<VodVideo> vodVideos = parseVodVideo(url, 0, "");
        System.out.println(vodVideos);

        /*Element videos = RSSUtils.getRssElement("https://cj.lziapi.com/api.php/provide/vod/from/lzm3u8/at/xml/?ac=detail&pg=4851");

        List<VodDetail> vodDetails = videos.children().stream().map(element -> {
            System.out.println(element.text());
            VodDetail vodDetail = new VodDetail();
            vodDetail.setPic(element.getElementsByTag("pic").get(0).text());
            vodDetail.setVid(Integer.parseInt(element.getElementsByTag("id").get(0).text()));
            String year = element.getElementsByTag("year").get(0).text();
            if (StringUtils.hasText(year)) {
                vodDetail.setYear(Integer.parseInt(year));
            }
            vodDetail.setDirector(element.getElementsByTag("director").get(0).text());

            Element ddEl = element.selectFirst("dl dd");
            if (ddEl != null) {
                vodDetail.setFlag(ddEl.attr("flag"));
                // 影片视频
                String text = ddEl.text();
                String[] v = text.split("#");
                List<VodVideo> list = Arrays.stream(v).map(s -> {
                    String[] strings = s.split("\\$");
                    VodVideo vodVideo = new VodVideo();
                    vodVideo.setName(strings[0]);
                    vodVideo.setUrl(strings[1]);
                    vodVideo.setVid(vodDetail.getVid());
                    vodVideo.setFlag(vodDetail.getFlag());
                    return vodVideo;
                }).toList();
            }

            vodDetail.setDes(element.getElementsByTag("des").get(0).text());
            vodDetail.setActor(element.getElementsByTag("actor").get(0).text());
            vodDetail.setLang(element.getElementsByTag("lang").get(0).text());
            vodDetail.setArea(element.getElementsByTag("area").get(0).text());
            return vodDetail;
        }).toList();*/
    }

    @Test
    public void test() {
        /*int remotePageCount = 21;
        int localRecordCount = 399;
        int PAGE_SIZE = 20;
        int page;
        if (localRecordCount <= 20) {
            page = remotePageCount - 1;
        } else {
            page = remotePageCount - (int) Math.floor((double) localRecordCount / PAGE_SIZE);
        }
        log.info("page: {}", page);*/
        /*int count = vodService.countByIdAndFlag(99763, "lzm3u8");

        log.info("count: {}", count);*/
    }

    @Test
    public void testAes() {
       /* String encrypt = AesEncryptUtils.AES_128_ECB_PKCS5Padding_Encrypt(
                "m8wZ0TSYN21736735714686", "\u001Dc\u0016<?X1\u001C\u001B?dA?\u000E?");

        log.info("encrypt: {}, {} , {}", encrypt, "\u001Dc\u0016<?X1\u001C\u001B?dA?\u000E?1", "\u001Dc\u0016<?X1\u001C\u001B?dA?\u000E?1".length());*/
    }

    @Test
    public void testMd5() {
        String encrypt = MD5Utils.encrypt(MD5Utils.encrypt(SALT) + MD5Utils.encrypt("jdy200255"));
        System.out.println(encrypt);
    }
}
