package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import java.util.List;

public class GeofencingEvent {
  private final int mErrorCode;
  
  private final int zzhxn;
  
  private final List<Geofence> zzhxo;
  
  private final Location zzhxp;
  
  private GeofencingEvent(int paramInt1, int paramInt2, List<Geofence> paramList, Location paramLocation) {
    this.mErrorCode = paramInt1;
    this.zzhxn = paramInt2;
    this.zzhxo = paramList;
    this.zzhxp = paramLocation;
  }
  
  public static GeofencingEvent fromIntent(Intent paramIntent) {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: ifnonnull -> 8
    //   6: aconst_null
    //   7: areturn
    //   8: iconst_m1
    //   9: istore_2
    //   10: aload_0
    //   11: ldc 'gms_error_code'
    //   13: iconst_m1
    //   14: invokevirtual getIntExtra : (Ljava/lang/String;I)I
    //   17: istore_3
    //   18: aload_0
    //   19: ldc 'com.google.android.location.intent.extra.transition'
    //   21: iconst_m1
    //   22: invokevirtual getIntExtra : (Ljava/lang/String;I)I
    //   25: istore #4
    //   27: iload_2
    //   28: istore #5
    //   30: iload #4
    //   32: iconst_m1
    //   33: if_icmpeq -> 61
    //   36: iload #4
    //   38: iconst_1
    //   39: if_icmpeq -> 57
    //   42: iload #4
    //   44: iconst_2
    //   45: if_icmpeq -> 57
    //   48: iload_2
    //   49: istore #5
    //   51: iload #4
    //   53: iconst_4
    //   54: if_icmpne -> 61
    //   57: iload #4
    //   59: istore #5
    //   61: aload_0
    //   62: ldc 'com.google.android.location.intent.extra.geofence_list'
    //   64: invokevirtual getSerializableExtra : (Ljava/lang/String;)Ljava/io/Serializable;
    //   67: checkcast java/util/ArrayList
    //   70: astore #6
    //   72: aload #6
    //   74: ifnonnull -> 80
    //   77: goto -> 138
    //   80: new java/util/ArrayList
    //   83: dup
    //   84: aload #6
    //   86: invokevirtual size : ()I
    //   89: invokespecial <init> : (I)V
    //   92: astore #7
    //   94: aload #6
    //   96: invokevirtual size : ()I
    //   99: istore #4
    //   101: iconst_0
    //   102: istore_2
    //   103: aload #7
    //   105: astore_1
    //   106: iload_2
    //   107: iload #4
    //   109: if_icmpge -> 138
    //   112: aload #6
    //   114: iload_2
    //   115: invokevirtual get : (I)Ljava/lang/Object;
    //   118: astore_1
    //   119: iinc #2, 1
    //   122: aload #7
    //   124: aload_1
    //   125: checkcast [B
    //   128: invokestatic zzo : ([B)Lcom/google/android/gms/internal/zzcac;
    //   131: invokevirtual add : (Ljava/lang/Object;)Z
    //   134: pop
    //   135: goto -> 103
    //   138: new com/google/android/gms/location/GeofencingEvent
    //   141: dup
    //   142: iload_3
    //   143: iload #5
    //   145: aload_1
    //   146: aload_0
    //   147: ldc 'com.google.android.location.intent.extra.triggering_location'
    //   149: invokevirtual getParcelableExtra : (Ljava/lang/String;)Landroid/os/Parcelable;
    //   152: checkcast android/location/Location
    //   155: invokespecial <init> : (IILjava/util/List;Landroid/location/Location;)V
    //   158: areturn
  }
  
  public int getErrorCode() {
    return this.mErrorCode;
  }
  
  public int getGeofenceTransition() {
    return this.zzhxn;
  }
  
  public List<Geofence> getTriggeringGeofences() {
    return this.zzhxo;
  }
  
  public Location getTriggeringLocation() {
    return this.zzhxp;
  }
  
  public boolean hasError() {
    return (this.mErrorCode != -1);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/GeofencingEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */