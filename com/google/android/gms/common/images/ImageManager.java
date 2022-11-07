package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.internal.zzbcj;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager {
  private static final Object zzfrb = new Object();
  
  private static HashSet<Uri> zzfrc = new HashSet<Uri>();
  
  private static ImageManager zzfrd;
  
  private final Context mContext;
  
  private final Handler mHandler;
  
  private final ExecutorService zzfre;
  
  private final zza zzfrf;
  
  private final zzbcj zzfrg;
  
  private final Map<zza, ImageReceiver> zzfrh;
  
  private final Map<Uri, ImageReceiver> zzfri;
  
  private final Map<Uri, Long> zzfrj;
  
  private ImageManager(Context paramContext, boolean paramBoolean) {
    this.mContext = paramContext.getApplicationContext();
    this.mHandler = new Handler(Looper.getMainLooper());
    this.zzfre = Executors.newFixedThreadPool(4);
    this.zzfrf = null;
    this.zzfrg = new zzbcj();
    this.zzfrh = new HashMap<zza, ImageReceiver>();
    this.zzfri = new HashMap<Uri, ImageReceiver>();
    this.zzfrj = new HashMap<Uri, Long>();
  }
  
  public static ImageManager create(Context paramContext) {
    if (zzfrd == null)
      zzfrd = new ImageManager(paramContext, false); 
    return zzfrd;
  }
  
  private final Bitmap zza(zzb paramzzb) {
    zza zza1 = this.zzfrf;
    return (zza1 == null) ? null : (Bitmap)zza1.get(paramzzb);
  }
  
  private final void zza(zza paramzza) {
    com.google.android.gms.common.internal.zzc.zzfy("ImageManager.loadImage() must be called in the main thread");
    (new zzc(this, paramzza)).run();
  }
  
  public final void loadImage(ImageView paramImageView, int paramInt) {
    zza(new zzc(paramImageView, paramInt));
  }
  
  public final void loadImage(ImageView paramImageView, Uri paramUri) {
    zza(new zzc(paramImageView, paramUri));
  }
  
  public final void loadImage(ImageView paramImageView, Uri paramUri, int paramInt) {
    zzc zzc = new zzc(paramImageView, paramUri);
    zzc.zzfrr = paramInt;
    zza(zzc);
  }
  
  public final void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri) {
    zza(new zzd(paramOnImageLoadedListener, paramUri));
  }
  
  public final void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri, int paramInt) {
    zzd zzd = new zzd(paramOnImageLoadedListener, paramUri);
    zzd.zzfrr = paramInt;
    zza(zzd);
  }
  
  @KeepName
  final class ImageReceiver extends ResultReceiver {
    private final Uri mUri;
    
    private final ArrayList<zza> zzfrk;
    
    ImageReceiver(ImageManager this$0, Uri param1Uri) {
      super(new Handler(Looper.getMainLooper()));
      this.mUri = param1Uri;
      this.zzfrk = new ArrayList<zza>();
    }
    
    public final void onReceiveResult(int param1Int, Bundle param1Bundle) {
      ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)param1Bundle.getParcelable("com.google.android.gms.extra.fileDescriptor");
      ImageManager.zzf(this.zzfrl).execute(new ImageManager.zzb(this.zzfrl, this.mUri, parcelFileDescriptor));
    }
    
    public final void zzaiz() {
      Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
      intent.putExtra("com.google.android.gms.extras.uri", (Parcelable)this.mUri);
      intent.putExtra("com.google.android.gms.extras.resultReceiver", (Parcelable)this);
      intent.putExtra("com.google.android.gms.extras.priority", 3);
      ImageManager.zzb(this.zzfrl).sendBroadcast(intent);
    }
    
    public final void zzb(zza param1zza) {
      com.google.android.gms.common.internal.zzc.zzfy("ImageReceiver.addImageRequest() must be called in the main thread");
      this.zzfrk.add(param1zza);
    }
    
    public final void zzc(zza param1zza) {
      com.google.android.gms.common.internal.zzc.zzfy("ImageReceiver.removeImageRequest() must be called in the main thread");
      this.zzfrk.remove(param1zza);
    }
  }
  
  public static interface OnImageLoadedListener {
    void onImageLoaded(Uri param1Uri, Drawable param1Drawable, boolean param1Boolean);
  }
  
  static final class zza extends LruCache<zzb, Bitmap> {}
  
  final class zzb implements Runnable {
    private final Uri mUri;
    
    private final ParcelFileDescriptor zzfrm;
    
    public zzb(ImageManager this$0, Uri param1Uri, ParcelFileDescriptor param1ParcelFileDescriptor) {
      this.mUri = param1Uri;
      this.zzfrm = param1ParcelFileDescriptor;
    }
    
    public final void run() {
      if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
        boolean bool = false;
        Bitmap bitmap = null;
        ParcelFileDescriptor parcelFileDescriptor = this.zzfrm;
        if (parcelFileDescriptor != null) {
          try {
            Bitmap bitmap1 = BitmapFactory.decodeFileDescriptor(parcelFileDescriptor.getFileDescriptor());
            bitmap = bitmap1;
          } catch (OutOfMemoryError outOfMemoryError) {
            String str = String.valueOf(this.mUri);
            StringBuilder stringBuilder1 = new StringBuilder(str.length() + 34);
            stringBuilder1.append("OOM while loading bitmap for uri: ");
            stringBuilder1.append(str);
            Log.e("ImageManager", stringBuilder1.toString(), outOfMemoryError);
            bool = true;
          } 
          try {
            this.zzfrm.close();
          } catch (IOException iOException) {
            Log.e("ImageManager", "closed failed", iOException);
          } 
        } else {
          bitmap = null;
          bool = false;
        } 
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ImageManager.zzg(this.zzfrl).post(new ImageManager.zzd(this.zzfrl, this.mUri, bitmap, bool, countDownLatch));
        try {
          countDownLatch.await();
          return;
        } catch (InterruptedException interruptedException) {
          String str = String.valueOf(this.mUri);
          StringBuilder stringBuilder1 = new StringBuilder(str.length() + 32);
          stringBuilder1.append("Latch interrupted while posting ");
          stringBuilder1.append(str);
          Log.w("ImageManager", stringBuilder1.toString());
          return;
        } 
      } 
      String str2 = String.valueOf(Thread.currentThread());
      String str1 = String.valueOf(Looper.getMainLooper().getThread());
      StringBuilder stringBuilder = new StringBuilder(str2.length() + 56 + str1.length());
      stringBuilder.append("checkNotMainThread: current thread ");
      stringBuilder.append(str2);
      stringBuilder.append(" IS the main thread ");
      stringBuilder.append(str1);
      stringBuilder.append("!");
      Log.e("Asserts", stringBuilder.toString());
      throw new IllegalStateException("LoadBitmapFromDiskRunnable can't be executed in the main thread");
    }
  }
  
  final class zzc implements Runnable {
    private final zza zzfrn;
    
    public zzc(ImageManager this$0, zza param1zza) {
      this.zzfrn = param1zza;
    }
    
    public final void run() {
      com.google.android.gms.common.internal.zzc.zzfy("LoadImageRunnable must be executed on the main thread");
      ImageManager.ImageReceiver imageReceiver1 = (ImageManager.ImageReceiver)ImageManager.zza(this.zzfrl).get(this.zzfrn);
      if (imageReceiver1 != null) {
        ImageManager.zza(this.zzfrl).remove(this.zzfrn);
        imageReceiver1.zzc(this.zzfrn);
      } 
      zza zza1 = this.zzfrn;
      zzb zzb = zza1.zzfrp;
      if (zzb.uri == null) {
        zza1.zza(ImageManager.zzb(this.zzfrl), ImageManager.zzc(this.zzfrl), true);
        return;
      } 
      Bitmap bitmap = ImageManager.zza(this.zzfrl, zzb);
      if (bitmap != null) {
        this.zzfrn.zza(ImageManager.zzb(this.zzfrl), bitmap, true);
        return;
      } 
      Long long_ = (Long)ImageManager.zzd(this.zzfrl).get(zzb.uri);
      if (long_ != null) {
        if (SystemClock.elapsedRealtime() - long_.longValue() < 3600000L) {
          this.zzfrn.zza(ImageManager.zzb(this.zzfrl), ImageManager.zzc(this.zzfrl), true);
          return;
        } 
        ImageManager.zzd(this.zzfrl).remove(zzb.uri);
      } 
      this.zzfrn.zza(ImageManager.zzb(this.zzfrl), ImageManager.zzc(this.zzfrl));
      ImageManager.ImageReceiver imageReceiver2 = (ImageManager.ImageReceiver)ImageManager.zze(this.zzfrl).get(zzb.uri);
      null = imageReceiver2;
      if (imageReceiver2 == null) {
        null = new ImageManager.ImageReceiver(this.zzfrl, zzb.uri);
        ImageManager.zze(this.zzfrl).put(zzb.uri, null);
      } 
      null.zzb(this.zzfrn);
      if (!(this.zzfrn instanceof zzd))
        ImageManager.zza(this.zzfrl).put(this.zzfrn, null); 
      synchronized (ImageManager.zzael()) {
        if (!ImageManager.zzaiy().contains(zzb.uri)) {
          ImageManager.zzaiy().add(zzb.uri);
          null.zzaiz();
        } 
        return;
      } 
    }
  }
  
  final class zzd implements Runnable {
    private final Bitmap mBitmap;
    
    private final Uri mUri;
    
    private final CountDownLatch zzaof;
    
    private boolean zzfro;
    
    public zzd(ImageManager this$0, Uri param1Uri, Bitmap param1Bitmap, boolean param1Boolean, CountDownLatch param1CountDownLatch) {
      this.mUri = param1Uri;
      this.mBitmap = param1Bitmap;
      this.zzfro = param1Boolean;
      this.zzaof = param1CountDownLatch;
    }
    
    public final void run() {
      boolean bool;
      com.google.android.gms.common.internal.zzc.zzfy("OnBitmapLoadedRunnable must be executed in the main thread");
      if (this.mBitmap != null) {
        bool = true;
      } else {
        bool = false;
      } 
      if (ImageManager.zzh(this.zzfrl) != null) {
        if (this.zzfro) {
          ImageManager.zzh(this.zzfrl).evictAll();
          System.gc();
          this.zzfro = false;
          ImageManager.zzg(this.zzfrl).post(this);
          return;
        } 
        if (bool)
          ImageManager.zzh(this.zzfrl).put(new zzb(this.mUri), this.mBitmap); 
      } 
      ImageManager.ImageReceiver imageReceiver = (ImageManager.ImageReceiver)ImageManager.zze(this.zzfrl).remove(this.mUri);
      if (imageReceiver != null) {
        ArrayList<zza> arrayList = ImageManager.ImageReceiver.zza(imageReceiver);
        int i = arrayList.size();
        for (byte b = 0; b < i; b++) {
          zza zza = arrayList.get(b);
          ImageManager imageManager = this.zzfrl;
          if (bool) {
            zza.zza(ImageManager.zzb(imageManager), this.mBitmap, false);
          } else {
            ImageManager.zzd(imageManager).put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
            zza.zza(ImageManager.zzb(this.zzfrl), ImageManager.zzc(this.zzfrl), false);
          } 
          if (!(zza instanceof zzd))
            ImageManager.zza(this.zzfrl).remove(zza); 
        } 
      } 
      this.zzaof.countDown();
      synchronized (ImageManager.zzael()) {
        ImageManager.zzaiy().remove(this.mUri);
        return;
      } 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/images/ImageManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */