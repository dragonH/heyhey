package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.HashMap;

public final class zzbdh extends zzbck implements zzbdn<String, Integer> {
  public static final Parcelable.Creator<zzbdh> CREATOR = new zzbdj();
  
  private int zzdxs = 1;
  
  private final HashMap<String, Integer> zzfwl = new HashMap<String, Integer>();
  
  private final SparseArray<String> zzfwm = new SparseArray();
  
  private final ArrayList<zzbdi> zzfwn = null;
  
  public zzbdh() {}
  
  zzbdh(int paramInt, ArrayList<zzbdi> paramArrayList) {
    zzd(paramArrayList);
  }
  
  private final void zzd(ArrayList<zzbdi> paramArrayList) {
    int i = paramArrayList.size();
    byte b = 0;
    while (b < i) {
      zzbdi zzbdi = (zzbdi)paramArrayList.get(b);
      b++;
      zzbdi = zzbdi;
      zzi(zzbdi.zzfwo, zzbdi.zzfwp);
    } 
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    ArrayList<zzbdi> arrayList = new ArrayList();
    for (String str : this.zzfwl.keySet())
      arrayList.add(new zzbdi(str, ((Integer)this.zzfwl.get(str)).intValue())); 
    zzbcn.zzc(paramParcel, 2, arrayList, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
  
  public final zzbdh zzi(String paramString, int paramInt) {
    this.zzfwl.put(paramString, Integer.valueOf(paramInt));
    this.zzfwm.put(paramInt, paramString);
    return this;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbdh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */