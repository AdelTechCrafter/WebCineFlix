package servlet.friend;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import services.Friend;

public class RemoveFriend extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String key=request.getParameter("key");
		int id_friend=Integer.parseInt(request.getParameter("id_friend"));
		try {
			JSONObject res = Friend.RemoveFriend(key, id_friend);
			response.setContentType("application/json");
			response.getWriter().println(res.toString());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}