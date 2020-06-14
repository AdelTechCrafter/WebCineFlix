package test.message;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import bd.MessageTools;
import bd.UserTools;

public class TestListMessage {
	public static void main(String[] args) {
		try {
			//MessageTools.AddMessage(UserTools.getkeyfromid(1),"I created Special Relativity theory");
			//List<JSONObject> liste=MessageTools.ListMessage("F63W65T92J9UUSCBN6YHLHISYB9V44R7");
			
			//MessageTools.AddMessageMain(UserTools.getkeyfromid(1),"Eintein first message into message_main db");
			//MessageTools.AddMessageMain(UserTools.getkeyfromid(1),"Eintein second message into message_main db");
			MessageTools.AddMessageMain(UserTools.getkeyfromid(1),"Eintein third message into message_main db");
			List<JSONObject> liste=MessageTools.ListMessageMain(UserTools.getkeyfromid(1));
			while(!liste.isEmpty()) {
				System.out.println(liste.remove(0).toString(2));
			}
			//System.out.println("message added");
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
		/*
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
		}*/
}
}
