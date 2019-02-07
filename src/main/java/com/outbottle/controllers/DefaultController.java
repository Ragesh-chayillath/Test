/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.controllers;

import com.outbottle.model.Blog;
import com.outbottle.model.User;
import com.outbottle.service.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author john
 */
@Controller
public class DefaultController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelMap map) {
        return new ModelAndView("index", "user", new User());
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelMap map) {
        return new ModelAndView("index", "user", new User());
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(ModelMap map) {
        return new ModelAndView("registration", "user", new User());
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public ModelAndView loginUser(@ModelAttribute("user") User user, BindingResult result, ModelMap model, HttpServletRequest request) {
        user = userService.getRegisteredUser(user);
        if (user != null && user.getUserId() > 0) {
            List<Blog> userBlogs = userService.getUserBlogs(user);
            Map map = new HashMap();
            map.put("user", user);
            map.put("blog", new Blog());
            map.put("blogs", userBlogs);
            request.getSession().setAttribute("sessionUser", user);
            return new ModelAndView("home", map);
        } else {
            model.addAttribute("msg", "Login Failed. Retry.");
            return new ModelAndView("index", "user", new User());
        }
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, BindingResult result, ModelMap model) {
        boolean success = userService.registerUser(user);
        if (success) {
            model.addAttribute("msg", "Registration Successfull.");
            return "index";
        } else {
            model.addAttribute("msg", "Registration Failed. Retry.");
        }
        return "registration";
    }

    @RequestMapping(value = "/addBlog", method = RequestMethod.POST)
    public ModelAndView loginUser(@ModelAttribute("blog") Blog blog, BindingResult result, ModelMap model, HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("sessionUser");
        boolean success = userService.addUserBlog(blog, sessionUser.getUserId());
        List<Blog> userBlogs = userService.getUserBlogs(sessionUser);
        Map map = new HashMap();
        map.put("user", sessionUser);
        map.put("blog", new Blog());
        map.put("blogs", userBlogs);
        if (success) {
            model.addAttribute("msg", "Blog created Successfully.");
            return new ModelAndView("home", map);
        } else {
            model.addAttribute("msg", "Blog Creation Failed. Retry.");
            return new ModelAndView("home", map);
        }
    }
}
