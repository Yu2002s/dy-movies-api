package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.common.entity.VodProvider;
import xyz.jdynb.dymovies.common.entity.VodVideo;
import xyz.jdynb.dymovies.common.pojo.VodSource;
import xyz.jdynb.dymovies.common.vo.VodSourceVideoVo;
import xyz.jdynb.dymovies.mapper.VodVideoMapper;
import xyz.jdynb.dymovies.service.VodProviderService;
import xyz.jdynb.dymovies.service.VodVideoService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VodVideoServiceImpl implements VodVideoService {

    @Resource
    private VodVideoMapper vodVideoMapper;

    @Resource
    private VodProviderService vodProviderService;

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
}
