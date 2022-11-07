package com.microsoft.appcenter.analytics;

import android.app.Activity;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import com.microsoft.appcenter.AbstractAppCenterService;
import com.microsoft.appcenter.analytics.channel.AnalyticsListener;
import com.microsoft.appcenter.analytics.channel.SessionTracker;
import com.microsoft.appcenter.analytics.ingestion.models.EventLog;
import com.microsoft.appcenter.analytics.ingestion.models.PageLog;
import com.microsoft.appcenter.analytics.ingestion.models.json.EventLogFactory;
import com.microsoft.appcenter.analytics.ingestion.models.json.PageLogFactory;
import com.microsoft.appcenter.analytics.ingestion.models.json.StartSessionLogFactory;
import com.microsoft.appcenter.channel.Channel;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.json.LogFactory;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.UUIDUtils;
import com.microsoft.appcenter.utils.async.AppCenterFuture;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class Analytics extends AbstractAppCenterService {
  private static final String ACTIVITY_SUFFIX = "Activity";
  
  private static final String ANALYTICS_GROUP = "group_analytics";
  
  public static final String LOG_TAG = "AppCenterAnalytics";
  
  @VisibleForTesting
  static final int MAX_NAME_LENGTH = 256;
  
  private static final int MAX_PROPERTY_COUNT = 5;
  
  @VisibleForTesting
  static final int MAX_PROPERTY_ITEM_LENGTH = 64;
  
  private static final String SERVICE_NAME = "Analytics";
  
  private static Analytics sInstance;
  
  private AnalyticsListener mAnalyticsListener;
  
  private boolean mAutoPageTrackingEnabled = false;
  
  private WeakReference<Activity> mCurrentActivity;
  
  private final Map<String, LogFactory> mFactories;
  
  private SessionTracker mSessionTracker;
  
  private Analytics() {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    this.mFactories = (Map)hashMap;
    hashMap.put("startSession", new StartSessionLogFactory());
    hashMap.put("page", new PageLogFactory());
    hashMap.put("event", new EventLogFactory());
  }
  
  private static String generatePageName(Class<?> paramClass) {
    String str2 = paramClass.getSimpleName();
    String str1 = str2;
    if (str2.endsWith("Activity")) {
      str1 = str2;
      if (str2.length() > 8)
        str1 = str2.substring(0, str2.length() - 8); 
    } 
    return str1;
  }
  
  public static Analytics getInstance() {
    // Byte code:
    //   0: ldc com/microsoft/appcenter/analytics/Analytics
    //   2: monitorenter
    //   3: getstatic com/microsoft/appcenter/analytics/Analytics.sInstance : Lcom/microsoft/appcenter/analytics/Analytics;
    //   6: ifnonnull -> 21
    //   9: new com/microsoft/appcenter/analytics/Analytics
    //   12: astore_0
    //   13: aload_0
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: putstatic com/microsoft/appcenter/analytics/Analytics.sInstance : Lcom/microsoft/appcenter/analytics/Analytics;
    //   21: getstatic com/microsoft/appcenter/analytics/Analytics.sInstance : Lcom/microsoft/appcenter/analytics/Analytics;
    //   24: astore_0
    //   25: ldc com/microsoft/appcenter/analytics/Analytics
    //   27: monitorexit
    //   28: aload_0
    //   29: areturn
    //   30: astore_0
    //   31: ldc com/microsoft/appcenter/analytics/Analytics
    //   33: monitorexit
    //   34: aload_0
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	30	finally
    //   21	25	30	finally
  }
  
  protected static boolean isAutoPageTrackingEnabled() {
    return getInstance().isInstanceAutoPageTrackingEnabled();
  }
  
  public static AppCenterFuture<Boolean> isEnabled() {
    return getInstance().isInstanceEnabledAsync();
  }
  
  private boolean isInstanceAutoPageTrackingEnabled() {
    return this.mAutoPageTrackingEnabled;
  }
  
  private void processOnResume(Activity paramActivity) {
    this.mSessionTracker.onActivityResumed();
    if (this.mAutoPageTrackingEnabled)
      queuePage(generatePageName(paramActivity.getClass()), null); 
  }
  
  @WorkerThread
  private void queuePage(String paramString, Map<String, String> paramMap) {
    PageLog pageLog = new PageLog();
    pageLog.setName(paramString);
    pageLog.setProperties(paramMap);
    this.mChannel.enqueue((Log)pageLog, "group_analytics");
  }
  
  protected static void setAutoPageTrackingEnabled(boolean paramBoolean) {
    getInstance().setInstanceAutoPageTrackingEnabled(paramBoolean);
  }
  
  public static AppCenterFuture<Void> setEnabled(boolean paramBoolean) {
    return getInstance().setInstanceEnabledAsync(paramBoolean);
  }
  
  private void setInstanceAutoPageTrackingEnabled(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield mAutoPageTrackingEnabled : Z
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_2
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_2
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  private void setInstanceListener(AnalyticsListener paramAnalyticsListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield mAnalyticsListener : Lcom/microsoft/appcenter/analytics/channel/AnalyticsListener;
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  @VisibleForTesting
  protected static void setListener(AnalyticsListener paramAnalyticsListener) {
    getInstance().setInstanceListener(paramAnalyticsListener);
  }
  
  public static void trackEvent(String paramString) {
    trackEvent(paramString, null);
  }
  
  public static void trackEvent(String paramString, Map<String, String> paramMap) {
    paramString = validateName(paramString, "Event");
    if (paramString != null) {
      paramMap = validateProperties(paramMap, paramString, "Event");
      getInstance().trackEventAsync(paramString, paramMap);
    } 
  }
  
  private void trackEventAsync(String paramString, Map<String, String> paramMap) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/analytics/Analytics$7
    //   5: astore_3
    //   6: aload_3
    //   7: aload_0
    //   8: aload_1
    //   9: aload_2
    //   10: invokespecial <init> : (Lcom/microsoft/appcenter/analytics/Analytics;Ljava/lang/String;Ljava/util/Map;)V
    //   13: aload_0
    //   14: aload_3
    //   15: invokevirtual post : (Ljava/lang/Runnable;)V
    //   18: aload_0
    //   19: monitorexit
    //   20: return
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	21	finally
  }
  
  protected static void trackPage(String paramString) {
    trackPage(paramString, null);
  }
  
  protected static void trackPage(String paramString, Map<String, String> paramMap) {
    paramString = validateName(paramString, "Page");
    if (paramString != null) {
      paramMap = validateProperties(paramMap, paramString, "Page");
      getInstance().trackPageAsync(paramString, paramMap);
    } 
  }
  
  private void trackPageAsync(String paramString, Map<String, String> paramMap) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/analytics/Analytics$6
    //   5: astore_3
    //   6: aload_3
    //   7: aload_0
    //   8: aload_1
    //   9: aload_2
    //   10: invokespecial <init> : (Lcom/microsoft/appcenter/analytics/Analytics;Ljava/lang/String;Ljava/util/Map;)V
    //   13: aload_0
    //   14: aload_3
    //   15: invokevirtual post : (Ljava/lang/Runnable;)V
    //   18: aload_0
    //   19: monitorexit
    //   20: return
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	21	finally
  }
  
  @VisibleForTesting
  static void unsetInstance() {
    // Byte code:
    //   0: ldc com/microsoft/appcenter/analytics/Analytics
    //   2: monitorenter
    //   3: aconst_null
    //   4: putstatic com/microsoft/appcenter/analytics/Analytics.sInstance : Lcom/microsoft/appcenter/analytics/Analytics;
    //   7: ldc com/microsoft/appcenter/analytics/Analytics
    //   9: monitorexit
    //   10: return
    //   11: astore_0
    //   12: ldc com/microsoft/appcenter/analytics/Analytics
    //   14: monitorexit
    //   15: aload_0
    //   16: athrow
    // Exception table:
    //   from	to	target	type
    //   3	7	11	finally
  }
  
  private static String validateName(String paramString1, String paramString2) {
    StringBuilder stringBuilder1;
    String str;
    if (paramString1 == null || paramString1.isEmpty()) {
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString2);
      stringBuilder1.append(" name cannot be null or empty.");
      AppCenterLog.error("AppCenterAnalytics", stringBuilder1.toString());
      return null;
    } 
    StringBuilder stringBuilder2 = stringBuilder1;
    if (stringBuilder1.length() > 256) {
      AppCenterLog.warn("AppCenterAnalytics", String.format("%s '%s' : name length cannot be longer than %s characters. Name will be truncated.", new Object[] { paramString2, stringBuilder1, Integer.valueOf(256) }));
      str = stringBuilder1.substring(0, 256);
    } 
    return str;
  }
  
  private static Map<String, String> validateProperties(Map<String, String> paramMap, String paramString1, String paramString2) {
    if (paramMap == null)
      return null; 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    for (Map.Entry<String, String> entry : paramMap.entrySet()) {
      String str2 = (String)entry.getKey();
      String str3 = (String)entry.getValue();
      if (hashMap.size() >= 5) {
        AppCenterLog.warn("AppCenterAnalytics", String.format("%s '%s' : properties cannot contain more than %s items. Skipping other properties.", new Object[] { paramString2, paramString1, Integer.valueOf(5) }));
        break;
      } 
      if (str2 == null || str2.isEmpty()) {
        AppCenterLog.warn("AppCenterAnalytics", String.format("%s '%s' : a property key cannot be null or empty. Property will be skipped.", new Object[] { paramString2, paramString1 }));
        continue;
      } 
      if (str3 == null) {
        AppCenterLog.warn("AppCenterAnalytics", String.format("%s '%s' : property '%s' : property value cannot be null. Property '%s' will be skipped.", new Object[] { paramString2, paramString1, str2, str2 }));
        continue;
      } 
      String str1 = str2;
      if (str2.length() > 64) {
        AppCenterLog.warn("AppCenterAnalytics", String.format("%s '%s' : property '%s' : property key length cannot be longer than %s characters. Property key will be truncated.", new Object[] { paramString2, paramString1, str2, Integer.valueOf(64) }));
        str1 = str2.substring(0, 64);
      } 
      str2 = str3;
      if (str3.length() > 64) {
        AppCenterLog.warn("AppCenterAnalytics", String.format("%s '%s' : property '%s' : property value cannot be longer than %s characters. Property value will be truncated.", new Object[] { paramString2, paramString1, str1, Integer.valueOf(64) }));
        str2 = str3.substring(0, 64);
      } 
      hashMap.put(str1, str2);
    } 
    return (Map)hashMap;
  }
  
  protected void applyEnabledState(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iload_1
    //   3: ifeq -> 64
    //   6: new com/microsoft/appcenter/analytics/channel/SessionTracker
    //   9: astore_2
    //   10: aload_2
    //   11: aload_0
    //   12: getfield mChannel : Lcom/microsoft/appcenter/channel/Channel;
    //   15: ldc 'group_analytics'
    //   17: invokespecial <init> : (Lcom/microsoft/appcenter/channel/Channel;Ljava/lang/String;)V
    //   20: aload_0
    //   21: aload_2
    //   22: putfield mSessionTracker : Lcom/microsoft/appcenter/analytics/channel/SessionTracker;
    //   25: aload_0
    //   26: getfield mChannel : Lcom/microsoft/appcenter/channel/Channel;
    //   29: aload_2
    //   30: invokeinterface addListener : (Lcom/microsoft/appcenter/channel/Channel$Listener;)V
    //   35: aload_0
    //   36: getfield mCurrentActivity : Ljava/lang/ref/WeakReference;
    //   39: astore_2
    //   40: aload_2
    //   41: ifnull -> 89
    //   44: aload_2
    //   45: invokevirtual get : ()Ljava/lang/Object;
    //   48: checkcast android/app/Activity
    //   51: astore_2
    //   52: aload_2
    //   53: ifnull -> 89
    //   56: aload_0
    //   57: aload_2
    //   58: invokespecial processOnResume : (Landroid/app/Activity;)V
    //   61: goto -> 89
    //   64: aload_0
    //   65: getfield mChannel : Lcom/microsoft/appcenter/channel/Channel;
    //   68: aload_0
    //   69: getfield mSessionTracker : Lcom/microsoft/appcenter/analytics/channel/SessionTracker;
    //   72: invokeinterface removeListener : (Lcom/microsoft/appcenter/channel/Channel$Listener;)V
    //   77: aload_0
    //   78: getfield mSessionTracker : Lcom/microsoft/appcenter/analytics/channel/SessionTracker;
    //   81: invokevirtual clearSessions : ()V
    //   84: aload_0
    //   85: aconst_null
    //   86: putfield mSessionTracker : Lcom/microsoft/appcenter/analytics/channel/SessionTracker;
    //   89: aload_0
    //   90: monitorexit
    //   91: return
    //   92: astore_2
    //   93: aload_0
    //   94: monitorexit
    //   95: aload_2
    //   96: athrow
    // Exception table:
    //   from	to	target	type
    //   6	40	92	finally
    //   44	52	92	finally
    //   56	61	92	finally
    //   64	89	92	finally
  }
  
  protected Channel.GroupListener getChannelListener() {
    return new Channel.GroupListener() {
        public void onBeforeSending(Log param1Log) {
          if (Analytics.this.mAnalyticsListener != null)
            Analytics.this.mAnalyticsListener.onBeforeSending(param1Log); 
        }
        
        public void onFailure(Log param1Log, Exception param1Exception) {
          if (Analytics.this.mAnalyticsListener != null)
            Analytics.this.mAnalyticsListener.onSendingFailed(param1Log, param1Exception); 
        }
        
        public void onSuccess(Log param1Log) {
          if (Analytics.this.mAnalyticsListener != null)
            Analytics.this.mAnalyticsListener.onSendingSucceeded(param1Log); 
        }
      };
  }
  
  @VisibleForTesting
  WeakReference<Activity> getCurrentActivity() {
    return this.mCurrentActivity;
  }
  
  protected String getGroupName() {
    return "group_analytics";
  }
  
  public Map<String, LogFactory> getLogFactories() {
    return this.mFactories;
  }
  
  protected String getLoggerTag() {
    return "AppCenterAnalytics";
  }
  
  public String getServiceName() {
    return "Analytics";
  }
  
  public void onActivityPaused(Activity paramActivity) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/analytics/Analytics$3
    //   5: astore_2
    //   6: aload_2
    //   7: aload_0
    //   8: invokespecial <init> : (Lcom/microsoft/appcenter/analytics/Analytics;)V
    //   11: new com/microsoft/appcenter/analytics/Analytics$4
    //   14: astore_1
    //   15: aload_1
    //   16: aload_0
    //   17: aload_2
    //   18: invokespecial <init> : (Lcom/microsoft/appcenter/analytics/Analytics;Ljava/lang/Runnable;)V
    //   21: aload_0
    //   22: aload_1
    //   23: aload_2
    //   24: aload_2
    //   25: invokevirtual post : (Ljava/lang/Runnable;Ljava/lang/Runnable;Ljava/lang/Runnable;)Z
    //   28: pop
    //   29: aload_0
    //   30: monitorexit
    //   31: return
    //   32: astore_1
    //   33: aload_0
    //   34: monitorexit
    //   35: aload_1
    //   36: athrow
    // Exception table:
    //   from	to	target	type
    //   2	29	32	finally
  }
  
  public void onActivityResumed(Activity paramActivity) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/analytics/Analytics$1
    //   5: astore_2
    //   6: aload_2
    //   7: aload_0
    //   8: aload_1
    //   9: invokespecial <init> : (Lcom/microsoft/appcenter/analytics/Analytics;Landroid/app/Activity;)V
    //   12: new com/microsoft/appcenter/analytics/Analytics$2
    //   15: astore_3
    //   16: aload_3
    //   17: aload_0
    //   18: aload_2
    //   19: aload_1
    //   20: invokespecial <init> : (Lcom/microsoft/appcenter/analytics/Analytics;Ljava/lang/Runnable;Landroid/app/Activity;)V
    //   23: aload_0
    //   24: aload_3
    //   25: aload_2
    //   26: aload_2
    //   27: invokevirtual post : (Ljava/lang/Runnable;Ljava/lang/Runnable;Ljava/lang/Runnable;)Z
    //   30: pop
    //   31: aload_0
    //   32: monitorexit
    //   33: return
    //   34: astore_1
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_1
    //   38: athrow
    // Exception table:
    //   from	to	target	type
    //   2	31	34	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/analytics/Analytics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */