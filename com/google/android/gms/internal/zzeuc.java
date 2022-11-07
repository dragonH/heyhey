package com.google.android.gms.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public abstract class zzeuc<MessageType extends zzeuc<MessageType, BuilderType>, BuilderType extends zzeud<MessageType, BuilderType>> implements zzewl {
  private static boolean zzoms = false;
  
  protected int zzomr = 0;
  
  protected static <T> void zza(Iterable<T> paramIterable, List<? super T> paramList) {
    zzeud.zza(paramIterable, paramList);
  }
  
  public final byte[] toByteArray() {
    try {
      byte[] arrayOfByte = new byte[zzhi()];
      zzeuy zzeuy = zzeuy.zzbc(arrayOfByte);
      zza(zzeuy);
      zzeuy.zzctn();
      return arrayOfByte;
    } catch (IOException iOException) {
      String str = getClass().getName();
      StringBuilder stringBuilder = new StringBuilder(str.length() + 62 + "byte array".length());
      stringBuilder.append("Serializing ");
      stringBuilder.append(str);
      stringBuilder.append(" to a ");
      stringBuilder.append("byte array");
      stringBuilder.append(" threw an IOException (should never happen).");
      throw new RuntimeException(stringBuilder.toString(), iOException);
    } 
  }
  
  public final zzeuk toByteString() {
    try {
      zzeup zzeup = zzeuk.zzjj(zzhi());
      zza(zzeup.zzcsm());
      return zzeup.zzcsl();
    } catch (IOException iOException) {
      String str = getClass().getName();
      StringBuilder stringBuilder = new StringBuilder(str.length() + 62 + "ByteString".length());
      stringBuilder.append("Serializing ");
      stringBuilder.append(str);
      stringBuilder.append(" to a ");
      stringBuilder.append("ByteString");
      stringBuilder.append(" threw an IOException (should never happen).");
      throw new RuntimeException(stringBuilder.toString(), iOException);
    } 
  }
  
  public final void writeTo(OutputStream paramOutputStream) throws IOException {
    zzeuy zzeuy = zzeuy.zzb(paramOutputStream, zzeuy.zzjw(zzhi()));
    zza(zzeuy);
    zzeuy.flush();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeuc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */