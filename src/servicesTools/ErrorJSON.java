package servicesTools;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorJSON {
	public static JSONObject serviceRefused(String message, int codeErreur) throws JSONException{
		JSONObject json = new JSONObject ();
		json.put("error "+codeErreur,message);
		return json;
	}
	
	public static JSONObject serviceAccepted() throws JSONException{
		JSONObject json = new JSONObject ();
		json.put("output ","OK");
		return json;
	}
}
