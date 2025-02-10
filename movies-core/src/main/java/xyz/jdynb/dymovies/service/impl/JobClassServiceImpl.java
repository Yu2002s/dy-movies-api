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

    @Override
    public void add(JobClass jobClass) {
        jobClassMapper.add(jobClass);
    }

    @Override
    public void update(JobClass jobClass) {
        jobClassMapper.update(jobClass);
    }

    @Override
    public void delete(long id) {
        jobClassMapper.delete(id);
    }
}
