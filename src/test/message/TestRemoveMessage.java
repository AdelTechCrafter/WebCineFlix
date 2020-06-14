package test.message;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.bson.types.ObjectId;
import org.json.JSONException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import bd.MessageTools;
import bd.UserTools;

public class TestRemoveMessage {
	public static void main(String[] args) {
		try {
			MessageTools.RemoveMessage(UserTools.getkeyfromid(2),new ObjectId("5ee63b7a3e34dd4c96d6678f"));
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
