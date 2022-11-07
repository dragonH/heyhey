package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class zzbds extends zzbck {
  public static final Parcelable.Creator<zzbds> CREATOR = new zzbdv();
  
  final String className;
  
  private int versionCode;
  
  private ArrayList<zzbdt> zzfxd;
  
  zzbds(int paramInt, String paramString, ArrayList<zzbdt> paramArrayList) {
    this.versionCode = paramInt;
    this.className = paramString;
    this.zzfxd = paramArrayList;
  }
  
  zzbds(String paramString, Map<String, zzbdm<?, ?>> paramMap) {
    String str;
    this.versionCode = 1;
    this.className = paramString;
    if (paramMap == null) {
      paramString = null;
    } else {
      ArrayList<zzbdt> arrayList = new ArrayList();
      Iterator<String> iterator = paramMap.keySet().iterator();
      while (true) {
        ArrayList<zzbdt> arrayList1 = arrayList;
        if (iterator.hasNext()) {
          str = iterator.next();
          arrayList.add(new zzbdt(str, paramMap.get(str)));
          continue;
        } 
        break;
      } 
    } 
    this.zzfxd = (ArrayList<zzbdt>)str;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.versionCode);
    zzbcn.zza(paramParcel, 2, this.className, false);
    zzbcn.zzc(paramParcel, 3, this.zzfxd, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
  
  final HashMap<String, zzbdm<?, ?>> zzakw() {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    int i = this.zzfxd.size();
    for (byte b = 0; b < i; b++) {
      zzbdt zzbdt = this.zzfxd.get(b);
      hashMap.put(zzbdt.key, zzbdt.zzfxe);
    } 
    return (HashMap)hashMap;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */