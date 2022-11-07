package android.support.v7.media;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.util.TimeUtils;

public final class MediaSessionStatus {
  static final String KEY_EXTRAS = "extras";
  
  static final String KEY_QUEUE_PAUSED = "queuePaused";
  
  static final String KEY_SESSION_STATE = "sessionState";
  
  static final String KEY_TIMESTAMP = "timestamp";
  
  public static final int SESSION_STATE_ACTIVE = 0;
  
  public static final int SESSION_STATE_ENDED = 1;
  
  public static final int SESSION_STATE_INVALIDATED = 2;
  
  final Bundle mBundle;
  
  MediaSessionStatus(Bundle paramBundle) {
    this.mBundle = paramBundle;
  }
  
  public static MediaSessionStatus fromBundle(Bundle paramBundle) {
    if (paramBundle != null) {
      MediaSessionStatus mediaSessionStatus = new MediaSessionStatus(paramBundle);
    } else {
      paramBundle = null;
    } 
    return (MediaSessionStatus)paramBundle;
  }
  
  private static String sessionStateToString(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? Integer.toString(paramInt) : "invalidated") : "ended") : "active";
  }
  
  public Bundle asBundle() {
    return this.mBundle;
  }
  
  public Bundle getExtras() {
    return this.mBundle.getBundle("extras");
  }
  
  public int getSessionState() {
    return this.mBundle.getInt("sessionState", 2);
  }
  
  public long getTimestamp() {
    return this.mBundle.getLong("timestamp");
  }
  
  public boolean isQueuePaused() {
    return this.mBundle.getBoolean("queuePaused");
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("MediaSessionStatus{ ");
    stringBuilder.append("timestamp=");
    TimeUtils.formatDuration(SystemClock.elapsedRealtime() - getTimestamp(), stringBuilder);
    stringBuilder.append(" ms ago");
    stringBuilder.append(", sessionState=");
    stringBuilder.append(sessionStateToString(getSessionState()));
    stringBuilder.append(", queuePaused=");
    stringBuilder.append(isQueuePaused());
    stringBuilder.append(", extras=");
    stringBuilder.append(getExtras());
    stringBuilder.append(" }");
    return stringBuilder.toString();
  }
  
  public static final class Builder {
    private final Bundle mBundle;
    
    public Builder(int param1Int) {
      this.mBundle = new Bundle();
      setTimestamp(SystemClock.elapsedRealtime());
      setSessionState(param1Int);
    }
    
    public Builder(MediaSessionStatus param1MediaSessionStatus) {
      if (param1MediaSessionStatus != null) {
        this.mBundle = new Bundle(param1MediaSessionStatus.mBundle);
        return;
      } 
      throw new IllegalArgumentException("status must not be null");
    }
    
    public MediaSessionStatus build() {
      return new MediaSessionStatus(this.mBundle);
    }
    
    public Builder setExtras(Bundle param1Bundle) {
      this.mBundle.putBundle("extras", param1Bundle);
      return this;
    }
    
    public Builder setQueuePaused(boolean param1Boolean) {
      this.mBundle.putBoolean("queuePaused", param1Boolean);
      return this;
    }
    
    public Builder setSessionState(int param1Int) {
      this.mBundle.putInt("sessionState", param1Int);
      return this;
    }
    
    public Builder setTimestamp(long param1Long) {
      this.mBundle.putLong("timestamp", param1Long);
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/MediaSessionStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */