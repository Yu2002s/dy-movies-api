package xyz.jdynb.dymovies.admin.service;

import xyz.jdynb.dymovies.common.entity.VodType;

import java.util.List;

public interface AdminVodTypeService {

    int countByFlag(String flag);

    List<VodType> findList(String flag);

    boolean update(VodType vodType);

    boolean add(VodType vodType);

    int addBatch(List<VodType> vodList);
}
