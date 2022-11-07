package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.android.gms.internal.zzbcp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ActivityRecognitionResult extends zzbck implements ReflectedParcelable {
  public static final Parcelable.Creator<ActivityRecognitionResult> CREATOR = new zzb();
  
  private Bundle extras;
  
  private List<DetectedActivity> zzhwu;
  
  private long zzhwv;
  
  private long zzhww;
  
  private int zzhwx;
  
  public ActivityRecognitionResult(DetectedActivity paramDetectedActivity, long paramLong1, long paramLong2) {
    this(paramDetectedActivity, paramLong1, paramLong2, 0, (Bundle)null);
  }
  
  private ActivityRecognitionResult(DetectedActivity paramDetectedActivity, long paramLong1, long paramLong2, int paramInt, Bundle paramBundle) {
    this(Collections.singletonList(paramDetectedActivity), paramLong1, paramLong2, 0, (Bundle)null);
  }
  
  public ActivityRecognitionResult(List<DetectedActivity> paramList, long paramLong1, long paramLong2) {
    this(paramList, paramLong1, paramLong2, 0, (Bundle)null);
  }
  
  public ActivityRecognitionResult(List<DetectedActivity> paramList, long paramLong1, long paramLong2, int paramInt, Bundle paramBundle) {
    boolean bool2;
    boolean bool1 = true;
    if (paramList != null && paramList.size() > 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    zzbp.zzb(bool2, "Must have at least 1 detected activity");
    if (paramLong1 > 0L && paramLong2 > 0L) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    zzbp.zzb(bool2, "Must set times");
    this.zzhwu = paramList;
    this.zzhwv = paramLong1;
    this.zzhww = paramLong2;
    this.zzhwx = paramInt;
    this.extras = paramBundle;
  }
  
  public static ActivityRecognitionResult extractResult(Intent paramIntent) {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic hasResult : (Landroid/content/Intent;)Z
    //   4: ifeq -> 53
    //   7: aload_0
    //   8: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   11: ldc 'com.google.android.location.internal.EXTRA_ACTIVITY_RESULT'
    //   13: invokevirtual get : (Ljava/lang/String;)Ljava/lang/Object;
    //   16: astore_1
    //   17: aload_1
    //   18: instanceof [B
    //   21: ifeq -> 43
    //   24: aload_1
    //   25: checkcast [B
    //   28: getstatic com/google/android/gms/location/ActivityRecognitionResult.CREATOR : Landroid/os/Parcelable$Creator;
    //   31: invokestatic zza : ([BLandroid/os/Parcelable$Creator;)Lcom/google/android/gms/internal/zzbco;
    //   34: astore_1
    //   35: aload_1
    //   36: checkcast com/google/android/gms/location/ActivityRecognitionResult
    //   39: astore_1
    //   40: goto -> 55
    //   43: aload_1
    //   44: instanceof com/google/android/gms/location/ActivityRecognitionResult
    //   47: ifeq -> 53
    //   50: goto -> 35
    //   53: aconst_null
    //   54: astore_1
    //   55: aload_1
    //   56: ifnull -> 61
    //   59: aload_1
    //   60: areturn
    //   61: aload_0
    //   62: invokestatic zzj : (Landroid/content/Intent;)Ljava/util/List;
    //   65: astore_0
    //   66: aload_0
    //   67: ifnull -> 100
    //   70: aload_0
    //   71: invokeinterface isEmpty : ()Z
    //   76: ifeq -> 82
    //   79: goto -> 100
    //   82: aload_0
    //   83: aload_0
    //   84: invokeinterface size : ()I
    //   89: iconst_1
    //   90: isub
    //   91: invokeinterface get : (I)Ljava/lang/Object;
    //   96: checkcast com/google/android/gms/location/ActivityRecognitionResult
    //   99: areturn
    //   100: aconst_null
    //   101: areturn
  }
  
  public static boolean hasResult(Intent paramIntent) {
    if (paramIntent == null)
      return false; 
    if (paramIntent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT"))
      return true; 
    List<ActivityRecognitionResult> list = zzj(paramIntent);
    return (list != null && !list.isEmpty());
  }
  
  private static boolean zzc(Bundle paramBundle1, Bundle paramBundle2) {
    if (paramBundle1 == null && paramBundle2 == null)
      return true; 
    if ((paramBundle1 == null && paramBundle2 != null) || (paramBundle1 != null && paramBundle2 == null))
      return false; 
    if (paramBundle1.size() != paramBundle2.size())
      return false; 
    for (String str : paramBundle1.keySet()) {
      if (!paramBundle2.containsKey(str))
        return false; 
      if (paramBundle1.get(str) == null) {
        if (paramBundle2.get(str) != null)
          return false; 
        continue;
      } 
      if (paramBundle1.get(str) instanceof Bundle) {
        if (!zzc(paramBundle1.getBundle(str), paramBundle2.getBundle(str)))
          return false; 
        continue;
      } 
      if (!paramBundle1.get(str).equals(paramBundle2.get(str)))
        return false; 
    } 
    return true;
  }
  
  @Nullable
  private static List<ActivityRecognitionResult> zzj(Intent paramIntent) {
    boolean bool;
    if (paramIntent == null) {
      bool = false;
    } else {
      bool = paramIntent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST");
    } 
    return !bool ? null : zzbcp.zzb(paramIntent, "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST", CREATOR);
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject != null && getClass() == paramObject.getClass()) {
      paramObject = paramObject;
      if (this.zzhwv == ((ActivityRecognitionResult)paramObject).zzhwv && this.zzhww == ((ActivityRecognitionResult)paramObject).zzhww && this.zzhwx == ((ActivityRecognitionResult)paramObject).zzhwx && zzbf.equal(this.zzhwu, ((ActivityRecognitionResult)paramObject).zzhwu) && zzc(this.extras, ((ActivityRecognitionResult)paramObject).extras))
        return true; 
    } 
    return false;
  }
  
  public int getActivityConfidence(int paramInt) {
    for (DetectedActivity detectedActivity : this.zzhwu) {
      if (detectedActivity.getType() == paramInt)
        return detectedActivity.getConfidence(); 
    } 
    return 0;
  }
  
  public long getElapsedRealtimeMillis() {
    return this.zzhww;
  }
  
  public DetectedActivity getMostProbableActivity() {
    return this.zzhwu.get(0);
  }
  
  public List<DetectedActivity> getProbableActivities() {
    return this.zzhwu;
  }
  
  public long getTime() {
    return this.zzhwv;
  }
  
  public int hashCode() {
    return Arrays.hashCode(new Object[] { Long.valueOf(this.zzhwv), Long.valueOf(this.zzhww), Integer.valueOf(this.zzhwx), this.zzhwu, this.extras });
  }
  
  public String toString() {
    String str = String.valueOf(this.zzhwu);
    long l1 = this.zzhwv;
    long l2 = this.zzhww;
    StringBuilder stringBuilder = new StringBuilder(str.length() + 124);
    stringBuilder.append("ActivityRecognitionResult [probableActivities=");
    stringBuilder.append(str);
    stringBuilder.append(", timeMillis=");
    stringBuilder.append(l1);
    stringBuilder.append(", elapsedRealtimeMillis=");
    stringBuilder.append(l2);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzhwu, false);
    zzbcn.zza(paramParcel, 2, this.zzhwv);
    zzbcn.zza(paramParcel, 3, this.zzhww);
    zzbcn.zzc(paramParcel, 4, this.zzhwx);
    zzbcn.zza(paramParcel, 5, this.extras, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/ActivityRecognitionResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */