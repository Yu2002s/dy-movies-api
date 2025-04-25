package xyz.jdynb.dymovies.admin.service.impl;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.jdynb.dymovies.admin.service.AdminVerifyService;
import xyz.jdynb.dymovies.common.constants.RequestConfig;
import xyz.jdynb.dymovies.common.utils.VerifyUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class AdminVerifyServiceImpl implements AdminVerifyService {

    @Resource
    private HttpServletResponse response;

    @Resource
    private HttpServletRequest request;

    @Override
    @CachePut(key = "#sectionId", value = "verify_code", unless = "#result == null")
    public String genCode(String sectionId) throws IOException {
        HttpSession session = request.getSession();
        Object[] objs = VerifyUtil.newBuilder()
                .setWidth(120)   //设置图片的宽度
                .setHeight(35)   //设置图片的高度
                .setSize(6)      //设置字符的个数
                .setLines(10)    //设置干扰线的条数
                .setFontSize(25) //设置字体的大小
                .setTilt(true)   //设置是否需要倾斜
                .setBackgroundColor(Color.LIGHT_GRAY) //设置验证码的背景颜色
                .build()         //构建VerifyUtil项目
                .createImage();  //生成图片
        // 将验证码存入Session
        session.setAttribute(RequestConfig.CODE_KEY + sectionId, objs[0]);
        BufferedImage bufferedImage = (BufferedImage) objs[1];
        response.setContentType("image/png");
        ImageIO.write(bufferedImage, "png", response.getOutputStream());
        return (String) objs[0];
    }

    @Override
    @Cacheable(key = "#sessionId", value = "verify_code", unless = "#result == null")
    public String getCode(String sessionId) {
        return null;
    }

    @CacheEvict(key = "#sectionId", value = "verify_code", condition = "#sectionId != null")
    @Override
    public void deleteCode(String sectionId) {
    }
}
