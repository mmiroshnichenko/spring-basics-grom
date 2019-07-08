package com.lesson6.DAO;

import com.lesson6.exception.BadRequestException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


public class BaseDAO<T> {
    private Class<T> typeOfT;

    CriteriaBuilder builder;
    CriteriaQuery<T> criteriaQuery;
    Root<T> root;
    List<Predicate> predicates;

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

    public T findById(long id) {
        return entityManager.find(this.typeOfT, id);
    }

    protected void startFilter() {
        builder = entityManager.getCriteriaBuilder();
        criteriaQuery = builder.createQuery(this.typeOfT);
        root = criteriaQuery.from(this.typeOfT);
        predicates = new ArrayList<>();
    }

    protected void addPredicates(Path path, Object filterValue, Operator operator) {
        if (filterValue == null) {
            return;
        }

        switch (operator) {
            case EQ:
                predicates.add(builder.equal(path, filterValue));
                break;
            case NE:
                predicates.add(builder.notEqual(path, filterValue));
                break;
            case LIKE:
                predicates.add(builder.like(path, "%" + filterValue + "%"));
                break;
            case LT:
                predicates.add(builder.lessThan(path, (Comparable) filterValue));
                break;
            case GT:
                predicates.add(builder.greaterThan(path, (Comparable) filterValue));
                break;
            case LTE:
                predicates.add(builder.lessThanOrEqualTo(path, (Comparable) filterValue));
                break;
            case GTE:
                predicates.add(builder.greaterThanOrEqualTo(path, (Comparable) filterValue));
                break;
        }
    }

    protected List<T> getFilteredList()
    {
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
