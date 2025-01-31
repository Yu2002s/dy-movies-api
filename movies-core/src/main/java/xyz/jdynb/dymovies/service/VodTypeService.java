package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.Vod;
import xyz.jdynb.dymovies.entity.VodType;
import xyz.jdynb.dymovies.pojo.Page;

import java.util.List;

public interface VodTypeService {

    int countByFlag(String flag);

    int addBatch(List<VodType> vodList);

    List<VodType> findList();

    List<VodType> findAll();
}
