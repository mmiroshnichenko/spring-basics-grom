package com.lesson7_1.service;

import com.lesson7_1.DAO.UserDAO;
import com.lesson7_1.entity.User;
import com.lesson7_1.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User authenticateUser(String userName, String password) throws BadRequestException {
        validateAuthentication(userName, password);
        return userDAO.authenticateUser(userName, password);
    }

    public User save(User user) throws Exception {
        validate(user);

        return userDAO.save(user);
    }

    public User update(User user) throws Exception {
        validate(user);

        User userDb = userDAO.findById(user.getId());
        userDb.setFirstName(user.getFirstName());
        userDb.setLastName(user.getLastName());
        userDb.setUserName(user.getUserName());
        userDb.setPassword(user.getPassword());

        return userDAO.update(userDb);
    }

    public void delete(long id) throws Exception {
        User user = userDAO.findById(id);

        userDAO.delete(user);
    }

    public User findById(long id) throws Exception {
        return userDAO.findById(id);
    }

    private void validate(User user) throws BadRequestException {
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new BadRequestException("Error: first name is required");
        }

        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new BadRequestException("Error: last name is required");
        }

        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            throw new BadRequestException("Error: user name is required");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new BadRequestException("Error: password is required");
        }
    }

    private void validateAuthentication(String userName, String password) throws BadRequestException {
        if (userName == null || userName.isEmpty()) {
            throw new BadRequestException("Error: user name is required");
        }

        if (password == null || password.isEmpty()) {
            throw new BadRequestException("Error: password is required");
        }
    }
}
