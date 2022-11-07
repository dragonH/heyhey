package android.support.v7.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.session.MediaSessionCompat;

@Deprecated
public class NotificationCompat extends NotificationCompat {
  @Deprecated
  public static MediaSessionCompat.Token getMediaSession(Notification paramNotification) {
    Bundle bundle = NotificationCompat.getExtras(paramNotification);
    if (bundle != null) {
      Parcelable parcelable;
      if (Build.VERSION.SDK_INT >= 21) {
        parcelable = bundle.getParcelable("android.mediaSession");
        if (parcelable != null)
          return MediaSessionCompat.Token.fromToken(parcelable); 
      } else {
        IBinder iBinder = BundleCompat.getBinder((Bundle)parcelable, "android.mediaSession");
        if (iBinder != null) {
          Parcel parcel = Parcel.obtain();
          parcel.writeStrongBinder(iBinder);
          parcel.setDataPosition(0);
          MediaSessionCompat.Token token = (MediaSessionCompat.Token)MediaSessionCompat.Token.CREATOR.createFromParcel(parcel);
          parcel.recycle();
          return token;
        } 
      } 
    } 
    return null;
  }
  
  @Deprecated
  public static class Builder extends NotificationCompat.Builder {
    @Deprecated
    public Builder(Context param1Context) {
      super(param1Context);
    }
  }
  
  @Deprecated
  public static class DecoratedCustomViewStyle extends NotificationCompat.DecoratedCustomViewStyle {}
  
  @Deprecated
  public static class DecoratedMediaCustomViewStyle extends android.support.v4.media.app.NotificationCompat.DecoratedMediaCustomViewStyle {}
  
  @Deprecated
  public static class MediaStyle extends android.support.v4.media.app.NotificationCompat.MediaStyle {
    @Deprecated
    public MediaStyle() {}
    
    @Deprecated
    public MediaStyle(NotificationCompat.Builder param1Builder) {
      super(param1Builder);
    }
    
    @Deprecated
    public MediaStyle setCancelButtonIntent(PendingIntent param1PendingIntent) {
      return (MediaStyle)super.setCancelButtonIntent(param1PendingIntent);
    }
    
    @Deprecated
    public MediaStyle setMediaSession(MediaSessionCompat.Token param1Token) {
      return (MediaStyle)super.setMediaSession(param1Token);
    }
    
    @Deprecated
    public MediaStyle setShowActionsInCompactView(int... param1VarArgs) {
      return (MediaStyle)super.setShowActionsInCompactView(param1VarArgs);
    }
    
    @Deprecated
    public MediaStyle setShowCancelButton(boolean param1Boolean) {
      return (MediaStyle)super.setShowCancelButton(param1Boolean);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/NotificationCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */