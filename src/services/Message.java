package services;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import servicesTools.ErrorJSON;
import bd.FriendTools;
import bd.MessageTools;
import bd.UserTools;

public class Message {
	public static JSONObject AddComment(String key, String text,int author_id,String author_name) throws JSONException{
		//1)param!=null
		if((key==null)||(text==null)||(author_id==0)||(author_name==null)){
			return ErrorJSON.serviceRefused("mauvais arguments",0);
		}
		//2)Insertion dans la table MESSAGE
		try {
			MessageTools.AjoutCommentaire(key, text);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject retour=new JSONObject();
		retour=ErrorJSON.serviceAccepted();
		return retour;		
	}
	public static JSONObject RemoveComment(String key,ObjectId id_comment) throws JSONException{
		//1)param!=null
		if((key==null)||(id_comment==null)){
			return ErrorJSON.serviceRefused("mauvais arguments",0);
		}
		//2)verifier que seul l'auteur du commentaire puisse supprimer le commentaire
		int id;
		JSONObject retour=new JSONObject();
		try {
			id = UserTools.getIdfromkey(key);
			if(!MessageTools.is_author(id, id_comment)) return ErrorJSON.serviceRefused("seul l'auteur peut supprimer son message",1004);
			//3)Suppression dans la table MESSAGE
			MessageTools.SupprimerCommentaire(id_comment);
			retour=ErrorJSON.serviceAccepted();
			return retour;		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	public static JSONObject AddPrivateMessages(String key, String text,int author_id,String author_name,int id_friend) throws JSONException, SQLException{
		//1)param!=null
		if((key==null)||(text==null)||(author_id==0)||(author_name==null)||(id_friend==0)){
			return ErrorJSON.serviceRefused("mauvais arguments",0);
		}
		//2)vérifier que les 2 utilisateurs sont amis
		if(!FriendTools.isFriendOf(author_id, id_friend)){
			return ErrorJSON.serviceRefused("Vous devez être amis pour échanger des messages privés",1);
		}
		//3)Insertion dans la Collection privateMessages
		try {
			MessageTools.AjoutPrivateMessage(key, text, id_friend);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject retour=new JSONObject();
		retour=ErrorJSON.serviceAccepted();
		return retour;		
	}
}
