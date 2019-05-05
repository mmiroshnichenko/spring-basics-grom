package com.lesson2.hw2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesson2.hw2.entity.Item;
import com.lesson2.hw2.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping(method = RequestMethod.GET, value = "/item", params = {"id"}, produces = "text/plain")
    public @ResponseBody
    String findById(@RequestParam(value = "id") String id) {
        try {
            return itemService.findById(Long.parseLong(id)).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/item", produces = "text/plain")
    public @ResponseBody
    String save(HttpServletRequest req) {
        try(ServletInputStream stream = req.getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            Item item = new Item();
            item = objectMapper.readValue(stream, item.getClass());
            return itemService.save(item).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/item", produces = "text/plain")
    public @ResponseBody
    String update(HttpServletRequest req) {
        try(ServletInputStream stream = req.getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            Item item = new Item();
            item = objectMapper.readValue(req.getInputStream(), item.getClass());
            return itemService.update(item).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/item", params = {"id"}, produces = "text/plain")
    public @ResponseBody
    String delete(@RequestParam(value = "id") String id) {
        try {
            Item item = itemService.findById(Long.parseLong(id));
            itemService.delete(item);

            return "Item was deleted";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
