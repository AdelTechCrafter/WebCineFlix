package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestBD {

	public static void main(String[] args) {
		//Connection c = Database.getMySQLConnection();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/AdelDB","root","root");
			Statement instruction = c.createStatement();
			String query="INSERT INTO USERS VALUES(NULL,\""+"Armstrong"+"\",\""+"Neil"+"\",\""+"logX"+"\",\""+"PASSWORD('passNeil')"+"\");";
		    System.out.println(query);
		    instruction.execute(query);
			ResultSet curseur = instruction.executeQuery("Select * from USERS;");
			while (curseur.next()){
				System.out.println(curseur.getString("id_user"));
				System.out.println(curseur.getString("login"));
				System.out.println(curseur.getString("Nom"));
				System.out.println(curseur.getString("prenom"));
				System.out.println(curseur.getString("Passwd"));		
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
		}
		
			
	}		

}


