package servlet.message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;



import services.Message;

public class DeleteComment extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//String key, String text,String author_id,String author_name
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String key=request.getParameter("key");
		String id_comment=request.getParameter("id_comment");
		try {
			
			JSONObject res=Message.RemoveComment(key, new ObjectId(id_comment));
			response.setContentType("application/json");
			response.getWriter().println(res.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}