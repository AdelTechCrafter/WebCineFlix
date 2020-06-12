package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import services.User;
import bd.UserTools;

public class TestLogout {
	public static void main(String[] args) {
		//Connection c = Database.getMySQLConnection();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//JSONObject json1=User.Logout(UserTools.getkeyfromid(1));
			JSONObject json2=User.Logout(UserTools.getkeyfromid(2));
			//System.out.println(json1.toString());
			System.out.println(json2.toString());
			
			
			
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/webcineflixdb","root","");
			Statement instruction = c.createStatement();
			
			ResultSet curseur = instruction.executeQuery("Select * from CONNEXIONS;");
			while (curseur.next()){
				System.out.println(curseur.getString("Connexion_key"));
				System.out.println(curseur.getString("id_user"));
				System.out.println(curseur.getDate("date_connexion").toString());
				System.out.println(curseur.getBoolean("root"));		
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
