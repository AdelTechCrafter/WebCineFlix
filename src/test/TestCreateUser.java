package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import services.User;

public class TestCreateUser {

	public static void main(String[] args) {
		//Connection c = Database.getMySQLConnection();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			User.CreateUser("abra","cadabra", "magic", "secret");
			User.CreateUser("abra2","cadabra2", "magic2", "secret2");
			User.CreateUser("abra3","cadabra3", "magic3", "secret3");
			User.CreateUser("abra4","cadabra3", "magic3", "secret3");
			User.CreateUser("abra5","cadabra5", "magic5", "secret5");
			User.CreateUser("abra6","cadabra6", "magic6", "secret6");
			
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/AdelDB","root","root");
			Statement instruction = c.createStatement();
			
			ResultSet curseur = instruction.executeQuery("Select * from USERS;");
			while (curseur.next()){
				System.out.println(curseur.getString("id_user"));
				System.out.println(curseur.getString("login"));
				System.out.println(curseur.getString("Nom"));
				System.out.println(curseur.getString("prenom"));
				System.out.println(curseur.getString("password"));		
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
