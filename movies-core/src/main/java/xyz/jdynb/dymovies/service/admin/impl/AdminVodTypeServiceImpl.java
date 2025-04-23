package xyz.jdynb.dymovies.service.admin.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.entity.VodType;
import xyz.jdynb.dymovies.mapper.VodTypeMapper;
import xyz.jdynb.dymovies.mapper.admin.AdminVodTypeMapper;
import xyz.jdynb.dymovies.service.VodTypeService;
import xyz.jdynb.dymovies.service.admin.AdminVodTypeService;

import java.util.List;

@Service
public class AdminVodTypeServiceImpl implements AdminVodTypeService {

    @Resource
    private AdminVodTypeMapper adminVodTypeMapper;

    @Resource
    private VodTypeMapper vodTypeMapper;

    public List<VodType> findList(String flag) {
        return adminVodTypeMapper.findList(flag);
    }

    @Override
    public List<VodType> findParentList(String flag) {
        return vodTypeMapper.findListByParent(flag);
    }

    @Override
    public boolean update(VodType vodType) {
        return adminVodTypeMapper.update(vodType) > 0;
    }

    @Override
    public boolean add(VodType vodType) {
        return false;
    }
}
