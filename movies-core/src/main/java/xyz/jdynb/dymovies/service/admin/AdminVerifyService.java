package xyz.jdynb.dymovies.service.admin;

import java.io.IOException;

public interface AdminVerifyService {

     String genCode(String sectionId) throws IOException;

     String getCode(String sectionId);

     void deleteCode(String sectionId);
}
