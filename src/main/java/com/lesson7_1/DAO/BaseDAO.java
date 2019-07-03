package com.lesson7_1.DAO;

import com.lesson7_1.exception.BadRequestException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseDAO<T> {
    private Class<T> typeOfT;

    @PersistenceContext
    protected EntityManager entityManager;

    public BaseDAO(Class<T> typeOfT) {
        this.typeOfT = typeOfT;
    }

    public T save(T object) {
        entityManager.persist(object);

        return object;
    }

    public T update(T object) {
        entityManager.merge(object);

        return object;
    }

    public void delete(T object) {
        entityManager.remove(entityManager.contains(object) ? object : entityManager.merge(object));
    }

    public T findById(long id) throws BadRequestException {
        return entityManager.find(this.typeOfT, id);
    }
}
