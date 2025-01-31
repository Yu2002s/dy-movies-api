package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.entity.VodType;
import xyz.jdynb.dymovies.service.VodTypeService;

import java.util.List;

@RestController
@RequestMapping("/vodTypes")
public class VodTypeController {

    @Resource
    private VodTypeService vodTypeService;

    @GetMapping
    public Result<List<VodType>> getVodTypes() {
        return Result.success(vodTypeService.findList());
    }

    @GetMapping("/all")
    public Result<List<VodType>> getAllVodTypes() {
        return Result.success(vodTypeService.findAll());
    }

}
