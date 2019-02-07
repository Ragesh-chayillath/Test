/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author student
 */
public class DbConnect {

    static Connection conn = null;
    static final String url = "jdbc:mysql://localhost:3306/";
    static final String dbName = "blogger";
    static final String driver = "com.mysql.jdbc.Driver";
    static final String userName = "root";
    static final String password = "root";

    static {
        try {
            Class.forName(driver).newInstance();
            System.out.println("driver loaded sucessfully");
            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("connect to the data base");
        } catch (ClassNotFoundException ce) {
            System.out.println("driver loading failed" + ce);
        } catch (Exception e) {
            System.out.println("error in connection to database");
        }
    }

    public ResultSet execute(String sql) {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception sqx) {
            System.out.println(" error in execution" + sqx);
        }
        return rs;
    }

    public int executeUpdates(String sql) {
        int result = 0;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);

        } catch (SQLException sqx) {
            System.out.println("error:" + sqx);
        }
        return result;
    }

    public int executeUpdateAndReturnKey(String sql) {
        int result = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int affected = pstmt.executeUpdate(sql);

            if (affected == 1) {
                // get candidate id
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    result = rs.getInt(1);
                }

            }
        } catch (SQLException sqx) {
            System.out.println("error:" + sqx);
        }
        return result;
    }
}
