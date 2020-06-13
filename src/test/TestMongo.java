package test;

import java.net.UnknownHostException;
import java.sql.SQLException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import bd.MessageTools;
import bd.UserTools;

public class TestMongo {

	public static void main(String[] args) {
		try {
			int author_id=UserTools.getIdfromkey("RRQLE7VIS5LM4ECHM4EJU5QHF6NPSI59");
			System.out.println("author id = "+author_id);
			String author_name = UserTools.getLogin(author_id);
			System.out.println("author name= "+author_name);
			MessageTools.AjoutCommentaire("RRQLE7VIS5LM4ECHM4EJU5QHF6NPSI59","je flood le chat");
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
