package com.google.android.gms.common.internal;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public final class zzaj {
  private static final Uri zzfvg;
  
  private static final Uri zzfvh;
  
  static {
    Uri uri = Uri.parse("https://plus.google.com/");
    zzfvg = uri;
    zzfvh = uri.buildUpon().appendPath("circles").appendPath("find").build();
  }
  
  public static Intent zzakj() {
    Intent intent = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
    intent.setPackage("com.google.android.wearable.app");
    return intent;
  }
  
  public static Intent zzge(String paramString) {
    Uri uri = Uri.fromParts("package", paramString, null);
    Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    intent.setData(uri);
    return intent;
  }
  
  public static Intent zzu(String paramString1, @Nullable String paramString2) {
    Intent intent = new Intent("android.intent.action.VIEW");
    Uri.Builder builder = Uri.parse("market://details").buildUpon().appendQueryParameter("id", paramString1);
    if (!TextUtils.isEmpty(paramString2))
      builder.appendQueryParameter("pcampaignid", paramString2); 
    intent.setData(builder.build());
    intent.setPackage("com.android.vending");
    intent.addFlags(524288);
    return intent;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzaj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */