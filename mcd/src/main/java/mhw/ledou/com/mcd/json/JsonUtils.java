package mhw.ledou.com.mcd.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import android.content.Context;

import mhw.ledou.com.mcd.activity.base.ShengModel;

public class JsonUtils {
	
	public static JSONArray toarray(Context context,String string){
		JSONArray array = null;
	try {
		  InputStreamReader isr = new InputStreamReader(context.getAssets().open(string),"UTF-8");
          BufferedReader br = new BufferedReader(isr);
          String line;
          StringBuilder builder = new StringBuilder();
          while((line = br.readLine()) != null){
              builder.append(line);
          }
          br.close();
          isr.close();
          JSONObject testjson = new JSONObject(builder.toString());//builder��ȡ��JSON�е����ݡ�
          array  = testjson.getJSONArray("RECORDS"); //��JSONObject��ȡ���������
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return array;
	
	
	
	
	}
	
	
	
	@SuppressWarnings("null")
	public static List<ShengModel> getlist(List<ShengModel> list, String id){
		List<ShengModel> smlist = new ArrayList<ShengModel>();
		
		for (ShengModel shengModel : list) {
			if(shengModel.getArea_parent_id().equals(id)){
				smlist.add(shengModel);
			}
		}
		
		
		return smlist;
		
	}
	
	
	
	
	
	
	
}
