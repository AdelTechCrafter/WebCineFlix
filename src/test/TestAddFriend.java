package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;

import services.Friend;

public class TestAddFriend {

	public static void main(String[] args) {
		//Connection c = Database.getMySQLConnection();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Friend.AddFriend("AWXIT8AVO9Q81JVAUJC1YZZFPW7WPZXV",3);
			//Friend.AddFriend("X3J5NPQG3YV0MVXWK3UIOVQ318CEWVJ0",2);
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/webcineflixdb","root","");
			Statement instruction = c.createStatement();
			
			ResultSet curseur = instruction.executeQuery("Select * from FRIENDS;");
			while (curseur.next()){
				System.out.println(curseur.getString("from_id"));
				System.out.println(curseur.getString("to_id"));
				System.out.println(curseur.getDate("date_ajout"));
						
			}
			curseur.close();
			instruction.close();
			c.close();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
}