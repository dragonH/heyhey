package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.R;

public final class zzbz {
  private final Resources zzfwf;
  
  private final String zzfwg;
  
  public zzbz(Context paramContext) {
    zzbp.zzu(paramContext);
    Resources resources = paramContext.getResources();
    this.zzfwf = resources;
    this.zzfwg = resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue);
  }
  
  public final String getString(String paramString) {
    int i = this.zzfwf.getIdentifier(paramString, "string", this.zzfwg);
    return (i == 0) ? null : this.zzfwf.getString(i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzbz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */