package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.common.anno.Cacheable;
import xyz.jdynb.dymovies.common.anno.RequireLogin;
import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.dto.VodCommentAddParamsDto;
import xyz.jdynb.dymovies.dto.VodCommentQueryParamsDto;
import xyz.jdynb.dymovies.entity.User;
import xyz.jdynb.dymovies.entity.VodComment;
import xyz.jdynb.dymovies.service.VodCommentService;

/**
 * 影片评论
 */
@RestController
@RequestMapping("/vodComments")
public class VodCommentController {

    @Resource
    private VodCommentService vodCommentService;

    @Cacheable(value = false)
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

    @RequireLogin
    @PostMapping
    public Result<String> addVodComment(HttpServletRequest request,
                                        @Validated @RequestBody VodCommentAddParamsDto params) {
        Integer uid = (Integer) request.getAttribute(User.ID);
        params.setFromUid(uid);
        return vodCommentService.add(params) > 0 ? Result.success("评论成功") : Result.error("评论失败");
    }
}
