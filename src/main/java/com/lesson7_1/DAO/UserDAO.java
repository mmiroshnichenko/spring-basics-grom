package com.lesson7_1.DAO;

import com.lesson7_1.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDAO extends BaseDAO<User> {
    public UserDAO() {
        super(User.class);
    }

    public User authenticateUser(String userName, String password) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(root.get("userName"), userName));
        predicates.add(builder.equal(root.get("password"), password));

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
