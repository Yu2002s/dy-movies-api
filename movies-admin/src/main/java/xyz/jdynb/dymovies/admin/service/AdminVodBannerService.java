package xyz.jdynb.dymovies.admin.service;

import xyz.jdynb.dymovies.admin.dto.VodBannerSaveParams;
import xyz.jdynb.dymovies.common.entity.VodBanner;

import java.util.List;

public interface AdminVodBannerService {

    boolean add(VodBannerSaveParams params);

    boolean update(VodBannerSaveParams params);

    boolean delete(List<Integer> ids);

    List<VodBanner> findList();
}
