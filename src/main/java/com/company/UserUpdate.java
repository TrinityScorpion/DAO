package com.company;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/edit")
public class UserUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        getServletContext().getRequestDispatcher("/users/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String name = request.getParameter("userName1");
        String email = request.getParameter("userEmail1");
        String password = request.getParameter("userPassword1");

        UserDAO userDao = new UserDAO();
        String userId = request.getParameter("id");
        User userToUpdate = userDao.read(Integer.parseInt(userId));
        userToUpdate.setUserName(name);
        userToUpdate.setEmail(email);
        userToUpdate.setPassword(password);
        userDao.update(userToUpdate);
        response.sendRedirect("/user/list");
    }
}
