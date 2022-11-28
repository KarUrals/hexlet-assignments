package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.List;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.User;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<User> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        String filePath = "src/main/resources/users.json";
        Path absolutePath = Paths.get(filePath).toAbsolutePath().normalize();
        String jsonUserArrayString = Files.readString(absolutePath);

        ObjectMapper objectMapper = new ObjectMapper();
        List<User> listUser = objectMapper.readValue(jsonUserArrayString, new TypeReference<>() {});
        return listUser;
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        StringBuilder body = new StringBuilder();
        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>                        
                        <meta charset=\"UTF-8\">
                        <title>Example application | User</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
                    </head>
                    <body>
                        <table>
                """);



        for (User user : getUsers()) {
            body.append("<tr><td>" + user.getId() +
                    "</td><td><a href=\"/users/" + user.getId() + "\">" + user.getFirstName() + " " + user.getLastName() + "</a></td></tr>");
        }

        body.append("""
                    </body>
                </html>
                """);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(body.toString());
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        StringBuilder body = new StringBuilder();
        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>                        
                        <meta charset=\"UTF-8\">
                        <title>Example application | User</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
                    </head>
                    <body>
                        <table>
                """);
        boolean userFound = false;
        for (User user : getUsers()) {
            if (user.getId().equals(id)) {
                body.append("<tr><td>" + user.getId() +
                        "</td><td>" + user.getFirstName() + " " + user.getLastName() +
                        "</td><td>" + user.getEmail() + "</td></tr>");
                userFound = true;
            }
        }

        body.append("""
                    </body>
                </html>
                """);

        if (!userFound) {
            response.sendError(404, "Not found");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(body.toString());
        // END
    }
}
