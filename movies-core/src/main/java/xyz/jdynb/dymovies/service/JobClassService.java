package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.entity.JobClass;

import java.util.List;

public interface JobClassService {

    List<JobClass> findAll();

    void add(JobClass jobClass);

    void update(JobClass jobClass);

    void delete(long id);
}
