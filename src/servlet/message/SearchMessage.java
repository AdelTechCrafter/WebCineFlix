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
public class SearchMessage extends HttpServlet {	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		String content=request.getParameter("content");
		String date_1=request.getParameter("date_1");
		String date_2=request.getParameter("date_2");
		List<JSONObject> ret = new ArrayList<JSONObject>();
		try
		{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				ret=services.Message.SearchMessage(content,date_1,date_2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//ret=serviceRefused.serviceRefused("Fail", 100);
		}
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print(ret.toString());
	}
}
