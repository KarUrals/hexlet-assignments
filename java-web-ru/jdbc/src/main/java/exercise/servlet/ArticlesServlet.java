package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;


public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String offset = request.getParameter("page");
        if (offset == null) {
            offset = "1";
        }
        int currentPage = Integer.parseInt(offset);
        List<Map<String, String>> articles = new ArrayList<>();

        String query = "SELECT id, title FROM articles ORDER BY id LIMIT 10 OFFSET ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(offset) * 10 - 10);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                articles.add(Map.of(
                        "id", rs.getString("id"),
                        "title", rs.getString("title")
                ));
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        int nextPage = currentPage + 1;
        int prevPage = currentPage - 1;
        if (prevPage < 1) {
            prevPage = 1;
        }
        request.setAttribute("prev", prevPage);
        request.setAttribute("next", nextPage);
        request.setAttribute("articles", articles);
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String id = getId(request);
        if (Integer.parseInt(id) > 100) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String query = "SELECT * FROM articles WHERE id = ?";

        String title = null;
        String body = null;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(id));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                title = rs.getString(2);
                body = rs.getString(3);
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        request.setAttribute("title", title);
        request.setAttribute("body", body);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
