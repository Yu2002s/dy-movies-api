package xyz.jdynb.dymovies.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/tests")
public class TestController {

    @GetMapping
    public String test() {
        return "test";
    }
}
