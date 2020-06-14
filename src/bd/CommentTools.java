package bd;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.bson.BasicBSONObject;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import servicesTools.serviceAccepted;

public class CommentTools 
{
	public static JSONObject AddComment(String key,String idm,String comment) throws UnknownHostException, SQLException, JSONException
	{
		DBCollection message=Database.getCollection("message");
		int id_int = Integer.parseInt(idm);
		BasicDBObject query=new BasicDBObject("id",id_int);
		DBCursor c= message.find(query);
		GregorianCalendar calendar = new GregorianCalendar();
		Date today= calendar.getTime();
		while (c.hasNext())
		{
			int idm2=(int)( Math.random()*( 1000000000 - 1 + 1 ) ) + 1;
			//"comments":[{"id":1,"auteur":"user3","texte":"hum","date":"2018-04-22T18:56:13.586Z","score":0},{"id":2,"auteur":"user4","texte":"humhum","date":"2018-04-22T18:56:13.586Z","score":0}]
			DBObject obj=c.next();
			int id_user = ((BasicBSONObject) obj).getInt("id_user");
			String login = UserTools.getLogin(id_user);
			int score = ((BasicBSONObject) obj).getInt("like");
			BasicDBObject ajout=new BasicDBObject();
			ajout.put("id", idm2);
			ajout.put("auteur", login);
			ajout.put("text", comment);
			ajout.put("date", today.toString());
			ajout.put("score", score); 				
			@SuppressWarnings("unchecked")
			ArrayList<BasicDBObject> commentaire=(ArrayList<BasicDBObject>) obj.get("comments");
			commentaire.add(ajout);
			obj.put("comments",commentaire);
			//System.out.println(obj);
		}
		return serviceAccepted.serviceAccepted();
	}
	
	public static List<JSONObject> ListComment(String key,String idm) throws UnknownHostException, JSONException, SQLException
	{	
		DBCollection message=Database.getCollection("message");
		int id_int = Integer.parseInt(idm); 
		BasicDBObject query=new BasicDBObject("id",id_int);
		DBCursor c= message.find(query);
		DBObject obj=c.next();
		System.out.println(obj);
		@SuppressWarnings("unchecked")
		ArrayList<BasicDBObject> commentaire=(ArrayList<BasicDBObject>) obj.get("comments");
		System.out.println(commentaire);
		return null;
	}
	
}
