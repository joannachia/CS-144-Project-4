package edu.ucla.cs.cs144;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;



/**
 * Created by kevin on 3/8/15.
 */
public class CreditCardServlet extends HttpServlet implements Servlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        Item item = (Item) session.getAttribute("item");
        request.setAttribute("confirmationUrl", "https://" + request.getServerName() + ":8443/eBay/confirmation");
        request.setAttribute("item",item);
        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher("/creditCardInfo.jsp");
        requestDispatcher.forward(request, response);

//        PrintWriter writer = response.getWriter();
//        writer.println("https://" + request.getServerName() + ":8443/eBay/confirmation");
//        writer.close();

    }
}
