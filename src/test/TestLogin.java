package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;




import services.User;
import bd.Database;


public class TestLogin {
	public static void main(String[] args) {
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
			User.Login("magic","secret");
			User.Login("magic2","secret2");
			User.Login("magic3","secret3");
			
			Connection c =Database.getMySQLConnection();
			Statement instruction = c.createStatement();
			ResultSet curseur = instruction.executeQuery("Select * from CONNEXIONS;");
			while (curseur.next()){
				System.out.println(curseur.getString("connexion_key"));
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
