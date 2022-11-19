package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        List<String> companies = getCompanies();
        String query = request.getQueryString();
        String param = request.getParameter("search");
        PrintWriter out = response.getWriter();

        if  (query == null || param.equals("")) {
            for (String company : companies) {
                out.println(company);
            }
        }

        if (!param.equals("")) {
            List<String> filteredCompanies = new ArrayList<>();

            for (String company : companies) {
                if (company.contains(param)) {
                    filteredCompanies.add(company);
                }
            }

            if (filteredCompanies.size() == 0) {
                out.println("Companies not found");
            } else {
                for (String company : filteredCompanies) {
                    out.println(company);
                }
            }
        }
        // END
    }
}
