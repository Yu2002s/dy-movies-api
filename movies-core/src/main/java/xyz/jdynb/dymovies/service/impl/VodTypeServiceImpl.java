package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.entity.VodType;
import xyz.jdynb.dymovies.mapper.VodTypeMapper;
import xyz.jdynb.dymovies.service.VodConfigService;
import xyz.jdynb.dymovies.service.VodTypeService;

import java.util.List;

@Service
@Slf4j
public class VodTypeServiceImpl implements VodTypeService {

    @Resource
    private VodTypeMapper vodTypeMapper;

    @Resource
    private VodConfigService vodConfigService;

    @Override
    public int countByFlag(String flag) {
        return vodTypeMapper.countByFlag(flag);
    }

    @Override
    public int addBatch(List<VodType> vodList) {
        return vodTypeMapper.addBatch(vodList);
    }

    @Override
    public List<VodType> findList() {
        String flag = vodConfigService.findFlag();
        return vodTypeMapper.findListByFlag(flag);
    }

    @Override
    public List<VodType> findParentList() {
        String flag = vodConfigService.findFlag();
        return vodTypeMapper.findListByParent(flag);
    }

    @Override
    public List<VodType> findAll() {
        String flag = vodConfigService.findFlag();
        List<VodType> vodTypes = vodTypeMapper.findAllByFlag(flag);
        vodTypes.forEach(vodType -> {
            List<VodType> children = vodType.getChildren();
            if (children != null) {
                children.add(0, new VodType(vodType.getId(), null, "全部", null));
            }
        });
        return vodTypes;
    }

    @Override
    public List<VodType> findListByPid(Integer pid) {
        String flag = vodConfigService.findFlag();
        return vodTypeMapper.findListByPid(flag, pid);
    }
}
