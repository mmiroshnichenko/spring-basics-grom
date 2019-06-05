//package com.lesson3.hw.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.lesson3.hw.entity.Storage;
//import com.lesson3.hw.service.StorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;
//
//@Controller
//public class StorageController {
//    private StorageService storageService;
//
//    @Autowired
//    public StorageController(StorageService storageService) {
//        this.storageService = storageService;
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/storage", params = {"id"}, produces = "text/plain")
//    public @ResponseBody
//    String findById(@RequestParam(value = "id") String id) {
//        try {
//            return storageService.findById(Long.parseLong(id)).toString();
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/storage", produces = "text/plain")
//    public @ResponseBody
//    String save(HttpServletRequest req) {
//        try(ServletInputStream stream = req.getInputStream()) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            Storage storage = new Storage();
//            storage = objectMapper.readValue(stream, storage.getClass());
//            return storageService.save(storage).toString();
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "/storage", produces = "text/plain")
//    public @ResponseBody
//    String update(HttpServletRequest req) {
//        try(ServletInputStream stream = req.getInputStream()) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            Storage storage = new Storage();
//            storage = objectMapper.readValue(req.getInputStream(), storage.getClass());
//            return storageService.update(storage).toString();
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/storage", params = {"id"}, produces = "text/plain")
//    public @ResponseBody
//    String delete(@RequestParam(value = "id") String id) {
//        try {
//            storageService.delete(Long.parseLong(id));
//
//            return "Storage was deleted";
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//}
