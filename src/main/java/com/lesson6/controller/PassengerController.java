package com.lesson6.controller;

import com.lesson6.entity.Passenger;
import com.lesson6.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PassengerController {
    private PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/passenger/save", produces = "text/plain")
    public @ResponseBody
    String save(@RequestBody Passenger passenger) {
        try {
            return passengerService.save(passenger).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/passenger/update", produces = "text/plain")
    public @ResponseBody
    String update(@RequestBody Passenger passenger) {
        try{
            return passengerService.update(passenger).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/passenger/delete", params = {"id"}, produces = "text/plain")
    public @ResponseBody
    String delete(@RequestParam(value = "id") String id) {
        try {
            passengerService.delete(Long.parseLong(id));
        } catch (Exception e) {
            return e.getMessage();
        }
        return "deleting ok";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/passenger/get", params = {"id"}, produces = "text/plain")
    public @ResponseBody
    String get(@RequestParam(value = "id") String id) {
        try {
            return passengerService.findById(Long.parseLong(id)).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/passenger/regular_passengers", params = {"year"}, produces = "text/plain")
    public @ResponseBody
    String regularPassengers(@RequestParam(value = "year") String year) {
        try {
            return passengerService.regularPassengers(Integer.parseInt(year)).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
