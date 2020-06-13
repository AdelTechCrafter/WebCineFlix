package test;

import java.net.UnknownHostException;
import java.sql.SQLException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import bd.MessageTools;


public class TestAddComment {
	public static void main(String[] args) {
		try {
			MessageTools.AjoutCommentaire("RRQLE7VIS5LM4ECHM4EJU5QHF6NPSI59","test comment 1");
			System.out.println("message added");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
