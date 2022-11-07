package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;

public final class MediaDescriptionCompat implements Parcelable {
  public static final long BT_FOLDER_TYPE_ALBUMS = 2L;
  
  public static final long BT_FOLDER_TYPE_ARTISTS = 3L;
  
  public static final long BT_FOLDER_TYPE_GENRES = 4L;
  
  public static final long BT_FOLDER_TYPE_MIXED = 0L;
  
  public static final long BT_FOLDER_TYPE_PLAYLISTS = 5L;
  
  public static final long BT_FOLDER_TYPE_TITLES = 1L;
  
  public static final long BT_FOLDER_TYPE_YEARS = 6L;
  
  public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new Parcelable.Creator<MediaDescriptionCompat>() {
      public MediaDescriptionCompat createFromParcel(Parcel param1Parcel) {
        return (Build.VERSION.SDK_INT < 21) ? new MediaDescriptionCompat(param1Parcel) : MediaDescriptionCompat.fromMediaDescription(MediaDescriptionCompatApi21.fromParcel(param1Parcel));
      }
      
      public MediaDescriptionCompat[] newArray(int param1Int) {
        return new MediaDescriptionCompat[param1Int];
      }
    };
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static final String DESCRIPTION_KEY_MEDIA_URI = "android.support.v4.media.description.MEDIA_URI";
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static final String DESCRIPTION_KEY_NULL_BUNDLE_FLAG = "android.support.v4.media.description.NULL_BUNDLE_FLAG";
  
  public static final String EXTRA_BT_FOLDER_TYPE = "android.media.extra.BT_FOLDER_TYPE";
  
  public static final String EXTRA_DOWNLOAD_STATUS = "android.media.extra.DOWNLOAD_STATUS";
  
  public static final long STATUS_DOWNLOADED = 2L;
  
  public static final long STATUS_DOWNLOADING = 1L;
  
  public static final long STATUS_NOT_DOWNLOADED = 0L;
  
  private final CharSequence mDescription;
  
  private Object mDescriptionObj;
  
  private final Bundle mExtras;
  
  private final Bitmap mIcon;
  
  private final Uri mIconUri;
  
  private final String mMediaId;
  
  private final Uri mMediaUri;
  
  private final CharSequence mSubtitle;
  
  private final CharSequence mTitle;
  
  MediaDescriptionCompat(Parcel paramParcel) {
    this.mMediaId = paramParcel.readString();
    this.mTitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.mSubtitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.mDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.mIcon = (Bitmap)paramParcel.readParcelable(null);
    this.mIconUri = (Uri)paramParcel.readParcelable(null);
    this.mExtras = paramParcel.readBundle();
    this.mMediaUri = (Uri)paramParcel.readParcelable(null);
  }
  
  MediaDescriptionCompat(String paramString, CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3, Bitmap paramBitmap, Uri paramUri1, Bundle paramBundle, Uri paramUri2) {
    this.mMediaId = paramString;
    this.mTitle = paramCharSequence1;
    this.mSubtitle = paramCharSequence2;
    this.mDescription = paramCharSequence3;
    this.mIcon = paramBitmap;
    this.mIconUri = paramUri1;
    this.mExtras = paramBundle;
    this.mMediaUri = paramUri2;
  }
  
  public static MediaDescriptionCompat fromMediaDescription(Object paramObject) {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aconst_null
    //   3: astore_2
    //   4: aload_1
    //   5: astore_3
    //   6: aload_0
    //   7: ifnull -> 204
    //   10: getstatic android/os/Build$VERSION.SDK_INT : I
    //   13: istore #4
    //   15: aload_1
    //   16: astore_3
    //   17: iload #4
    //   19: bipush #21
    //   21: if_icmplt -> 204
    //   24: new android/support/v4/media/MediaDescriptionCompat$Builder
    //   27: dup
    //   28: invokespecial <init> : ()V
    //   31: astore #5
    //   33: aload #5
    //   35: aload_0
    //   36: invokestatic getMediaId : (Ljava/lang/Object;)Ljava/lang/String;
    //   39: invokevirtual setMediaId : (Ljava/lang/String;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   42: pop
    //   43: aload #5
    //   45: aload_0
    //   46: invokestatic getTitle : (Ljava/lang/Object;)Ljava/lang/CharSequence;
    //   49: invokevirtual setTitle : (Ljava/lang/CharSequence;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   52: pop
    //   53: aload #5
    //   55: aload_0
    //   56: invokestatic getSubtitle : (Ljava/lang/Object;)Ljava/lang/CharSequence;
    //   59: invokevirtual setSubtitle : (Ljava/lang/CharSequence;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   62: pop
    //   63: aload #5
    //   65: aload_0
    //   66: invokestatic getDescription : (Ljava/lang/Object;)Ljava/lang/CharSequence;
    //   69: invokevirtual setDescription : (Ljava/lang/CharSequence;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   72: pop
    //   73: aload #5
    //   75: aload_0
    //   76: invokestatic getIconBitmap : (Ljava/lang/Object;)Landroid/graphics/Bitmap;
    //   79: invokevirtual setIconBitmap : (Landroid/graphics/Bitmap;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   82: pop
    //   83: aload #5
    //   85: aload_0
    //   86: invokestatic getIconUri : (Ljava/lang/Object;)Landroid/net/Uri;
    //   89: invokevirtual setIconUri : (Landroid/net/Uri;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   92: pop
    //   93: aload_0
    //   94: invokestatic getExtras : (Ljava/lang/Object;)Landroid/os/Bundle;
    //   97: astore_1
    //   98: aload_1
    //   99: ifnonnull -> 107
    //   102: aconst_null
    //   103: astore_3
    //   104: goto -> 117
    //   107: aload_1
    //   108: ldc 'android.support.v4.media.description.MEDIA_URI'
    //   110: invokevirtual getParcelable : (Ljava/lang/String;)Landroid/os/Parcelable;
    //   113: checkcast android/net/Uri
    //   116: astore_3
    //   117: aload_3
    //   118: ifnull -> 153
    //   121: aload_1
    //   122: ldc 'android.support.v4.media.description.NULL_BUNDLE_FLAG'
    //   124: invokevirtual containsKey : (Ljava/lang/String;)Z
    //   127: ifeq -> 141
    //   130: aload_1
    //   131: invokevirtual size : ()I
    //   134: iconst_2
    //   135: if_icmpne -> 141
    //   138: goto -> 155
    //   141: aload_1
    //   142: ldc 'android.support.v4.media.description.MEDIA_URI'
    //   144: invokevirtual remove : (Ljava/lang/String;)V
    //   147: aload_1
    //   148: ldc 'android.support.v4.media.description.NULL_BUNDLE_FLAG'
    //   150: invokevirtual remove : (Ljava/lang/String;)V
    //   153: aload_1
    //   154: astore_2
    //   155: aload #5
    //   157: aload_2
    //   158: invokevirtual setExtras : (Landroid/os/Bundle;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   161: pop
    //   162: aload_3
    //   163: ifnull -> 176
    //   166: aload #5
    //   168: aload_3
    //   169: invokevirtual setMediaUri : (Landroid/net/Uri;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   172: pop
    //   173: goto -> 193
    //   176: iload #4
    //   178: bipush #23
    //   180: if_icmplt -> 193
    //   183: aload #5
    //   185: aload_0
    //   186: invokestatic getMediaUri : (Ljava/lang/Object;)Landroid/net/Uri;
    //   189: invokevirtual setMediaUri : (Landroid/net/Uri;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   192: pop
    //   193: aload #5
    //   195: invokevirtual build : ()Landroid/support/v4/media/MediaDescriptionCompat;
    //   198: astore_3
    //   199: aload_3
    //   200: aload_0
    //   201: putfield mDescriptionObj : Ljava/lang/Object;
    //   204: aload_3
    //   205: areturn
  }
  
  public int describeContents() {
    return 0;
  }
  
  @Nullable
  public CharSequence getDescription() {
    return this.mDescription;
  }
  
  @Nullable
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  @Nullable
  public Bitmap getIconBitmap() {
    return this.mIcon;
  }
  
  @Nullable
  public Uri getIconUri() {
    return this.mIconUri;
  }
  
  public Object getMediaDescription() {
    Object object1 = this.mDescriptionObj;
    Object object2 = object1;
    if (object1 == null) {
      int i = Build.VERSION.SDK_INT;
      if (i < 21) {
        object2 = object1;
      } else {
        Object object = MediaDescriptionCompatApi21.Builder.newInstance();
        MediaDescriptionCompatApi21.Builder.setMediaId(object, this.mMediaId);
        MediaDescriptionCompatApi21.Builder.setTitle(object, this.mTitle);
        MediaDescriptionCompatApi21.Builder.setSubtitle(object, this.mSubtitle);
        MediaDescriptionCompatApi21.Builder.setDescription(object, this.mDescription);
        MediaDescriptionCompatApi21.Builder.setIconBitmap(object, this.mIcon);
        MediaDescriptionCompatApi21.Builder.setIconUri(object, this.mIconUri);
        object1 = this.mExtras;
        object2 = object1;
        if (i < 23) {
          object2 = object1;
          if (this.mMediaUri != null) {
            object2 = object1;
            if (object1 == null) {
              object2 = new Bundle();
              object2.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
            } 
            object2.putParcelable("android.support.v4.media.description.MEDIA_URI", (Parcelable)this.mMediaUri);
          } 
        } 
        MediaDescriptionCompatApi21.Builder.setExtras(object, (Bundle)object2);
        if (i >= 23)
          MediaDescriptionCompatApi23.Builder.setMediaUri(object, this.mMediaUri); 
        object2 = MediaDescriptionCompatApi21.Builder.build(object);
        this.mDescriptionObj = object2;
      } 
    } 
    return object2;
  }
  
  @Nullable
  public String getMediaId() {
    return this.mMediaId;
  }
  
  @Nullable
  public Uri getMediaUri() {
    return this.mMediaUri;
  }
  
  @Nullable
  public CharSequence getSubtitle() {
    return this.mSubtitle;
  }
  
  @Nullable
  public CharSequence getTitle() {
    return this.mTitle;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mTitle);
    stringBuilder.append(", ");
    stringBuilder.append(this.mSubtitle);
    stringBuilder.append(", ");
    stringBuilder.append(this.mDescription);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (Build.VERSION.SDK_INT < 21) {
      paramParcel.writeString(this.mMediaId);
      TextUtils.writeToParcel(this.mTitle, paramParcel, paramInt);
      TextUtils.writeToParcel(this.mSubtitle, paramParcel, paramInt);
      TextUtils.writeToParcel(this.mDescription, paramParcel, paramInt);
      paramParcel.writeParcelable((Parcelable)this.mIcon, paramInt);
      paramParcel.writeParcelable((Parcelable)this.mIconUri, paramInt);
      paramParcel.writeBundle(this.mExtras);
      paramParcel.writeParcelable((Parcelable)this.mMediaUri, paramInt);
    } else {
      MediaDescriptionCompatApi21.writeToParcel(getMediaDescription(), paramParcel, paramInt);
    } 
  }
  
  public static final class Builder {
    private CharSequence mDescription;
    
    private Bundle mExtras;
    
    private Bitmap mIcon;
    
    private Uri mIconUri;
    
    private String mMediaId;
    
    private Uri mMediaUri;
    
    private CharSequence mSubtitle;
    
    private CharSequence mTitle;
    
    public MediaDescriptionCompat build() {
      return new MediaDescriptionCompat(this.mMediaId, this.mTitle, this.mSubtitle, this.mDescription, this.mIcon, this.mIconUri, this.mExtras, this.mMediaUri);
    }
    
    public Builder setDescription(@Nullable CharSequence param1CharSequence) {
      this.mDescription = param1CharSequence;
      return this;
    }
    
    public Builder setExtras(@Nullable Bundle param1Bundle) {
      this.mExtras = param1Bundle;
      return this;
    }
    
    public Builder setIconBitmap(@Nullable Bitmap param1Bitmap) {
      this.mIcon = param1Bitmap;
      return this;
    }
    
    public Builder setIconUri(@Nullable Uri param1Uri) {
      this.mIconUri = param1Uri;
      return this;
    }
    
    public Builder setMediaId(@Nullable String param1String) {
      this.mMediaId = param1String;
      return this;
    }
    
    public Builder setMediaUri(@Nullable Uri param1Uri) {
      this.mMediaUri = param1Uri;
      return this;
    }
    
    public Builder setSubtitle(@Nullable CharSequence param1CharSequence) {
      this.mSubtitle = param1CharSequence;
      return this;
    }
    
    public Builder setTitle(@Nullable CharSequence param1CharSequence) {
      this.mTitle = param1CharSequence;
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/media/MediaDescriptionCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */