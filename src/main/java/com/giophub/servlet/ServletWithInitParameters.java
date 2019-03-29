package com.giophub.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ServletWithInitParameters
 */
public class ServletWithInitParameters
				extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	static final Logger LOG = LoggerFactory.getLogger(ServletWithInitParameters.class);
       
	private String name;
	private String city;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletWithInitParameters() {
        super();

        // TODO Auto-generated constructor stub
		LOG.trace("LOG.trace >>> Hello Trace");
		LOG.debug("LOG.debug >>> Hello Debug");
		LOG.info ("LOG.info  >>> Hello Info");
		LOG.warn ("LOG.warn  >>> Hello Warn");
		LOG.warn ("LOG.error >>> Hello Error");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config)
	{		
		this.name = config.getInitParameter("name");
		this.city = config.getInitParameter("city");
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		LOG.debug("Param name: " + this.name);
		response.getWriter().append("Served at: ").append(request.getContextPath());

		LOG.info("Param name: " + this.name);
		LOG.info("Param city: " + this.city);
		response.getWriter().append("Param name: ").append(this.name + "\n");
		response.getWriter().append("Param city: ").append(this.city + "\n");

		// read global context parameter
		String myContextParam = request.getSession().getServletContext().getInitParameter("myParam");
		response.getWriter().append("CONTEXT param: ").append(myContextParam + "\n");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
