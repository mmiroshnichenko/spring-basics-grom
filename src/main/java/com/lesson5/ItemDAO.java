package com.lesson5;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ItemDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public Item save(Item item) {
        entityManager.persist(item);

        return item;
    }

    public Item update(Item item) {
        entityManager.merge(item);

        return item;
    }

    public void delete(Item item) {
        entityManager.detach(item);
    }

    public Item findById(long id) throws BadRequestException {
        Item item = entityManager.find(Item.class, id);
        if (item == null) {
            throw new BadRequestException("Error: incorrect itemId:" + id);
        }

        return item;
    }
}