package com.microsoft.appcenter.analytics.channel;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import com.microsoft.appcenter.analytics.ingestion.models.StartSessionLog;
import com.microsoft.appcenter.channel.Channel;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.UUIDUtils;
import com.microsoft.appcenter.utils.storage.StorageHelper;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

public class SessionTracker implements Channel.Listener {
  private static final long SESSION_TIMEOUT = 20000L;
  
  private static final String STORAGE_KEY = "sessions";
  
  private static final String STORAGE_KEY_VALUE_SEPARATOR = "/";
  
  private static final int STORAGE_MAX_SESSIONS = 5;
  
  private final Channel mChannel;
  
  private final String mGroupName;
  
  private Long mLastPausedTime;
  
  private long mLastQueuedLogTime;
  
  private Long mLastResumedTime;
  
  private final NavigableMap<Long, UUID> mSessions = new TreeMap<Long, UUID>();
  
  private UUID mSid;
  
  public SessionTracker(Channel paramChannel, String paramString) {
    this.mChannel = paramChannel;
    this.mGroupName = paramString;
    Set set = StorageHelper.PreferencesStorage.getStringSet("sessions");
    if (set != null)
      for (String paramString : set) {
        String[] arrayOfString = paramString.split("/");
        try {
          long l = Long.parseLong(arrayOfString[0]);
          UUID uUID = UUID.fromString(arrayOfString[1]);
          this.mSessions.put(Long.valueOf(l), uUID);
        } catch (RuntimeException runtimeException) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Ignore invalid session in store: ");
          stringBuilder1.append(paramString);
          AppCenterLog.warn("AppCenterAnalytics", stringBuilder1.toString(), runtimeException);
        } 
      }  
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Loaded stored sessions: ");
    stringBuilder.append(this.mSessions);
    AppCenterLog.debug("AppCenterAnalytics", stringBuilder.toString());
  }
  
  private boolean hasSessionTimedOut() {
    boolean bool3;
    long l1 = SystemClock.elapsedRealtime();
    long l2 = this.mLastQueuedLogTime;
    boolean bool1 = true;
    boolean bool2 = true;
    if (l1 - l2 >= 20000L) {
      null = true;
    } else {
      null = false;
    } 
    Long long_ = this.mLastPausedTime;
    if (long_ == null) {
      if (this.mLastResumedTime == null && null) {
        null = bool2;
      } else {
        null = false;
      } 
      return null;
    } 
    if (this.mLastResumedTime == null)
      return null; 
    if (long_.longValue() >= this.mLastResumedTime.longValue() && l1 - this.mLastPausedTime.longValue() >= 20000L) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (this.mLastResumedTime.longValue() - Math.max(this.mLastPausedTime.longValue(), this.mLastQueuedLogTime) >= 20000L) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("noLogSentForLong=");
    stringBuilder.append(null);
    stringBuilder.append(" isBackgroundForLong=");
    stringBuilder.append(bool2);
    stringBuilder.append(" wasBackgroundForLong=");
    stringBuilder.append(bool3);
    AppCenterLog.debug("AppCenterAnalytics", stringBuilder.toString());
    if (null) {
      null = bool1;
      if (!bool2) {
        if (bool3)
          return bool1; 
      } else {
        return null;
      } 
    } 
    return false;
  }
  
  private void sendStartSessionIfNeeded() {
    if (this.mSid == null || hasSessionTimedOut()) {
      this.mSid = UUIDUtils.randomUUID();
      this.mSessions.put(Long.valueOf(System.currentTimeMillis()), this.mSid);
      if (this.mSessions.size() > 5)
        this.mSessions.pollFirstEntry(); 
      HashSet<String> hashSet = new HashSet();
      for (Map.Entry<Long, UUID> entry : this.mSessions.entrySet()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(entry.getKey());
        stringBuilder.append("/");
        stringBuilder.append(entry.getValue());
        hashSet.add(stringBuilder.toString());
      } 
      StorageHelper.PreferencesStorage.putStringSet("sessions", hashSet);
      this.mLastQueuedLogTime = SystemClock.elapsedRealtime();
      StartSessionLog startSessionLog = new StartSessionLog();
      startSessionLog.setSid(this.mSid);
      this.mChannel.enqueue((Log)startSessionLog, this.mGroupName);
    } 
  }
  
  public void clearSessions() {
    StorageHelper.PreferencesStorage.remove("sessions");
  }
  
  public void onActivityPaused() {
    AppCenterLog.debug("AppCenterAnalytics", "onActivityPaused");
    this.mLastPausedTime = Long.valueOf(SystemClock.elapsedRealtime());
  }
  
  public void onActivityResumed() {
    AppCenterLog.debug("AppCenterAnalytics", "onActivityResumed");
    this.mLastResumedTime = Long.valueOf(SystemClock.elapsedRealtime());
    sendStartSessionIfNeeded();
  }
  
  public void onEnqueuingLog(@NonNull Log paramLog, @NonNull String paramString) {
    if (!(paramLog instanceof StartSessionLog) && !(paramLog instanceof com.microsoft.appcenter.ingestion.models.StartServiceLog)) {
      Date date = paramLog.getTimestamp();
      if (date != null) {
        Map.Entry<Long, UUID> entry = this.mSessions.lowerEntry(Long.valueOf(date.getTime()));
        if (entry != null)
          paramLog.setSid(entry.getValue()); 
      } 
      if (paramLog.getSid() == null) {
        sendStartSessionIfNeeded();
        paramLog.setSid(this.mSid);
        this.mLastQueuedLogTime = SystemClock.elapsedRealtime();
      } 
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/analytics/channel/SessionTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */