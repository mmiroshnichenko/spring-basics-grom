package com.lesson2.hw1;


import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    private Long id;
    private String name;
    private List paramsToCall;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List getParamsToCall() {
        return paramsToCall;
    }
}
