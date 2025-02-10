package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.entity.VodType;

import java.util.List;

public interface VodTypeService {

    int countByFlag(String flag);

    int addBatch(List<VodType> vodList);

    List<VodType> findList();

    List<VodType> findAll();
}
