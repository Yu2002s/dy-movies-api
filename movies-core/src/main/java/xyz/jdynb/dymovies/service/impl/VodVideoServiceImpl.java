package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.entity.VodProvider;
import xyz.jdynb.dymovies.entity.VodVideo;
import xyz.jdynb.dymovies.mapper.VodVideoMapper;
import xyz.jdynb.dymovies.pojo.VodSource;
import xyz.jdynb.dymovies.service.VodProviderService;
import xyz.jdynb.dymovies.service.VodService;
import xyz.jdynb.dymovies.service.VodVideoService;
import xyz.jdynb.dymovies.vo.VodSourceVideoVo;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VodVideoServiceImpl implements VodVideoService {

    @Resource
    private VodVideoMapper vodVideoMapper;

    @Resource
    private VodProviderService vodProviderService;

    @Resource
    private VodService vodService;

    @Override
    public int countByVid(Integer vid) {
        return vodVideoMapper.countByVid(vid);
    }

    @Override
    public int countByVidAndFlag(Integer vid, String flag) {
        return vodVideoMapper.countByVidAndFlag(vid, flag);
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
    public int addBatch(List<VodVideo> vodVideos, String flag) {
        if (vodVideos.isEmpty()) {
            return 0;
        }
        return vodVideoMapper.addBatch(vodVideos, flag);
    }

    @Override
    public void add(VodVideo vodVideo, String flag) {
        vodVideoMapper.add(vodVideo, flag);
    }

    @Override
    public void createTable(String flag) {
        vodVideoMapper.createTable(flag);
    }

    @Override
    public boolean existTable(String flag) {
        return vodVideoMapper.existTable(flag) > 0;
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
    public List<VodVideo> findByDetailId(Integer detailId, String flag) {
        Integer vid = vodService.findVid(detailId, flag);
        return findByVid(vid, flag);
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
