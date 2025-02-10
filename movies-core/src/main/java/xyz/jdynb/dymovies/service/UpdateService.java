package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.entity.AppUpdate;

public interface UpdateService {

    AppUpdate findLastByCode(long code);

}
