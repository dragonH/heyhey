package android.support.v7.media;

import android.content.IntentFilter;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class MediaRouteDescriptor {
  static final String KEY_CAN_DISCONNECT = "canDisconnect";
  
  static final String KEY_CONNECTING = "connecting";
  
  static final String KEY_CONNECTION_STATE = "connectionState";
  
  static final String KEY_CONTROL_FILTERS = "controlFilters";
  
  static final String KEY_DESCRIPTION = "status";
  
  static final String KEY_DEVICE_TYPE = "deviceType";
  
  static final String KEY_ENABLED = "enabled";
  
  static final String KEY_EXTRAS = "extras";
  
  static final String KEY_GROUP_MEMBER_IDS = "groupMemberIds";
  
  static final String KEY_ICON_URI = "iconUri";
  
  static final String KEY_ID = "id";
  
  static final String KEY_MAX_CLIENT_VERSION = "maxClientVersion";
  
  static final String KEY_MIN_CLIENT_VERSION = "minClientVersion";
  
  static final String KEY_NAME = "name";
  
  static final String KEY_PLAYBACK_STREAM = "playbackStream";
  
  static final String KEY_PLAYBACK_TYPE = "playbackType";
  
  static final String KEY_PRESENTATION_DISPLAY_ID = "presentationDisplayId";
  
  static final String KEY_SETTINGS_INTENT = "settingsIntent";
  
  static final String KEY_VOLUME = "volume";
  
  static final String KEY_VOLUME_HANDLING = "volumeHandling";
  
  static final String KEY_VOLUME_MAX = "volumeMax";
  
  final Bundle mBundle;
  
  List<IntentFilter> mControlFilters;
  
  MediaRouteDescriptor(Bundle paramBundle, List<IntentFilter> paramList) {
    this.mBundle = paramBundle;
    this.mControlFilters = paramList;
  }
  
  public static MediaRouteDescriptor fromBundle(Bundle paramBundle) {
    MediaRouteDescriptor mediaRouteDescriptor = null;
    if (paramBundle != null)
      mediaRouteDescriptor = new MediaRouteDescriptor(paramBundle, null); 
    return mediaRouteDescriptor;
  }
  
  public Bundle asBundle() {
    return this.mBundle;
  }
  
  public boolean canDisconnectAndKeepPlaying() {
    return this.mBundle.getBoolean("canDisconnect", false);
  }
  
  void ensureControlFilters() {
    if (this.mControlFilters == null) {
      ArrayList<IntentFilter> arrayList = this.mBundle.getParcelableArrayList("controlFilters");
      this.mControlFilters = arrayList;
      if (arrayList == null)
        this.mControlFilters = Collections.emptyList(); 
    } 
  }
  
  public int getConnectionState() {
    return this.mBundle.getInt("connectionState", 0);
  }
  
  public List<IntentFilter> getControlFilters() {
    ensureControlFilters();
    return this.mControlFilters;
  }
  
  public String getDescription() {
    return this.mBundle.getString("status");
  }
  
  public int getDeviceType() {
    return this.mBundle.getInt("deviceType");
  }
  
  public Bundle getExtras() {
    return this.mBundle.getBundle("extras");
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public List<String> getGroupMemberIds() {
    return this.mBundle.getStringArrayList("groupMemberIds");
  }
  
  public Uri getIconUri() {
    Uri uri;
    String str = this.mBundle.getString("iconUri");
    if (str == null) {
      str = null;
    } else {
      uri = Uri.parse(str);
    } 
    return uri;
  }
  
  public String getId() {
    return this.mBundle.getString("id");
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public int getMaxClientVersion() {
    return this.mBundle.getInt("maxClientVersion", 2147483647);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public int getMinClientVersion() {
    return this.mBundle.getInt("minClientVersion", 1);
  }
  
  public String getName() {
    return this.mBundle.getString("name");
  }
  
  public int getPlaybackStream() {
    return this.mBundle.getInt("playbackStream", -1);
  }
  
  public int getPlaybackType() {
    return this.mBundle.getInt("playbackType", 1);
  }
  
  public int getPresentationDisplayId() {
    return this.mBundle.getInt("presentationDisplayId", -1);
  }
  
  public IntentSender getSettingsActivity() {
    return (IntentSender)this.mBundle.getParcelable("settingsIntent");
  }
  
  public int getVolume() {
    return this.mBundle.getInt("volume");
  }
  
  public int getVolumeHandling() {
    return this.mBundle.getInt("volumeHandling", 0);
  }
  
  public int getVolumeMax() {
    return this.mBundle.getInt("volumeMax");
  }
  
  @Deprecated
  public boolean isConnecting() {
    return this.mBundle.getBoolean("connecting", false);
  }
  
  public boolean isEnabled() {
    return this.mBundle.getBoolean("enabled", true);
  }
  
  public boolean isValid() {
    ensureControlFilters();
    return !(TextUtils.isEmpty(getId()) || TextUtils.isEmpty(getName()) || this.mControlFilters.contains(null));
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("MediaRouteDescriptor{ ");
    stringBuilder.append("id=");
    stringBuilder.append(getId());
    stringBuilder.append(", groupMemberIds=");
    stringBuilder.append(getGroupMemberIds());
    stringBuilder.append(", name=");
    stringBuilder.append(getName());
    stringBuilder.append(", description=");
    stringBuilder.append(getDescription());
    stringBuilder.append(", iconUri=");
    stringBuilder.append(getIconUri());
    stringBuilder.append(", isEnabled=");
    stringBuilder.append(isEnabled());
    stringBuilder.append(", isConnecting=");
    stringBuilder.append(isConnecting());
    stringBuilder.append(", connectionState=");
    stringBuilder.append(getConnectionState());
    stringBuilder.append(", controlFilters=");
    stringBuilder.append(Arrays.toString(getControlFilters().toArray()));
    stringBuilder.append(", playbackType=");
    stringBuilder.append(getPlaybackType());
    stringBuilder.append(", playbackStream=");
    stringBuilder.append(getPlaybackStream());
    stringBuilder.append(", deviceType=");
    stringBuilder.append(getDeviceType());
    stringBuilder.append(", volume=");
    stringBuilder.append(getVolume());
    stringBuilder.append(", volumeMax=");
    stringBuilder.append(getVolumeMax());
    stringBuilder.append(", volumeHandling=");
    stringBuilder.append(getVolumeHandling());
    stringBuilder.append(", presentationDisplayId=");
    stringBuilder.append(getPresentationDisplayId());
    stringBuilder.append(", extras=");
    stringBuilder.append(getExtras());
    stringBuilder.append(", isValid=");
    stringBuilder.append(isValid());
    stringBuilder.append(", minClientVersion=");
    stringBuilder.append(getMinClientVersion());
    stringBuilder.append(", maxClientVersion=");
    stringBuilder.append(getMaxClientVersion());
    stringBuilder.append(" }");
    return stringBuilder.toString();
  }
  
  public static final class Builder {
    private final Bundle mBundle;
    
    private ArrayList<IntentFilter> mControlFilters;
    
    private ArrayList<String> mGroupMemberIds;
    
    public Builder(MediaRouteDescriptor param1MediaRouteDescriptor) {
      if (param1MediaRouteDescriptor != null) {
        this.mBundle = new Bundle(param1MediaRouteDescriptor.mBundle);
        param1MediaRouteDescriptor.ensureControlFilters();
        if (!param1MediaRouteDescriptor.mControlFilters.isEmpty())
          this.mControlFilters = new ArrayList<IntentFilter>(param1MediaRouteDescriptor.mControlFilters); 
        return;
      } 
      throw new IllegalArgumentException("descriptor must not be null");
    }
    
    public Builder(String param1String1, String param1String2) {
      this.mBundle = new Bundle();
      setId(param1String1);
      setName(param1String2);
    }
    
    public Builder addControlFilter(IntentFilter param1IntentFilter) {
      if (param1IntentFilter != null) {
        if (this.mControlFilters == null)
          this.mControlFilters = new ArrayList<IntentFilter>(); 
        if (!this.mControlFilters.contains(param1IntentFilter))
          this.mControlFilters.add(param1IntentFilter); 
        return this;
      } 
      throw new IllegalArgumentException("filter must not be null");
    }
    
    public Builder addControlFilters(Collection<IntentFilter> param1Collection) {
      if (param1Collection != null) {
        if (!param1Collection.isEmpty()) {
          Iterator<IntentFilter> iterator = param1Collection.iterator();
          while (iterator.hasNext())
            addControlFilter(iterator.next()); 
        } 
        return this;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException("filters must not be null");
      throw illegalArgumentException;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Builder addGroupMemberId(String param1String) {
      if (!TextUtils.isEmpty(param1String)) {
        if (this.mGroupMemberIds == null)
          this.mGroupMemberIds = new ArrayList<String>(); 
        if (!this.mGroupMemberIds.contains(param1String))
          this.mGroupMemberIds.add(param1String); 
        return this;
      } 
      throw new IllegalArgumentException("groupMemberId must not be empty");
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Builder addGroupMemberIds(Collection<String> param1Collection) {
      if (param1Collection != null) {
        if (!param1Collection.isEmpty()) {
          Iterator<String> iterator = param1Collection.iterator();
          while (iterator.hasNext())
            addGroupMemberId(iterator.next()); 
        } 
        return this;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException("groupMemberIds must not be null");
      throw illegalArgumentException;
    }
    
    public MediaRouteDescriptor build() {
      ArrayList<IntentFilter> arrayList1 = this.mControlFilters;
      if (arrayList1 != null)
        this.mBundle.putParcelableArrayList("controlFilters", arrayList1); 
      ArrayList<String> arrayList = this.mGroupMemberIds;
      if (arrayList != null)
        this.mBundle.putStringArrayList("groupMemberIds", arrayList); 
      return new MediaRouteDescriptor(this.mBundle, this.mControlFilters);
    }
    
    public Builder setCanDisconnect(boolean param1Boolean) {
      this.mBundle.putBoolean("canDisconnect", param1Boolean);
      return this;
    }
    
    @Deprecated
    public Builder setConnecting(boolean param1Boolean) {
      this.mBundle.putBoolean("connecting", param1Boolean);
      return this;
    }
    
    public Builder setConnectionState(int param1Int) {
      this.mBundle.putInt("connectionState", param1Int);
      return this;
    }
    
    public Builder setDescription(String param1String) {
      this.mBundle.putString("status", param1String);
      return this;
    }
    
    public Builder setDeviceType(int param1Int) {
      this.mBundle.putInt("deviceType", param1Int);
      return this;
    }
    
    public Builder setEnabled(boolean param1Boolean) {
      this.mBundle.putBoolean("enabled", param1Boolean);
      return this;
    }
    
    public Builder setExtras(Bundle param1Bundle) {
      this.mBundle.putBundle("extras", param1Bundle);
      return this;
    }
    
    public Builder setIconUri(Uri param1Uri) {
      if (param1Uri != null) {
        this.mBundle.putString("iconUri", param1Uri.toString());
        return this;
      } 
      throw new IllegalArgumentException("iconUri must not be null");
    }
    
    public Builder setId(String param1String) {
      this.mBundle.putString("id", param1String);
      return this;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Builder setMaxClientVersion(int param1Int) {
      this.mBundle.putInt("maxClientVersion", param1Int);
      return this;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Builder setMinClientVersion(int param1Int) {
      this.mBundle.putInt("minClientVersion", param1Int);
      return this;
    }
    
    public Builder setName(String param1String) {
      this.mBundle.putString("name", param1String);
      return this;
    }
    
    public Builder setPlaybackStream(int param1Int) {
      this.mBundle.putInt("playbackStream", param1Int);
      return this;
    }
    
    public Builder setPlaybackType(int param1Int) {
      this.mBundle.putInt("playbackType", param1Int);
      return this;
    }
    
    public Builder setPresentationDisplayId(int param1Int) {
      this.mBundle.putInt("presentationDisplayId", param1Int);
      return this;
    }
    
    public Builder setSettingsActivity(IntentSender param1IntentSender) {
      this.mBundle.putParcelable("settingsIntent", (Parcelable)param1IntentSender);
      return this;
    }
    
    public Builder setVolume(int param1Int) {
      this.mBundle.putInt("volume", param1Int);
      return this;
    }
    
    public Builder setVolumeHandling(int param1Int) {
      this.mBundle.putInt("volumeHandling", param1Int);
      return this;
    }
    
    public Builder setVolumeMax(int param1Int) {
      this.mBundle.putInt("volumeMax", param1Int);
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/MediaRouteDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */