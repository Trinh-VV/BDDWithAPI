package common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtils {
	

	public String getValueByJsonKey(String json, String key) {
		JSONObject jObject = new JSONObject(json);
		String result = jObject.getString(key);
		return result;
	}

	public static Object getValueByJsonKey(Object jsonObject, String key) {
		JSONObject resultObj = (JSONObject) jsonObject;
		Object result = resultObj.get(key);
		return result;
	}

	public static String readJsonFile(String jsonFilePath) {
		String jsonString ="";
		
		//đọc file json
		try (BufferedReader br = new BufferedReader(new FileReader(jsonFilePath))) {
            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
            jsonString = jsonStringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return jsonString;
	}
}
