package com.google.android.gms.location.places.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.zze;

public class zzb {
  protected final Intent mIntent;
  
  public zzb(String paramString) {
    Intent intent = new Intent(paramString);
    this.mIntent = intent;
    intent.setPackage("com.google.android.gms");
  }
  
  protected Intent build(Activity paramActivity) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
    Resources.Theme theme = paramActivity.getTheme();
    TypedValue typedValue1 = new TypedValue();
    TypedValue typedValue2 = new TypedValue();
    if (theme.resolveAttribute(16843827, typedValue1, true) && !this.mIntent.hasExtra("primary_color"))
      this.mIntent.putExtra("primary_color", typedValue1.data); 
    if (theme.resolveAttribute(16843828, typedValue2, true) && !this.mIntent.hasExtra("primary_color_dark"))
      this.mIntent.putExtra("primary_color_dark", typedValue2.data); 
    GoogleApiAvailability.getInstance();
    zze.zzbu((Context)paramActivity);
    return this.mIntent;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/ui/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */