package servlet.comments;

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
public class ListComment extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		String key=request.getParameter("key");
		String id_message=request.getParameter("id");
		//BasicDBObject ret = new BasicDBObject();
		//JSONObject ret = new JSONObject();
		List<JSONObject> ret = new ArrayList<JSONObject>();
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			ret=services.Comments.ListComment(key,id_message);
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