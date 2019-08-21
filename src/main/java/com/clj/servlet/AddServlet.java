package com.clj.servlet;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("title").trim();
        String content = req.getParameter("content");
        String url = "jdbc:mysql:///?user=root&password=root";

        try {
            new Driver();
            Connection connection = DriverManager.getConnection(url);
            String sql = "insert into unicorn_db.article value(null,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,content);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            System.out.println(title + ","+ content);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/queryArticle");
    }
}
