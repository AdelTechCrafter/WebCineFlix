package test.comments;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.json.JSONException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import bd.CommentTools;
import bd.UserTools;

public class TestAddComment {
	public static void main(String[] args) {
		try {
			//MessageTools.AddMessage(UserTools.getkeyfromid(1),"I created Special Relativity theory");
			//MessageTools.AddMessage(UserTools.getkeyfromid(2),"I discovered radium");
			CommentTools.AddComment(UserTools.getkeyfromid(2),"704388147","this is amazing !");
			System.out.println("message added");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MongoClient m;
		try {
			m = new MongoClient("localhost",27017);
			DB db= m.getDB("webcineflixdb");
			DBCollection collection=db.getCollection("message");
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
