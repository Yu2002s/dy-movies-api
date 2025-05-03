package xyz.jdynb.dymovies.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.common.entity.VodProvider;
import xyz.jdynb.dymovies.common.entity.VodVideo;
import xyz.jdynb.dymovies.common.pojo.VodSource;
import xyz.jdynb.dymovies.common.vo.VodSourceVideoVo;
import xyz.jdynb.dymovies.mapper.VodVideoMapper;
import xyz.jdynb.dymovies.service.VodProviderService;
import xyz.jdynb.dymovies.service.VodVideoService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VodVideoServiceImpl implements VodVideoService {

    @Resource
    private VodVideoMapper vodVideoMapper;

    @Resource
    private VodProviderService vodProviderService;

    @Resource
    private HttpServletResponse httpServletResponse;

    @Override
    public void createTable(String flag) {
        vodVideoMapper.createTable(flag);
    }

    @Override
    public boolean existTable(String flag) {
        return vodVideoMapper.existTable(flag) > 0;
    }


    @Override
    public VodVideo findById(Integer id, String flag) {
        return vodVideoMapper.findById(id, flag);
    }

    @Override
    public List<VodVideo> findByVid(Integer vid, String flag) {
        if (!existTable(flag)) {
            return Collections.emptyList();
        }
        return vodVideoMapper.findByVid(vid, flag);
    }

    @Override
    public List<VodSource> findSourcesByName(String name) {
        List<VodVideo> videos = vodVideoMapper.findListByName(name);
        Map<String, List<VodVideo>> sourceMap = videos.stream().collect(Collectors.groupingBy(VodVideo::getFlag));
        Set<Map.Entry<String, List<VodVideo>>> entries = sourceMap.entrySet();
        List<VodSource> sources = new ArrayList<>();
        for (Map.Entry<String, List<VodVideo>> entry : entries) {
            String key = entry.getKey();
            List<VodVideo> value = entry.getValue();
            VodSource vodSource = new VodSource();
            vodSource.setName(key);
            vodSource.setCount(value.size());
            vodSource.setVideos(value);
            sources.add(vodSource);
        }
        return sources;
    }

    @Override
    public VodSourceVideoVo findSourcesAndVideos(Integer vid, String flag) {
        List<VodProvider> vodProviders = vodProviderService.findAll();
        List<VodVideo> videos = findByVid(vid, flag);
        return new VodSourceVideoVo(vodProviders, videos);
    }


    @Override
    public List<VodVideo> findListByName(String name, String flag) {
        if (!existTable(flag)) {
            // 可能表不能存在的情况
            return Collections.emptyList();
        }
        return vodVideoMapper.findListByNameAndFlag(name, flag);
    }

    @Override
    public void updateUrlById(Integer id, String url, String flag) {
        vodVideoMapper.updateUrlById(id, url, flag);
    }

    @Override
    public void proxy(String url) {
        httpServletResponse.setContentType("multipart/form-data");
        BufferedReader br = null;
        try(HttpResponse response = HttpRequest.get(url).execute()) {
            InputStream inputStream = response.bodyStream();
            br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            Pattern pattern = Pattern.compile("(\\d+)\\.ts$");
            long beforeNum = -1;
            while ((line = br.readLine()) != null) {
                if (line.endsWith(".ts")) {
                    Matcher matcher = pattern.matcher(line);
                    if (!matcher.find()) {
                        sb.append(line);
                    }
                    long num = Long.parseLong(matcher.group(1));
                    if (beforeNum == -1 || num - beforeNum == 1) {
                        beforeNum = num;
                        sb.append(line);
                    }
                }
            }
        } catch (IOException e) {
            log.error("代理视频地址失败: {}", e.getMessage());
            try {
                httpServletResponse.sendRedirect(url);
            } catch (IOException ex) {
                log.error("重定向失败: {}", ex.getMessage());
            }
        } finally {
            IoUtil.close(br);
        }

    }
}
