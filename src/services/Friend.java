package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import bd.FriendTools;
import bd.UserTools;
import servicesTools.ErrorJSON;

public class Friend {
	public static JSONObject AddFriend(String key,int id_friend) throws JSONException, SQLException{
		int id_user=UserTools.getIdfromkey(key);
		//1)param!=null
		if((key==null)||(id_friend==0)){
			return ErrorJSON.serviceRefused("mauvais arguments",0);
		}
		//2)verifier utilisateur connecté
		if(!UserTools.isConnected(id_user)){
			return ErrorJSON.serviceRefused("l'utilisateur n'est pas connecté",2);
		}
		//3)Verifier si id_friend existe
		String login=UserTools.getLogin(id_friend);
		boolean is_user=UserTools.userExists(login);
		if(!is_user) return ErrorJSON.serviceRefused("l'utilisateur n'existe pas",1);
		//4)verifier déjà amis
		if(FriendTools.isFriendOf(id_user, id_friend)){
			String logfriend=UserTools.getLogin(id_friend);
			return ErrorJSON.serviceRefused("vous êtes déjà amis avec"+logfriend,1);
		}
		//5)Insertion dans la table Friend 
		FriendTools.AddFriend(key, id_friend);
		JSONObject retour=ErrorJSON.serviceAccepted();
		return retour;		
	}
	public static JSONObject RemoveFriend(String key,int id_friend) throws JSONException, SQLException{
		int id_user=UserTools.getIdfromkey(key);
		//1)param!=null
		if((key==null)||(id_friend==0)){
			return ErrorJSON.serviceRefused("mauvais arguments",0);
		}
		//2)verifier utilisateur connecté
		if(!UserTools.isConnected(id_user)){
			return ErrorJSON.serviceRefused("l'utilisateur n'est pas connecté",2);
		}
		//3)Verifier si id_friend existe
		String login=UserTools.getLogin(id_friend);
		boolean is_user=UserTools.userExists(login);
		if(!is_user) return ErrorJSON.serviceRefused("l'utilisateur n'existe pas",1);
		//4)verifier si non amis
		if(!FriendTools.isFriendOf(id_user, id_friend)){
			String logfriend=UserTools.getLogin(id_friend);
			return ErrorJSON.serviceRefused("vous n'êtes pas amis avec"+logfriend,1);
		}		
		//5)Suppression dans la table Friend 
		FriendTools.DeleteFriend(key, id_friend);
		JSONObject retour=ErrorJSON.serviceAccepted();
		return retour;		
	}
}
