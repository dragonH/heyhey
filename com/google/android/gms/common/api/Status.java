package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;

public final class Status extends zzbck implements Result, ReflectedParcelable {
  public static final Parcelable.Creator<Status> CREATOR;
  
  public static final Status zzfhv = new Status(0);
  
  public static final Status zzfhw = new Status(14);
  
  public static final Status zzfhx = new Status(8);
  
  public static final Status zzfhy = new Status(15);
  
  public static final Status zzfhz = new Status(16);
  
  private static Status zzfia = new Status(17);
  
  private static Status zzfib = new Status(18);
  
  @Nullable
  private final PendingIntent mPendingIntent;
  
  private int zzdxs;
  
  private final int zzfac;
  
  @Nullable
  private final String zzffg;
  
  static {
    CREATOR = new zzg();
  }
  
  public Status(int paramInt) {
    this(paramInt, null);
  }
  
  Status(int paramInt1, int paramInt2, @Nullable String paramString, @Nullable PendingIntent paramPendingIntent) {
    this.zzdxs = paramInt1;
    this.zzfac = paramInt2;
    this.zzffg = paramString;
    this.mPendingIntent = paramPendingIntent;
  }
  
  public Status(int paramInt, @Nullable String paramString) {
    this(1, paramInt, paramString, null);
  }
  
  public Status(int paramInt, @Nullable String paramString, @Nullable PendingIntent paramPendingIntent) {
    this(1, paramInt, paramString, paramPendingIntent);
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof Status))
      return false; 
    paramObject = paramObject;
    return (this.zzdxs == ((Status)paramObject).zzdxs && this.zzfac == ((Status)paramObject).zzfac && zzbf.equal(this.zzffg, ((Status)paramObject).zzffg) && zzbf.equal(this.mPendingIntent, ((Status)paramObject).mPendingIntent));
  }
  
  public final PendingIntent getResolution() {
    return this.mPendingIntent;
  }
  
  public final Status getStatus() {
    return this;
  }
  
  public final int getStatusCode() {
    return this.zzfac;
  }
  
  @Nullable
  public final String getStatusMessage() {
    return this.zzffg;
  }
  
  public final boolean hasResolution() {
    return (this.mPendingIntent != null);
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { Integer.valueOf(this.zzdxs), Integer.valueOf(this.zzfac), this.zzffg, this.mPendingIntent });
  }
  
  public final boolean isCanceled() {
    return (this.zzfac == 16);
  }
  
  public final boolean isInterrupted() {
    return (this.zzfac == 14);
  }
  
  public final boolean isSuccess() {
    return (this.zzfac <= 0);
  }
  
  public final void startResolutionForResult(Activity paramActivity, int paramInt) throws IntentSender.SendIntentException {
    if (!hasResolution())
      return; 
    paramActivity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), paramInt, null, 0, 0, 0);
  }
  
  public final String toString() {
    return zzbf.zzt(this).zzg("statusCode", zzafu()).zzg("resolution", this.mPendingIntent).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, getStatusCode());
    zzbcn.zza(paramParcel, 2, getStatusMessage(), false);
    zzbcn.zza(paramParcel, 3, (Parcelable)this.mPendingIntent, paramInt, false);
    zzbcn.zzc(paramParcel, 1000, this.zzdxs);
    zzbcn.zzai(paramParcel, i);
  }
  
  public final String zzafu() {
    String str = this.zzffg;
    return (str != null) ? str : CommonStatusCodes.getStatusCodeString(this.zzfac);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/Status.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */