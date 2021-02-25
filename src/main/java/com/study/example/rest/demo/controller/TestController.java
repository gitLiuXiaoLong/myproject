package com.study.example.rest.demo.controller;

import com.study.example.rest.demo.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ITestService testService;

    @RequestMapping(value = "/testName")
    public String getTestName(String name) {
        return testService.getTestName(name);
    }
}
