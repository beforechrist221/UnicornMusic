package com.clj.servlet;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/remove")
public class RemoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        try {
            new Driver();
            String url = "jdbc:mysql:///?user=root&password=root";
            Connection connection = DriverManager.getConnection(url);
            String sql = "delete from unicorn_db.article where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            resp.sendRedirect("/queryArticle");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
