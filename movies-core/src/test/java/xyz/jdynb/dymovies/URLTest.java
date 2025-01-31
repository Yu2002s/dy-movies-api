package xyz.jdynb.dymovies;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.jdynb.dymovies.service.VodParserService;

@SpringBootTest
public class URLTest {

    @Resource
    private VodParserService vodParserService;

    @Test
    public void test() {
        /*String lzm3u8 = vodParserService.parseByVideoId(2513539);
        System.out.println(lzm3u8);*/
    }

}
