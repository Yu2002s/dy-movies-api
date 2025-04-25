package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.jdynb.dymovies.admin.service.AdminVodConfigService;
import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.dto.VodLatestQueryParamsDto;
import xyz.jdynb.dymovies.common.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.common.entity.Vod;
import xyz.jdynb.dymovies.mapper.VodMapper;
import xyz.jdynb.dymovies.service.VodService;

import java.util.List;

@Service
public class VodServiceImpl implements VodService {

    @Resource
    private VodMapper vodMapper;

    @Resource
    private AdminVodConfigService adminVodConfigService;

    @Override
    public int addOrUpdate(Vod vod) {
        return 0;
    }

    @Override
    public int count(String flag) {
        return vodMapper.countByFlag(flag);
    }

    @Override
    public Page<Vod> findListByType(VodQueryParamsDto vodQueryParamsDto) {
        String flag = adminVodConfigService.findFlag();
        vodQueryParamsDto.setFlag(flag);

        int total;
        // 有 pid 代表是有父级，此时是查找子集的类型
        if (vodQueryParamsDto.getPid() != null && vodQueryParamsDto.getPid() != 0) {
            // 获取总记录数
            // 直接通过tid进行获取
            total = vodMapper.countByTidAndFlag(vodQueryParamsDto.getTid(), flag);
        } else {
            // 通过pid进行获取，此时是父级
            total = vodMapper.countByPidAndFlag(vodQueryParamsDto.getTid(), flag);
        }
        List<Vod> vodList = vodMapper.findListByTid(vodQueryParamsDto);
        return Page.of(vodQueryParamsDto.getPage(), total, vodQueryParamsDto.getPageSize(), vodList);
    }

    @Override
    public List<Vod> findLast(int pageSize) {
        return findLast(pageSize, null);
    }

    @Override
    public List<Vod> findLast(int pageSize, Integer typeId) {
        return findLast(VodLatestQueryParamsDto.create(typeId, pageSize));
    }

    @Override
    public List<Vod> findLast(VodLatestQueryParamsDto vodLatestQueryParamsDto) {
        String flag = adminVodConfigService.findFlag();
        vodLatestQueryParamsDto.setFlag(flag);
        return vodMapper.findLast(vodLatestQueryParamsDto);
    }

    @Override
    public Integer findVid(Integer id, String flag) {
        return vodMapper.findVid(id, flag);
    }

    @Override
    public Page<Vod> findList(VodQueryParamsDto vodQueryParamsDto) {
        if (!StringUtils.hasText(vodQueryParamsDto.getFlag())) {
            vodQueryParamsDto.setFlag(adminVodConfigService.findFlag());
        }
        String year = vodQueryParamsDto.getYear();
        if (StringUtils.hasText(year)) {
            String[] years = year.split("-");
            if (years.length == 2) {
                vodQueryParamsDto.setYear(years[0]);
                vodQueryParamsDto.setYearEnd(years[1]);
            }
        }
        int count = count(vodQueryParamsDto);
        return Page.of(vodQueryParamsDto.getPage(), count, vodQueryParamsDto.getPageSize(),
                vodMapper.findList(vodQueryParamsDto));
    }

    @Override
    public int count(VodQueryParamsDto vodQueryParamsDto) {
        if (!StringUtils.hasText(vodQueryParamsDto.getFlag())) {
            vodQueryParamsDto.setFlag(adminVodConfigService.findFlag());
        }
        return vodMapper.count(vodQueryParamsDto);
    }
}
