package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.common.entity.VodType;

import java.util.List;

public interface VodTypeService {

    List<VodType> findParentList(String flag);

    List<VodType> findList();

    List<VodType> findAll();

    List<VodType> findListByPid(Integer pid);
}
