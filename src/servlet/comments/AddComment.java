package servlet.comments;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.Comments;
import servicesTools.serviceRefused;

@SuppressWarnings("serial")
public class AddComment extends HttpServlet {	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		String key=request.getParameter("key");
		String idm=request.getParameter("id_message");
		String comment=request.getParameter("comment");
		JSONObject ret=new JSONObject();
		try
		{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				ret=Comments.AddComment(key,idm,comment);
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