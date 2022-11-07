package com.google.android.gms.iid;

import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.internal.ReflectedParcelable;

public class MessengerCompat implements ReflectedParcelable {
  public static final Parcelable.Creator<MessengerCompat> CREATOR = new zzd();
  
  private Messenger zzhtu;
  
  private zzb zzhtv;
  
  public MessengerCompat(IBinder paramIBinder) {
    zzb zzb1;
    if (Build.VERSION.SDK_INT >= 21) {
      this.zzhtu = new Messenger(paramIBinder);
      return;
    } 
    if (paramIBinder == null) {
      paramIBinder = null;
    } else {
      IInterface iInterface = paramIBinder.queryLocalInterface("com.google.android.gms.iid.IMessengerCompat");
      if (iInterface instanceof zzb) {
        zzb1 = (zzb)iInterface;
      } else {
        zzb1 = new zzc((IBinder)zzb1);
      } 
    } 
    this.zzhtv = zzb1;
  }
  
  private final IBinder getBinder() {
    Messenger messenger = this.zzhtu;
    return (messenger != null) ? messenger.getBinder() : this.zzhtv.asBinder();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == null)
      return false; 
    try {
      return getBinder().equals(((MessengerCompat)paramObject).getBinder());
    } catch (ClassCastException classCastException) {
      return false;
    } 
  }
  
  public int hashCode() {
    return getBinder().hashCode();
  }
  
  public final void send(Message paramMessage) throws RemoteException {
    Messenger messenger = this.zzhtu;
    if (messenger != null) {
      messenger.send(paramMessage);
      return;
    } 
    this.zzhtv.send(paramMessage);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    IBinder iBinder;
    Messenger messenger = this.zzhtu;
    if (messenger != null) {
      iBinder = messenger.getBinder();
    } else {
      iBinder = this.zzhtv.asBinder();
    } 
    paramParcel.writeStrongBinder(iBinder);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/iid/MessengerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */