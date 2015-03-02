package edu.ucla.cs.cs144;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProxyServlet extends HttpServlet implements Servlet {
       
    public ProxyServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    	throws ServletException, IOException
    {
        PrintWriter writer = response.getWriter();

        try {
            writer.print("before");
	        String forwardURLString="http://google.com/complete/search?output=toolbar&q=" + request.getParameter("q");
	        URL forwardURL = new URL(forwardURLString);
	        HttpURLConnection forwardConnection = (HttpURLConnection) forwardURL.openConnection();
            writer.print("after");

	        for (String key : forwardConnection.getHeaderFields().keySet()) {
	        	response.setHeader(key, forwardConnection.getHeaderField(key));
	        }

            BufferedReader reader = new BufferedReader(new InputStreamReader(forwardConnection.getInputStream()));

            String responseString = "testing";
            String line;
            while ((line = reader.readLine()) != null) {
                responseString += line;
            }
            reader.close();

            writer.print(responseString);
		} catch (Exception e){
            writer.print("exception");
		}
        writer.close();

    }
}
