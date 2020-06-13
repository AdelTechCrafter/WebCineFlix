package bd;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;


public class MessageTools {
	
	public static void AjoutCommentaire(String key, String text) throws UnknownHostException,SQLException{
		
		int author_id=UserTools.getIdfromkey(key);	
		String author_name=UserTools.getLogin(author_id);
		DBCollection messages=Database.getCollection("messages");
		BasicDBObject query=new BasicDBObject();
		GregorianCalendar calendar =new java.util.GregorianCalendar();
		Date date_message=calendar.getTime();
		query.put("author_id",author_id);
		query.put("author_name",author_name);
		query.put("text",text);
		query.put("date",date_message);
		messages.insert(query);
	}
	
	public static void AjoutPrivateMessage(String key, String text,int id_friend) throws UnknownHostException,SQLException{
		
		int author_id=UserTools.getIdfromkey(key);	
		String author_name=UserTools.getLogin(author_id);
		DBCollection messages=Database.getCollection("messages");
		BasicDBObject query=new BasicDBObject();
		GregorianCalendar calendar =new java.util.GregorianCalendar();
		Date date_message=calendar.getTime();
		query.put("author_id",author_id);
		query.put("author_name",author_name);
		query.put("date",date_message);
		query.put("text",text);
		query.put("id_friend",id_friend);
		messages.insert(query);
	}
	
	
	public static void SupprimerCommentaire(ObjectId id_com) throws UnknownHostException,SQLException{
			DBCollection messages=Database.getCollection("messages");
			BasicDBObject query=new BasicDBObject();
			query.put("_id",id_com);
			messages.remove(query);
		
	}
	public static boolean is_author(int id_user, ObjectId id_com) throws UnknownHostException,SQLException{
		DBCollection messages=Database.getCollection("messages");
		BasicDBObject query=new BasicDBObject();
		query.put("_id",id_com);
		query.put("author_id",id_user);
		DBObject retour=messages.findOne(query);
		return (retour!=null);
}
	public static boolean messageExists(ObjectId id_com) throws UnknownHostException,SQLException{
		DBCollection messages=Database.getCollection("messages");
		BasicDBObject query=new BasicDBObject();
		query.put("_id",id_com);
		DBCursor trouve=messages.find(query);
		return trouve.hasNext();
}
	/*
	public static List<JSONObject> ListMessages(String key,String id_user){
		try {
			List<JSONObject> Lmessages= new ArrayList<>();
			DBCollection messages=Database.getCollection("messages");
			BasicDBObject query=new BasicDBObject();
			int author_id=UserTools.getIdfromkey(key);
			query.put("author_id",author_id);
			DBCursor cursor=messages.find(query);
			while(cursor.hasNext()){
				DBObject comment=cursor.next();
				Lmessages.add((JSONObject) JSON.parse(comment.toString()));
				
			}
			return Lmessages;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	*/
	
	public static List<JSONObject> ListMessage(String key,String id_user) throws UnknownHostException, JSONException, SQLException
	{	
		DBCollection message=Database.getCollection("messages");
		//BasicDBObject retour=new BasicDBObject();
		int id_int = Integer.parseInt(id_user); 
		BasicDBObject query=new BasicDBObject("author_id",id_int);
		DBCursor c= message.find(query);
		List <JSONObject> lr = new ArrayList<JSONObject>();
		while (c.hasNext())
		{
			DBObject obj=c.next();
			JSONObject temp=new JSONObject();			
			String text= ((BasicBSONObject) obj).getString("text");
			Integer id = ((BasicBSONObject) obj).getInt("author_id");
			String login = UserTools.getLogin(id_int);
			Date d=((BasicBSONObject) obj).getDate("date");
			
			temp.put("author_id", id);
			temp.put("author_name",login);
			temp.put("text", text);
			temp.put("date", d);
			temp.put("comments", ((BasicBSONObject) obj).get("comments"));
			temp.put("like", ((BasicBSONObject) obj).getInt("like"));
			lr.add(temp);
		}
		return lr;
	}	
	public static List<JSONObject> ListMessageMain(String key,String id_user) throws UnknownHostException, JSONException, SQLException
	{	
		DBCollection message=Database.getCollection("message_main");
		DBCursor c= message.find();
		List <JSONObject> lr = new ArrayList<JSONObject>();
		while (c.hasNext())
		{
			DBObject obj=c.next();
			JSONObject temp=new JSONObject();			
			String s = ((BasicBSONObject) obj).getString("content");
			
			Integer id = ((BasicBSONObject) obj).getInt("id");
			Integer idu = ((BasicBSONObject) obj).getInt("id_user");
		
			String login = UserTools.getLogin(idu);
			//String logins=Integer.toString(login);
			temp.put("id", id);
			temp.put("login",login);
			temp.put("text", s);
			
			Date d=((BasicBSONObject) obj).getDate("date");
			temp.put("date", d);
			temp.put("comments", ((BasicBSONObject) obj).get("comments"));
			temp.put("like", ((BasicBSONObject) obj).getInt("like"));
			lr.add(temp);
		}
		return lr;
	}
	/*
	public static List<JSONObject> ListPrivateMessages(String key,int id_friend){
		try {
			List<JSONObject> Lmessages= new ArrayList<>();
			DBCollection messages=Database.getCollection("privateMessages");
			BasicDBObject query=new BasicDBObject();
			int author_id=UserTools.getIdfromkey(key);
			query.put("author_id",author_id);
			query.put("id_friend",id_friend);
			DBCursor cursor=messages.find(query);
			while(cursor.hasNext()){
				DBObject comment=cursor.next();
				Lmessages.add((JSONObject) JSON.parse(comment.toString()));
				
			}
			return Lmessages;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
	
	
	
}
