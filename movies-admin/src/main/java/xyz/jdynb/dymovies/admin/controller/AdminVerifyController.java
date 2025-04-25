package xyz.jdynb.dymovies.admin.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jdynb.dymovies.admin.service.AdminVerifyService;

import java.io.IOException;

@RestController
@RequestMapping("/admin/verifies")
public class AdminVerifyController {

    @Resource
    private AdminVerifyService adminVerifyService;

    @GetMapping
    public void verify(HttpServletRequest request) throws IOException {
        adminVerifyService.genCode(request.getSession().getId());
    }

}
