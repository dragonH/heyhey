package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.zzbp;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public final class zzy {
  private static final Lock zzedl = new ReentrantLock();
  
  private static zzy zzedm;
  
  private final Lock zzedn = new ReentrantLock();
  
  private final SharedPreferences zzedo;
  
  private zzy(Context paramContext) {
    this.zzedo = paramContext.getSharedPreferences("com.google.android.gms.signin", 0);
  }
  
  public static zzy zzbl(Context paramContext) {
    zzbp.zzu(paramContext);
    Lock lock = zzedl;
    lock.lock();
    try {
      if (zzedm == null) {
        zzy zzy1 = new zzy();
        this(paramContext.getApplicationContext());
        zzedm = zzy1;
      } 
      return zzedm;
    } finally {
      zzedl.unlock();
    } 
  }
  
  private final GoogleSignInAccount zzep(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return null; 
    paramString = zzer(zzq("googleSignInAccount", paramString));
    if (paramString != null)
      try {
        return GoogleSignInAccount.zzem(paramString);
      } catch (JSONException jSONException) {} 
    return null;
  }
  
  private final GoogleSignInOptions zzeq(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return null; 
    paramString = zzer(zzq("googleSignInOptions", paramString));
    if (paramString != null)
      try {
        return GoogleSignInOptions.zzen(paramString);
      } catch (JSONException jSONException) {} 
    return null;
  }
  
  private final String zzer(String paramString) {
    this.zzedn.lock();
    try {
      paramString = this.zzedo.getString(paramString, null);
      return paramString;
    } finally {
      this.zzedn.unlock();
    } 
  }
  
  private final void zzes(String paramString) {
    this.zzedn.lock();
    try {
      this.zzedo.edit().remove(paramString).apply();
      return;
    } finally {
      this.zzedn.unlock();
    } 
  }
  
  private final void zzp(String paramString1, String paramString2) {
    this.zzedn.lock();
    try {
      this.zzedo.edit().putString(paramString1, paramString2).apply();
      return;
    } finally {
      this.zzedn.unlock();
    } 
  }
  
  private static String zzq(String paramString1, String paramString2) {
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString1).length() + ":".length() + String.valueOf(paramString2).length());
    stringBuilder.append(paramString1);
    stringBuilder.append(":");
    stringBuilder.append(paramString2);
    return stringBuilder.toString();
  }
  
  public final void zza(GoogleSignInAccount paramGoogleSignInAccount, GoogleSignInOptions paramGoogleSignInOptions) {
    zzbp.zzu(paramGoogleSignInAccount);
    zzbp.zzu(paramGoogleSignInOptions);
    zzp("defaultGoogleSignInAccount", paramGoogleSignInAccount.zzaae());
    zzbp.zzu(paramGoogleSignInAccount);
    zzbp.zzu(paramGoogleSignInOptions);
    String str = paramGoogleSignInAccount.zzaae();
    zzp(zzq("googleSignInAccount", str), paramGoogleSignInAccount.zzaaf());
    zzp(zzq("googleSignInOptions", str), paramGoogleSignInOptions.zzaai());
  }
  
  public final GoogleSignInAccount zzaas() {
    return zzep(zzer("defaultGoogleSignInAccount"));
  }
  
  public final GoogleSignInOptions zzaat() {
    return zzeq(zzer("defaultGoogleSignInAccount"));
  }
  
  public final void zzaau() {
    String str = zzer("defaultGoogleSignInAccount");
    zzes("defaultGoogleSignInAccount");
    if (!TextUtils.isEmpty(str)) {
      zzes(zzq("googleSignInAccount", str));
      zzes(zzq("googleSignInOptions", str));
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/auth/api/signin/internal/zzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */