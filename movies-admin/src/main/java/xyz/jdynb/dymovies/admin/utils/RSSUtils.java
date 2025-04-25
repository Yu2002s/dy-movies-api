package xyz.jdynb.dymovies.admin.utils;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import xyz.jdynb.dymovies.admin.pojo.CollectXmlData;
import xyz.jdynb.dymovies.common.constants.RequestConfig;
import xyz.jdynb.dymovies.common.utils.IpUtil;

import java.io.IOException;

@Slf4j
public class RSSUtils {

    public static final String ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7";

    public static Element getRssElement(String url) throws IOException {
        String ip = IpUtil.getRandomChinaIP();
        Document document = Jsoup.connect(url)
                .header("Accept", ACCEPT)
                .header("User-Agent", RequestConfig.USER_AGENT)
                .header("Accept-Language", RequestConfig.ACCEPT_LANGUAGE)
                .header("X-Forwarded-For", ip)
                .header("X-Real-IP", ip)
                .header("X-Forwarded-For", ip)
                .header("HTTP_X_FORWARDED_FOR", ip)
                .header("HTTP_CLIENT_IP", ip)
                .header("REMOTE_ADDR", ip)
                .header("X-Real-IP", ip)
                .header("X-Originating-IP", ip)
                .header("Proxy-Client-IP", ip)
                .header("X-Remote-IP", ip)
                .header("WL-Proxy-Client-IP", ip)
                .parser(Parser.xmlParser())
                .get();
        return document.getElementsByTag("list").get(0);
    }

    public static CollectXmlData getXmlData(Element list) {
        CollectXmlData collectXmlList = new CollectXmlData();
        int page = Integer.parseInt(list.attr("page"));
        int pageSize = Integer.parseInt(list.attr("pagesize"));
        int pageCount = Integer.parseInt(list.attr("pagecount"));
        int recordCount = Integer.parseInt(list.attr("recordcount"));
        collectXmlList.setPage(page);
        collectXmlList.setPageSize(pageSize);
        collectXmlList.setPageCount(pageCount);
        collectXmlList.setRecordCount(recordCount);
        Element classEl = list.nextElementSibling();
        collectXmlList.setClassEl(classEl);
        collectXmlList.setData(list.children());
        return collectXmlList;
    }

    public static CollectXmlData getXmlData(String url) throws IOException {
        Element listElement = getRssElement(url);
        return getXmlData(listElement);
    }
}
