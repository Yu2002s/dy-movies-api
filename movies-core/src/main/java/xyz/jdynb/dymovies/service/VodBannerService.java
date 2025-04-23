package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.dto.VodBannerSaveParams;
import xyz.jdynb.dymovies.entity.VodBanner;
import xyz.jdynb.dymovies.entity.VodDetail;

import java.util.List;

public interface VodBannerService {

    List<VodBanner> findList();
}
