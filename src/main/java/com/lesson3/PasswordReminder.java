package com.lesson3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PasswordReminder {
    private DbConnector dbConnector;

    @Autowired
    public PasswordReminder(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void sendPassword() {
        //logic
    }
}
