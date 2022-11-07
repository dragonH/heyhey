package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.zzbf;
import java.lang.ref.WeakReference;

public final class zzc extends zza {
  private WeakReference<ImageView> zzfrw;
  
  public zzc(ImageView paramImageView, int paramInt) {
    super(null, paramInt);
    com.google.android.gms.common.internal.zzc.zzr(paramImageView);
    this.zzfrw = new WeakReference<ImageView>(paramImageView);
  }
  
  public zzc(ImageView paramImageView, Uri paramUri) {
    super(paramUri, 0);
    com.google.android.gms.common.internal.zzc.zzr(paramImageView);
    this.zzfrw = new WeakReference<ImageView>(paramImageView);
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof zzc))
      return false; 
    if (this == paramObject)
      return true; 
    zzc zzc1 = (zzc)paramObject;
    paramObject = this.zzfrw.get();
    ImageView imageView = zzc1.zzfrw.get();
    return (imageView != null && paramObject != null && zzbf.equal(imageView, paramObject));
  }
  
  public final int hashCode() {
    return 0;
  }
  
  protected final void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzfrw : Ljava/lang/ref/WeakReference;
    //   4: invokevirtual get : ()Ljava/lang/Object;
    //   7: checkcast android/widget/ImageView
    //   10: astore #5
    //   12: aload #5
    //   14: ifnull -> 209
    //   17: iconst_0
    //   18: istore #6
    //   20: iload_3
    //   21: ifne -> 35
    //   24: iload #4
    //   26: ifne -> 35
    //   29: iconst_1
    //   30: istore #7
    //   32: goto -> 38
    //   35: iconst_0
    //   36: istore #7
    //   38: iload #7
    //   40: ifeq -> 74
    //   43: aload #5
    //   45: instanceof com/google/android/gms/internal/zzbci
    //   48: ifeq -> 74
    //   51: invokestatic zzajc : ()I
    //   54: istore #8
    //   56: aload_0
    //   57: getfield zzfrr : I
    //   60: istore #9
    //   62: iload #9
    //   64: ifeq -> 74
    //   67: iload #8
    //   69: iload #9
    //   71: if_icmpeq -> 209
    //   74: aload_0
    //   75: iload_2
    //   76: iload_3
    //   77: invokevirtual zzc : (ZZ)Z
    //   80: istore_2
    //   81: aconst_null
    //   82: astore #10
    //   84: aload_1
    //   85: astore #11
    //   87: iload_2
    //   88: ifeq -> 143
    //   91: aload #5
    //   93: invokevirtual getDrawable : ()Landroid/graphics/drawable/Drawable;
    //   96: astore #12
    //   98: aload #12
    //   100: ifnull -> 128
    //   103: aload #12
    //   105: astore #11
    //   107: aload #12
    //   109: instanceof com/google/android/gms/internal/zzbcd
    //   112: ifeq -> 131
    //   115: aload #12
    //   117: checkcast com/google/android/gms/internal/zzbcd
    //   120: invokevirtual zzaja : ()Landroid/graphics/drawable/Drawable;
    //   123: astore #11
    //   125: goto -> 131
    //   128: aconst_null
    //   129: astore #11
    //   131: new com/google/android/gms/internal/zzbcd
    //   134: dup
    //   135: aload #11
    //   137: aload_1
    //   138: invokespecial <init> : (Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V
    //   141: astore #11
    //   143: aload #5
    //   145: aload #11
    //   147: invokevirtual setImageDrawable : (Landroid/graphics/drawable/Drawable;)V
    //   150: aload #5
    //   152: instanceof com/google/android/gms/internal/zzbci
    //   155: ifeq -> 194
    //   158: aload #10
    //   160: astore_1
    //   161: iload #4
    //   163: ifeq -> 174
    //   166: aload_0
    //   167: getfield zzfrp : Lcom/google/android/gms/common/images/zzb;
    //   170: getfield uri : Landroid/net/Uri;
    //   173: astore_1
    //   174: aload_1
    //   175: invokestatic zzn : (Landroid/net/Uri;)V
    //   178: iload #7
    //   180: ifeq -> 189
    //   183: aload_0
    //   184: getfield zzfrr : I
    //   187: istore #6
    //   189: iload #6
    //   191: invokestatic zzcb : (I)V
    //   194: iload_2
    //   195: ifeq -> 209
    //   198: aload #11
    //   200: checkcast com/google/android/gms/internal/zzbcd
    //   203: sipush #250
    //   206: invokevirtual startTransition : (I)V
    //   209: return
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/images/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */