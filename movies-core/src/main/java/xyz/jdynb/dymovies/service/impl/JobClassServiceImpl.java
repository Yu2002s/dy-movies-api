package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.entity.JobClass;
import xyz.jdynb.dymovies.mapper.JobClassMapper;
import xyz.jdynb.dymovies.service.JobClassService;

import java.util.List;

@Service
public class JobClassServiceImpl implements JobClassService {

    @Resource
    private JobClassMapper jobClassMapper;

    @Override
    public List<JobClass> findAll() {
        return jobClassMapper.findAll();
    }
}
