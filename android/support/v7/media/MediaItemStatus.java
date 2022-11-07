package android.support.v7.media;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.util.TimeUtils;

public final class MediaItemStatus {
  public static final String EXTRA_HTTP_RESPONSE_HEADERS = "android.media.status.extra.HTTP_RESPONSE_HEADERS";
  
  public static final String EXTRA_HTTP_STATUS_CODE = "android.media.status.extra.HTTP_STATUS_CODE";
  
  static final String KEY_CONTENT_DURATION = "contentDuration";
  
  static final String KEY_CONTENT_POSITION = "contentPosition";
  
  static final String KEY_EXTRAS = "extras";
  
  static final String KEY_PLAYBACK_STATE = "playbackState";
  
  static final String KEY_TIMESTAMP = "timestamp";
  
  public static final int PLAYBACK_STATE_BUFFERING = 3;
  
  public static final int PLAYBACK_STATE_CANCELED = 5;
  
  public static final int PLAYBACK_STATE_ERROR = 7;
  
  public static final int PLAYBACK_STATE_FINISHED = 4;
  
  public static final int PLAYBACK_STATE_INVALIDATED = 6;
  
  public static final int PLAYBACK_STATE_PAUSED = 2;
  
  public static final int PLAYBACK_STATE_PENDING = 0;
  
  public static final int PLAYBACK_STATE_PLAYING = 1;
  
  final Bundle mBundle;
  
  MediaItemStatus(Bundle paramBundle) {
    this.mBundle = paramBundle;
  }
  
  public static MediaItemStatus fromBundle(Bundle paramBundle) {
    if (paramBundle != null) {
      MediaItemStatus mediaItemStatus = new MediaItemStatus(paramBundle);
    } else {
      paramBundle = null;
    } 
    return (MediaItemStatus)paramBundle;
  }
  
  private static String playbackStateToString(int paramInt) {
    switch (paramInt) {
      default:
        return Integer.toString(paramInt);
      case 7:
        return "error";
      case 6:
        return "invalidated";
      case 5:
        return "canceled";
      case 4:
        return "finished";
      case 3:
        return "buffering";
      case 2:
        return "paused";
      case 1:
        return "playing";
      case 0:
        break;
    } 
    return "pending";
  }
  
  public Bundle asBundle() {
    return this.mBundle;
  }
  
  public long getContentDuration() {
    return this.mBundle.getLong("contentDuration", -1L);
  }
  
  public long getContentPosition() {
    return this.mBundle.getLong("contentPosition", -1L);
  }
  
  public Bundle getExtras() {
    return this.mBundle.getBundle("extras");
  }
  
  public int getPlaybackState() {
    return this.mBundle.getInt("playbackState", 7);
  }
  
  public long getTimestamp() {
    return this.mBundle.getLong("timestamp");
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("MediaItemStatus{ ");
    stringBuilder.append("timestamp=");
    TimeUtils.formatDuration(SystemClock.elapsedRealtime() - getTimestamp(), stringBuilder);
    stringBuilder.append(" ms ago");
    stringBuilder.append(", playbackState=");
    stringBuilder.append(playbackStateToString(getPlaybackState()));
    stringBuilder.append(", contentPosition=");
    stringBuilder.append(getContentPosition());
    stringBuilder.append(", contentDuration=");
    stringBuilder.append(getContentDuration());
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
      setPlaybackState(param1Int);
    }
    
    public Builder(MediaItemStatus param1MediaItemStatus) {
      if (param1MediaItemStatus != null) {
        this.mBundle = new Bundle(param1MediaItemStatus.mBundle);
        return;
      } 
      throw new IllegalArgumentException("status must not be null");
    }
    
    public MediaItemStatus build() {
      return new MediaItemStatus(this.mBundle);
    }
    
    public Builder setContentDuration(long param1Long) {
      this.mBundle.putLong("contentDuration", param1Long);
      return this;
    }
    
    public Builder setContentPosition(long param1Long) {
      this.mBundle.putLong("contentPosition", param1Long);
      return this;
    }
    
    public Builder setExtras(Bundle param1Bundle) {
      this.mBundle.putBundle("extras", param1Bundle);
      return this;
    }
    
    public Builder setPlaybackState(int param1Int) {
      this.mBundle.putInt("playbackState", param1Int);
      return this;
    }
    
    public Builder setTimestamp(long param1Long) {
      this.mBundle.putLong("timestamp", param1Long);
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/MediaItemStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */