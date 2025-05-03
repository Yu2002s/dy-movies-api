package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.dto.PageParams;
import xyz.jdynb.dymovies.common.entity.Feedback;

public interface FeedbackService {

    Page<Feedback> findList(PageParams pageParams);

    boolean save(Feedback feedback);

    boolean update(Feedback feedback);
}
