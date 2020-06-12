package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;



public class UserTools {
//---------------------------------Exists/Checks---------------------------------------------------------	
	public static boolean userExists(String login)throws SQLException{
		Connection c = Database.getMySQLConnection();
		Statement instruction = c.createStatement();
        String query="SELECT * FROM USERS WHERE login=\""+login+"\";";
        System.out.println(query);
        ResultSet rs = instruction.executeQuery(query);
        boolean retour = rs.next();
        rs.close();
        instruction.close();
        c.close();
        return retour;
    }
	public static boolean userExistsv2(int id)throws SQLException{
            Connection c = Database.getMySQLConnection();
            Statement instruction =c.createStatement();
            String query="SELECT * FROM USERS WHERE id_user=\""+id+"\";";
            System.out.println(query);
            ResultSet rs = instruction.executeQuery(query);
            boolean retour = rs.next();
            rs.close();
            instruction.close();
            c.close();
            return retour;
            
    }
	public static boolean Checkpasswd(String login,String passwd) throws SQLException{
		Connection connection =Database.getMySQLConnection();
		Statement instruction =connection.createStatement();
        String query="SELECT * FROM USERS WHERE login=\""+login+"\" AND password=\""+"PASSWORD("+passwd+")"+"\";";
        System.out.println(query);
        instruction.executeQuery(query);
        ResultSet rs = instruction.getResultSet();
        return rs.next();
	}
	
	public static boolean isConnected (int id) throws SQLException
	{
		Connection c=Database.getMySQLConnection();
		Statement instruction = c.createStatement();
		String query="SELECT * from CONNEXIONS WHERE id_user='"+id+"';";
		ResultSet rs=instruction.executeQuery(query);
		boolean retour=rs.next();
		rs.close();
		instruction.close();
		c.close();
		return retour;
	}
//--------------------------GETTERS---------------------------------------------------------
	public static int getIdfromlog(String login)throws SQLException{
		Connection connection =Database.getMySQLConnection();
        Statement instruction =connection.createStatement();
        String query="select * from USERS where login='"+login+"';";
        System.out.println(query);
        ResultSet rs=instruction.executeQuery(query);
        int id=0;
        while (rs.next())
			id=rs.getInt("id_user");
        rs.close();
        instruction.close();
        connection.close(); 
		return id;
	}
	public static int getIdfromkey(String key)throws SQLException{
	
            Connection connection =Database.getMySQLConnection();
            Statement instruction = connection.createStatement();
            String query="SELECT * FROM CONNEXIONS where connexion_key='"+key+"';";
            System.out.println(query);
            ResultSet rs=instruction.executeQuery(query);
            int id=0;
            while (rs.next())
    			id=rs.getInt("id_user");
            rs.close();
            instruction.close();
            connection.close();
            return id;
    }
	public static String getLogin(int id)throws SQLException{
		Connection connection =Database.getMySQLConnection();
		 Statement instruction =connection.createStatement();
        String query="SELECT * FROM USERS WHERE id_user='"+id+"';";
        System.out.println(query);
        ResultSet rs = instruction.executeQuery(query);
        String retour="";
        while(rs.next())
        	retour = rs.getString("login");
        rs.close();
        instruction.close();
        connection.close();
        return retour;
    }
	public static String getPrenom(int id)throws SQLException{
		Connection connection =Database.getMySQLConnection();
		 Statement instruction =connection.createStatement();
        String query="SELECT * FROM USERS WHERE id_user='"+id+"';";
        System.out.println(query);
        ResultSet rs = instruction.executeQuery(query);
        String retour="";
        while(rs.next())
        	retour = rs.getString("prenom");
        rs.close();
        instruction.close();
        connection.close();
        return retour;
    }
	public static String getNom(int id)throws SQLException{
		Connection connection =Database.getMySQLConnection();
		 Statement instruction =connection.createStatement();
        String query="SELECT * FROM USERS WHERE id_user='"+id+"';";
        System.out.println(query);
        ResultSet rs = instruction.executeQuery(query);
        String retour="";
        while(rs.next())
        	retour = rs.getString("nom");
        rs.close();
        instruction.close();
        connection.close();
        return retour;
    }
	
	public static String getkeyfromid(int id)throws SQLException{
		Connection connection = Database.getMySQLConnection();
		 Statement instruction =connection.createStatement();
        String query="SELECT * FROM CONNEXIONS WHERE id_user='"+id+"';";
        System.out.println(query);
        ResultSet rs = instruction.executeQuery(query);
        String retour="";
        while(rs.next())
        	retour = rs.getString("connexion_key");
        rs.close();
        instruction.close();
        connection.close();
        return retour;
    }	

//---------------------------KEY GENERATOR-------------------------------------------------------	
	public static String generatekey(){
		String tabval = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder keybuild = new StringBuilder();
        Random rnd = new Random();
        while (keybuild.length() < 32) {
            int index = (int) (rnd.nextFloat() * tabval.length());
            keybuild.append(tabval.charAt(index));
        }
        String key = keybuild.toString();
        return key;
	}	
//---------------------------Insertions---------------------------------------------------------------	
	public static void InsertUser(String nom,String prenom,String login,String passwd) throws SQLException{
		Connection c =Database.getMySQLConnection();
		Statement instruction =c.createStatement();
        String query="INSERT INTO USERS VALUES(NULL,\""+nom+"\",\""+prenom+"\",\""+login+"\",\""+"PASSWORD("+passwd+")"+"\")";
        System.out.println(query);
        instruction.executeUpdate(query);
        instruction.close();
        c.close();	
	}
	
	public static String InsertConnexion(int id,boolean root) throws SQLException{
		
		if(UserTools.isConnected(id)){
			System.out.println("utilisateur déjà connecté");
			return "";
		}
		else{
			String key=generatekey();
	        Connection c =Database.getMySQLConnection();
	        Statement instruction =c.createStatement();
	        String query="INSERT INTO CONNEXIONS VALUES(\""+key+"\","+id+",NOW(),"+root+")";
	        System.out.println(query);
	        instruction.executeUpdate(query);
	        instruction.close();
	        c.close();
	        return key;	
		}
		
		
    }

//--------------------------Delete/Update----------------------------------------------------------
	public static void DeleteConnexion(String key) throws SQLException{
		Connection c =Database.getMySQLConnection();
        Statement instruction =c.createStatement();
        //System.out.println("avant la supression");
        String query="DELETE FROM CONNEXIONS WHERE connexion_key=\""+key+"\";";
        System.out.println(query);
        instruction.executeUpdate(query);
        instruction.close();
        c.close();
        //System.out.println("après la supression");
	}
	public static void DeleteUser(String key) throws SQLException{
		int id= getIdfromkey(key);
        Connection c =Database.getMySQLConnection();
        String query="DELETE FROM USERS WHERE id_user=\""+id+"\";";
        System.out.println(query);
        Statement instruction =c.createStatement();
        instruction.executeUpdate(query);
        instruction.close();
        c.close();
  
	}
	public static void DeleteUser(int id) throws SQLException{
        Connection c =Database.getMySQLConnection();
        String query="DELETE FROM USERS WHERE id_user=\""+id+"\";";
        String query2="DELETE FROM CONNEXIONS WHERE id_user=\""+id+"\";";
        System.out.println(query);
        Statement instruction =c.createStatement();
        instruction.executeUpdate(query);
        instruction.executeUpdate(query2);
        instruction.close();
        c.close();
	}
	
	public static boolean Search(String key,String query,String[] friends){
		//TODO
		return true;
	}
	
		
}
