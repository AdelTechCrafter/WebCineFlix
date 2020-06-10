package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class FriendTools {
	public static void AddFriend(String key, int id_friend)throws SQLException{
		Connection c =Database.getMySQLConnection();
        Statement instruction =c.createStatement();
        int id=UserTools.getIdfromkey(key);
        String query="INSERT INTO FRIENDS VALUES(\""+id+"\","+id_friend+",NOW())";
        System.out.println(query);
        instruction.executeUpdate(query);
        instruction.close();
        c.close();
   
	}
	public static void DeleteFriend(String key, int id_friend)throws SQLException{
		int from_id=UserTools.getIdfromkey(key);
		Connection c =Database.getMySQLConnection();
        Statement instruction =c.createStatement();
        String query="DELETE FROM FRIENDS WHERE from_id =\""+from_id+"\" AND to_id=\""+id_friend+"\";";
        System.out.println(query);
        instruction.executeUpdate(query);
        instruction.close();
        c.close();
			
        
	}
	public static boolean isFriendOf (int from_id,int to_id) throws SQLException
	{
		Connection c=Database.getMySQLConnection();
		Statement instruction = c.createStatement();
		String query="SELECT * FROM FRIENDS WHERE from_id =\""+from_id+"\" AND to_id=\""+to_id+"\";";
		ResultSet rs=instruction.executeQuery(query);
		boolean retour=rs.next();
		rs.close();
		instruction.close();
		c.close();
		return retour;
	}
	public static List<String> ListFriend(String key)throws SQLException{
		List<Integer> id_amis= new ArrayList<Integer>();
		List<String> amis= new ArrayList<String>();
		Connection c =Database.getMySQLConnection();
        Statement instruction =c.createStatement();
        int id=UserTools.getIdfromkey(key);
        String query="SELECT * FROM FRIENDS WHERE from_id='"+id+"';";
        System.out.println(query);
        ResultSet rs=instruction.executeQuery(query);
        while(rs.next())
        	id_amis.add(rs.getInt("to_id"));
        instruction.close();
        c.close();
        for(Integer num:id_amis){
        	amis.add(UserTools.getNom(num)+" "+UserTools.getPrenom(num));
        }
        return amis;
	}
}
