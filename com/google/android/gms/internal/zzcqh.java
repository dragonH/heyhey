package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.common.util.zzx;

public final class zzcqh {
  private static boolean DEBUG = false;
  
  private static String TAG = "WakeLock";
  
  private static String zzjnz = "*gcore*:";
  
  private final Context mContext;
  
  private final String zzfxz;
  
  private final String zzfyb;
  
  private final PowerManager.WakeLock zzjoa;
  
  private WorkSource zzjob;
  
  private final int zzjoc;
  
  private final String zzjod;
  
  private boolean zzjoe = true;
  
  private int zzjof;
  
  private int zzjog;
  
  public zzcqh(Context paramContext, int paramInt, String paramString) {
    this(paramContext, 1, paramString, null, str);
  }
  
  @SuppressLint({"UnwrappedWakeLock"})
  private zzcqh(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3) {
    this(paramContext, 1, paramString1, null, paramString3, null);
  }
  
  @SuppressLint({"UnwrappedWakeLock"})
  private zzcqh(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4) {
    zzbp.zzh(paramString1, "Wake lock name can NOT be empty");
    this.zzjoc = paramInt;
    this.zzjod = null;
    this.zzfyb = null;
    Context context = paramContext.getApplicationContext();
    this.mContext = context;
    if (!"com.google.android.gms".equals(paramContext.getPackageName())) {
      paramString2 = String.valueOf(zzjnz);
      String str = String.valueOf(paramString1);
      if (str.length() != 0) {
        paramString2 = paramString2.concat(str);
      } else {
        paramString2 = new String(paramString2);
      } 
      this.zzfxz = paramString2;
    } else {
      this.zzfxz = paramString1;
    } 
    PowerManager.WakeLock wakeLock = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(paramInt, paramString1);
    this.zzjoa = wakeLock;
    if (zzx.zzco(context)) {
      paramString1 = paramString3;
      if (zzt.zzgm(paramString3))
        paramString1 = paramContext.getPackageName(); 
      WorkSource workSource = zzx.zzac(paramContext, paramString1);
      this.zzjob = workSource;
      if (workSource != null && zzx.zzco(context)) {
        WorkSource workSource1 = this.zzjob;
        if (workSource1 != null) {
          workSource1.add(workSource);
        } else {
          this.zzjob = workSource;
        } 
        workSource = this.zzjob;
        try {
          wakeLock.setWorkSource(workSource);
          return;
        } catch (IllegalArgumentException illegalArgumentException) {
          Log.wtf(TAG, illegalArgumentException.toString());
        } 
      } 
    } 
  }
  
  private final String zzg(String paramString, boolean paramBoolean) {
    return this.zzjoe ? (paramBoolean ? null : this.zzjod) : this.zzjod;
  }
  
  private final boolean zzlb(String paramString) {
    if (TextUtils.isEmpty(null))
      return false; 
    throw null;
  }
  
  public final void acquire(long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: aconst_null
    //   2: invokespecial zzlb : (Ljava/lang/String;)Z
    //   5: istore_3
    //   6: aload_0
    //   7: aconst_null
    //   8: iload_3
    //   9: invokespecial zzg : (Ljava/lang/String;Z)Ljava/lang/String;
    //   12: astore #4
    //   14: aload_0
    //   15: monitorenter
    //   16: aload_0
    //   17: getfield zzjof : I
    //   20: ifgt -> 30
    //   23: aload_0
    //   24: getfield zzjog : I
    //   27: ifle -> 50
    //   30: aload_0
    //   31: getfield zzjoa : Landroid/os/PowerManager$WakeLock;
    //   34: invokevirtual isHeld : ()Z
    //   37: ifne -> 50
    //   40: aload_0
    //   41: iconst_0
    //   42: putfield zzjof : I
    //   45: aload_0
    //   46: iconst_0
    //   47: putfield zzjog : I
    //   50: aload_0
    //   51: getfield zzjoe : Z
    //   54: istore #5
    //   56: iload #5
    //   58: ifeq -> 84
    //   61: aload_0
    //   62: getfield zzjof : I
    //   65: istore #6
    //   67: aload_0
    //   68: iload #6
    //   70: iconst_1
    //   71: iadd
    //   72: putfield zzjof : I
    //   75: iload #6
    //   77: ifeq -> 96
    //   80: iload_3
    //   81: ifne -> 96
    //   84: iload #5
    //   86: ifne -> 149
    //   89: aload_0
    //   90: getfield zzjog : I
    //   93: ifne -> 149
    //   96: invokestatic zzalb : ()Lcom/google/android/gms/common/stats/zze;
    //   99: pop
    //   100: aload_0
    //   101: getfield mContext : Landroid/content/Context;
    //   104: aload_0
    //   105: getfield zzjoa : Landroid/os/PowerManager$WakeLock;
    //   108: aload #4
    //   110: invokestatic zza : (Landroid/os/PowerManager$WakeLock;Ljava/lang/String;)Ljava/lang/String;
    //   113: bipush #7
    //   115: aload_0
    //   116: getfield zzfxz : Ljava/lang/String;
    //   119: aload #4
    //   121: aconst_null
    //   122: aload_0
    //   123: getfield zzjoc : I
    //   126: aload_0
    //   127: getfield zzjob : Landroid/os/WorkSource;
    //   130: invokestatic zzb : (Landroid/os/WorkSource;)Ljava/util/List;
    //   133: ldc2_w 1000
    //   136: invokestatic zza : (Landroid/content/Context;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;J)V
    //   139: aload_0
    //   140: aload_0
    //   141: getfield zzjog : I
    //   144: iconst_1
    //   145: iadd
    //   146: putfield zzjog : I
    //   149: aload_0
    //   150: monitorexit
    //   151: aload_0
    //   152: getfield zzjoa : Landroid/os/PowerManager$WakeLock;
    //   155: ldc2_w 1000
    //   158: invokevirtual acquire : (J)V
    //   161: return
    //   162: astore #4
    //   164: aload_0
    //   165: monitorexit
    //   166: aload #4
    //   168: athrow
    // Exception table:
    //   from	to	target	type
    //   16	30	162	finally
    //   30	50	162	finally
    //   50	56	162	finally
    //   61	75	162	finally
    //   89	96	162	finally
    //   96	149	162	finally
    //   149	151	162	finally
    //   164	166	162	finally
  }
  
  public final boolean isHeld() {
    return this.zzjoa.isHeld();
  }
  
  public final void release() {
    // Byte code:
    //   0: aload_0
    //   1: aconst_null
    //   2: invokespecial zzlb : (Ljava/lang/String;)Z
    //   5: istore_1
    //   6: aload_0
    //   7: aconst_null
    //   8: iload_1
    //   9: invokespecial zzg : (Ljava/lang/String;Z)Ljava/lang/String;
    //   12: astore_2
    //   13: aload_0
    //   14: monitorenter
    //   15: aload_0
    //   16: getfield zzjoe : Z
    //   19: istore_3
    //   20: iload_3
    //   21: ifeq -> 47
    //   24: aload_0
    //   25: getfield zzjof : I
    //   28: iconst_1
    //   29: isub
    //   30: istore #4
    //   32: aload_0
    //   33: iload #4
    //   35: putfield zzjof : I
    //   38: iload #4
    //   40: ifeq -> 59
    //   43: iload_1
    //   44: ifne -> 59
    //   47: iload_3
    //   48: ifne -> 107
    //   51: aload_0
    //   52: getfield zzjog : I
    //   55: iconst_1
    //   56: if_icmpne -> 107
    //   59: invokestatic zzalb : ()Lcom/google/android/gms/common/stats/zze;
    //   62: pop
    //   63: aload_0
    //   64: getfield mContext : Landroid/content/Context;
    //   67: aload_0
    //   68: getfield zzjoa : Landroid/os/PowerManager$WakeLock;
    //   71: aload_2
    //   72: invokestatic zza : (Landroid/os/PowerManager$WakeLock;Ljava/lang/String;)Ljava/lang/String;
    //   75: bipush #8
    //   77: aload_0
    //   78: getfield zzfxz : Ljava/lang/String;
    //   81: aload_2
    //   82: aconst_null
    //   83: aload_0
    //   84: getfield zzjoc : I
    //   87: aload_0
    //   88: getfield zzjob : Landroid/os/WorkSource;
    //   91: invokestatic zzb : (Landroid/os/WorkSource;)Ljava/util/List;
    //   94: invokestatic zza : (Landroid/content/Context;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;)V
    //   97: aload_0
    //   98: aload_0
    //   99: getfield zzjog : I
    //   102: iconst_1
    //   103: isub
    //   104: putfield zzjog : I
    //   107: aload_0
    //   108: monitorexit
    //   109: aload_0
    //   110: getfield zzjoa : Landroid/os/PowerManager$WakeLock;
    //   113: invokevirtual release : ()V
    //   116: return
    //   117: astore_2
    //   118: aload_0
    //   119: monitorexit
    //   120: aload_2
    //   121: athrow
    // Exception table:
    //   from	to	target	type
    //   15	20	117	finally
    //   24	38	117	finally
    //   51	59	117	finally
    //   59	107	117	finally
    //   107	109	117	finally
    //   118	120	117	finally
  }
  
  public final void setReferenceCounted(boolean paramBoolean) {
    this.zzjoa.setReferenceCounted(false);
    this.zzjoe = false;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcqh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */