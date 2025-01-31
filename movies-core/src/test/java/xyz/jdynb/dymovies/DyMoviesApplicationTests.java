package xyz.jdynb.dymovies;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.jdynb.dymovies.service.VodService;
import xyz.jdynb.dymovies.utils.MD5Utils;

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

    @Test
    void testCollect() throws Exception {
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
