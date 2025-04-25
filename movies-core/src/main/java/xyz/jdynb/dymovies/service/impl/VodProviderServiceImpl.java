package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.common.entity.VodProvider;
import xyz.jdynb.dymovies.mapper.VodProviderMapper;
import xyz.jdynb.dymovies.service.VodProviderService;

import java.util.List;

@Service
public class VodProviderServiceImpl implements VodProviderService {

    @Resource
    private VodProviderMapper vodProviderMapper;

    @Override
    public List<VodProvider> findAll() {
        return vodProviderMapper.findAll();
    }
}
