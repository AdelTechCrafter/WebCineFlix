package servlet.message;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import bd.UserTools;
import services.Message;

public class AddPrivateMessage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String key=request.getParameter("key");
			String text=request.getParameter("text");
			int id_friend=Integer.parseInt(request.getParameter("id_friend"));
			int author_id=UserTools.getIdfromkey(key);
			String author_name=UserTools.getLogin(author_id);
			
			JSONObject res=Message.AddPrivateMessages(key, text, author_id, author_name, id_friend);
			response.setContentType("application/json");
			response.getWriter().println(res.toString());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}