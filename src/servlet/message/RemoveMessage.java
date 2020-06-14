package servlet.message;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.json.JSONObject;
import servicesTools.serviceRefused;

@SuppressWarnings("serial")
public class RemoveMessage extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		String key=request.getParameter("key");
		String id_message=request.getParameter("id_message");
		JSONObject ret=new JSONObject();
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			ObjectId objid = new ObjectId(id_message);
			ret=services.Message.RemoveMessage(key,objid);
		}
		catch(Exception e)
		{
			ret=serviceRefused.serviceRefused("RemoveMessage Fail", 100);
		}
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print(ret.toString());
	}
}
