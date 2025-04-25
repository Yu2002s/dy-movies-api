package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.jdynb.dymovies.admin.service.AdminVodConfigService;
import xyz.jdynb.dymovies.common.entity.VodType;
import xyz.jdynb.dymovies.mapper.VodTypeMapper;
import xyz.jdynb.dymovies.service.VodTypeService;

import java.util.List;

@Service
@Slf4j
public class VodTypeServiceImpl implements VodTypeService {

    @Resource
    private VodTypeMapper vodTypeMapper;

    @Resource
    private AdminVodConfigService adminVodConfigService;

    @Override
    public List<VodType> findList() {
        String flag = adminVodConfigService.findFlag();
        return vodTypeMapper.findListByFlag(flag);
    }

    @Override
    public List<VodType> findParentList(String flag) {
        String currentFlag;
        if (StringUtils.hasText(flag)) {
            currentFlag = flag;
        } else {
            currentFlag = adminVodConfigService.findFlag();
        }
        return vodTypeMapper.findListByParent(currentFlag);
    }

    @Override
    public List<VodType> findAll() {
        String flag = adminVodConfigService.findFlag();
        /*vodTypes.forEach(vodType -> {
            List<VodType> children = vodType.getChildren();
            if (children != null) {
                children.add(0, new VodType(vodType.getId(), null, "全部", null));
            }
        });*/
        return vodTypeMapper.findAllByFlag(flag);
    }

    @Override
    public List<VodType> findListByPid(Integer pid) {
        String flag = adminVodConfigService.findFlag();
        return vodTypeMapper.findListByPid(flag, pid);
    }
}
