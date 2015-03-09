package edu.ucla.cs.cs144;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kevin on 3/8/15.
 */
public class ConfirmationServlet extends HttpServlet implements Servlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        HttpSession session = request.getSession();
        String creditCardNumber = request.getParameter("creditCardNumber");
        PrintWriter writer = response.getWriter();
        writer.println(creditCardNumber);
        writer.close();
    }
}
