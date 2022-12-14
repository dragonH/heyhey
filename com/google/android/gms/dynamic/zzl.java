package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;

public abstract class zzl extends zzec implements zzk {
  public zzl() {
    attachInterface(this, "com.google.android.gms.dynamic.IFragmentWrapper");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    IBinder iBinder2;
    IObjectWrapper iObjectWrapper4;
    IBinder iBinder1;
    IObjectWrapper iObjectWrapper3;
    zzk zzk2;
    String str;
    IObjectWrapper iObjectWrapper2;
    zzk zzk1;
    Bundle bundle;
    IObjectWrapper iObjectWrapper1;
    boolean bool;
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2))
      return true; 
    IBinder iBinder3 = null;
    IBinder iBinder4 = null;
    switch (paramInt1) {
      default:
        return false;
      case 27:
        iBinder2 = paramParcel1.readStrongBinder();
        if (iBinder2 == null) {
          iBinder2 = iBinder4;
        } else {
          IInterface iInterface = iBinder2.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
          if (iInterface instanceof IObjectWrapper) {
            iObjectWrapper4 = (IObjectWrapper)iInterface;
          } else {
            iObjectWrapper4 = new zzm((IBinder)iObjectWrapper4);
          } 
        } 
        zzw(iObjectWrapper4);
        paramParcel2.writeNoException();
        return true;
      case 26:
        startActivityForResult((Intent)zzed.zza((Parcel)iObjectWrapper4, Intent.CREATOR), iObjectWrapper4.readInt());
        paramParcel2.writeNoException();
        return true;
      case 25:
        startActivity((Intent)zzed.zza((Parcel)iObjectWrapper4, Intent.CREATOR));
        paramParcel2.writeNoException();
        return true;
      case 24:
        setUserVisibleHint(zzed.zza((Parcel)iObjectWrapper4));
        paramParcel2.writeNoException();
        return true;
      case 23:
        setRetainInstance(zzed.zza((Parcel)iObjectWrapper4));
        paramParcel2.writeNoException();
        return true;
      case 22:
        setMenuVisibility(zzed.zza((Parcel)iObjectWrapper4));
        paramParcel2.writeNoException();
        return true;
      case 21:
        setHasOptionsMenu(zzed.zza((Parcel)iObjectWrapper4));
        paramParcel2.writeNoException();
        return true;
      case 20:
        iBinder1 = iObjectWrapper4.readStrongBinder();
        if (iBinder1 == null) {
          iBinder1 = iBinder3;
        } else {
          IInterface iInterface = iBinder1.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
          if (iInterface instanceof IObjectWrapper) {
            iObjectWrapper3 = (IObjectWrapper)iInterface;
          } else {
            iObjectWrapper3 = new zzm((IBinder)iObjectWrapper3);
          } 
        } 
        zzv(iObjectWrapper3);
        paramParcel2.writeNoException();
        return true;
      case 19:
        bool = isVisible();
        paramParcel2.writeNoException();
        zzed.zza(paramParcel2, bool);
        return true;
      case 18:
        bool = isResumed();
        paramParcel2.writeNoException();
        zzed.zza(paramParcel2, bool);
        return true;
      case 17:
        bool = isRemoving();
        paramParcel2.writeNoException();
        zzed.zza(paramParcel2, bool);
        return true;
      case 16:
        bool = isInLayout();
        paramParcel2.writeNoException();
        zzed.zza(paramParcel2, bool);
        return true;
      case 15:
        bool = isHidden();
        paramParcel2.writeNoException();
        zzed.zza(paramParcel2, bool);
        return true;
      case 14:
        bool = isDetached();
        paramParcel2.writeNoException();
        zzed.zza(paramParcel2, bool);
        return true;
      case 13:
        bool = isAdded();
        paramParcel2.writeNoException();
        zzed.zza(paramParcel2, bool);
        return true;
      case 12:
        iObjectWrapper3 = getView();
        break;
      case 11:
        bool = getUserVisibleHint();
        paramParcel2.writeNoException();
        zzed.zza(paramParcel2, bool);
        return true;
      case 10:
        paramInt1 = getTargetRequestCode();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 9:
        zzk2 = zzaof();
        break;
      case 8:
        str = getTag();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str);
        return true;
      case 7:
        bool = getRetainInstance();
        paramParcel2.writeNoException();
        zzed.zza(paramParcel2, bool);
        return true;
      case 6:
        iObjectWrapper2 = zzaoe();
        break;
      case 5:
        zzk1 = zzaod();
        break;
      case 4:
        paramInt1 = getId();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 3:
        bundle = getArguments();
        paramParcel2.writeNoException();
        zzed.zzb(paramParcel2, (Parcelable)bundle);
        return true;
      case 2:
        iObjectWrapper1 = zzaoc();
        break;
    } 
    paramParcel2.writeNoException();
    zzed.zza(paramParcel2, iObjectWrapper1);
    return true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/dynamic/zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */