package com.google.android.gms.common;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzat;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzl;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

abstract class zzg extends zzat {
  private int zzffo;
  
  protected zzg(byte[] paramArrayOfbyte) {
    byte[] arrayOfByte = paramArrayOfbyte;
    if (paramArrayOfbyte.length != 25) {
      int i = paramArrayOfbyte.length;
      int j = paramArrayOfbyte.length;
      boolean bool = false;
      String str = zzl.zza(paramArrayOfbyte, 0, j, false);
      StringBuilder stringBuilder2 = new StringBuilder(String.valueOf(str).length() + 51);
      stringBuilder2.append("Cert hash data has incorrect length (");
      stringBuilder2.append(i);
      stringBuilder2.append("):\n");
      stringBuilder2.append(str);
      Log.wtf("GoogleCertificates", stringBuilder2.toString(), new Exception());
      arrayOfByte = Arrays.copyOfRange(paramArrayOfbyte, 0, 25);
      if (arrayOfByte.length == 25)
        bool = true; 
      j = arrayOfByte.length;
      StringBuilder stringBuilder1 = new StringBuilder(55);
      stringBuilder1.append("cert hash data has incorrect length. length=");
      stringBuilder1.append(j);
      zzbp.zzb(bool, stringBuilder1.toString());
    } 
    this.zzffo = Arrays.hashCode(arrayOfByte);
  }
  
  protected static byte[] zzfr(String paramString) {
    try {
      return paramString.getBytes("ISO-8859-1");
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw new AssertionError(unsupportedEncodingException);
    } 
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject != null && paramObject instanceof com.google.android.gms.common.internal.zzas)
      try {
        paramObject = paramObject;
        if (paramObject.zzafa() != hashCode())
          return false; 
        paramObject = paramObject.zzaez();
        if (paramObject == null)
          return false; 
        paramObject = zzn.zzx((IObjectWrapper)paramObject);
        return Arrays.equals(getBytes(), (byte[])paramObject);
      } catch (RemoteException remoteException) {
        Log.e("GoogleCertificates", "Failed to get Google certificates from remote", (Throwable)remoteException);
      }  
    return false;
  }
  
  abstract byte[] getBytes();
  
  public int hashCode() {
    return this.zzffo;
  }
  
  public final IObjectWrapper zzaez() {
    return zzn.zzw(getBytes());
  }
  
  public final int zzafa() {
    return hashCode();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */