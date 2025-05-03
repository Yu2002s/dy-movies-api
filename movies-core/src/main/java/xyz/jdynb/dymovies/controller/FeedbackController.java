package xyz.jdynb.dymovies.controller;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.dto.PageParams;
import xyz.jdynb.dymovies.common.entity.Feedback;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.service.FeedbackService;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Resource
    private FeedbackService feedbackService;

    @GetMapping
    public Result<Page<Feedback>> getFeedbacks(@Validated PageParams pageParams) {
        return Result.success(feedbackService.findList(pageParams));
    }

    @PostMapping
    public Result<Feedback> saveFeedback(@Validated @RequestBody Feedback feedback) {
        return feedbackService.save(feedback) ? Result.success("反馈已提交") : Result.error("操作失败");
    }
}
