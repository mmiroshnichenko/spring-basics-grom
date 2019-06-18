package com.lesson6.controller;

import com.lesson6.entity.Plane;
import com.lesson6.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlaneController {
    private PlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/plane/save", produces = "text/plain")
    public @ResponseBody
    String save(@RequestBody Plane plane) {
        try {
            return planeService.save(plane).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/plane/update", produces = "text/plain")
    public @ResponseBody
    String update(@RequestBody Plane plane) {
        try{
            return planeService.update(plane).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/plane/delete", params = {"id"}, produces = "text/plain")
    public @ResponseBody
    String delete(@RequestParam(value = "id") String id) {
        try {
            planeService.delete(Long.parseLong(id));
        } catch (Exception e) {
            return e.getMessage();
        }
        return "deleting ok";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/plane/get", params = {"id"}, produces = "text/plain")
    public @ResponseBody
    String get(@RequestParam(value = "id") String id) {
        try {
            return planeService.findById(Long.parseLong(id)).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
