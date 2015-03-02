package edu.ucla.cs.cs144;

import java.io.IOException;
import java.net.HttpURLConnection;
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
        
    	try {
	        String forwardURLString = request.getParameter("fwdurl");
	        if(forwardURLString == null)
	        	forwardURLString="http://localhost:1448/eBay/";

	        URL forwardURL = new URL(forwardURLString);
	        HttpURLConnection forwardConnection = (HttpURLConnection) forwardURL.openConnection();

	        for (String key : forwardConnection.getHeaderFields().keySet()) {
	        	response.setHeader(key, forwardConnection.getHeaderField(key));
	        }

	        InputStream in = forwardConnection.getInputStream();
	        OutputStream out = response.getOutputStream();


	        int length = 0;
	        byte[] buffer = new byte[1024];
	        while( (length = in.read(buffer)) > 0) 
	        	out.write(buffer, 0, length);
	        

	        in.close();
	        out.close();
		
		} catch (Exception e){

		}

    }
}
