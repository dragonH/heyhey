package com.microsoft.appcenter.channel;

import android.support.annotation.NonNull;
import com.microsoft.appcenter.ingestion.models.Log;

public interface Channel {
  void addGroup(String paramString, int paramInt1, long paramLong, int paramInt2, GroupListener paramGroupListener);
  
  void addListener(Listener paramListener);
  
  void clear(String paramString);
  
  void enqueue(@NonNull Log paramLog, @NonNull String paramString);
  
  void invalidateDeviceCache();
  
  boolean isEnabled();
  
  void removeGroup(String paramString);
  
  void removeListener(Listener paramListener);
  
  void setEnabled(boolean paramBoolean);
  
  void setLogUrl(String paramString);
  
  void shutdown();
  
  public static interface GroupListener {
    void onBeforeSending(Log param1Log);
    
    void onFailure(Log param1Log, Exception param1Exception);
    
    void onSuccess(Log param1Log);
  }
  
  public static interface Listener {
    void onEnqueuingLog(@NonNull Log param1Log, @NonNull String param1String);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/channel/Channel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */