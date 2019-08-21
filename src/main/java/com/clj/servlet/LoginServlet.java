package com.clj.servlet;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            new Driver();
            String url = "jdbc:mysql:///?user=root&password=root";
            Connection connection = DriverManager.getConnection(url);
            String sql="select * from unicorn_db.user where email=? and password=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(email + ","+password);

            if (resultSet.next()){
                String username = resultSet.getString("username");
                req.getSession().setAttribute("username",username);
                resp.sendRedirect("/queryArticle");
            }else {
                req.setAttribute("message","Invalid Email or Password.");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
