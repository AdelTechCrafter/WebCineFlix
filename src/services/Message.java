package services;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

public class Message {
	/*
	public static JSONObject AddComment(String key, String text) throws JSONException, UnknownHostException, SQLException{
		//1)param!=null
		if((key==null)||(text==null)){
			return ErrorJSON.serviceRefused("mauvais arguments",0);
		}
		//2)Insertion dans la table MESSAGE
		MessageTools.AjoutCommentaire(key, text);
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
			if(!(MessageTools.is_author(id, id_comment))) return ErrorJSON.serviceRefused("seul l'auteur peut supprimer son message",1004);
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
	}*/
	
	public static JSONObject AddMessage(String key,String message)throws JSONException, UnknownHostException, SQLException
	{
		return bd.MessageTools.AddMessage(key,message);
	}
	
	public static JSONObject AddMessageMain(String key,String message)throws JSONException, UnknownHostException, SQLException
	{
		return bd.MessageTools.AddMessageMain(key,message);
	}
	
	public static JSONObject RemoveMessage(String key,ObjectId id_message)throws JSONException, UnknownHostException, SQLException
	{
		return bd.MessageTools.RemoveMessage(key,id_message);
	}
	
	public static List<JSONObject> ListMessage(String key, String id_users)throws JSONException, UnknownHostException, SQLException
	{
		return bd.MessageTools.ListMessage(key,id_users);
	}
	
	public static List<JSONObject> ListMessageMain(String key, String id_users)throws JSONException, UnknownHostException, SQLException
	{
		return bd.MessageTools.ListMessageMain(key,id_users);
	}
	
	public static List<JSONObject> SearchMessage(String content,String date_1,String date_2)throws JSONException, UnknownHostException, SQLException, ParseException
	{
		return bd.MessageTools.SearchMessage(content,date_1,date_2);
	}
}
