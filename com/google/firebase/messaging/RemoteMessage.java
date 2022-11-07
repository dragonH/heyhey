package com.google.firebase.messaging;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.Map;

public final class RemoteMessage extends zzbck {
  public static final Parcelable.Creator<RemoteMessage> CREATOR = new zzf();
  
  Bundle mBundle;
  
  private Map<String, String> zzdks;
  
  private Notification zzngk;
  
  RemoteMessage(Bundle paramBundle) {
    this.mBundle = paramBundle;
  }
  
  public final String getCollapseKey() {
    return this.mBundle.getString("collapse_key");
  }
  
  public final Map<String, String> getData() {
    if (this.zzdks == null) {
      this.zzdks = (Map<String, String>)new ArrayMap();
      for (String str : this.mBundle.keySet()) {
        Object object = this.mBundle.get(str);
        if (object instanceof String) {
          object = object;
          if (!str.startsWith("google.") && !str.startsWith("gcm.") && !str.equals("from") && !str.equals("message_type") && !str.equals("collapse_key"))
            this.zzdks.put(str, object); 
        } 
      } 
    } 
    return this.zzdks;
  }
  
  public final String getFrom() {
    return this.mBundle.getString("from");
  }
  
  public final String getMessageId() {
    String str1 = this.mBundle.getString("google.message_id");
    String str2 = str1;
    if (str1 == null)
      str2 = this.mBundle.getString("message_id"); 
    return str2;
  }
  
  public final String getMessageType() {
    return this.mBundle.getString("message_type");
  }
  
  public final Notification getNotification() {
    if (this.zzngk == null && zza.zzad(this.mBundle))
      this.zzngk = new Notification(this.mBundle, null); 
    return this.zzngk;
  }
  
  public final long getSentTime() {
    Object object = this.mBundle.get("google.sent_time");
    if (object instanceof Long)
      return ((Long)object).longValue(); 
    if (object instanceof String)
      try {
        return Long.parseLong((String)object);
      } catch (NumberFormatException numberFormatException) {
        object = String.valueOf(object);
        StringBuilder stringBuilder = new StringBuilder(object.length() + 19);
        stringBuilder.append("Invalid sent time: ");
        stringBuilder.append((String)object);
        Log.w("FirebaseMessaging", stringBuilder.toString());
      }  
    return 0L;
  }
  
  public final String getTo() {
    return this.mBundle.getString("google.to");
  }
  
  public final int getTtl() {
    Object object = this.mBundle.get("google.ttl");
    if (object instanceof Integer)
      return ((Integer)object).intValue(); 
    if (object instanceof String)
      try {
        return Integer.parseInt((String)object);
      } catch (NumberFormatException numberFormatException) {
        String str = String.valueOf(object);
        object = new StringBuilder(str.length() + 13);
        object.append("Invalid TTL: ");
        object.append(str);
        Log.w("FirebaseMessaging", object.toString());
      }  
    return 0;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 2, this.mBundle, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
  
  public static class Builder {
    private final Bundle mBundle;
    
    private final Map<String, String> zzdks;
    
    public Builder(String param1String) {
      Bundle bundle = new Bundle();
      this.mBundle = bundle;
      this.zzdks = (Map<String, String>)new ArrayMap();
      if (TextUtils.isEmpty(param1String)) {
        param1String = String.valueOf(param1String);
        if (param1String.length() != 0) {
          param1String = "Invalid to: ".concat(param1String);
        } else {
          param1String = new String("Invalid to: ");
        } 
        throw new IllegalArgumentException(param1String);
      } 
      bundle.putString("google.to", param1String);
    }
    
    public Builder addData(String param1String1, String param1String2) {
      this.zzdks.put(param1String1, param1String2);
      return this;
    }
    
    public RemoteMessage build() {
      Bundle bundle = new Bundle();
      for (Map.Entry<String, String> entry : this.zzdks.entrySet())
        bundle.putString((String)entry.getKey(), (String)entry.getValue()); 
      bundle.putAll(this.mBundle);
      String str = FirebaseInstanceId.getInstance().getToken();
      if (str != null) {
        this.mBundle.putString("from", str);
      } else {
        this.mBundle.remove("from");
      } 
      return new RemoteMessage(bundle);
    }
    
    public Builder clearData() {
      this.zzdks.clear();
      return this;
    }
    
    public Builder setCollapseKey(String param1String) {
      this.mBundle.putString("collapse_key", param1String);
      return this;
    }
    
    public Builder setData(Map<String, String> param1Map) {
      this.zzdks.clear();
      this.zzdks.putAll(param1Map);
      return this;
    }
    
    public Builder setMessageId(String param1String) {
      this.mBundle.putString("google.message_id", param1String);
      return this;
    }
    
    public Builder setMessageType(String param1String) {
      this.mBundle.putString("message_type", param1String);
      return this;
    }
    
    public Builder setTtl(int param1Int) {
      this.mBundle.putString("google.ttl", String.valueOf(param1Int));
      return this;
    }
  }
  
  public static class Notification {
    private final String mTag;
    
    private final String zzbrq;
    
    private final String zzehk;
    
    private final String zzngl;
    
    private final String[] zzngm;
    
    private final String zzngn;
    
    private final String[] zzngo;
    
    private final String zzngp;
    
    private final String zzngq;
    
    private final String zzngr;
    
    private final String zzngs;
    
    private final Uri zzngt;
    
    private Notification(Bundle param1Bundle) {
      this.zzehk = zza.zze(param1Bundle, "gcm.n.title");
      this.zzngl = zza.zzh(param1Bundle, "gcm.n.title");
      this.zzngm = zzk(param1Bundle, "gcm.n.title");
      this.zzbrq = zza.zze(param1Bundle, "gcm.n.body");
      this.zzngn = zza.zzh(param1Bundle, "gcm.n.body");
      this.zzngo = zzk(param1Bundle, "gcm.n.body");
      this.zzngp = zza.zze(param1Bundle, "gcm.n.icon");
      this.zzngq = zza.zzaf(param1Bundle);
      this.mTag = zza.zze(param1Bundle, "gcm.n.tag");
      this.zzngr = zza.zze(param1Bundle, "gcm.n.color");
      this.zzngs = zza.zze(param1Bundle, "gcm.n.click_action");
      this.zzngt = zza.zzae(param1Bundle);
    }
    
    private static String[] zzk(Bundle param1Bundle, String param1String) {
      Object[] arrayOfObject = zza.zzi(param1Bundle, param1String);
      if (arrayOfObject == null)
        return null; 
      String[] arrayOfString = new String[arrayOfObject.length];
      for (byte b = 0; b < arrayOfObject.length; b++)
        arrayOfString[b] = String.valueOf(arrayOfObject[b]); 
      return arrayOfString;
    }
    
    @Nullable
    public String getBody() {
      return this.zzbrq;
    }
    
    @Nullable
    public String[] getBodyLocalizationArgs() {
      return this.zzngo;
    }
    
    @Nullable
    public String getBodyLocalizationKey() {
      return this.zzngn;
    }
    
    @Nullable
    public String getClickAction() {
      return this.zzngs;
    }
    
    @Nullable
    public String getColor() {
      return this.zzngr;
    }
    
    @Nullable
    public String getIcon() {
      return this.zzngp;
    }
    
    @Nullable
    public Uri getLink() {
      return this.zzngt;
    }
    
    @Nullable
    public String getSound() {
      return this.zzngq;
    }
    
    @Nullable
    public String getTag() {
      return this.mTag;
    }
    
    @Nullable
    public String getTitle() {
      return this.zzehk;
    }
    
    @Nullable
    public String[] getTitleLocalizationArgs() {
      return this.zzngm;
    }
    
    @Nullable
    public String getTitleLocalizationKey() {
      return this.zzngl;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/messaging/RemoteMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */