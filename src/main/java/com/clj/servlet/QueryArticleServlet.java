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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/queryArticle")
public class QueryArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setCharacterEncoding("UTF-8");
        List<Article> articles = new ArrayList<>();
        try {
            new Driver();
            String url = "jdbc:mysql:///?user=root&password=root";
            Connection connection = DriverManager.getConnection(url);
            String sql="select * from unicorn_db.article;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

           while (resultSet.next()){
               int id = resultSet.getInt("id");
               String title = resultSet.getString("title");
               String content = resultSet.getString("content");
               Article article = new Article(id, title, content);
//               System.out.println(id+","+title+","+content);
               articles.add(article);
//               System.out.println(articles);
           }
           resultSet.close();
           preparedStatement.close();
           connection.close();
           req.getSession().setAttribute("articles",articles);
//           System.out.println(articles);

           resp.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
