package com.microsoft.appcenter.ingestion.models.json;

import java.util.List;

public interface ModelFactory<M extends com.microsoft.appcenter.ingestion.models.Model> {
  M create();
  
  List<M> createList(int paramInt);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/json/ModelFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */