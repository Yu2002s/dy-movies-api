package xyz.jdynb.dymovies.admin.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.admin.mapper.AdminVodTypeMapper;
import xyz.jdynb.dymovies.admin.service.AdminVodTypeService;
import xyz.jdynb.dymovies.common.entity.VodType;

import java.util.List;

@Service
public class AdminVodTypeServiceImpl implements AdminVodTypeService {

    @Resource
    private AdminVodTypeMapper adminVodTypeMapper;

    public List<VodType> findList(String flag) {
        return adminVodTypeMapper.findList(flag);
    }

    @Override
    public boolean update(VodType vodType) {
        return adminVodTypeMapper.update(vodType) > 0;
    }

    @Override
    public boolean add(VodType vodType) {
        return false;
    }

    @Override
    public int countByFlag(String flag) {
        return adminVodTypeMapper.countByFlag(flag);
    }

    @Override
    public int addBatch(List<VodType> vodList) {
        return adminVodTypeMapper.addBatch(vodList);
    }

}
