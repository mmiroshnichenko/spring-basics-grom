package com.lesson7_1.DAO;

import com.lesson7_1.entity.Operator;
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
        startFilter();

        addPredicates(root.get("userName"), userName, Operator.EQ);
        addPredicates(root.get("password"), password, Operator.EQ);

        return getSingleResult();
    }
}
