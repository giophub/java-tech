package com.giophub.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class SampleServletWithParameters
 */
public class SampleServletWithParameters
		extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(SampleServletWithParameters.class);
       
    private String name;
    private String city;
    
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SampleServletWithParameters() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		this.name = request.getParameter("name");
		this.city = request.getParameter("city");
		LOG.info("Param name: {}, city: ", this.name, this.city);
		response.getWriter().append("Param name: ").append(this.name + "\n");
		response.getWriter().append("Param city: ").append(this.city + "\n");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
