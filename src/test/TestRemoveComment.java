package test;

import java.net.UnknownHostException;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class TestRemoveComment {
	public static void main(String[]args){
		Mongo m;
		try {
			String id1="5a967b3e9b1b2e7572d458d0";
			
			m = new Mongo("localhost");
			DB db = m.getDB("test");
			DBCollection message=db.getCollection("messages");
			BasicDBObject query=new BasicDBObject();
			query.put("author_id","2");
			query.put("_id",id1);
			message.remove(query);
			System.out.println("Suppression du message, idmessage:"+id1);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}