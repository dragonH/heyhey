package com.microsoft.appcenter.ingestion.models;

import java.util.Date;
import java.util.UUID;

public interface Log extends Model {
  Device getDevice();
  
  UUID getSid();
  
  Date getTimestamp();
  
  String getType();
  
  void setDevice(Device paramDevice);
  
  void setSid(UUID paramUUID);
  
  void setTimestamp(Date paramDate);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/Log.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */