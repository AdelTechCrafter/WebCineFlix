package test;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.bson.types.ObjectId;
import org.json.JSONException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import services.Message;

public class TestMongoDeleteComment {

	public static void main(String[] args) {
		try {
			Message.RemoveComment("RRQLE7VIS5LM4ECHM4EJU5QHF6NPSI59",new ObjectId("5ee3ec15d144d8865e739485"));
			/*System.out.println("author id = "+author_id);
			String author_name = UserTools.getLogin(author_id);
			System.out.println("author name= "+author_name);
			MessageTools.AjoutCommentaire("RRQLE7VIS5LM4ECHM4EJU5QHF6NPSI59","je flood le chat");
			System.out.println("message added");*/
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MongoClient m;
		try {
			m = new MongoClient("localhost",27017);
			DB db= m.getDB("webcineflixdb");
			DBCollection collection=db.getCollection("messages");
			DBCursor cursor=collection.find();
			System.out.println("Connection successfull");
			while(cursor.hasNext()) 
			{
				System.out.println(cursor.next());
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
