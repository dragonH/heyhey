package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class WebImage extends zzbck {
  public static final Parcelable.Creator<WebImage> CREATOR = new zze();
  
  private final int zzakq;
  
  private final int zzakr;
  
  private int zzdxs;
  
  private final Uri zzeuy;
  
  WebImage(int paramInt1, Uri paramUri, int paramInt2, int paramInt3) {
    this.zzdxs = paramInt1;
    this.zzeuy = paramUri;
    this.zzakq = paramInt2;
    this.zzakr = paramInt3;
  }
  
  public WebImage(Uri paramUri) throws IllegalArgumentException {
    this(paramUri, 0, 0);
  }
  
  public WebImage(Uri paramUri, int paramInt1, int paramInt2) throws IllegalArgumentException {
    this(1, paramUri, paramInt1, paramInt2);
    if (paramUri != null) {
      if (paramInt1 >= 0 && paramInt2 >= 0)
        return; 
      throw new IllegalArgumentException("width and height must not be negative");
    } 
    throw new IllegalArgumentException("url cannot be null");
  }
  
  public WebImage(JSONObject paramJSONObject) throws IllegalArgumentException {
    this(zzp(paramJSONObject), paramJSONObject.optInt("width", 0), paramJSONObject.optInt("height", 0));
  }
  
  private static Uri zzp(JSONObject paramJSONObject) {
    if (paramJSONObject.has("url"))
      try {
        return Uri.parse(paramJSONObject.getString("url"));
      } catch (JSONException jSONException) {} 
    return null;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject != null && paramObject instanceof WebImage) {
      paramObject = paramObject;
      if (zzbf.equal(this.zzeuy, ((WebImage)paramObject).zzeuy) && this.zzakq == ((WebImage)paramObject).zzakq && this.zzakr == ((WebImage)paramObject).zzakr)
        return true; 
    } 
    return false;
  }
  
  public final int getHeight() {
    return this.zzakr;
  }
  
  public final Uri getUrl() {
    return this.zzeuy;
  }
  
  public final int getWidth() {
    return this.zzakq;
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.zzeuy, Integer.valueOf(this.zzakq), Integer.valueOf(this.zzakr) });
  }
  
  public final JSONObject toJson() {
    JSONObject jSONObject = new JSONObject();
    try {
      jSONObject.put("url", this.zzeuy.toString());
      jSONObject.put("width", this.zzakq);
      jSONObject.put("height", this.zzakr);
    } catch (JSONException jSONException) {}
    return jSONObject;
  }
  
  public final String toString() {
    return String.format(Locale.US, "Image %dx%d %s", new Object[] { Integer.valueOf(this.zzakq), Integer.valueOf(this.zzakr), this.zzeuy.toString() });
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    zzbcn.zza(paramParcel, 2, (Parcelable)getUrl(), paramInt, false);
    zzbcn.zzc(paramParcel, 3, getWidth());
    zzbcn.zzc(paramParcel, 4, getHeight());
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/images/WebImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */