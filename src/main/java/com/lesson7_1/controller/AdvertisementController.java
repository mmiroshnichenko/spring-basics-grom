package com.lesson7_1.controller;

import com.lesson7_1.entity.Advertisement;
import com.lesson7_1.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class AdvertisementController extends BaseController {
    private AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/advertisement/save", produces = "text/plain")
    public @ResponseBody
    String save(HttpSession session, @RequestBody Advertisement advertisement) {
        try {
            checkAuthentication(session);

            return advertisementService.save(advertisement).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/advertisement/update", produces = "text/plain")
    public @ResponseBody
    String update(HttpSession session, @RequestBody Advertisement advertisement) {
        try{
            checkAuthentication(session);

            return advertisementService.update(advertisement).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/advertisement/delete", params = {"id"}, produces = "text/plain")
    public @ResponseBody
    String delete(HttpSession session, @RequestParam(value = "id") String id) {
        try {
            checkAuthentication(session);

            advertisementService.delete(Long.parseLong(id));
        } catch (Exception e) {
            return e.getMessage();
        }
        return "deleting ok";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/get", params = {"id"}, produces = "text/plain")
    public @ResponseBody
    String get(HttpSession session, @RequestParam(value = "id") String id) {
        try {
            checkAuthentication(session);

            return advertisementService.findById(Long.parseLong(id)).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
