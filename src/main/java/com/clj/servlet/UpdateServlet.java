package com.clj.servlet;

import com.clj.model.Article;
import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/update")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.valueOf(req.getParameter("id"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        try {
            new Driver();
            String url = "jdbc:mysql:///?user=root&password=root";
            Connection connection = DriverManager.getConnection(url);
            String sql = "update unicorn_db.article set "+
                    "title = ?,"+
                    "content = ?"+
                    "where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,content);
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();

            resp.sendRedirect("/queryArticle");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
