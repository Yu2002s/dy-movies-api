package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.entity.VodVideo;
import xyz.jdynb.dymovies.mapper.VodVideoMapper;
import xyz.jdynb.dymovies.pojo.VodSource;
import xyz.jdynb.dymovies.service.VodVideoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VodVideoServiceImpl implements VodVideoService {

    @Resource
    private VodVideoMapper vodVideoMapper;

    @Override
    public int countByVid(Integer vid) {
        return vodVideoMapper.countByVid(vid);
    }

    @Override
    public int countByVidAndFlag(Integer vid, String flag) {
        return vodVideoMapper.countByVidAndFlag(vid, flag);
    }

    @Override
    public VodVideo findById(Integer id) {
        return vodVideoMapper.findById(id);
    }

    @Override
    public int addBatch(List<VodVideo> vodVideos) {
        return vodVideoMapper.addBatch(vodVideos);
    }

    @Override
    public void add(VodVideo vodVideo) {
        vodVideoMapper.add(vodVideo);
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
    public void updateUrlById(Integer id, String url) {
        vodVideoMapper.updateUrlById(id, url);
    }
}
