package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.entity.User;

public interface UserService {

    User findById(Integer id);

    User findByEmail(String email);

    String getOrInsertUserByEmail(String email, String code);

    int add(String email, String code);

    String sendCode(String email);

    String getCode(String email);
}
