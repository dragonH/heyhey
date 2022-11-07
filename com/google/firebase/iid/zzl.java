package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.SystemClock;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.iid.MessengerCompat;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Iterator;
import java.util.Random;

public final class zzl {
  private static PendingIntent zzhrm;
  
  private static String zzhtw;
  
  private static boolean zzhtx = false;
  
  private static int zzhty;
  
  private static int zzhtz;
  
  private static int zzhua;
  
  private static BroadcastReceiver zzhub;
  
  private Context zzahz;
  
  private Messenger zzhrq;
  
  private Messenger zzhud;
  
  private MessengerCompat zzhue;
  
  private long zzhuf;
  
  private long zzhug;
  
  private int zzhuh;
  
  private int zzhui;
  
  private long zzhuj;
  
  private final SimpleArrayMap<String, zzp> zznfw = new SimpleArrayMap();
  
  public zzl(Context paramContext) {
    this.zzahz = paramContext;
  }
  
  private static String zza(KeyPair paramKeyPair, String... paramVarArgs) {
    String str;
    try {
      byte[] arrayOfByte = TextUtils.join("\n", (Object[])paramVarArgs).getBytes("UTF-8");
      try {
        String str1;
        PrivateKey privateKey = paramKeyPair.getPrivate();
        if (privateKey instanceof java.security.interfaces.RSAPrivateKey) {
          str1 = "SHA256withRSA";
        } else {
          str1 = "SHA256withECDSA";
        } 
        Signature signature = Signature.getInstance(str1);
        signature.initSign(privateKey);
        signature.update(arrayOfByte);
        return FirebaseInstanceId.zzn(signature.sign());
      } catch (GeneralSecurityException generalSecurityException) {
        str = "Unable to sign registration request";
      } 
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      str = "Unable to encode string";
    } 
    Log.e("InstanceID/Rpc", str, unsupportedEncodingException);
    return null;
  }
  
  private static boolean zza(PackageManager paramPackageManager) {
    Iterator iterator = paramPackageManager.queryBroadcastReceivers(new Intent("com.google.iid.TOKEN_REQUEST"), 0).iterator();
    while (iterator.hasNext()) {
      if (zza(paramPackageManager, ((ResolveInfo)iterator.next()).activityInfo.packageName, "com.google.iid.TOKEN_REQUEST")) {
        zzhtx = true;
        return true;
      } 
    } 
    return false;
  }
  
  private static boolean zza(PackageManager paramPackageManager, String paramString1, String paramString2) {
    if (paramPackageManager.checkPermission("com.google.android.c2dm.permission.SEND", paramString1) == 0)
      return zzb(paramPackageManager, paramString1); 
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString1).length() + 56 + String.valueOf(paramString2).length());
    stringBuilder.append("Possible malicious package ");
    stringBuilder.append(paramString1);
    stringBuilder.append(" declares ");
    stringBuilder.append(paramString2);
    stringBuilder.append(" without permission");
    Log.w("InstanceID/Rpc", stringBuilder.toString());
    return false;
  }
  
  private final void zzast() {
    if (this.zzhrq != null)
      return; 
    zzdf(this.zzahz);
    this.zzhrq = new Messenger(new zzm(this, Looper.getMainLooper()));
  }
  
  public static String zzasu() {
    // Byte code:
    //   0: ldc com/google/firebase/iid/zzl
    //   2: monitorenter
    //   3: getstatic com/google/firebase/iid/zzl.zzhua : I
    //   6: istore_0
    //   7: iload_0
    //   8: iconst_1
    //   9: iadd
    //   10: putstatic com/google/firebase/iid/zzl.zzhua : I
    //   13: iload_0
    //   14: invokestatic toString : (I)Ljava/lang/String;
    //   17: astore_1
    //   18: ldc com/google/firebase/iid/zzl
    //   20: monitorexit
    //   21: aload_1
    //   22: areturn
    //   23: astore_1
    //   24: ldc com/google/firebase/iid/zzl
    //   26: monitorexit
    //   27: aload_1
    //   28: athrow
    // Exception table:
    //   from	to	target	type
    //   3	18	23	finally
  }
  
  private final Intent zzb(Bundle paramBundle, KeyPair paramKeyPair) throws IOException {
    // Byte code:
    //   0: invokestatic zzasu : ()Ljava/lang/String;
    //   3: astore_3
    //   4: new com/google/firebase/iid/zzo
    //   7: dup
    //   8: aconst_null
    //   9: invokespecial <init> : (Lcom/google/firebase/iid/zzm;)V
    //   12: astore #4
    //   14: aload_0
    //   15: getfield zznfw : Landroid/support/v4/util/SimpleArrayMap;
    //   18: astore #5
    //   20: aload #5
    //   22: monitorenter
    //   23: aload_0
    //   24: getfield zznfw : Landroid/support/v4/util/SimpleArrayMap;
    //   27: aload_3
    //   28: aload #4
    //   30: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   33: pop
    //   34: aload #5
    //   36: monitorexit
    //   37: invokestatic elapsedRealtime : ()J
    //   40: lstore #6
    //   42: aload_0
    //   43: getfield zzhuj : J
    //   46: lstore #8
    //   48: lload #8
    //   50: lconst_0
    //   51: lcmp
    //   52: ifeq -> 136
    //   55: lload #6
    //   57: lload #8
    //   59: lcmp
    //   60: ifle -> 66
    //   63: goto -> 136
    //   66: aload_0
    //   67: getfield zzhui : I
    //   70: istore #10
    //   72: new java/lang/StringBuilder
    //   75: dup
    //   76: bipush #78
    //   78: invokespecial <init> : (I)V
    //   81: astore_1
    //   82: aload_1
    //   83: ldc_w 'Backoff mode, next request attempt: '
    //   86: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: pop
    //   90: aload_1
    //   91: lload #8
    //   93: lload #6
    //   95: lsub
    //   96: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   99: pop
    //   100: aload_1
    //   101: ldc_w ' interval: '
    //   104: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: pop
    //   108: aload_1
    //   109: iload #10
    //   111: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   114: pop
    //   115: ldc 'InstanceID/Rpc'
    //   117: aload_1
    //   118: invokevirtual toString : ()Ljava/lang/String;
    //   121: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   124: pop
    //   125: new java/io/IOException
    //   128: dup
    //   129: ldc_w 'RETRY_LATER'
    //   132: invokespecial <init> : (Ljava/lang/String;)V
    //   135: athrow
    //   136: aload_0
    //   137: invokespecial zzast : ()V
    //   140: getstatic com/google/firebase/iid/zzl.zzhtw : Ljava/lang/String;
    //   143: ifnull -> 790
    //   146: aload_0
    //   147: invokestatic elapsedRealtime : ()J
    //   150: putfield zzhuf : J
    //   153: getstatic com/google/firebase/iid/zzl.zzhtx : Z
    //   156: ifeq -> 166
    //   159: ldc 'com.google.iid.TOKEN_REQUEST'
    //   161: astore #5
    //   163: goto -> 171
    //   166: ldc_w 'com.google.android.c2dm.intent.REGISTER'
    //   169: astore #5
    //   171: new android/content/Intent
    //   174: dup
    //   175: aload #5
    //   177: invokespecial <init> : (Ljava/lang/String;)V
    //   180: astore #5
    //   182: aload #5
    //   184: getstatic com/google/firebase/iid/zzl.zzhtw : Ljava/lang/String;
    //   187: invokevirtual setPackage : (Ljava/lang/String;)Landroid/content/Intent;
    //   190: pop
    //   191: aload_0
    //   192: getfield zzahz : Landroid/content/Context;
    //   195: astore #11
    //   197: aload_1
    //   198: ldc_w 'gmsv'
    //   201: aload #11
    //   203: aload #11
    //   205: invokestatic zzdf : (Landroid/content/Context;)Ljava/lang/String;
    //   208: invokestatic zzao : (Landroid/content/Context;Ljava/lang/String;)I
    //   211: invokestatic toString : (I)Ljava/lang/String;
    //   214: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   217: aload_1
    //   218: ldc_w 'osv'
    //   221: getstatic android/os/Build$VERSION.SDK_INT : I
    //   224: invokestatic toString : (I)Ljava/lang/String;
    //   227: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   230: aload_1
    //   231: ldc_w 'app_ver'
    //   234: aload_0
    //   235: getfield zzahz : Landroid/content/Context;
    //   238: invokestatic zzej : (Landroid/content/Context;)I
    //   241: invokestatic toString : (I)Ljava/lang/String;
    //   244: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   247: aload_1
    //   248: ldc_w 'app_ver_name'
    //   251: aload_0
    //   252: getfield zzahz : Landroid/content/Context;
    //   255: invokestatic zzdd : (Landroid/content/Context;)Ljava/lang/String;
    //   258: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   261: aload_1
    //   262: ldc_w 'cliv'
    //   265: ldc_w 'fiid-11400000'
    //   268: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   271: aload_1
    //   272: ldc_w 'appid'
    //   275: aload_2
    //   276: invokestatic zza : (Ljava/security/KeyPair;)Ljava/lang/String;
    //   279: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   282: aload_2
    //   283: invokevirtual getPublic : ()Ljava/security/PublicKey;
    //   286: invokeinterface getEncoded : ()[B
    //   291: invokestatic zzn : ([B)Ljava/lang/String;
    //   294: astore #11
    //   296: aload_1
    //   297: ldc_w 'pub2'
    //   300: aload #11
    //   302: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   305: aload_1
    //   306: ldc_w 'sig'
    //   309: aload_2
    //   310: iconst_2
    //   311: anewarray java/lang/String
    //   314: dup
    //   315: iconst_0
    //   316: aload_0
    //   317: getfield zzahz : Landroid/content/Context;
    //   320: invokevirtual getPackageName : ()Ljava/lang/String;
    //   323: aastore
    //   324: dup
    //   325: iconst_1
    //   326: aload #11
    //   328: aastore
    //   329: invokestatic zza : (Ljava/security/KeyPair;[Ljava/lang/String;)Ljava/lang/String;
    //   332: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   335: aload #5
    //   337: aload_1
    //   338: invokevirtual putExtras : (Landroid/os/Bundle;)Landroid/content/Intent;
    //   341: pop
    //   342: aload_0
    //   343: getfield zzahz : Landroid/content/Context;
    //   346: aload #5
    //   348: invokestatic zzd : (Landroid/content/Context;Landroid/content/Intent;)V
    //   351: aload_0
    //   352: invokestatic elapsedRealtime : ()J
    //   355: putfield zzhuf : J
    //   358: new java/lang/StringBuilder
    //   361: dup
    //   362: aload_3
    //   363: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   366: invokevirtual length : ()I
    //   369: iconst_5
    //   370: iadd
    //   371: invokespecial <init> : (I)V
    //   374: astore_1
    //   375: aload_1
    //   376: ldc_w '|ID|'
    //   379: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   382: pop
    //   383: aload_1
    //   384: aload_3
    //   385: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   388: pop
    //   389: aload_1
    //   390: ldc_w '|'
    //   393: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   396: pop
    //   397: aload #5
    //   399: ldc_w 'kid'
    //   402: aload_1
    //   403: invokevirtual toString : ()Ljava/lang/String;
    //   406: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   409: pop
    //   410: new java/lang/StringBuilder
    //   413: dup
    //   414: aload_3
    //   415: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   418: invokevirtual length : ()I
    //   421: iconst_5
    //   422: iadd
    //   423: invokespecial <init> : (I)V
    //   426: astore_1
    //   427: aload_1
    //   428: ldc_w '|ID|'
    //   431: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   434: pop
    //   435: aload_1
    //   436: aload_3
    //   437: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   440: pop
    //   441: aload_1
    //   442: ldc_w '|'
    //   445: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   448: pop
    //   449: aload #5
    //   451: ldc_w 'X-kid'
    //   454: aload_1
    //   455: invokevirtual toString : ()Ljava/lang/String;
    //   458: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   461: pop
    //   462: ldc_w 'com.google.android.gsf'
    //   465: getstatic com/google/firebase/iid/zzl.zzhtw : Ljava/lang/String;
    //   468: invokevirtual equals : (Ljava/lang/Object;)Z
    //   471: istore #12
    //   473: ldc 'InstanceID/Rpc'
    //   475: iconst_3
    //   476: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   479: ifeq -> 530
    //   482: aload #5
    //   484: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   487: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   490: astore_1
    //   491: new java/lang/StringBuilder
    //   494: dup
    //   495: aload_1
    //   496: invokevirtual length : ()I
    //   499: bipush #8
    //   501: iadd
    //   502: invokespecial <init> : (I)V
    //   505: astore_2
    //   506: aload_2
    //   507: ldc_w 'Sending '
    //   510: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   513: pop
    //   514: aload_2
    //   515: aload_1
    //   516: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   519: pop
    //   520: ldc 'InstanceID/Rpc'
    //   522: aload_2
    //   523: invokevirtual toString : ()Ljava/lang/String;
    //   526: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   529: pop
    //   530: iload #12
    //   532: ifeq -> 621
    //   535: aload_0
    //   536: monitorenter
    //   537: getstatic com/google/firebase/iid/zzl.zzhub : Landroid/content/BroadcastReceiver;
    //   540: ifnonnull -> 611
    //   543: new com/google/firebase/iid/zzn
    //   546: astore_1
    //   547: aload_1
    //   548: aload_0
    //   549: invokespecial <init> : (Lcom/google/firebase/iid/zzl;)V
    //   552: aload_1
    //   553: putstatic com/google/firebase/iid/zzl.zzhub : Landroid/content/BroadcastReceiver;
    //   556: ldc 'InstanceID/Rpc'
    //   558: iconst_3
    //   559: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   562: ifeq -> 574
    //   565: ldc 'InstanceID/Rpc'
    //   567: ldc_w 'Registered GSF callback receiver'
    //   570: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   573: pop
    //   574: new android/content/IntentFilter
    //   577: astore_1
    //   578: aload_1
    //   579: ldc_w 'com.google.android.c2dm.intent.REGISTRATION'
    //   582: invokespecial <init> : (Ljava/lang/String;)V
    //   585: aload_1
    //   586: aload_0
    //   587: getfield zzahz : Landroid/content/Context;
    //   590: invokevirtual getPackageName : ()Ljava/lang/String;
    //   593: invokevirtual addCategory : (Ljava/lang/String;)V
    //   596: aload_0
    //   597: getfield zzahz : Landroid/content/Context;
    //   600: getstatic com/google/firebase/iid/zzl.zzhub : Landroid/content/BroadcastReceiver;
    //   603: aload_1
    //   604: ldc 'com.google.android.c2dm.permission.SEND'
    //   606: aconst_null
    //   607: invokevirtual registerReceiver : (Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;Ljava/lang/String;Landroid/os/Handler;)Landroid/content/Intent;
    //   610: pop
    //   611: aload_0
    //   612: monitorexit
    //   613: goto -> 723
    //   616: astore_1
    //   617: aload_0
    //   618: monitorexit
    //   619: aload_1
    //   620: athrow
    //   621: aload #5
    //   623: ldc_w 'google.messenger'
    //   626: aload_0
    //   627: getfield zzhrq : Landroid/os/Messenger;
    //   630: invokevirtual putExtra : (Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
    //   633: pop
    //   634: aload_0
    //   635: getfield zzhud : Landroid/os/Messenger;
    //   638: ifnonnull -> 648
    //   641: aload_0
    //   642: getfield zzhue : Lcom/google/android/gms/iid/MessengerCompat;
    //   645: ifnull -> 705
    //   648: invokestatic obtain : ()Landroid/os/Message;
    //   651: astore_2
    //   652: aload_2
    //   653: aload #5
    //   655: putfield obj : Ljava/lang/Object;
    //   658: aload_0
    //   659: getfield zzhud : Landroid/os/Messenger;
    //   662: astore_1
    //   663: aload_1
    //   664: ifnull -> 675
    //   667: aload_1
    //   668: aload_2
    //   669: invokevirtual send : (Landroid/os/Message;)V
    //   672: goto -> 733
    //   675: aload_0
    //   676: getfield zzhue : Lcom/google/android/gms/iid/MessengerCompat;
    //   679: aload_2
    //   680: invokevirtual send : (Landroid/os/Message;)V
    //   683: goto -> 733
    //   686: astore_1
    //   687: ldc 'InstanceID/Rpc'
    //   689: iconst_3
    //   690: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   693: ifeq -> 705
    //   696: ldc 'InstanceID/Rpc'
    //   698: ldc_w 'Messenger failed, fallback to startService'
    //   701: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   704: pop
    //   705: getstatic com/google/firebase/iid/zzl.zzhtx : Z
    //   708: ifeq -> 723
    //   711: aload_0
    //   712: getfield zzahz : Landroid/content/Context;
    //   715: aload #5
    //   717: invokevirtual sendBroadcast : (Landroid/content/Intent;)V
    //   720: goto -> 733
    //   723: aload_0
    //   724: getfield zzahz : Landroid/content/Context;
    //   727: aload #5
    //   729: invokevirtual startService : (Landroid/content/Intent;)Landroid/content/ComponentName;
    //   732: pop
    //   733: aload #4
    //   735: invokevirtual zzcgd : ()Landroid/content/Intent;
    //   738: astore_2
    //   739: aload_0
    //   740: getfield zznfw : Landroid/support/v4/util/SimpleArrayMap;
    //   743: astore_1
    //   744: aload_1
    //   745: monitorenter
    //   746: aload_0
    //   747: getfield zznfw : Landroid/support/v4/util/SimpleArrayMap;
    //   750: aload_3
    //   751: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   754: pop
    //   755: aload_1
    //   756: monitorexit
    //   757: aload_2
    //   758: areturn
    //   759: astore_2
    //   760: aload_1
    //   761: monitorexit
    //   762: aload_2
    //   763: athrow
    //   764: astore_2
    //   765: aload_0
    //   766: getfield zznfw : Landroid/support/v4/util/SimpleArrayMap;
    //   769: astore_1
    //   770: aload_1
    //   771: monitorenter
    //   772: aload_0
    //   773: getfield zznfw : Landroid/support/v4/util/SimpleArrayMap;
    //   776: aload_3
    //   777: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   780: pop
    //   781: aload_1
    //   782: monitorexit
    //   783: aload_2
    //   784: athrow
    //   785: astore_2
    //   786: aload_1
    //   787: monitorexit
    //   788: aload_2
    //   789: athrow
    //   790: new java/io/IOException
    //   793: dup
    //   794: ldc_w 'MISSING_INSTANCEID_SERVICE'
    //   797: invokespecial <init> : (Ljava/lang/String;)V
    //   800: athrow
    //   801: astore_1
    //   802: aload #5
    //   804: monitorexit
    //   805: aload_1
    //   806: athrow
    // Exception table:
    //   from	to	target	type
    //   23	37	801	finally
    //   537	574	616	finally
    //   574	611	616	finally
    //   611	613	616	finally
    //   617	619	616	finally
    //   658	663	686	android/os/RemoteException
    //   667	672	686	android/os/RemoteException
    //   675	683	686	android/os/RemoteException
    //   733	739	764	finally
    //   746	757	759	finally
    //   760	762	759	finally
    //   772	783	785	finally
    //   786	788	785	finally
    //   802	805	801	finally
  }
  
  private final void zzb(String paramString, Intent paramIntent) {
    synchronized (this.zznfw) {
      zzp zzp = (zzp)this.zznfw.remove(paramString);
      if (zzp == null) {
        paramString = String.valueOf(paramString);
        if (paramString.length() != 0) {
          paramString = "Missing callback for ".concat(paramString);
        } else {
          paramString = new String("Missing callback for ");
        } 
        Log.w("InstanceID/Rpc", paramString);
        return;
      } 
      zzp.zzq(paramIntent);
      return;
    } 
  }
  
  private static boolean zzb(PackageManager paramPackageManager, String paramString) {
    try {
      ApplicationInfo applicationInfo = paramPackageManager.getApplicationInfo(paramString, 0);
      zzhtw = applicationInfo.packageName;
      zzhtz = applicationInfo.uid;
      return true;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return false;
    } 
  }
  
  private final void zzbk(String paramString1, String paramString2) {
    // Byte code:
    //   0: aload_0
    //   1: getfield zznfw : Landroid/support/v4/util/SimpleArrayMap;
    //   4: astore_3
    //   5: aload_3
    //   6: monitorenter
    //   7: aload_1
    //   8: ifnonnull -> 60
    //   11: iconst_0
    //   12: istore #4
    //   14: iload #4
    //   16: aload_0
    //   17: getfield zznfw : Landroid/support/v4/util/SimpleArrayMap;
    //   20: invokevirtual size : ()I
    //   23: if_icmpge -> 50
    //   26: aload_0
    //   27: getfield zznfw : Landroid/support/v4/util/SimpleArrayMap;
    //   30: iload #4
    //   32: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   35: checkcast com/google/firebase/iid/zzp
    //   38: aload_2
    //   39: invokeinterface onError : (Ljava/lang/String;)V
    //   44: iinc #4, 1
    //   47: goto -> 14
    //   50: aload_0
    //   51: getfield zznfw : Landroid/support/v4/util/SimpleArrayMap;
    //   54: invokevirtual clear : ()V
    //   57: goto -> 125
    //   60: aload_0
    //   61: getfield zznfw : Landroid/support/v4/util/SimpleArrayMap;
    //   64: aload_1
    //   65: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   68: checkcast com/google/firebase/iid/zzp
    //   71: astore #5
    //   73: aload #5
    //   75: ifnonnull -> 117
    //   78: aload_1
    //   79: invokevirtual length : ()I
    //   82: ifeq -> 96
    //   85: ldc_w 'Missing callback for '
    //   88: aload_1
    //   89: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   92: astore_1
    //   93: goto -> 107
    //   96: new java/lang/String
    //   99: dup
    //   100: ldc_w 'Missing callback for '
    //   103: invokespecial <init> : (Ljava/lang/String;)V
    //   106: astore_1
    //   107: ldc 'InstanceID/Rpc'
    //   109: aload_1
    //   110: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   113: pop
    //   114: aload_3
    //   115: monitorexit
    //   116: return
    //   117: aload #5
    //   119: aload_2
    //   120: invokeinterface onError : (Ljava/lang/String;)V
    //   125: aload_3
    //   126: monitorexit
    //   127: return
    //   128: astore_1
    //   129: aload_3
    //   130: monitorexit
    //   131: goto -> 136
    //   134: aload_1
    //   135: athrow
    //   136: goto -> 134
    // Exception table:
    //   from	to	target	type
    //   14	44	128	finally
    //   50	57	128	finally
    //   60	73	128	finally
    //   78	93	128	finally
    //   96	107	128	finally
    //   107	116	128	finally
    //   117	125	128	finally
    //   125	127	128	finally
    //   129	131	128	finally
  }
  
  public static void zzd(Context paramContext, Intent paramIntent) {
    // Byte code:
    //   0: ldc com/google/firebase/iid/zzl
    //   2: monitorenter
    //   3: getstatic com/google/firebase/iid/zzl.zzhrm : Landroid/app/PendingIntent;
    //   6: ifnonnull -> 35
    //   9: new android/content/Intent
    //   12: astore_2
    //   13: aload_2
    //   14: invokespecial <init> : ()V
    //   17: aload_2
    //   18: ldc_w 'com.google.example.invalidpackage'
    //   21: invokevirtual setPackage : (Ljava/lang/String;)Landroid/content/Intent;
    //   24: pop
    //   25: aload_0
    //   26: iconst_0
    //   27: aload_2
    //   28: iconst_0
    //   29: invokestatic getBroadcast : (Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;
    //   32: putstatic com/google/firebase/iid/zzl.zzhrm : Landroid/app/PendingIntent;
    //   35: aload_1
    //   36: ldc_w 'app'
    //   39: getstatic com/google/firebase/iid/zzl.zzhrm : Landroid/app/PendingIntent;
    //   42: invokevirtual putExtra : (Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
    //   45: pop
    //   46: ldc com/google/firebase/iid/zzl
    //   48: monitorexit
    //   49: return
    //   50: astore_0
    //   51: ldc com/google/firebase/iid/zzl
    //   53: monitorexit
    //   54: aload_0
    //   55: athrow
    // Exception table:
    //   from	to	target	type
    //   3	35	50	finally
    //   35	46	50	finally
  }
  
  public static String zzdf(Context paramContext) {
    // Byte code:
    //   0: getstatic com/google/firebase/iid/zzl.zzhtw : Ljava/lang/String;
    //   3: astore_1
    //   4: aload_1
    //   5: ifnull -> 10
    //   8: aload_1
    //   9: areturn
    //   10: invokestatic myUid : ()I
    //   13: putstatic com/google/firebase/iid/zzl.zzhty : I
    //   16: aload_0
    //   17: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   20: astore_0
    //   21: invokestatic isAtLeastO : ()Z
    //   24: ifne -> 101
    //   27: aload_0
    //   28: new android/content/Intent
    //   31: dup
    //   32: ldc_w 'com.google.android.c2dm.intent.REGISTER'
    //   35: invokespecial <init> : (Ljava/lang/String;)V
    //   38: iconst_0
    //   39: invokevirtual queryIntentServices : (Landroid/content/Intent;I)Ljava/util/List;
    //   42: invokeinterface iterator : ()Ljava/util/Iterator;
    //   47: astore_1
    //   48: aload_1
    //   49: invokeinterface hasNext : ()Z
    //   54: ifeq -> 91
    //   57: aload_0
    //   58: aload_1
    //   59: invokeinterface next : ()Ljava/lang/Object;
    //   64: checkcast android/content/pm/ResolveInfo
    //   67: getfield serviceInfo : Landroid/content/pm/ServiceInfo;
    //   70: getfield packageName : Ljava/lang/String;
    //   73: ldc_w 'com.google.android.c2dm.intent.REGISTER'
    //   76: invokestatic zza : (Landroid/content/pm/PackageManager;Ljava/lang/String;Ljava/lang/String;)Z
    //   79: ifeq -> 48
    //   82: iconst_0
    //   83: putstatic com/google/firebase/iid/zzl.zzhtx : Z
    //   86: iconst_1
    //   87: istore_2
    //   88: goto -> 93
    //   91: iconst_0
    //   92: istore_2
    //   93: iload_2
    //   94: ifeq -> 101
    //   97: getstatic com/google/firebase/iid/zzl.zzhtw : Ljava/lang/String;
    //   100: areturn
    //   101: aload_0
    //   102: invokestatic zza : (Landroid/content/pm/PackageManager;)Z
    //   105: ifeq -> 112
    //   108: getstatic com/google/firebase/iid/zzl.zzhtw : Ljava/lang/String;
    //   111: areturn
    //   112: ldc 'InstanceID/Rpc'
    //   114: ldc_w 'Failed to resolve IID implementation package, falling back'
    //   117: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   120: pop
    //   121: aload_0
    //   122: ldc_w 'com.google.android.gms'
    //   125: invokestatic zzb : (Landroid/content/pm/PackageManager;Ljava/lang/String;)Z
    //   128: ifeq -> 141
    //   131: invokestatic isAtLeastO : ()Z
    //   134: putstatic com/google/firebase/iid/zzl.zzhtx : Z
    //   137: getstatic com/google/firebase/iid/zzl.zzhtw : Ljava/lang/String;
    //   140: areturn
    //   141: invokestatic zzalj : ()Z
    //   144: ifne -> 165
    //   147: aload_0
    //   148: ldc_w 'com.google.android.gsf'
    //   151: invokestatic zzb : (Landroid/content/pm/PackageManager;Ljava/lang/String;)Z
    //   154: ifeq -> 165
    //   157: iconst_0
    //   158: putstatic com/google/firebase/iid/zzl.zzhtx : Z
    //   161: getstatic com/google/firebase/iid/zzl.zzhtw : Ljava/lang/String;
    //   164: areturn
    //   165: ldc 'InstanceID/Rpc'
    //   167: ldc_w 'Google Play services is missing, unable to get tokens'
    //   170: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   173: pop
    //   174: aconst_null
    //   175: areturn
  }
  
  final Intent zza(Bundle paramBundle, KeyPair paramKeyPair) throws IOException {
    Intent intent1 = zzb(paramBundle, paramKeyPair);
    Intent intent2 = intent1;
    if (intent1 != null) {
      intent2 = intent1;
      if (intent1.hasExtra("google.messenger")) {
        Intent intent = zzb(paramBundle, paramKeyPair);
        intent2 = intent;
        if (intent != null) {
          intent2 = intent;
          if (intent.hasExtra("google.messenger"))
            intent2 = null; 
        } 
      } 
    } 
    return intent2;
  }
  
  final void zzc(Message paramMessage) {
    if (paramMessage == null)
      return; 
    Object object = paramMessage.obj;
    if (object instanceof Intent) {
      object = object;
      object.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
      if (object.hasExtra("google.messenger")) {
        object = object.getParcelableExtra("google.messenger");
        if (object instanceof MessengerCompat)
          this.zzhue = (MessengerCompat)object; 
        if (object instanceof Messenger)
          this.zzhud = (Messenger)object; 
      } 
      zzi((Intent)paramMessage.obj);
      return;
    } 
    Log.w("InstanceID/Rpc", "Dropping invalid message");
  }
  
  final void zzi(Intent paramIntent) {
    String str1;
    StringBuilder stringBuilder;
    if (paramIntent == null) {
      if (Log.isLoggable("InstanceID/Rpc", 3))
        Log.d("InstanceID/Rpc", "Unexpected response: null"); 
      return;
    } 
    if (!"com.google.android.c2dm.intent.REGISTRATION".equals(paramIntent.getAction())) {
      if (Log.isLoggable("InstanceID/Rpc", 3)) {
        str1 = String.valueOf(paramIntent.getAction());
        if (str1.length() != 0) {
          str1 = "Unexpected response ".concat(str1);
        } else {
          str1 = new String("Unexpected response ");
        } 
        Log.d("InstanceID/Rpc", str1);
      } 
      return;
    } 
    String str2 = str1.getStringExtra("registration_id");
    String str3 = str2;
    if (str2 == null)
      str3 = str1.getStringExtra("unregistered"); 
    if (str3 == null) {
      str2 = str1.getStringExtra("error");
      if (str2 == null) {
        str3 = String.valueOf(str1.getExtras());
        stringBuilder = new StringBuilder(str3.length() + 49);
        stringBuilder.append("Unexpected response, no error or registration id ");
        stringBuilder.append(str3);
        Log.w("InstanceID/Rpc", stringBuilder.toString());
        return;
      } 
      if (Log.isLoggable("InstanceID/Rpc", 3)) {
        if (str2.length() != 0) {
          str3 = "Received InstanceID error ".concat(str2);
        } else {
          str3 = new String("Received InstanceID error ");
        } 
        Log.d("InstanceID/Rpc", str3);
      } 
      if (str2.startsWith("|")) {
        String[] arrayOfString = str2.split("\\|");
        if (!"ID".equals(arrayOfString[1])) {
          if (str2.length() != 0) {
            str3 = "Unexpected structured response ".concat(str2);
          } else {
            str3 = new String("Unexpected structured response ");
          } 
          Log.w("InstanceID/Rpc", str3);
        } 
        if (arrayOfString.length > 2) {
          str3 = arrayOfString[2];
          str2 = arrayOfString[3];
          if (str2.startsWith(":"))
            str2 = str2.substring(1); 
        } else {
          str2 = "UNKNOWN";
          str3 = null;
        } 
        stringBuilder.putExtra("error", str2);
      } else {
        str3 = null;
      } 
      zzbk(str3, str2);
      long l = stringBuilder.getLongExtra("Retry-After", 0L);
      if (l > 0L) {
        this.zzhug = SystemClock.elapsedRealtime();
        this.zzhui = (int)l * 1000;
        l = SystemClock.elapsedRealtime();
        int i = this.zzhui;
        this.zzhuj = l + i;
        stringBuilder = new StringBuilder(52);
        stringBuilder.append("Explicit request from server to backoff: ");
        stringBuilder.append(i);
        Log.w("InstanceID/Rpc", stringBuilder.toString());
        return;
      } 
      if (("SERVICE_NOT_AVAILABLE".equals(str2) || "AUTHENTICATION_FAILED".equals(str2)) && "com.google.android.gsf".equals(zzhtw)) {
        int i = this.zzhuh + 1;
        this.zzhuh = i;
        if (i >= 3) {
          if (i == 3)
            this.zzhui = (new Random()).nextInt(1000) + 1000; 
          this.zzhui <<= 1;
          l = SystemClock.elapsedRealtime();
          i = this.zzhui;
          this.zzhuj = l + i;
          stringBuilder = new StringBuilder(String.valueOf(str2).length() + 31);
          stringBuilder.append("Backoff due to ");
          stringBuilder.append(str2);
          stringBuilder.append(" for ");
          stringBuilder.append(i);
          Log.w("InstanceID/Rpc", stringBuilder.toString());
        } 
      } 
      return;
    } 
    this.zzhuf = SystemClock.elapsedRealtime();
    this.zzhuj = 0L;
    this.zzhuh = 0;
    this.zzhui = 0;
    if (str3.startsWith("|")) {
      String[] arrayOfString = str3.split("\\|");
      if (!"ID".equals(arrayOfString[1])) {
        if (str3.length() != 0) {
          str3 = "Unexpected structured response ".concat(str3);
        } else {
          str3 = new String("Unexpected structured response ");
        } 
        Log.w("InstanceID/Rpc", str3);
      } 
      str2 = arrayOfString[2];
      if (arrayOfString.length > 4)
        if ("SYNC".equals(arrayOfString[3])) {
          FirebaseInstanceId.zzek(this.zzahz);
        } else if ("RST".equals(arrayOfString[3])) {
          Context context = this.zzahz;
          zzj.zza(context, null);
          FirebaseInstanceId.zza(context, zzj.zzcga());
          stringBuilder.removeExtra("registration_id");
          zzb(str2, (Intent)stringBuilder);
          return;
        }  
      String str = arrayOfString[arrayOfString.length - 1];
      str3 = str;
      if (str.startsWith(":"))
        str3 = str.substring(1); 
      stringBuilder.putExtra("registration_id", str3);
      str3 = str2;
    } else {
      str3 = null;
    } 
    if (str3 == null) {
      if (Log.isLoggable("InstanceID/Rpc", 3))
        Log.d("InstanceID/Rpc", "Ignoring response without a request ID"); 
      return;
    } 
    zzb(str3, (Intent)stringBuilder);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */