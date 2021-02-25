package com.study.example.rest.demo.service.impl;

import com.study.example.rest.demo.service.ITestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements ITestService {

    @Override
    public String getTestName(String name) {
        return name;
    }
}
