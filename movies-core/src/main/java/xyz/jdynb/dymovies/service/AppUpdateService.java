package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.common.entity.AppUpdate;

public interface AppUpdateService {

    AppUpdate findLastByCode(long code);

}
