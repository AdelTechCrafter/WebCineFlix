package servlet.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@SuppressWarnings("serial")
public class ListMessage extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		String key=request.getParameter("key");
		//String id_user=request.getParameter("id");
		List<JSONObject> ret = new ArrayList<JSONObject>();
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			ret=services.Message.ListMessage(key);
		}
		catch(Exception e)
		{
			//ret=serviceRefused.serviceRefused("ListMessage Fail", 100);
		}
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print(ret.toString());
	}

}
