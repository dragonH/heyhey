package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class BitmapTeleporter extends zzbck implements ReflectedParcelable {
  public static final Parcelable.Creator<BitmapTeleporter> CREATOR = new zza();
  
  private ParcelFileDescriptor zzcqz;
  
  private int zzdxs;
  
  private int zzecz;
  
  private Bitmap zzfqb;
  
  private boolean zzfqc;
  
  private File zzfqd;
  
  BitmapTeleporter(int paramInt1, ParcelFileDescriptor paramParcelFileDescriptor, int paramInt2) {
    this.zzdxs = paramInt1;
    this.zzcqz = paramParcelFileDescriptor;
    this.zzecz = paramInt2;
    this.zzfqb = null;
    this.zzfqc = false;
  }
  
  public BitmapTeleporter(Bitmap paramBitmap) {
    this.zzdxs = 1;
    this.zzcqz = null;
    this.zzecz = 0;
    this.zzfqb = paramBitmap;
    this.zzfqc = true;
  }
  
  private static void zza(Closeable paramCloseable) {
    try {
      paramCloseable.close();
      return;
    } catch (IOException iOException) {
      Log.w("BitmapTeleporter", "Could not close stream", iOException);
      return;
    } 
  }
  
  private final FileOutputStream zzait() {
    File file = this.zzfqd;
    if (file != null)
      try {
        file = File.createTempFile("teleporter", ".tmp", file);
        try {
          FileOutputStream fileOutputStream = new FileOutputStream();
          this(file);
          this.zzcqz = ParcelFileDescriptor.open(file, 268435456);
          file.delete();
          return fileOutputStream;
        } catch (FileNotFoundException fileNotFoundException) {
          throw new IllegalStateException("Temporary file is somehow already deleted");
        } 
      } catch (IOException iOException) {
        throw new IllegalStateException("Could not create temporary file", iOException);
      }  
    throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
  }
  
  public final void release() {
    if (!this.zzfqc)
      try {
        this.zzcqz.close();
        return;
      } catch (IOException iOException) {
        Log.w("BitmapTeleporter", "Could not close PFD", iOException);
      }  
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.zzcqz == null) {
      Bitmap bitmap = this.zzfqb;
      ByteBuffer byteBuffer = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
      bitmap.copyPixelsToBuffer(byteBuffer);
      byte[] arrayOfByte = byteBuffer.array();
      DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(zzait()));
      try {
        dataOutputStream.writeInt(arrayOfByte.length);
        dataOutputStream.writeInt(bitmap.getWidth());
        dataOutputStream.writeInt(bitmap.getHeight());
        dataOutputStream.writeUTF(bitmap.getConfig().toString());
        dataOutputStream.write(arrayOfByte);
        zza(dataOutputStream);
      } catch (IOException iOException) {
        IllegalStateException illegalStateException = new IllegalStateException();
        this("Could not write into unlinked file", iOException);
        throw illegalStateException;
      } finally {}
    } 
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    zzbcn.zza(paramParcel, 2, (Parcelable)this.zzcqz, paramInt | 0x1, false);
    zzbcn.zzc(paramParcel, 3, this.zzecz);
    zzbcn.zzai(paramParcel, i);
    this.zzcqz = null;
  }
  
  public final Bitmap zzais() {
    if (!this.zzfqc) {
      DataInputStream dataInputStream = new DataInputStream((InputStream)new ParcelFileDescriptor.AutoCloseInputStream(this.zzcqz));
      try {
        byte[] arrayOfByte = new byte[dataInputStream.readInt()];
        int i = dataInputStream.readInt();
        int j = dataInputStream.readInt();
        Bitmap.Config config = Bitmap.Config.valueOf(dataInputStream.readUTF());
        dataInputStream.read(arrayOfByte);
        zza(dataInputStream);
        ByteBuffer byteBuffer = ByteBuffer.wrap(arrayOfByte);
        Bitmap bitmap = Bitmap.createBitmap(i, j, config);
        bitmap.copyPixelsFromBuffer(byteBuffer);
        this.zzfqb = bitmap;
        this.zzfqc = true;
      } catch (IOException iOException) {
        IllegalStateException illegalStateException = new IllegalStateException();
        this("Could not read from parcel file descriptor", iOException);
        throw illegalStateException;
      } finally {
        Exception exception;
      } 
    } 
    return this.zzfqb;
  }
  
  public final void zzc(File paramFile) {
    if (paramFile != null) {
      this.zzfqd = paramFile;
      return;
    } 
    throw new NullPointerException("Cannot set null temp directory");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/data/BitmapTeleporter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */