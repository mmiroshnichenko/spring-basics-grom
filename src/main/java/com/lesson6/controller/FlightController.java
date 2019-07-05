package com.lesson6.controller;

import com.lesson6.entity.Filter;
import com.lesson6.entity.Flight;
import com.lesson6.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

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

    @RequestMapping(method = RequestMethod.GET, value = "/flight/most_popular_to", produces = "text/plain")
    public @ResponseBody
    String mostPopularTo() {
        try {
            return flightService.mostPopularTo().toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/flight/most_popular_from", produces = "text/plain")
    public @ResponseBody
    String mostPopularFrom() {
        try {
            return flightService.mostPopularFrom().toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/flight/flights_by_date", params = {"dateFrom", "dateTo", "cityFrom", "cityTo", "modelPlane"}, produces = "text/plain")
    public @ResponseBody
    String flightsByDate(
            @RequestParam(value = "dateFrom") String dateFrom,
            @RequestParam(value = "dateTo") String dateTo,
            @RequestParam(value = "cityFrom") String cityFrom,
            @RequestParam(value = "cityTo") String cityTo,
            @RequestParam(value = "modelPlane") String modelPlane) {
        try {
            Filter filter = new Filter();
            if (dateFrom != null && !dateFrom.isEmpty()) {
                filter.setDateFrom(new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom));
            }
            if (dateTo != null && !dateTo.isEmpty()) {
                filter.setDateTo(new SimpleDateFormat("yyyy-MM-dd").parse(dateTo));
            }
            if (cityFrom != null && !cityFrom.isEmpty()) {
                filter.setCityFrom(cityFrom);
            }
            if (cityTo != null && !cityTo.isEmpty()) {
                filter.setCityTo(cityTo);
            }
            if (modelPlane != null && !modelPlane.isEmpty()) {
                filter.setModelPlane(modelPlane);
            }

            return flightService.flightsByDate(filter).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/flight/flights_by_filter")
    public @ResponseBody
    String flightsByFilter(Filter filter) {
        try {
            return flightService.flightsByDate(filter).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
