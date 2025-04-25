package xyz.jdynb.dymovies.admin.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.admin.entity.JobClass;
import xyz.jdynb.dymovies.admin.mapper.JobClassMapper;
import xyz.jdynb.dymovies.admin.service.JobClassService;

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
