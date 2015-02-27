package edu.ucla.cs.cs144;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;


public class SearchServlet extends HttpServlet implements Servlet {
       
    public SearchServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    	throws ServletException, IOException
    {

    	SearchResult[] results = AuctionSearchClient.basicSearch(request.getParameter("q"), 
    		Integer.parseInt(request.getParameter("numResultsToSkip")), 
    		Integer.parseInt(request.getParameter("numResultsToReturn")));

    	request.setAttribute("searchResults", results);

     //    PrintWriter printWriter = response.getWriter();
     //    printWriter.print("<!doctype html><html><body>");



     //    for (SearchResult searchResult : searchResults) {
     //    	printWriter.print("id: " + searchResult.getItemId() + ", name: " 
     //    			+ searchResult.getName() + "<br>");
     //    }
     //   	printWriter.print("</html></body>");
     //    printWriter.close();
     //     // your codes here


        RequestDispatcher requestDispatcher; 
		requestDispatcher = request.getRequestDispatcher("/searchResult.jsp");
		requestDispatcher.forward(request, response);


    }
}