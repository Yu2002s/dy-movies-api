package xyz.jdynb.dymovies.admin.service;

import xyz.jdynb.dymovies.admin.entity.AdminVodProvider;

import java.util.List;

public interface AdminVodProviderService {

    List<AdminVodProvider> findAll();

    boolean add(AdminVodProvider vodProvider);

    boolean update(AdminVodProvider vodProvider);

    boolean delete(Integer id);
}
