package xyz.jdynb.dymovies.admin.service;

import xyz.jdynb.dymovies.admin.entity.AdminTvLive;

import java.util.List;

public interface AdminTvLiveService {

    List<AdminTvLive> findAll();

    boolean update(AdminTvLive tvLive);

    boolean add(AdminTvLive tvLive);

    boolean delete(int id);
}
