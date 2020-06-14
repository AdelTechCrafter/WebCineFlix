package servicesTools;

import org.json.JSONObject;

public class serviceRefused 
{	
	@SuppressWarnings("all")
	public static JSONObject serviceRefused (String n, int idError)
	{
		JSONObject response=new JSONObject();
		try
		{
			response.put("status","ko");
			response.put("message",n);
			response.put("idError"," "+idError);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
}