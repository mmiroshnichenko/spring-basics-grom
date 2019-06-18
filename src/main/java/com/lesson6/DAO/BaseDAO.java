package com.lesson6.DAO;

import com.lesson6.exception.BadRequestException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


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
        T object = entityManager.find(this.typeOfT, id);
        if (object == null) {
            throw new BadRequestException("Error: incorrect " + this.typeOfT.getName() + " Id:" + id);
        }

        return object;
    }
}
