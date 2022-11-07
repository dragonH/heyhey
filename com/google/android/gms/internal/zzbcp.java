package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbp;
import java.util.ArrayList;

public final class zzbcp {
  public static <T extends zzbco> T zza(Intent paramIntent, String paramString, Parcelable.Creator<T> paramCreator) {
    byte[] arrayOfByte = paramIntent.getByteArrayExtra(paramString);
    return (arrayOfByte == null) ? null : zza(arrayOfByte, paramCreator);
  }
  
  public static <T extends zzbco> T zza(byte[] paramArrayOfbyte, Parcelable.Creator<T> paramCreator) {
    zzbp.zzu(paramCreator);
    Parcel parcel = Parcel.obtain();
    parcel.unmarshall(paramArrayOfbyte, 0, paramArrayOfbyte.length);
    parcel.setDataPosition(0);
    zzbco zzbco = (zzbco)paramCreator.createFromParcel(parcel);
    parcel.recycle();
    return (T)zzbco;
  }
  
  public static <T extends zzbco> void zza(T paramT, Intent paramIntent, String paramString) {
    paramIntent.putExtra(paramString, zza(paramT));
  }
  
  public static <T extends zzbco> byte[] zza(T paramT) {
    Parcel parcel = Parcel.obtain();
    paramT.writeToParcel(parcel, 0);
    byte[] arrayOfByte = parcel.marshall();
    parcel.recycle();
    return arrayOfByte;
  }
  
  public static <T extends zzbco> ArrayList<T> zzb(Intent paramIntent, String paramString, Parcelable.Creator<T> paramCreator) {
    ArrayList<Object> arrayList = (ArrayList)paramIntent.getSerializableExtra(paramString);
    if (arrayList == null)
      return null; 
    ArrayList<T> arrayList1 = new ArrayList(arrayList.size());
    int i = arrayList.size();
    byte b = 0;
    while (b < i) {
      byte[] arrayOfByte = (byte[])arrayList.get(b);
      b++;
      arrayList1.add(zza(arrayOfByte, paramCreator));
    } 
    return arrayList1;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbcp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */