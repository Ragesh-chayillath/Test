/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.service;

import com.outbottle.dao.UserDao;
import com.outbottle.model.Blog;
import com.outbottle.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ragesh.chayillath
 */
@Service
public class UserService {
   @Autowired UserDao userDao;
   
   public boolean registerUser(User user){
      return userDao.registerUser(user);
   }
      public User getRegisteredUser(User user){
      return userDao.getRegisteredUser(user);
   }

    public boolean addUserBlog(Blog blog,Integer user) {
        return userDao.addUserBlog(blog,user);
    }
    
    public List<Blog> getUserBlogs(User user){
        return userDao.getUserBlogs(user);
    }      
}
