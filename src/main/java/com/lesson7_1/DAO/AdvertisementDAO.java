package com.lesson7_1.DAO;

import com.lesson7_1.entity.Advertisement;
import com.lesson7_1.entity.Filter;
import com.lesson7_1.entity.Operator;
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

        addPredicates(predicates, builder, root.get("dateExpired"), new Date(), Operator.GT);
        addPredicates(predicates, builder, root.get("description"), filter.getKeyWord(), Operator.LIKE);
        addPredicates(predicates, builder, root.get("city"), filter.getCity(), Operator.EQ);
        addPredicates(predicates, builder, root.get("category"), filter.getCategory().toString(), Operator.EQ);
        addPredicates(predicates, builder, root.get("subcategory"), filter.getSubcategory().toString(), Operator.EQ);

        criteriaQuery.orderBy(builder.desc(root.get("dateCreated")));

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(criteriaQuery).setMaxResults(100).getResultList();
    }
}