package com.microsoft.appcenter.ingestion.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public interface Model {
  void read(JSONObject paramJSONObject) throws JSONException;
  
  void write(JSONStringer paramJSONStringer) throws JSONException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/Model.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */