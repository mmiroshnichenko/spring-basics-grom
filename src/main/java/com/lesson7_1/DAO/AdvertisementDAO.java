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

        startFilter();

        addPredicates(root.get("dateExpired"), new Date(), Operator.GT);
        addPredicates(root.get("description"), filter.getKeyWord(), Operator.LIKE);
        addPredicates(root.get("city"), filter.getCity(), Operator.EQ);
        addPredicates(root.get("category"), filter.getCategory().toString(), Operator.EQ);
        addPredicates(root.get("subcategory"), filter.getSubcategory().toString(), Operator.EQ);

        criteriaQuery.orderBy(builder.desc(root.get("dateCreated")));

        return getFilteredList(100);
    }
}