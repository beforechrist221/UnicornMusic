package com.clj.servlet;

import com.clj.model.Article;
import com.mysql.jdbc.Driver;

import javax.naming.ldap.PagedResultsControl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/edit")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));

        try {
            new Driver();
            String url = "jdbc:mysql:///?user=root&password=root";
            Connection connection = DriverManager.getConnection(url);
            String sql = "select * from unicorn_db.article where id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Article article = new Article(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("content")
            );

            req.getSession().setAttribute("article",article);
            resp.sendRedirect("edit.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
