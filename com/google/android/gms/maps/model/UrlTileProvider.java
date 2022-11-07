package com.google.android.gms.maps.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public abstract class UrlTileProvider implements TileProvider {
  private final int zzakq;
  
  private final int zzakr;
  
  public UrlTileProvider(int paramInt1, int paramInt2) {
    this.zzakq = paramInt1;
    this.zzakr = paramInt2;
  }
  
  private static long zza(InputStream paramInputStream, OutputStream paramOutputStream) throws IOException {
    byte[] arrayOfByte = new byte[4096];
    long l = 0L;
    while (true) {
      int i = paramInputStream.read(arrayOfByte);
      if (i != -1) {
        paramOutputStream.write(arrayOfByte, 0, i);
        l += i;
        continue;
      } 
      return l;
    } 
  }
  
  public final Tile getTile(int paramInt1, int paramInt2, int paramInt3) {
    URL uRL = getTileUrl(paramInt1, paramInt2, paramInt3);
    if (uRL == null)
      return TileProvider.NO_TILE; 
    try {
      Tile tile = new Tile();
      paramInt1 = this.zzakq;
      paramInt2 = this.zzakr;
      InputStream inputStream = uRL.openStream();
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      this();
      zza(inputStream, byteArrayOutputStream);
      this(paramInt1, paramInt2, byteArrayOutputStream.toByteArray());
    } catch (IOException iOException) {
      iOException = null;
    } 
    return (Tile)iOException;
  }
  
  public abstract URL getTileUrl(int paramInt1, int paramInt2, int paramInt3);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/UrlTileProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */