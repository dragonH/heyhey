package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class zzbdr extends zzbck {
  public static final Parcelable.Creator<zzbdr> CREATOR = new zzbdu();
  
  private int zzdxs;
  
  private final HashMap<String, Map<String, zzbdm<?, ?>>> zzfxa;
  
  private final ArrayList<zzbds> zzfxb;
  
  private final String zzfxc;
  
  zzbdr(int paramInt, ArrayList<zzbds> paramArrayList, String paramString) {
    this.zzdxs = paramInt;
    this.zzfxb = null;
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    int i = paramArrayList.size();
    for (paramInt = 0; paramInt < i; paramInt++) {
      zzbds zzbds = paramArrayList.get(paramInt);
      hashMap.put(zzbds.className, zzbds.zzakw());
    } 
    this.zzfxa = (HashMap)hashMap;
    this.zzfxc = (String)zzbp.zzu(paramString);
    zzaku();
  }
  
  private final void zzaku() {
    for (String str : this.zzfxa.keySet()) {
      Map map = this.zzfxa.get(str);
      Iterator<String> iterator = map.keySet().iterator();
      while (iterator.hasNext())
        ((zzbdm)map.get(iterator.next())).zza(this); 
    } 
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (String str : this.zzfxa.keySet()) {
      stringBuilder.append(str);
      stringBuilder.append(":\n");
      Map map = this.zzfxa.get(str);
      for (String str1 : map.keySet()) {
        stringBuilder.append("  ");
        stringBuilder.append(str1);
        stringBuilder.append(": ");
        stringBuilder.append(map.get(str1));
      } 
    } 
    return stringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    ArrayList<zzbds> arrayList = new ArrayList();
    for (String str : this.zzfxa.keySet())
      arrayList.add(new zzbds(str, this.zzfxa.get(str))); 
    zzbcn.zzc(paramParcel, 2, arrayList, false);
    zzbcn.zza(paramParcel, 3, this.zzfxc, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
  
  public final String zzakv() {
    return this.zzfxc;
  }
  
  public final Map<String, zzbdm<?, ?>> zzgk(String paramString) {
    return this.zzfxa.get(paramString);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbdr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */