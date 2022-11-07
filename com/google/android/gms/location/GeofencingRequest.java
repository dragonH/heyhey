package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.android.gms.internal.zzcac;
import java.util.ArrayList;
import java.util.List;

public class GeofencingRequest extends zzbck {
  public static final Parcelable.Creator<GeofencingRequest> CREATOR = new zzi();
  
  public static final int INITIAL_TRIGGER_DWELL = 4;
  
  public static final int INITIAL_TRIGGER_ENTER = 1;
  
  public static final int INITIAL_TRIGGER_EXIT = 2;
  
  private final String mTag;
  
  private final List<zzcac> zzhxq;
  
  private final int zzhxr;
  
  GeofencingRequest(List<zzcac> paramList, int paramInt, String paramString) {
    this.zzhxq = paramList;
    this.zzhxr = paramInt;
    this.mTag = paramString;
  }
  
  public List<Geofence> getGeofences() {
    ArrayList<zzcac> arrayList = new ArrayList();
    arrayList.addAll(this.zzhxq);
    return (List)arrayList;
  }
  
  public int getInitialTrigger() {
    return this.zzhxr;
  }
  
  public String toString() {
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("GeofencingRequest[");
    stringBuilder1.append("geofences=");
    stringBuilder1.append(this.zzhxq);
    int i = this.zzhxr;
    StringBuilder stringBuilder2 = new StringBuilder(30);
    stringBuilder2.append(", initialTrigger=");
    stringBuilder2.append(i);
    stringBuilder2.append(", ");
    stringBuilder1.append(stringBuilder2.toString());
    String str = String.valueOf(this.mTag);
    if (str.length() != 0) {
      str = "tag=".concat(str);
    } else {
      str = new String("tag=");
    } 
    stringBuilder1.append(str);
    stringBuilder1.append("]");
    return stringBuilder1.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzhxq, false);
    zzbcn.zzc(paramParcel, 2, getInitialTrigger());
    zzbcn.zza(paramParcel, 3, this.mTag, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
  
  public static final class Builder {
    private String mTag = "";
    
    private final List<zzcac> zzhxq = new ArrayList<zzcac>();
    
    private int zzhxr = 5;
    
    public final Builder addGeofence(Geofence param1Geofence) {
      zzbp.zzb(param1Geofence, "geofence can't be null.");
      zzbp.zzb(param1Geofence instanceof zzcac, "Geofence must be created using Geofence.Builder.");
      this.zzhxq.add((zzcac)param1Geofence);
      return this;
    }
    
    public final Builder addGeofences(List<Geofence> param1List) {
      if (param1List != null && !param1List.isEmpty())
        for (Geofence geofence : param1List) {
          if (geofence != null)
            addGeofence(geofence); 
        }  
      return this;
    }
    
    public final GeofencingRequest build() {
      zzbp.zzb(this.zzhxq.isEmpty() ^ true, "No geofence has been added to this request.");
      return new GeofencingRequest(this.zzhxq, this.zzhxr, this.mTag);
    }
    
    public final Builder setInitialTrigger(int param1Int) {
      this.zzhxr = param1Int & 0x7;
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/GeofencingRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */