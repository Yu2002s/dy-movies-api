package xyz.jdynb.dymovies.service.admin;

import xyz.jdynb.dymovies.dto.VodBannerSaveParams;
import xyz.jdynb.dymovies.entity.VodBanner;

import java.util.List;

public interface AdminVodBannerService {

    boolean add(VodBannerSaveParams params);

    boolean update(VodBannerSaveParams params);

    boolean delete(List<Integer> ids);

    List<VodBanner> findList();
}
