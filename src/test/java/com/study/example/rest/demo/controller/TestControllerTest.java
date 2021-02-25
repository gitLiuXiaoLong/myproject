package com.study.example.rest.demo.controller;


import com.study.example.rest.demo.service.ITestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestControllerTest {
    @Autowired
    private ITestService testService;

    @Test
    public void  getTestService() {
        System.out.println(testService.getTestName("testName"));
    }
}