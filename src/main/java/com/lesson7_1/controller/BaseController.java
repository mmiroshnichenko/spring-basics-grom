package com.lesson7_1.controller;

import com.lesson7_1.exception.BadRequestException;

import javax.servlet.http.HttpSession;

public class BaseController {
    protected void checkAuthentication(HttpSession session) throws BadRequestException {
        if (session.getAttribute("MEMBER") == null) {
            throw new BadRequestException("Error: user is not authenticated");
        }
    }
}
