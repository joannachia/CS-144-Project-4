package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemServlet extends HttpServlet implements Servlet {
       
    public ItemServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String results = AuctionSearchClient.getXMLDataForItemId(request.getParameter("id"));

        request.setAttribute("itemId", results);		//string
        request.setAttribute("itemName", results);		//string
        request.setAttribute("categories", results);	//array of strings
        request.setAttribute("currently", results);		//string
        request.setAttribute("buyPrice", results);		//string
        request.setAttribute("firstBid", results);		//string
        request.setAttribute("num_bids", results);		//string
        request.setAttribute("bids", resulst);			//array of bid types
        request.setAttribute("location", location);		//location type
        request.setAttribute("country", country);		//string
        request.setAttribute("starts", starts);			//string
        request.setAttribute("ends", ends);				//string
        request.setAttribute("seller", seller);			//seller type



        RequestDispatcher requestDispatcher;
		requestDispatcher = request.getRequestDispatcher("/itemResult.jsp");
		requestDispatcher.forward(request, response);

    }
}
