package xyz.jdynb.dymovies.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.dto.PageParams;
import xyz.jdynb.dymovies.common.entity.Feedback;
import xyz.jdynb.dymovies.mapper.FeedbackMapper;
import xyz.jdynb.dymovies.service.FeedbackService;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {


    @Resource
    private FeedbackMapper feedbackMapper;

    @Override
    public Page<Feedback> findList(PageParams pageParams) {

        int count = feedbackMapper.count();

        List<Feedback> list = feedbackMapper.findList(pageParams);

        return Page.of(pageParams.getPage(), count, pageParams.getPageSize(), list);
    }

    @Override
    public boolean update(Feedback feedback) {
        return feedbackMapper.update(feedback) > 0;
    }

    @Override
    public boolean save(Feedback feedback) {
        if (feedback.getId() != null) {
            return update(feedback);
        }
        return feedbackMapper.add(feedback) > 0;
    }
}
