package com.lesson5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class ItemController {

    private ItemDAO dao;

    @Autowired
    public ItemController(ItemDAO dao) {
         this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/item/save", produces = "text/plain")
    public @ResponseBody
    String save(@RequestBody Item item) {
        try {
            dao.save(item);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "ok";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/item/update", produces = "text/plain")
    public @ResponseBody
    String update(@RequestBody Item item) {
        try{
            Item itemDb = dao.findById(item.getId());
            itemDb.setDescription(item.getDescription());
            dao.update(itemDb);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "ok";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/item/delete", params = {"id"}, produces = "text/plain")
    public @ResponseBody
    String delete(@RequestParam(value = "id") String id) {
        try {
            Item itemDb = dao.findById(Long.parseLong(id));
            dao.delete(itemDb);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "ok";
    }
}