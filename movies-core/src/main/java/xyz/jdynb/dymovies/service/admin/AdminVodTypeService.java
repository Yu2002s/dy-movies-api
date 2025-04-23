package xyz.jdynb.dymovies.service.admin;

import xyz.jdynb.dymovies.entity.VodType;

import java.util.List;

public interface AdminVodTypeService {

    List<VodType> findList(String flag);

    boolean update(VodType vodType);

    List<VodType> findParentList(String flag);

    boolean add(VodType vodType);
}
