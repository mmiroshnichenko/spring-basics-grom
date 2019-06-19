package com.lesson6.controller;

import com.lesson6.entity.Flight;
import com.lesson6.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FlightController {
    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/flight/save", produces = "text/plain")
    public @ResponseBody
    String save(@RequestBody Flight flight) {
        try {
            return flightService.save(flight).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/flight/update", produces = "text/plain")
    public @ResponseBody
    String update(@RequestBody Flight flight) {
        try{
            return flightService.update(flight).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/flight/delete", params = {"id"}, produces = "text/plain")
    public @ResponseBody
    String delete(@RequestParam(value = "id") String id) {
        try {
            flightService.delete(Long.parseLong(id));
        } catch (Exception e) {
            return e.getMessage();
        }
        return "deleting ok";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/flight/get", params = {"id"}, produces = "text/plain")
    public @ResponseBody
    String get(@RequestParam(value = "id") String id) {
        try {
            return flightService.findById(Long.parseLong(id)).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
