package com.company;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = DbUtil.connect();
        User newUser = new UserDAO();
        UserDAO userDao = new UserDAO();
        UserDAO userDao1 = new UserDAO();

        newUser.setUserName("maniusxxx");
        newUser.setEmail("manifYFVHGdsk@lgchem.com");
        newUser.setPassword("dfdssdff");

        userDao.setUserName("userdao");
        userDao.setEmail("userdao@wp.pl");
        userDao.setPassword("dadasdadasdasd");

        userDao1.setUserName("userdao1dgsdgddf");
        userDao1.setEmail("userdao1@sfdsfdswp.pl");
        userDao1.setPassword("sfsdfsdfsdfdsfdsfsfsfsfdf");
        userDao.update(userDao1);
        //userDao.read(2);
        //userDao.create(userDao1);
        //System.out.println(userDao.read(21));
        //userDao.printData();
//        userDao.remove(21);
    }

}