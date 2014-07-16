package com.weicheche.android.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SafeJSONObject {

	public SafeJSONObject() {
		// TODO Auto-generated constructor stub
	}
	
	public static long getLong(JSONObject object, String name, long d){
		long res = d;
		try {
			res = object.getLong(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public static int getBoolean(JSONObject object, String name, int d){
		int res = d;
		try {
			if(object.getBoolean(name))
				res = 1;
			else 
				res = 0;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public static boolean getBoolean(JSONObject object, String name, boolean d){
		boolean res = d;
		try {
			res = object.getBoolean(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public static int getInt(JSONObject object, String name, int d){
		int res = d;
		try {
			res = object.getInt(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public static String getString(JSONObject object, String name, String d){
		String res = d;
		try {
			res = object.getString(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	/// put
	public static void putBoolean(JSONObject object, String name, int d){
		try {
			if (0 == d){
				object.put(name, false);
			}else{
				object.put(name, true);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static double getDouble(JSONObject object, String name, double d){
		double res = d;
		try {
			res = object.getDouble(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public static JSONObject getJSONObject(JSONObject object, String name,
			JSONObject d) {
		JSONObject res = d;
		try {
			res = object.getJSONObject(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public static JSONArray getJSONArray(JSONObject object, String name,
			JSONArray d) {
		JSONArray res = d;
		try {
			res = object.getJSONArray(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
}
