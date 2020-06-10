package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


 
/**
 * Servlet implementation class HelloWorld
 */
public class HelloWorld extends HttpServlet {
 
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
 * Default constructor.
 */
 public HelloWorld() {
 }
 
 /*
 * This method will handle all GET request.
 */
 protected void doGet(HttpServletRequest request,
 HttpServletResponse response) throws ServletException, IOException {
	 response.setContentType("text/html");
	 PrintWriter out = response.getWriter ();
	 out.println("<html><body>");
	 out.print("<h2>Hello world</h2>");
	 out.println("<hr>");
	 out.println("Time on the server is: "+ new java.util.Date());
	 out.println("</body></html>");
 	//response.setContentType( " text / plain " );
	//PrintWriter out = response.getWriter ();
	//out.println( " Hello , hello !!!! " );
 }
 

}