package exercise;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import exercise.servlet.WelcomeServlet;
import exercise.servlet.UsersServlet;

public class App {

    private static int getPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.valueOf(port);
        }
        return 5000;
    }

    public static Tomcat getApp(int port) {
        Tomcat app = new Tomcat();

        app.setBaseDir(System.getProperty("java.io.tmpdir"));
        app.setPort(port);

        Context ctx = app.addContext("", new File(".").getAbsolutePath());

        app.addServlet(ctx, WelcomeServlet.class.getSimpleName(), new WelcomeServlet());
        ctx.addServletMappingDecoded("", WelcomeServlet.class.getSimpleName());

        // BEGIN
        app.addServlet(ctx, UsersServlet.class.getSimpleName(), new UsersServlet());
        ctx.addServletMappingDecoded("/users/*", UsersServlet.class.getSimpleName());
        // END

        return app;
    }

    public static void main(String[] args) throws LifecycleException, IOException {
        Tomcat app = getApp(getPort());
        app.start();
        app.getServer().await();
//        System.out.println("Hello");
//        String filePath = "src/main/resources/users.json";
//        Path absolutePath = Paths.get(filePath).toAbsolutePath().normalize();
//        String jsonUserArrayString = Files.readString(absolutePath);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<User> list = objectMapper.readValue(jsonUserArrayString, new TypeReference<>() {
//        });
//
//        for (User user : list) {
//            System.out.println(user.getFirstName());
//        }
    }
}
