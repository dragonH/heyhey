package com.microsoft.appcenter.ingestion.models.json;

import android.support.annotation.NonNull;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.LogContainer;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class DefaultLogSerializer implements LogSerializer {
  private static final String LOGS = "logs";
  
  private final Map<String, LogFactory> mLogFactories = new HashMap<String, LogFactory>();
  
  @NonNull
  private Log readLog(JSONObject paramJSONObject) throws JSONException {
    Log log;
    String str = paramJSONObject.getString("type");
    LogFactory logFactory = this.mLogFactories.get(str);
    if (logFactory != null) {
      log = logFactory.create();
      log.read(paramJSONObject);
      return log;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown log type: ");
    stringBuilder.append((String)log);
    throw new JSONException(stringBuilder.toString());
  }
  
  @NonNull
  private JSONStringer writeLog(JSONStringer paramJSONStringer, Log paramLog) throws JSONException {
    paramJSONStringer.object();
    paramLog.write(paramJSONStringer);
    paramJSONStringer.endObject();
    return paramJSONStringer;
  }
  
  public void addLogFactory(@NonNull String paramString, @NonNull LogFactory paramLogFactory) {
    this.mLogFactories.put(paramString, paramLogFactory);
  }
  
  @NonNull
  public LogContainer deserializeContainer(@NonNull String paramString) throws JSONException {
    JSONObject jSONObject = new JSONObject(paramString);
    LogContainer logContainer = new LogContainer();
    JSONArray jSONArray = jSONObject.getJSONArray("logs");
    ArrayList<Log> arrayList = new ArrayList();
    for (byte b = 0; b < jSONArray.length(); b++)
      arrayList.add(readLog(jSONArray.getJSONObject(b))); 
    logContainer.setLogs(arrayList);
    return logContainer;
  }
  
  @NonNull
  public Log deserializeLog(@NonNull String paramString) throws JSONException {
    return readLog(new JSONObject(paramString));
  }
  
  @NonNull
  public String serializeContainer(@NonNull LogContainer paramLogContainer) throws JSONException {
    if (AppCenterLog.getLogLevel() <= 2) {
      JSONStringer jSONStringer;
      try {
        Constructor<JSONStringer> constructor = JSONStringer.class.getDeclaredConstructor(new Class[] { int.class });
        constructor.setAccessible(true);
        JSONStringer jSONStringer1 = constructor.newInstance(new Object[] { Integer.valueOf(2) });
      } catch (Exception exception1) {
        AppCenterLog.error("AppCenter", "Failed to setup pretty json, falling back to default one", exception1);
        exception1 = null;
      } 
      Exception exception2 = exception1;
      if (exception1 == null)
        jSONStringer = new JSONStringer(); 
      jSONStringer.object();
      jSONStringer.key("logs").array();
      Iterator<Log> iterator = paramLogContainer.getLogs().iterator();
      while (iterator.hasNext())
        writeLog(jSONStringer, iterator.next()); 
      jSONStringer.endArray();
      jSONStringer.endObject();
      return jSONStringer.toString();
    } 
    Object object = null;
  }
  
  @NonNull
  public String serializeLog(@NonNull Log paramLog) throws JSONException {
    return writeLog(new JSONStringer(), paramLog).toString();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/json/DefaultLogSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */