package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzbz;

@Deprecated
public final class zzca {
  private static final Object zzaqd = new Object();
  
  private static zzca zzfoj;
  
  private final String mAppId;
  
  private final Status zzfok;
  
  private final boolean zzfol;
  
  private final boolean zzfom;
  
  private zzca(Context paramContext) {
    Resources resources = paramContext.getResources();
    int i = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
    boolean bool1 = false;
    boolean bool2 = true;
    if (i != 0) {
      bool2 = bool1;
      if (resources.getInteger(i) != 0)
        bool2 = true; 
      this.zzfom = bool2 ^ true;
    } else {
      this.zzfom = false;
    } 
    this.zzfol = bool2;
    String str2 = zzbe.zzcf(paramContext);
    String str1 = str2;
    if (str2 == null)
      str1 = (new zzbz(paramContext)).getString("google_app_id"); 
    if (TextUtils.isEmpty(str1)) {
      this.zzfok = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
      this.mAppId = null;
      return;
    } 
    this.mAppId = str1;
    this.zzfok = Status.zzfhv;
  }
  
  public static String zzaie() {
    return (zzft("getGoogleAppId")).mAppId;
  }
  
  public static boolean zzaif() {
    return (zzft("isMeasurementExplicitlyDisabled")).zzfom;
  }
  
  public static Status zzcb(Context paramContext) {
    zzbp.zzb(paramContext, "Context must not be null.");
    synchronized (zzaqd) {
      if (zzfoj == null) {
        zzca zzca1 = new zzca();
        this(paramContext);
        zzfoj = zzca1;
      } 
      return zzfoj.zzfok;
    } 
  }
  
  private static zzca zzft(String paramString) {
    synchronized (zzaqd) {
      zzca zzca1 = zzfoj;
      if (zzca1 != null)
        return zzca1; 
      IllegalStateException illegalStateException = new IllegalStateException();
      int i = String.valueOf(paramString).length();
      StringBuilder stringBuilder = new StringBuilder();
      this(i + 34);
      stringBuilder.append("Initialize must be called before ");
      stringBuilder.append(paramString);
      stringBuilder.append(".");
      this(stringBuilder.toString());
      throw illegalStateException;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzca.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */