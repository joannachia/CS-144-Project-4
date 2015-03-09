package edu.ucla.cs.cs144;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kevin on 3/8/15.
 */
public class CreditCardServlet extends HttpServlet implements Servlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Item item = (Item) session.getAttribute("item");
        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher("/creditCardInfo.jsp");
        requestDispatcher.forward(request, response);
    }
}
