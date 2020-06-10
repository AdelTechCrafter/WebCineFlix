package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;

import services.Friend;

public class TestRemoveFriend {

	public static void main(String[] args) {
		//Connection c = Database.getMySQLConnection();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Friend.RemoveFriend("KFDB5FZRPPNPBI7BCF1F3FK4G032V1US",3);
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/AdelDB","root","root");
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