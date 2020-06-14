package bd;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import servicesTools.ErrorJSON;




public class MessageTools {
	/*
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
	*/
	public static JSONObject AddMessage(String key,String message) throws UnknownHostException, SQLException, JSONException
	{
		DBCollection message_co=Database.getCollection("message");
		BasicDBObject bdo=new BasicDBObject();
		int id_user = UserTools.getIdfromkey(key);
		GregorianCalendar calendar = new GregorianCalendar();
		Date today= calendar.getTime();
		JSONObject ret=new JSONObject();
		if (id_user==0)
			return ErrorJSON.serviceRefused("Key associe a aucun utilisateur", 100);
		else
		{
			int idm=(int)( Math.random()*( 1000000000 - 1 + 1 ) ) + 1;
			bdo.put("id", idm);
			bdo.put("id_user", id_user);
			bdo.put("text", message);
			bdo.put("date",today);
			bdo.put("comments", new ArrayList<String>());
			bdo.put("like", 0);
			message_co.insert(bdo);
			ret.put("id",idm);
			ret.put("id_user", id_user);
			ret.put("text", message);
			ret.put("date",today);
			ret.put("comments", new ArrayList<String>());
			ret.put("like", 0);
			return ret;
		}
	}
	
	public static JSONObject AddMessageMain(String key,String message) throws UnknownHostException, SQLException, JSONException
	{
		DBCollection message_co=Database.getCollection("message_main");
		BasicDBObject bdo=new BasicDBObject();
		int id_user = UserTools.getIdfromkey(key);
		GregorianCalendar calendar = new GregorianCalendar();
		Date today= calendar.getTime();
		JSONObject ret=new JSONObject();
		if (id_user==0)
			return ErrorJSON.serviceRefused("Key associe a aucun utilisateur", 100);
		else
		{
			int idm=(int)( Math.random()*( 1000000000 - 1 + 1 ) ) + 1;
			bdo.put("id", idm);
			bdo.put("id_user", id_user);
			bdo.put("text", message);
			bdo.put("date",today);
			bdo.put("comments", new ArrayList<String>());
			bdo.put("like", 0);
			message_co.insert(bdo);
			ret.put("id",idm);
			ret.put("id_user", id_user);
			ret.put("text", message);
			ret.put("date",today);
			ret.put("comments", new ArrayList<String>());
			ret.put("like", 0);
			return ret;
		}
	}
	
	public static JSONObject RemoveMessage(String key,ObjectId id_message) throws UnknownHostException, SQLException, JSONException
	{
		DBCollection message=Database.getCollection("message");
		int id_user = UserTools.getIdfromkey(key);
		if (id_user==0)
			return ErrorJSON.serviceRefused("Key associe a  aucun utilisateur", 100);
		else
		{
			BasicDBObject query=new BasicDBObject();
			query.append("_id",id_message);
			query.append("id_user",id_user);
			message.remove(query);
			return ErrorJSON.serviceAccepted();
		}
	}
	
	public static List<JSONObject> ListMessage(String key,String id_users) throws UnknownHostException, JSONException, SQLException{	
		DBCollection message=Database.getCollection("message");
		int id_int = Integer.parseInt(id_users); 
		BasicDBObject query=new BasicDBObject("id_user",id_int);
		DBCursor c= message.find(query);
		List <JSONObject> lr = new ArrayList<JSONObject>();
		
		while (c.hasNext())
		{
			DBObject obj=c.next();
			JSONObject temp=new JSONObject();			
			String s = ((BasicBSONObject) obj).getString("content");
			Integer id = ((BasicBSONObject) obj).getInt("id");
			String login = UserTools.getLogin(id_int);
			Date d=((BasicBSONObject) obj).getDate("date");
			
			temp.put("id", id);
			temp.put("login",login);
			temp.put("text", s);
			temp.put("date", d);
			temp.put("comments", ((BasicBSONObject) obj).get("comments"));
			temp.put("like", ((BasicBSONObject) obj).getInt("like"));
			
			lr.add(temp);
		}
		return lr;
	}	
	
	public static List<JSONObject> ListMessageMain(String key,String id_users) throws UnknownHostException, JSONException, SQLException
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
			Date d=((BasicBSONObject) obj).getDate("date");
			
			temp.put("id", id);
			temp.put("login",login);
			temp.put("text", s);
			temp.put("date", d);
			temp.put("comments", ((BasicBSONObject) obj).get("comments"));
			temp.put("like", ((BasicBSONObject) obj).getInt("like"));
			
			lr.add(temp);
		}
		return lr;
	}
	
	public static List<JSONObject> SearchMessage(String content,String date_1, String date_2) throws UnknownHostException, SQLException, JSONException, ParseException
	{
		DBCollection message=Database.getCollection("message");
		//BasicDBObject bdo=new BasicDBObject();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date d1=formatter.parse(date_1);
		Date d2=formatter.parse(date_2);
		//JSONObject ret=new JSONObject();
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
			Date d=((BasicBSONObject) obj).getDate("date");
			int like=((BasicBSONObject) obj).getInt("like");
			
			if (d1.compareTo(d) > 0 && d2.compareTo(d)<0)
			{
				if(s.toLowerCase().contains(content.toLowerCase()))
				{
					if (like>10)
					{
						temp.put("id", id);
						temp.put("login",login);
						temp.put("text", s);
						temp.put("date", d);
						temp.put("comments", ((BasicBSONObject) obj).get("comments"));
						temp.put("like", ((BasicBSONObject) obj).getInt("like"));
						lr.add(temp);
					}
				}
			}
		}
		return lr;
	}
	
	
	
	
}
