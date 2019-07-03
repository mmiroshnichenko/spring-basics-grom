package com.lesson7_1.DAO;

import com.lesson7_1.entity.Advertisement;
import com.lesson7_1.entity.Filter;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class AdvertisementDAO extends BaseDAO<Advertisement> {
    public AdvertisementDAO() {
        super(Advertisement.class);
    }

    public List<Advertisement> list(Filter filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Advertisement> criteriaQuery = builder.createQuery(Advertisement.class);
        Root<Advertisement> root = criteriaQuery.from(Advertisement.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.greaterThan(root.get("dateExpired"), new Date()));
        boolean filterExec = false;
        if (filter.getKeyWord() != null) {
            predicates.add(builder.like(root.get("description"), "%" + filter.getKeyWord() + "%"));
            filterExec = true;
        }
        if (filter.getCity() != null) {
            predicates.add(builder.equal(root.get("city"), filter.getCity()));
            filterExec = true;
        }
        if (filter.getCategory() != null) {
            predicates.add(builder.equal(root.get("category"), filter.getCategory().toString()));
            filterExec = true;
        }
        if (filter.getSubcategory() != null) {
            predicates.add(builder.equal(root.get("subcategory"), filter.getSubcategory().toString()));
            filterExec = true;
        }
        criteriaQuery.orderBy(builder.desc(root.get("dateCreated")));

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        TypedQuery<Advertisement> query = entityManager.createQuery(criteriaQuery);
        if (!filterExec) {
            query.setMaxResults(100);
        }

        return query.getResultList();
    }
}