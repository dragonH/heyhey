package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

final class zzd {
  private SharedPreferences zzamc;
  
  zzd(Context paramContext) {
    try {
      return;
    } finally {
      paramContext = null;
      Log.w("GmscoreFlag", "Error while getting SharedPreferences ", (Throwable)paramContext);
      this.zzamc = null;
    } 
  }
  
  final boolean getBoolean(String paramString, boolean paramBoolean) {
    try {
      return (sharedPreferences == null) ? false : sharedPreferences.getBoolean(paramString, false);
    } finally {
      paramString = null;
      Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", (Throwable)paramString);
    } 
  }
  
  final float getFloat(String paramString, float paramFloat) {
    try {
      return (sharedPreferences == null) ? 0.0F : sharedPreferences.getFloat(paramString, 0.0F);
    } finally {
      paramString = null;
      Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", (Throwable)paramString);
    } 
  }
  
  final String getString(String paramString1, String paramString2) {
    try {
      return (sharedPreferences == null) ? paramString2 : sharedPreferences.getString(paramString1, paramString2);
    } finally {
      paramString1 = null;
      Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", (Throwable)paramString1);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/ads/identifier/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */