/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.dao;

import com.outbottle.config.DbConnect;
import com.outbottle.model.Blog;
import com.outbottle.model.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ragesh.chayillath
 */
@Repository
public class UserDao {

    public boolean registerUser(User user) {

        DbConnect db = new DbConnect();
        String sql = "insert into `user` \n"
                + "(`user_name`,"
                + "`email`,"
                + "`password`"
                + ")"
                + "values"
                + "('" + user.getName() + "',"
                + "'" + user.getEmail() + "',"
                + "'" + user.getPassword() + "'"
                + ");";
        return db.executeUpdates(sql) > 0;
    }

    public User getRegisteredUser(User user) {

        DbConnect db = new DbConnect();
        String sql = "SELECT `user_id`,"
                + "	`user_name`,"
                + "	`email`,"
                + "	`password`"
                + "	 FROM"
                + "	`user`"
                + "WHERE email='" + user.getEmail() + "' AND PASSWORD='" + user.getPassword() + "'";

        ResultSet rs = db.execute(sql);
        try {
            if (rs != null && rs.next()) {
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("user_name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public boolean addUserBlog(Blog blog, Integer userId) {
        DbConnect db = new DbConnect();
        String sql = "INSERT INTO `blog`"
                + "(`blog_title`,"
                + "`blog_description`"
                + ")"
                + "VALUES"
                + "('" + blog.getBlog_title() + "',"
                + "'" + blog.getBlog_description() + "'"
                + ");";

        int blogId = db.executeUpdateAndReturnKey(sql);

        sql = "INSERT INTO `user_blog`"
                + "	(`user_id`,"
                + "	`blog_id`"
                + "	)"
                + "	VALUES"
                + "	('" + userId + "',"
                + "	'" + blogId + "'"
                + "	);";
        int result = db.executeUpdates(sql);
        return result > 0;
    }

    public List<Blog> getUserBlogs(User user) {
        List<Blog> blogs=new ArrayList<Blog>();
        DbConnect db = new DbConnect();
        String sql = "SELECT 	b.`blog_id`, "
                + "b.`blog_title`, "
                + "b.`blog_description` "
                + "FROM "
                + "`blog` b "
                + "INNER JOIN `user_blog` u ON u.blog_id=b.blog_id "
                + " WHERE u.user_id="+user.getUserId()+"";

        ResultSet rs = db.execute(sql);
        try {
            if (rs != null) {
                Blog blog;
                while (rs.next()) {
                  blog=new Blog();
                  blog.setBlogId(rs.getInt("blog_id"));
                  blog.setBlog_description(rs.getString("blog_description"));
                  blog.setBlog_title(rs.getString("blog_title"));  
                  blogs.add(blog);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return blogs;
    }

}
