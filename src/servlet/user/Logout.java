package servlet.user;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import services.User;
public class Logout {
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String key=request.getParameter("key");
		try {
			JSONObject res = User.Logout(key);
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