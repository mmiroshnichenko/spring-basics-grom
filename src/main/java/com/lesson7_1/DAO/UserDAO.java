package com.lesson7_1.DAO;

import com.lesson7_1.entity.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDAO extends BaseDAO<User> {
    public UserDAO() {
        super(User.class);
    }
}
