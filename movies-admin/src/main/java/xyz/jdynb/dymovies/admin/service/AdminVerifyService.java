package xyz.jdynb.dymovies.admin.service;

import java.io.IOException;

public interface AdminVerifyService {

     String genCode(String sectionId) throws IOException;

     String getCode(String sectionId);

     void deleteCode(String sectionId);
}
