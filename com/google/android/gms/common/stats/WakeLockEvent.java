package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.internal.zzbcn;
import java.util.List;

public final class WakeLockEvent extends StatsEvent {
  public static final Parcelable.Creator<WakeLockEvent> CREATOR = new zzd();
  
  private final long mTimeout;
  
  private int zzdxs;
  
  private final long zzfxx;
  
  private int zzfxy;
  
  private final String zzfxz;
  
  private final String zzfya;
  
  private final String zzfyb;
  
  private final int zzfyc;
  
  private final List<String> zzfyd;
  
  private final String zzfye;
  
  private final long zzfyf;
  
  private int zzfyg;
  
  private final String zzfyh;
  
  private final float zzfyi;
  
  private long zzfyj;
  
  WakeLockEvent(int paramInt1, long paramLong1, int paramInt2, String paramString1, int paramInt3, List<String> paramList, String paramString2, long paramLong2, int paramInt4, String paramString3, String paramString4, float paramFloat, long paramLong3, String paramString5) {
    this.zzdxs = paramInt1;
    this.zzfxx = paramLong1;
    this.zzfxy = paramInt2;
    this.zzfxz = paramString1;
    this.zzfya = paramString3;
    this.zzfyb = paramString5;
    this.zzfyc = paramInt3;
    this.zzfyj = -1L;
    this.zzfyd = paramList;
    this.zzfye = paramString2;
    this.zzfyf = paramLong2;
    this.zzfyg = paramInt4;
    this.zzfyh = paramString4;
    this.zzfyi = paramFloat;
    this.mTimeout = paramLong3;
  }
  
  public WakeLockEvent(long paramLong1, int paramInt1, String paramString1, int paramInt2, List<String> paramList, String paramString2, long paramLong2, int paramInt3, String paramString3, String paramString4, float paramFloat, long paramLong3, String paramString5) {
    this(2, paramLong1, paramInt1, paramString1, paramInt2, paramList, paramString2, paramLong2, paramInt3, paramString3, paramString4, paramFloat, paramLong3, paramString5);
  }
  
  public final int getEventType() {
    return this.zzfxy;
  }
  
  public final long getTimeMillis() {
    return this.zzfxx;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    zzbcn.zza(paramParcel, 2, super.getTimeMillis());
    zzbcn.zza(paramParcel, 4, this.zzfxz, false);
    zzbcn.zzc(paramParcel, 5, this.zzfyc);
    zzbcn.zzb(paramParcel, 6, this.zzfyd, false);
    zzbcn.zza(paramParcel, 8, this.zzfyf);
    zzbcn.zza(paramParcel, 10, this.zzfya, false);
    zzbcn.zzc(paramParcel, 11, super.getEventType());
    zzbcn.zza(paramParcel, 12, this.zzfye, false);
    zzbcn.zza(paramParcel, 13, this.zzfyh, false);
    zzbcn.zzc(paramParcel, 14, this.zzfyg);
    zzbcn.zza(paramParcel, 15, this.zzfyi);
    zzbcn.zza(paramParcel, 16, this.mTimeout);
    zzbcn.zza(paramParcel, 17, this.zzfyb, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
  
  public final long zzakz() {
    return this.zzfyj;
  }
  
  public final String zzala() {
    String str2;
    String str1 = this.zzfxz;
    int i = this.zzfyc;
    List<String> list = this.zzfyd;
    String str3 = "";
    if (list == null) {
      str2 = "";
    } else {
      str2 = TextUtils.join(",", (Iterable)str2);
    } 
    int j = this.zzfyg;
    String str4 = this.zzfya;
    String str5 = str4;
    if (str4 == null)
      str5 = ""; 
    String str6 = this.zzfyh;
    str4 = str6;
    if (str6 == null)
      str4 = ""; 
    float f = this.zzfyi;
    str6 = this.zzfyb;
    if (str6 != null)
      str3 = str6; 
    StringBuilder stringBuilder = new StringBuilder("\t".length() + 37 + String.valueOf(str1).length() + "\t".length() + "\t".length() + String.valueOf(str2).length() + "\t".length() + "\t".length() + String.valueOf(str5).length() + "\t".length() + String.valueOf(str4).length() + "\t".length() + "\t".length() + String.valueOf(str3).length());
    stringBuilder.append("\t");
    stringBuilder.append(str1);
    stringBuilder.append("\t");
    stringBuilder.append(i);
    stringBuilder.append("\t");
    stringBuilder.append(str2);
    stringBuilder.append("\t");
    stringBuilder.append(j);
    stringBuilder.append("\t");
    stringBuilder.append(str5);
    stringBuilder.append("\t");
    stringBuilder.append(str4);
    stringBuilder.append("\t");
    stringBuilder.append(f);
    stringBuilder.append("\t");
    stringBuilder.append(str3);
    return stringBuilder.toString();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/stats/WakeLockEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */