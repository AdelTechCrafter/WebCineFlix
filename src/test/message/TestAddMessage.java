package test.message;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.json.JSONException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import bd.MessageTools;
import bd.UserTools;


public class TestAddMessage {
	public static void main(String[] args) {
		try {
			//MessageTools.AddMessage(UserTools.getkeyfromid(1),"I created Special Relativity theory");
			MessageTools.AddMessage(UserTools.getkeyfromid(1),"Einstein theorem 2");
			MessageTools.AddMessage(UserTools.getkeyfromid(1),"Einstein theorem 3");
			MessageTools.AddMessage(UserTools.getkeyfromid(1),"Einstein theorem 4");
			MessageTools.AddMessage(UserTools.getkeyfromid(1),"Einstein theorem 5");
			MessageTools.AddMessage(UserTools.getkeyfromid(1),"Einstein theorem 6");
			
			MessageTools.AddMessage(UserTools.getkeyfromid(2),"Marie curie theory 1");
			MessageTools.AddMessage(UserTools.getkeyfromid(2),"Marie curie theory 2");
			MessageTools.AddMessage(UserTools.getkeyfromid(2),"Marie curie theory 3");
			MessageTools.AddMessage(UserTools.getkeyfromid(2),"Marie curie theory 4");
			MessageTools.AddMessage(UserTools.getkeyfromid(2),"Marie curie theory 5");
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
