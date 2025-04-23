package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.dto.Page;
import xyz.jdynb.dymovies.entity.AppUpdate;

public interface UpdateService {

    int count();

    AppUpdate findLastByCode(long code);

    Page<AppUpdate> findListByPage(int page, int pageSize);

    boolean add(AppUpdate appUpdate);

    boolean update(AppUpdate appUpdate);

    boolean delete(Integer id);
}
