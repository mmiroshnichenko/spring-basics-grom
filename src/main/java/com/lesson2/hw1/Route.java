package com.lesson2.hw1;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Route {
    private String id;
    private List steps;


    public String getId() {
        return id;
    }

    public List getSteps() {
        return steps;
    }
}
