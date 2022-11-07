package com.microsoft.appcenter.analytics.channel;

import com.microsoft.appcenter.ingestion.models.Log;

public interface AnalyticsListener {
  void onBeforeSending(Log paramLog);
  
  void onSendingFailed(Log paramLog, Exception paramException);
  
  void onSendingSucceeded(Log paramLog);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/analytics/channel/AnalyticsListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */