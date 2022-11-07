package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbco;

public class zzd<T extends zzbco> extends AbstractDataBuffer<T> {
  private static final String[] zzfqj = new String[] { "data" };
  
  private final Parcelable.Creator<T> zzfqk;
  
  public zzd(DataHolder paramDataHolder, Parcelable.Creator<T> paramCreator) {
    super(paramDataHolder);
    this.zzfqk = paramCreator;
  }
  
  public static <T extends zzbco> void zza(DataHolder.zza paramzza, T paramT) {
    Parcel parcel = Parcel.obtain();
    paramT.writeToParcel(parcel, 0);
    ContentValues contentValues = new ContentValues();
    contentValues.put("data", parcel.marshall());
    paramzza.zza(contentValues);
    parcel.recycle();
  }
  
  public static DataHolder.zza zzaiu() {
    return DataHolder.zza(zzfqj);
  }
  
  public T zzbw(int paramInt) {
    DataHolder dataHolder = this.zzflf;
    byte[] arrayOfByte = dataHolder.zzg("data", paramInt, dataHolder.zzbx(paramInt));
    Parcel parcel = Parcel.obtain();
    parcel.unmarshall(arrayOfByte, 0, arrayOfByte.length);
    parcel.setDataPosition(0);
    zzbco zzbco = (zzbco)this.zzfqk.createFromParcel(parcel);
    parcel.recycle();
    return (T)zzbco;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/data/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */