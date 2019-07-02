package com.lesson7_1.controller;

import com.lesson7_1.entity.User;
import com.lesson7_1.exception.BadRequestException;
import com.lesson7_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/login", produces = "text/plain")
    public @ResponseBody
    String authenticate(HttpSession session, String username, String password) {
        try {
            User user = userService.authenticateUser(username, password);
            if (user != null) {
                session.setAttribute("MEMBER", user);
            } else {
                return "Invalid username or password";
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return "success";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/logout", produces = "text/plain")
    public @ResponseBody
    String logout(HttpSession session) {
        try {
            checkAuthentication(session);

            session.removeAttribute("MEMBER");
        } catch (Exception e) {
            return e.getMessage();
        }

        return "success";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/save", produces = "text/plain")
    public @ResponseBody
    String save(HttpSession session, @RequestBody User user) {
        try {
            checkAuthentication(session);

            return userService.save(user).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user/update", produces = "text/plain")
    public @ResponseBody
    String update(HttpSession session, @RequestBody User user) {
        try{
            checkAuthentication(session);

            return userService.update(user).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/delete", params = {"id"}, produces = "text/plain")
    public @ResponseBody
    String delete(HttpSession session, @RequestParam(value = "id") String id) {
        try {
            checkAuthentication(session);

            userService.delete(Long.parseLong(id));
        } catch (Exception e) {
            return e.getMessage();
        }
        return "deleting ok";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/get", params = {"id"}, produces = "text/plain")
    public @ResponseBody
    String get(HttpSession session, @RequestParam(value = "id") String id) {
        try {
            checkAuthentication(session);

            return userService.findById(Long.parseLong(id)).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private void checkAuthentication(HttpSession session) throws BadRequestException {
        if (session.getAttribute("MEMBER") == null) {
            throw new BadRequestException("Error: user is not authenticated");
        }
    }
}
