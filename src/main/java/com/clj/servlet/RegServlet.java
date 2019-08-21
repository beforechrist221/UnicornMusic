package com.clj.servlet;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

//@WebServlet(urlPatterns = "/reg")
public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email").trim();
        String url = "jdbc:mysql:///?user=root&password=root";

        try {
            new Driver();

            Connection connection = DriverManager.getConnection(url);
            String sql = "select * from unicorn_db.user where email = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                req.setAttribute("message","Email already existed.");
                req.getRequestDispatcher("reg.jsp").forward(req,resp);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String username = req.getParameter("username").trim();
        String password = req.getParameter("password");

//        System.out.println(email + ","+username +","+password);
        try {
            new Driver();

            Connection connection = DriverManager.getConnection(url);
            String sql = "insert into unicorn_db.user value(null,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,password);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            System.out.println(email + ","+username +","+password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("login.jsp");
    }
}
