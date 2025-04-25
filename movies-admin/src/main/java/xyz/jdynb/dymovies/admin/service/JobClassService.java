package xyz.jdynb.dymovies.admin.service;

import xyz.jdynb.dymovies.admin.entity.JobClass;

import java.util.List;

public interface JobClassService {

    List<JobClass> findAll();

    void add(JobClass jobClass);

    void update(JobClass jobClass);

    void delete(long id);
}
