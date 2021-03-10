package com.company;

import entity.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
            Connection connection = DbUtil.connect();
            User user = new User();
            User userDao = new UserDAO();
            userDao.setUserName("Ada");
            user.setEmail("aaawg@o2.pl");
            user.setPassword("krolozloty");


    }
}
