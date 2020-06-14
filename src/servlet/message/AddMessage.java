package servlet.message;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import servicesTools.serviceRefused;

@SuppressWarnings("serial")
public class AddMessage extends HttpServlet {	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		String key=request.getParameter("key");
		String message=request.getParameter("message");
		JSONObject ret=new JSONObject();
		try
		{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				ret=services.Message.AddMessage(key,message);
		}
		catch(Exception e)
		{
			ret=serviceRefused.serviceRefused("AddMessage Fail", 100);
		}
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print(ret.toString());
	}
}
