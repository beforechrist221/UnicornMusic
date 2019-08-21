package com.clj.servlet;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/checkEmail")
public class CheckEmailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json");
//        System.out.println(req.getParameter("email"));
        String email = req.getParameter("email").trim();
        try {
            new Driver();
            String url = "jdbc:mysql:///?user=root&password=root";
            Connection connection = DriverManager.getConnection(url);
            String sql = "select * from unicorn_db.user where email = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json;charset = utf-8");
                String data = "{\"isEmailExisted\":true}";
                resp.getWriter().write(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
