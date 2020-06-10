package test;
import services.Message;

import java.sql.SQLException;

import org.json.JSONException;

import bd.UserTools;

public class TestAddComment {
	public static void main(String[]args){
		
		try {
			/*
			
			DBCollection message=db.getCollection("messages");
			BasicDBObject query=new BasicDBObject();
			GregorianCalendar calendar =new java.util.GregorianCalendar();
			Date date_message=calendar.getTime();
			query.put("author_id","5");
			query.put("author_name","cadabra6");
			query.put("date",date_message);
			query.put("text","Bonjour test 6");
			message.insert(query);
			*/
			Message.AddComment(UserTools.getkeyfromid(1),"test texte",3,"cadabra3");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
			
		
		
	
	
}
