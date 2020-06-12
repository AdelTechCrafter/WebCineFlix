package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import servicesTools.ErrorJSON;
import bd.UserTools;

public class User {
	public static JSONObject CreateUser(String nom,String prenom,String login, String passwd) throws JSONException, SQLException{
		//1)param!=null
		if((nom==null)||(prenom==null)||(login==null)||(passwd==null)){
			return ErrorJSON.serviceRefused("mauvais arguments",0);
		}
		//2)verifier si user existe->erreur
		boolean is_user=UserTools.userExists(login);
		if(is_user) return ErrorJSON.serviceRefused("l'utilisateur existe deja",1001);
		
		//4)inserer user dans la base de donnee
		UserTools.InsertUser(nom, prenom, login, passwd);
		JSONObject o=new JSONObject();
		o=ErrorJSON.serviceAccepted();
		return o;
	}
	public static JSONObject Login(String login, String passwd) throws JSONException, SQLException{
		//1)param!=null
		if((login==null)||(passwd==null)){
			return ErrorJSON.serviceRefused("mauvais arguments",0);
		}
		//2)verifier si user existe
		boolean is_user=UserTools.userExists(login);
		if(!is_user) return ErrorJSON.serviceRefused("Unknown user "+login,1);
		
		//3)verifier mdp
		boolean passwd_ok=UserTools.Checkpasswd(login,passwd);
		if(!passwd_ok) return ErrorJSON.serviceRefused("Bad password "+login,2);
		//4)generer la cle + insertion dans la table CONNEXION: InsertConnexion(int id,boolean root)
		JSONObject retour=new JSONObject();
		int id=UserTools.getIdfromlog(login);
		String key=UserTools.InsertConnexion(id,false);
		retour.put("key",key); 
		return retour;		
	}
	public static JSONObject Logout(String key) throws JSONException, SQLException{
		//1)param!=null
		if(key==null){
			return ErrorJSON.serviceRefused("mauvais arguments",0);
		}
		//2)verifier que l'utilisateur soit co
		if(!UserTools.isConnected(UserTools.getIdfromkey(key)))return ErrorJSON.serviceRefused("user not connected",404);
		//3) suppression  de l'utilisateur dans la table CONNEXION
		UserTools.DeleteConnexion(key);
		JSONObject o=new JSONObject();
		o=ErrorJSON.serviceAccepted();
		return o;		
	}
	public static JSONObject DeleteUser(String key) throws JSONException, SQLException{
		//1)param!=null
		int id=UserTools.getIdfromkey(key);
		if(id==0){
			return ErrorJSON.serviceRefused("mauvais arguments",0);
		}
		//2)verifier si user n'existe pas->pas besoin d'interroger le SGBD
		boolean is_user=UserTools.userExistsv2(id);
		if(!is_user) return ErrorJSON.serviceRefused("l'utilisateur n'existe pas",2);
		//2)verifier que l'utilisateur soit co
		if(!UserTools.isConnected(id))return ErrorJSON.serviceRefused("user not connected",404);
		//3)Supprimer user dans la base de donnees (table USERS)
		UserTools.DeleteUser(key);
		JSONObject o=new JSONObject();
		o=ErrorJSON.serviceAccepted();
		return o;
	}
	public static JSONObject DeleteUser(int id) throws JSONException, SQLException{
		//1)param!=null
		if(id==0){
			return ErrorJSON.serviceRefused("mauvais arguments",0);
		}
		//2)verifier si user n'existe pas->pas besoin d'interroger le SGBD
		boolean is_user=UserTools.userExistsv2(id);
		if(!is_user) return ErrorJSON.serviceRefused("l'utilisateur n'existe pas",2);
		//2)verifier que l'utilisateur soit co
		if(!UserTools.isConnected(id))return ErrorJSON.serviceRefused("user not connected",404);
		//3)Supprimer user dans la base de donnees (table USERS)
		UserTools.DeleteUser(id);
		JSONObject o=new JSONObject();
		o=ErrorJSON.serviceAccepted();
		return o;
	}
}
