package xyz.jdynb.dymovies.service;

public interface MailService {


    boolean sendMail(String to, String subject, String content);

}
