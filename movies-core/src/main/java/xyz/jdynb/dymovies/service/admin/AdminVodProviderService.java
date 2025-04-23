package xyz.jdynb.dymovies.service.admin;

import xyz.jdynb.dymovies.entity.AdminVodProvider;

import java.util.List;

public interface AdminVodProviderService {

    List<AdminVodProvider> findAll();

    boolean add(AdminVodProvider vodProvider);

    boolean update(AdminVodProvider vodProvider);

    boolean delete(Integer id);
}
