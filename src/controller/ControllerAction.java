package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class ControllerAction extends HttpServlet {
	
	private static final long SerialVersionID = 1L;
	
	private Map<String,Object> commandMap = new HashMap<String,Object>();
	
	public void init(ServletConfig config) throws ServletException{
		String props = config.getInitParameter("propertyConfig");
		
		Properties pr = new Properties();
		FileInputStream f = null;
		String path = config.getServletContext().getRealPath("/WEB-INF");
		try {
			f = new FileInputStream(new File(path,props));
			pr.load(f);
		}catch(IOException e) {
			throw new ServletException(e);
		}finally {
			if( f != null) try {f.close();}catch(IOException e) {}
		}
		
		Iterator<Object> keyIter = pr.keySet().iterator();
		while(keyIter.hasNext()) {
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			try {
				Class<?> commandClass = Class.forName(className);
				
				Object commandInstance = commandClass.newInstance();
				
				commandMap.put(command, commandInstance);
			}catch(ClassNotFoundException e) {
				System.out.println((String)keyIter.next());
				throw new ServletException(e);
			}catch(InstantiationException e) {
				throw new ServletException(e);
			}catch(IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
		
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		requestPro(req,resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		requestPro(req,resp);
	}
	
	
	public void requestPro (HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		String view = null;
		CommandAction com = null;
	try {
		String command = req.getRequestURI();
		if(command.indexOf(req.getContextPath()) == 0) {
			command = command.substring(req.getContextPath().length());
		}
		com = (CommandAction)commandMap.get(command);
		view = com.requestPro(req, resp);
	}catch(Throwable e) {
		throw new ServletException(e);
	}
	RequestDispatcher dispatcher = req.getRequestDispatcher(view);
	dispatcher.forward(req, resp);
	
	}
	
}
