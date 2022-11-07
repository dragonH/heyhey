package com.microsoft.appcenter;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.microsoft.appcenter.channel.Channel;
import com.microsoft.appcenter.ingestion.models.json.LogFactory;
import java.util.Map;

public interface AppCenterService extends Application.ActivityLifecycleCallbacks {
  @Nullable
  Map<String, LogFactory> getLogFactories();
  
  String getServiceName();
  
  boolean isInstanceEnabled();
  
  @WorkerThread
  void onStarted(@NonNull Context paramContext, @NonNull String paramString, @NonNull Channel paramChannel);
  
  void onStarting(@NonNull AppCenterHandler paramAppCenterHandler);
  
  void setInstanceEnabled(boolean paramBoolean);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/AppCenterService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */