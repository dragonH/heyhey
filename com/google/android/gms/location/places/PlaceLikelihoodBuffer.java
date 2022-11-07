package com.google.android.gms.location.places;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.location.places.internal.zzah;
import com.google.android.gms.location.places.internal.zzaj;
import java.util.Comparator;

public class PlaceLikelihoodBuffer extends AbstractDataBuffer<PlaceLikelihood> implements Result {
  private static final Comparator<zzah> zzibe = new zzi();
  
  private final Status mStatus;
  
  private final int zzbfe;
  
  private final String zzias;
  
  private final boolean zzibf;
  
  public PlaceLikelihoodBuffer(DataHolder paramDataHolder, int paramInt) {
    this(paramDataHolder, false, paramInt);
  }
  
  private PlaceLikelihoodBuffer(DataHolder paramDataHolder, boolean paramBoolean, int paramInt) {
    super(paramDataHolder);
    StringBuilder stringBuilder;
    this.mStatus = PlacesStatusCodes.zzcl(paramDataHolder.getStatusCode());
    switch (paramInt) {
      default:
        stringBuilder = new StringBuilder(27);
        stringBuilder.append("invalid source: ");
        stringBuilder.append(paramInt);
        throw new IllegalArgumentException(stringBuilder.toString());
      case 100:
      case 101:
      case 102:
      case 103:
      case 104:
      case 105:
      case 106:
      case 107:
      case 108:
        break;
    } 
    this.zzbfe = paramInt;
    this.zzibf = false;
    if (stringBuilder.zzafi() != null) {
      String str = stringBuilder.zzafi().getString("com.google.android.gms.location.places.PlaceLikelihoodBuffer.ATTRIBUTIONS_EXTRA_KEY");
    } else {
      stringBuilder = null;
    } 
    this.zzias = (String)stringBuilder;
  }
  
  public static int zzw(Bundle paramBundle) {
    return paramBundle.getInt("com.google.android.gms.location.places.PlaceLikelihoodBuffer.SOURCE_EXTRA_KEY");
  }
  
  public PlaceLikelihood get(int paramInt) {
    return (PlaceLikelihood)new zzaj(this.zzflf, paramInt);
  }
  
  @Nullable
  public CharSequence getAttributions() {
    return this.zzias;
  }
  
  public Status getStatus() {
    return this.mStatus;
  }
  
  public String toString() {
    return zzbf.zzt(this).zzg("status", getStatus()).zzg("attributions", this.zzias).toString();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlaceLikelihoodBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */