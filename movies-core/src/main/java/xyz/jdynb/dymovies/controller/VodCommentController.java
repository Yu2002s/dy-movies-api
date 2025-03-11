package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.dto.VodCommentQueryParamsDto;
import xyz.jdynb.dymovies.entity.VodComment;
import xyz.jdynb.dymovies.dto.Page;
import xyz.jdynb.dymovies.service.VodCommentService;

@RestController
@RequestMapping("/vodComments")
public class VodCommentController {

    @Resource
    private VodCommentService vodCommentService;

    @GetMapping("{detailId}")
    public Page<VodComment> getVodComments(@PathVariable Integer detailId,
                                           @RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "10") int pageSize) {
        VodCommentQueryParamsDto vodCommentQueryParamsDto = new VodCommentQueryParamsDto();
        vodCommentQueryParamsDto.setDetailId(detailId);
        vodCommentQueryParamsDto.setPage(page);
        vodCommentQueryParamsDto.setPageSize(pageSize);
        return vodCommentService.findList(vodCommentQueryParamsDto);
    }

}
