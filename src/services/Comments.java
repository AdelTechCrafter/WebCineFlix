package services;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class Comments 
{
	public static JSONObject AddComment(String key,String idm,String comment)throws JSONException, UnknownHostException, SQLException
	{
		return bd.CommentTools.AddComment(key,idm,comment);
	}
	
	public static List<JSONObject> ListComment(String key,String idm)throws JSONException, UnknownHostException, SQLException
	{
		return bd.CommentTools.ListComment(key,idm);
	}

}
