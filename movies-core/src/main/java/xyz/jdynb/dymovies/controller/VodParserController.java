package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.pojo.VodParseUrl;
import xyz.jdynb.dymovies.service.VodParserService;

@RestController
@RequestMapping("/vodParses")
public class VodParserController {

    @Resource
    private VodParserService vodParserService;

    @GetMapping("{id}")
    public Result<VodParseUrl> parseVideo(@PathVariable("id") Integer id) {
        return Result.success(vodParserService.parseByVideoId(id));
    }
}
