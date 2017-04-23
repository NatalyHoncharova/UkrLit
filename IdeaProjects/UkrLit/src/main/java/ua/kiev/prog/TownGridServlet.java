package ua.kiev.prog;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.kiev.prog.NavigateTownGridServlet;
import ua.kiev.prog.model.TownServices;

public class TownGridServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionValue = "";
        String townidselected = "";
        Enumeration en = request.getParameterNames();
        while (en.hasMoreElements()) {
            townidselected = (String) en.nextElement();
            actionValue = request.getParameter(townidselected);
        }
        if (townidselected.trim().equalsIgnoreCase("")) {
            request.setAttribute("errorMessage", "Cannot find town id");
            request.getRequestDispatcher("/table.jsp").forward(request, response);
        } else {
            if (actionValue.contains("Remove")) {
                TownServices townService = new TownServices();
                if (townService.deleteTown(townidselected)) {
                    request.setAttribute("successMessage", "Successfully delete town: <b>" + townidselected + "</b>");
                    request.getRequestDispatcher("/table.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Failed to delete town: <b>" + townidselected + "</b>, please try again");
                    request.getRequestDispatcher("/table.jsp").forward(request, response);
                }
            } else {
                //process redirect to modify user page with query string user id
                //redirect to modify page
            }
        }
    }
}