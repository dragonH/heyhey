package com.microsoft.appcenter.ingestion.models.json;

import com.microsoft.appcenter.ingestion.models.Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public final class JSONUtils {
  public static <M extends Model> List<M> readArray(JSONObject paramJSONObject, String paramString, ModelFactory<M> paramModelFactory) throws JSONException {
    JSONArray jSONArray = paramJSONObject.optJSONArray(paramString);
    if (jSONArray == null)
      return null; 
    List<M> list = paramModelFactory.createList(jSONArray.length());
    for (byte b = 0; b < jSONArray.length(); b++) {
      paramJSONObject = jSONArray.getJSONObject(b);
      M m = paramModelFactory.create();
      m.read(paramJSONObject);
      list.add(m);
    } 
    return list;
  }
  
  public static Boolean readBoolean(JSONObject paramJSONObject, String paramString) throws JSONException {
    return paramJSONObject.has(paramString) ? Boolean.valueOf(paramJSONObject.getBoolean(paramString)) : null;
  }
  
  public static Integer readInteger(JSONObject paramJSONObject, String paramString) throws JSONException {
    return paramJSONObject.has(paramString) ? Integer.valueOf(paramJSONObject.getInt(paramString)) : null;
  }
  
  public static Long readLong(JSONObject paramJSONObject, String paramString) throws JSONException {
    return paramJSONObject.has(paramString) ? Long.valueOf(paramJSONObject.getLong(paramString)) : null;
  }
  
  public static Map<String, String> readMap(JSONObject paramJSONObject, String paramString) throws JSONException {
    paramJSONObject = paramJSONObject.optJSONObject(paramString);
    if (paramJSONObject == null)
      return null; 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>(paramJSONObject.length());
    Iterator<String> iterator = paramJSONObject.keys();
    while (iterator.hasNext()) {
      String str = iterator.next();
      hashMap.put(str, paramJSONObject.getString(str));
    } 
    return (Map)hashMap;
  }
  
  public static List<String> readStringArray(JSONObject paramJSONObject, String paramString) throws JSONException {
    JSONArray jSONArray = paramJSONObject.optJSONArray(paramString);
    if (jSONArray == null)
      return null; 
    ArrayList<String> arrayList = new ArrayList(jSONArray.length());
    for (byte b = 0; b < jSONArray.length(); b++)
      arrayList.add(jSONArray.getString(b)); 
    return arrayList;
  }
  
  public static void write(JSONStringer paramJSONStringer, String paramString, Object paramObject) throws JSONException {
    if (paramObject != null)
      paramJSONStringer.key(paramString).value(paramObject); 
  }
  
  public static void writeArray(JSONStringer paramJSONStringer, String paramString, List<? extends Model> paramList) throws JSONException {
    if (paramList != null) {
      paramJSONStringer.key(paramString).array();
      for (Model model : paramList) {
        paramJSONStringer.object();
        model.write(paramJSONStringer);
        paramJSONStringer.endObject();
      } 
      paramJSONStringer.endArray();
    } 
  }
  
  public static void writeMap(JSONStringer paramJSONStringer, String paramString, Map<String, String> paramMap) throws JSONException {
    if (paramMap != null) {
      paramJSONStringer.key(paramString).object();
      for (Map.Entry<String, String> entry : paramMap.entrySet())
        paramJSONStringer.key((String)entry.getKey()).value(entry.getValue()); 
      paramJSONStringer.endObject();
    } 
  }
  
  public static void writeStringArray(JSONStringer paramJSONStringer, String paramString, List<String> paramList) throws JSONException {
    if (paramList != null) {
      paramJSONStringer.key(paramString).array();
      Iterator<String> iterator = paramList.iterator();
      while (iterator.hasNext())
        paramJSONStringer.value(iterator.next()); 
      paramJSONStringer.endArray();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/json/JSONUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */