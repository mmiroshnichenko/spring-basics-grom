package com.lesson2.hw2.service;

import com.lesson2.hw2.DAO.ItemDAO;
import com.lesson2.hw2.entity.Item;
import com.lesson2.hw2.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ItemService {
    @Autowired
    private ItemDAO itemDAO;

    public Item save(Item item) throws Exception {
        if (item.getId() != 0) {
            throw new BadRequestException("Error: incorrect request. itemId(id:" + item.getId() + ")");
        }
        item.setDateCreated(new Date());
        item.setLastUpdatedDate(new Date());
        return itemDAO.save(item);
    }

    public Item update(Item item) throws Exception {
        Item itemForUpdate = findById(item.getId());
        item.setDateCreated(itemForUpdate.getDateCreated());
        item.setLastUpdatedDate(new Date());

        return itemDAO.update(item);
    }

    public void delete(Item item) throws Exception {
        itemDAO.delete(item);
    }

    public Item findById(long itemId) throws Exception {
        if (itemId <= 0) {
            throw new BadRequestException("Error: incorrect itemId(id:" + itemId + ")");
        }

        Item item = itemDAO.findById(itemId);
        if (item == null) {
            throw new BadRequestException("Error: item with id:" + itemId + " has not found");
        }
        return item;
    }
}
