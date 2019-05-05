package com.lesson2.hw1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class RouteController {
    @Autowired
    private Step step;

    @Autowired
    private Service service;

    @Autowired
    private Route route;

    @RequestMapping(method = RequestMethod.GET, value = "/callByBean", produces = "text/plain")
    public @ResponseBody
    String callByBean() {
        Long serviceId = service.getId();
        String serviceName = service.getName();
        List paramsToCall = service.getParamsToCall();

        Long stepId = step.getId();
        Map paramsServiceFrom = step.getParamsServiceFrom();
        Map paramsServiceTo = step.getParamsServiceTo();
        Service serviceFrom = step.getServiceFrom();
        Service serviceTo = step.getServiceTo();

        String routeId = route.getId();
        List routeSteps = route.getSteps();


        return "call all bean getters";
    }
}
