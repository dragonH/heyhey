package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.common.util.zzu;
import java.io.File;
import java.io.IOException;
import java.security.KeyPair;

final class zzr {
  private Context zzahz;
  
  SharedPreferences zzhul;
  
  public zzr(Context paramContext) {
    this(paramContext, "com.google.android.gms.appid");
  }
  
  private zzr(Context paramContext, String paramString) {
    this.zzahz = paramContext;
    this.zzhul = paramContext.getSharedPreferences(paramString, 0);
    String str = String.valueOf(paramString);
    if ("-no-backup".length() != 0) {
      str = str.concat("-no-backup");
    } else {
      str = new String(str);
    } 
    File file = new File(zzu.getNoBackupFilesDir(this.zzahz), str);
    if (!file.exists())
      try {
        if (file.createNewFile() && !isEmpty()) {
          Log.i("InstanceID/Store", "App restored, clearing state");
          FirebaseInstanceId.zza(this.zzahz, this);
        } 
        return;
      } catch (IOException iOException) {
        if (Log.isLoggable("InstanceID/Store", 3)) {
          String str1 = String.valueOf(iOException.getMessage());
          if (str1.length() != 0) {
            str1 = "Error creating file in no backup dir: ".concat(str1);
          } else {
            str1 = new String("Error creating file in no backup dir: ");
          } 
          Log.d("InstanceID/Store", str1);
        } 
      }  
  }
  
  private static String zzbl(String paramString1, String paramString2) {
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString1).length() + "|S|".length() + String.valueOf(paramString2).length());
    stringBuilder.append(paramString1);
    stringBuilder.append("|S|");
    stringBuilder.append(paramString2);
    return stringBuilder.toString();
  }
  
  private final void zzht(String paramString) {
    SharedPreferences.Editor editor = this.zzhul.edit();
    for (String str : this.zzhul.getAll().keySet()) {
      if (str.startsWith(paramString))
        editor.remove(str); 
    } 
    editor.commit();
  }
  
  private static String zzn(String paramString1, String paramString2, String paramString3) {
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString1).length() + 1 + "|T|".length() + String.valueOf(paramString2).length() + String.valueOf(paramString3).length());
    stringBuilder.append(paramString1);
    stringBuilder.append("|T|");
    stringBuilder.append(paramString2);
    stringBuilder.append("|");
    stringBuilder.append(paramString3);
    return stringBuilder.toString();
  }
  
  public final boolean isEmpty() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzhul : Landroid/content/SharedPreferences;
    //   6: invokeinterface getAll : ()Ljava/util/Map;
    //   11: invokeinterface isEmpty : ()Z
    //   16: istore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: iload_1
    //   20: ireturn
    //   21: astore_2
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_2
    //   25: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	21	finally
  }
  
  public final void zza(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload #4
    //   4: aload #5
    //   6: invokestatic currentTimeMillis : ()J
    //   9: invokestatic zzc : (Ljava/lang/String;Ljava/lang/String;J)Ljava/lang/String;
    //   12: astore #4
    //   14: aload #4
    //   16: ifnonnull -> 22
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: aload_0
    //   23: getfield zzhul : Landroid/content/SharedPreferences;
    //   26: invokeinterface edit : ()Landroid/content/SharedPreferences$Editor;
    //   31: astore #5
    //   33: aload #5
    //   35: aload_1
    //   36: aload_2
    //   37: aload_3
    //   38: invokestatic zzn : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   41: aload #4
    //   43: invokeinterface putString : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
    //   48: pop
    //   49: aload #5
    //   51: invokeinterface commit : ()Z
    //   56: pop
    //   57: aload_0
    //   58: monitorexit
    //   59: return
    //   60: astore_1
    //   61: aload_0
    //   62: monitorexit
    //   63: aload_1
    //   64: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	60	finally
    //   22	57	60	finally
  }
  
  public final void zzasv() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzhul : Landroid/content/SharedPreferences;
    //   6: invokeinterface edit : ()Landroid/content/SharedPreferences$Editor;
    //   11: invokeinterface clear : ()Landroid/content/SharedPreferences$Editor;
    //   16: invokeinterface commit : ()Z
    //   21: pop
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	25	finally
  }
  
  public final void zzf(String paramString1, String paramString2, String paramString3) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: aload_2
    //   4: aload_3
    //   5: invokestatic zzn : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   8: astore_1
    //   9: aload_0
    //   10: getfield zzhul : Landroid/content/SharedPreferences;
    //   13: invokeinterface edit : ()Landroid/content/SharedPreferences$Editor;
    //   18: astore_2
    //   19: aload_2
    //   20: aload_1
    //   21: invokeinterface remove : (Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
    //   26: pop
    //   27: aload_2
    //   28: invokeinterface commit : ()Z
    //   33: pop
    //   34: aload_0
    //   35: monitorexit
    //   36: return
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: athrow
    // Exception table:
    //   from	to	target	type
    //   2	34	37	finally
  }
  
  public final void zzhu(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   7: ldc '|T|'
    //   9: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   12: invokespecial zzht : (Ljava/lang/String;)V
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	18	finally
  }
  
  public final zzs zzo(String paramString1, String paramString2, String paramString3) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzhul : Landroid/content/SharedPreferences;
    //   6: aload_1
    //   7: aload_2
    //   8: aload_3
    //   9: invokestatic zzn : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   12: aconst_null
    //   13: invokeinterface getString : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   18: invokestatic zzrf : (Ljava/lang/String;)Lcom/google/firebase/iid/zzs;
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: areturn
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	26	finally
  }
  
  public final long zzrb(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ldc 'cre'
    //   5: invokestatic zzbl : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   8: astore_1
    //   9: aload_0
    //   10: getfield zzhul : Landroid/content/SharedPreferences;
    //   13: aload_1
    //   14: aconst_null
    //   15: invokeinterface getString : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   20: astore_1
    //   21: aload_1
    //   22: ifnull -> 34
    //   25: aload_1
    //   26: invokestatic parseLong : (Ljava/lang/String;)J
    //   29: lstore_2
    //   30: aload_0
    //   31: monitorexit
    //   32: lload_2
    //   33: lreturn
    //   34: aload_0
    //   35: monitorexit
    //   36: lconst_0
    //   37: lreturn
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: athrow
    //   43: astore_1
    //   44: goto -> 34
    // Exception table:
    //   from	to	target	type
    //   2	21	38	finally
    //   25	30	43	java/lang/NumberFormatException
    //   25	30	38	finally
  }
  
  final KeyPair zzrc(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic zzasp : ()Ljava/security/KeyPair;
    //   5: astore_2
    //   6: invokestatic currentTimeMillis : ()J
    //   9: lstore_3
    //   10: aload_0
    //   11: getfield zzhul : Landroid/content/SharedPreferences;
    //   14: invokeinterface edit : ()Landroid/content/SharedPreferences$Editor;
    //   19: astore #5
    //   21: aload #5
    //   23: aload_1
    //   24: ldc '|P|'
    //   26: invokestatic zzbl : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   29: aload_2
    //   30: invokevirtual getPublic : ()Ljava/security/PublicKey;
    //   33: invokeinterface getEncoded : ()[B
    //   38: invokestatic zzn : ([B)Ljava/lang/String;
    //   41: invokeinterface putString : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
    //   46: pop
    //   47: aload #5
    //   49: aload_1
    //   50: ldc '|K|'
    //   52: invokestatic zzbl : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   55: aload_2
    //   56: invokevirtual getPrivate : ()Ljava/security/PrivateKey;
    //   59: invokeinterface getEncoded : ()[B
    //   64: invokestatic zzn : ([B)Ljava/lang/String;
    //   67: invokeinterface putString : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
    //   72: pop
    //   73: aload #5
    //   75: aload_1
    //   76: ldc 'cre'
    //   78: invokestatic zzbl : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   81: lload_3
    //   82: invokestatic toString : (J)Ljava/lang/String;
    //   85: invokeinterface putString : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
    //   90: pop
    //   91: aload #5
    //   93: invokeinterface commit : ()Z
    //   98: pop
    //   99: aload_0
    //   100: monitorexit
    //   101: aload_2
    //   102: areturn
    //   103: astore_1
    //   104: aload_0
    //   105: monitorexit
    //   106: aload_1
    //   107: athrow
    // Exception table:
    //   from	to	target	type
    //   2	99	103	finally
  }
  
  final void zzrd(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   7: ldc '|'
    //   9: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   12: invokespecial zzht : (Ljava/lang/String;)V
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	18	finally
  }
  
  public final KeyPair zzre(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzhul : Landroid/content/SharedPreferences;
    //   6: aload_1
    //   7: ldc '|P|'
    //   9: invokestatic zzbl : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   12: aconst_null
    //   13: invokeinterface getString : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   18: astore_2
    //   19: aload_0
    //   20: getfield zzhul : Landroid/content/SharedPreferences;
    //   23: aload_1
    //   24: ldc '|K|'
    //   26: invokestatic zzbl : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   29: aconst_null
    //   30: invokeinterface getString : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   35: astore_1
    //   36: aload_2
    //   37: ifnull -> 181
    //   40: aload_1
    //   41: ifnonnull -> 47
    //   44: goto -> 181
    //   47: aload_2
    //   48: bipush #8
    //   50: invokestatic decode : (Ljava/lang/String;I)[B
    //   53: astore_3
    //   54: aload_1
    //   55: bipush #8
    //   57: invokestatic decode : (Ljava/lang/String;I)[B
    //   60: astore_2
    //   61: ldc_w 'RSA'
    //   64: invokestatic getInstance : (Ljava/lang/String;)Ljava/security/KeyFactory;
    //   67: astore_1
    //   68: new java/security/spec/X509EncodedKeySpec
    //   71: astore #4
    //   73: aload #4
    //   75: aload_3
    //   76: invokespecial <init> : ([B)V
    //   79: aload_1
    //   80: aload #4
    //   82: invokevirtual generatePublic : (Ljava/security/spec/KeySpec;)Ljava/security/PublicKey;
    //   85: astore_3
    //   86: new java/security/spec/PKCS8EncodedKeySpec
    //   89: astore #4
    //   91: aload #4
    //   93: aload_2
    //   94: invokespecial <init> : ([B)V
    //   97: new java/security/KeyPair
    //   100: dup
    //   101: aload_3
    //   102: aload_1
    //   103: aload #4
    //   105: invokevirtual generatePrivate : (Ljava/security/spec/KeySpec;)Ljava/security/PrivateKey;
    //   108: invokespecial <init> : (Ljava/security/PublicKey;Ljava/security/PrivateKey;)V
    //   111: astore_1
    //   112: aload_0
    //   113: monitorexit
    //   114: aload_1
    //   115: areturn
    //   116: astore_1
    //   117: goto -> 121
    //   120: astore_1
    //   121: aload_1
    //   122: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   125: astore_2
    //   126: aload_2
    //   127: invokevirtual length : ()I
    //   130: istore #5
    //   132: new java/lang/StringBuilder
    //   135: astore_1
    //   136: aload_1
    //   137: iload #5
    //   139: bipush #19
    //   141: iadd
    //   142: invokespecial <init> : (I)V
    //   145: aload_1
    //   146: ldc_w 'Invalid key stored '
    //   149: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: pop
    //   153: aload_1
    //   154: aload_2
    //   155: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: pop
    //   159: ldc 'InstanceID/Store'
    //   161: aload_1
    //   162: invokevirtual toString : ()Ljava/lang/String;
    //   165: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   168: pop
    //   169: aload_0
    //   170: getfield zzahz : Landroid/content/Context;
    //   173: aload_0
    //   174: invokestatic zza : (Landroid/content/Context;Lcom/google/firebase/iid/zzr;)V
    //   177: aload_0
    //   178: monitorexit
    //   179: aconst_null
    //   180: areturn
    //   181: aload_0
    //   182: monitorexit
    //   183: aconst_null
    //   184: areturn
    //   185: astore_1
    //   186: aload_0
    //   187: monitorexit
    //   188: aload_1
    //   189: athrow
    // Exception table:
    //   from	to	target	type
    //   2	36	185	finally
    //   47	112	120	java/security/spec/InvalidKeySpecException
    //   47	112	116	java/security/NoSuchAlgorithmException
    //   47	112	185	finally
    //   121	177	185	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */