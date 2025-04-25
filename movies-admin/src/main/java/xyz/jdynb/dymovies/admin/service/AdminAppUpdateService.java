package xyz.jdynb.dymovies.admin.service;

import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.entity.AppUpdate;

public interface AdminAppUpdateService {

    int count();

    Page<AppUpdate> findListByPage(int page, int pageSize);

    boolean add(AppUpdate appUpdate);

    boolean update(AppUpdate appUpdate);

    boolean delete(Integer id);

}
