package edu.ucla.cs.cs144;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by kevin on 3/8/15.
 */
public class ConfirmationServlet extends HttpServlet implements Servlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Item item = (Item) session.getAttribute("item");
        String creditCardNumber = request.getParameter("creditCardNumber");
        Date time = new Date(session.getLastAccessedTime());

        request.setAttribute("item", item);
        request.setAttribute("time", time);
        request.setAttribute("creditCardNumber", creditCardNumber);

        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher("/cardConfirmation.jsp");
        requestDispatcher.forward(request, response);
        
        // PrintWriter writer = response.getWriter();
        // writer.println(creditCardNumber);
        // writer.close();
    }
}
