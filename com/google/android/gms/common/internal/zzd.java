package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.zze;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzd<T extends IInterface> {
  private static String[] zzftj = new String[] { "service_esmobile", "service_googleme" };
  
  private final Context mContext;
  
  final Handler mHandler;
  
  private final Object mLock = new Object();
  
  private final Looper zzakg;
  
  private final zze zzfko;
  
  private int zzfso;
  
  private long zzfsp;
  
  private long zzfsq;
  
  private int zzfsr;
  
  private long zzfss;
  
  private zzal zzfst;
  
  private final zzaf zzfsu;
  
  private final Object zzfsv = new Object();
  
  private zzax zzfsw;
  
  protected zzj zzfsx;
  
  private T zzfsy;
  
  private final ArrayList<zzi<?>> zzfsz = new ArrayList<zzi<?>>();
  
  private zzl zzfta;
  
  private int zzftb = 1;
  
  private final zzf zzftc;
  
  private final zzg zzftd;
  
  private final int zzfte;
  
  private final String zzftf;
  
  private ConnectionResult zzftg = null;
  
  private boolean zzfth = false;
  
  protected AtomicInteger zzfti = new AtomicInteger(0);
  
  protected zzd(Context paramContext, Looper paramLooper, int paramInt, zzf paramzzf, zzg paramzzg, String paramString) {
    this(paramContext, paramLooper, zzaf.zzce(paramContext), zze.zzaex(), paramInt, zzbp.<zzf>zzu(paramzzf), zzbp.<zzg>zzu(paramzzg), null);
  }
  
  protected zzd(Context paramContext, Looper paramLooper, zzaf paramzzaf, zze paramzze, int paramInt, zzf paramzzf, zzg paramzzg, String paramString) {
    this.mContext = zzbp.<Context>zzb(paramContext, "Context must not be null");
    this.zzakg = zzbp.<Looper>zzb(paramLooper, "Looper must not be null");
    this.zzfsu = zzbp.<zzaf>zzb(paramzzaf, "Supervisor must not be null");
    this.zzfko = zzbp.<zze>zzb(paramzze, "API availability must not be null");
    this.mHandler = new zzh(this, paramLooper);
    this.zzfte = paramInt;
    this.zzftc = paramzzf;
    this.zzftd = paramzzg;
    this.zzftf = paramString;
  }
  
  private final void zza(int paramInt, T paramT) {
    int i;
    byte b;
    boolean bool;
    if (paramInt == 4) {
      i = 1;
    } else {
      i = 0;
    } 
    if (paramT != null) {
      b = 1;
    } else {
      b = 0;
    } 
    if (i == b) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzbh(bool);
    synchronized (this.mLock) {
      this.zzftb = paramInt;
      this.zzfsy = paramT;
      if (paramInt != 1) {
        if (paramInt != 2 && paramInt != 3) {
          if (paramInt == 4)
            zza(paramT); 
        } else {
          if (this.zzfta != null) {
            zzal zzal2 = this.zzfst;
            if (zzal2 != null) {
              String str4 = zzal2.zzakk();
              String str5 = this.zzfst.getPackageName();
              i = String.valueOf(str4).length();
              paramInt = String.valueOf(str5).length();
              StringBuilder stringBuilder = new StringBuilder();
              this(i + 70 + paramInt);
              stringBuilder.append("Calling connect() while still connected, missing disconnect() for ");
              stringBuilder.append(str4);
              stringBuilder.append(" on ");
              stringBuilder.append(str5);
              Log.e("GmsClient", stringBuilder.toString());
              this.zzfsu.zza(this.zzfst.zzakk(), this.zzfst.getPackageName(), this.zzfst.zzakg(), this.zzfta, zzaje());
              this.zzfti.incrementAndGet();
            } 
          } 
          zzl zzl1 = new zzl();
          this(this, this.zzfti.get());
          this.zzfta = zzl1;
          zzal zzal1 = new zzal();
          this(zzajd(), zzhc(), false, 129);
          this.zzfst = zzal1;
          zzaf zzaf1 = this.zzfsu;
          String str2 = zzal1.zzakk();
          String str3 = this.zzfst.getPackageName();
          paramInt = this.zzfst.zzakg();
          zzl zzl2 = this.zzfta;
          String str1 = zzaje();
          zzag zzag = new zzag();
          this(str2, str3, paramInt);
          if (!zzaf1.zza(zzag, zzl2, str1)) {
            String str4 = this.zzfst.zzakk();
            String str5 = this.zzfst.getPackageName();
            i = String.valueOf(str4).length();
            paramInt = String.valueOf(str5).length();
            StringBuilder stringBuilder = new StringBuilder();
            this(i + 34 + paramInt);
            stringBuilder.append("unable to connect to service: ");
            stringBuilder.append(str4);
            stringBuilder.append(" on ");
            stringBuilder.append(str5);
            Log.e("GmsClient", stringBuilder.toString());
            zza(16, (Bundle)null, this.zzfti.get());
          } 
        } 
      } else if (this.zzfta != null) {
        this.zzfsu.zza(zzhc(), zzajd(), 129, this.zzfta, zzaje());
        this.zzfta = null;
      } 
      return;
    } 
  }
  
  private final boolean zza(int paramInt1, int paramInt2, T paramT) {
    synchronized (this.mLock) {
      if (this.zzftb != paramInt1)
        return false; 
      zza(paramInt2, paramT);
      return true;
    } 
  }
  
  @Nullable
  private final String zzaje() {
    String str1 = this.zzftf;
    String str2 = str1;
    if (str1 == null)
      str2 = this.mContext.getClass().getName(); 
    return str2;
  }
  
  private final boolean zzajg() {
    synchronized (this.mLock) {
      boolean bool;
      if (this.zzftb == 3) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  private final boolean zzajm() {
    if (this.zzfth)
      return false; 
    if (TextUtils.isEmpty(zzhd()))
      return false; 
    if (TextUtils.isEmpty(null))
      return false; 
    try {
      Class.forName(zzhd());
      return true;
    } catch (ClassNotFoundException classNotFoundException) {
      return false;
    } 
  }
  
  private final void zzcd(int paramInt) {
    if (zzajg()) {
      paramInt = 5;
      this.zzfth = true;
    } else {
      paramInt = 4;
    } 
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(paramInt, this.zzfti.get(), 16));
  }
  
  public void disconnect() {
    this.zzfti.incrementAndGet();
    synchronized (this.zzfsz) {
      int i = this.zzfsz.size();
      for (byte b = 0; b < i; b++)
        ((zzi)this.zzfsz.get(b)).removeListener(); 
      this.zzfsz.clear();
      synchronized (this.zzfsv) {
        this.zzfsw = null;
        zza(1, (T)null);
        return;
      } 
    } 
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    synchronized (this.mLock) {
      int i = this.zzftb;
      T t = this.zzfsy;
      synchronized (this.zzfsv) {
        zzax zzax1 = this.zzfsw;
        paramPrintWriter.append(paramString).append("mConnectState=");
        if (i != 1) {
          if (i != 2) {
            if (i != 3) {
              if (i != 4) {
                if (i != 5) {
                  null = "UNKNOWN";
                } else {
                  null = "DISCONNECTING";
                } 
              } else {
                null = "CONNECTED";
              } 
            } else {
              null = "LOCAL_CONNECTING";
            } 
          } else {
            null = "REMOTE_CONNECTING";
          } 
        } else {
          null = "DISCONNECTED";
        } 
        paramPrintWriter.print((String)null);
        paramPrintWriter.append(" mService=");
        if (t == null) {
          paramPrintWriter.append("null");
        } else {
          paramPrintWriter.append(zzhd()).append("@").append(Integer.toHexString(System.identityHashCode(t.asBinder())));
        } 
        paramPrintWriter.append(" mServiceBroker=");
        if (zzax1 == null) {
          paramPrintWriter.println("null");
        } else {
          paramPrintWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(zzax1.asBinder())));
        } 
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzfsq > 0L) {
          PrintWriter printWriter = paramPrintWriter.append(paramString).append("lastConnectedTime=");
          long l = this.zzfsq;
          String str = simpleDateFormat.format(new Date(this.zzfsq));
          null = new StringBuilder(String.valueOf(str).length() + 21);
          null.append(l);
          null.append(" ");
          null.append(str);
          printWriter.println(null.toString());
        } 
        if (this.zzfsp > 0L) {
          paramPrintWriter.append(paramString).append("lastSuspendedCause=");
          i = this.zzfso;
          if (i != 1) {
            if (i != 2) {
              null = String.valueOf(i);
            } else {
              null = "CAUSE_NETWORK_LOST";
            } 
          } else {
            null = "CAUSE_SERVICE_DISCONNECTED";
          } 
          paramPrintWriter.append((CharSequence)null);
          PrintWriter printWriter = paramPrintWriter.append(" lastSuspendedTime=");
          long l = this.zzfsp;
          null = simpleDateFormat.format(new Date(this.zzfsp));
          StringBuilder stringBuilder = new StringBuilder(String.valueOf(null).length() + 21);
          stringBuilder.append(l);
          stringBuilder.append(" ");
          stringBuilder.append((String)null);
          printWriter.println(stringBuilder.toString());
        } 
        if (this.zzfss > 0L) {
          paramPrintWriter.append(paramString).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzfsr));
          PrintWriter printWriter = paramPrintWriter.append(" lastFailedTime=");
          long l = this.zzfss;
          String str = simpleDateFormat.format(new Date(this.zzfss));
          null = new StringBuilder(String.valueOf(str).length() + 21);
          null.append(l);
          null.append(" ");
          null.append(str);
          printWriter.println(null.toString());
        } 
        return;
      } 
    } 
  }
  
  public Account getAccount() {
    return null;
  }
  
  public final Context getContext() {
    return this.mContext;
  }
  
  public final Looper getLooper() {
    return this.zzakg;
  }
  
  public final boolean isConnected() {
    synchronized (this.mLock) {
      boolean bool;
      if (this.zzftb == 4) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  public final boolean isConnecting() {
    synchronized (this.mLock) {
      int i = this.zzftb;
      if (i == 2 || i == 3)
        return true; 
      return false;
    } 
  }
  
  @CallSuper
  protected void onConnectionFailed(ConnectionResult paramConnectionResult) {
    this.zzfsr = paramConnectionResult.getErrorCode();
    this.zzfss = System.currentTimeMillis();
  }
  
  @CallSuper
  protected final void onConnectionSuspended(int paramInt) {
    this.zzfso = paramInt;
    this.zzfsp = System.currentTimeMillis();
  }
  
  protected final void zza(int paramInt1, @Nullable Bundle paramBundle, int paramInt2) {
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(7, paramInt2, -1, new zzo(this, paramInt1, null)));
  }
  
  protected void zza(int paramInt1, IBinder paramIBinder, Bundle paramBundle, int paramInt2) {
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(1, paramInt2, -1, new zzn(this, paramInt1, paramIBinder, paramBundle)));
  }
  
  @CallSuper
  protected void zza(@NonNull T paramT) {
    this.zzfsq = System.currentTimeMillis();
  }
  
  @WorkerThread
  public final void zza(zzam paramzzam, Set<Scope> paramSet) {
    Bundle bundle = zzzu();
    zzy zzy = new zzy(this.zzfte);
    zzy.zzfue = this.mContext.getPackageName();
    zzy.zzfuh = bundle;
    if (paramSet != null)
      zzy.zzfug = paramSet.<Scope>toArray(new Scope[paramSet.size()]); 
    if (zzaac()) {
      Account account;
      if (getAccount() != null) {
        account = getAccount();
      } else {
        account = new Account("<<default account>>", "com.google");
      } 
      zzy.zzfui = account;
      if (paramzzam != null)
        zzy.zzfuf = paramzzam.asBinder(); 
    } else if (zzajk()) {
      zzy.zzfui = getAccount();
    } 
    zzy.zzfuj = zzajh();
    try {
      synchronized (this.zzfsv) {
        zzax zzax1 = this.zzfsw;
        if (zzax1 != null) {
          zzk zzk = new zzk();
          this(this, this.zzfti.get());
          zzax1.zza(zzk, zzy);
        } else {
          Log.w("GmsClient", "mServiceBroker is null, client disconnected");
        } 
        return;
      } 
    } catch (DeadObjectException deadObjectException) {
      Log.w("GmsClient", "IGmsServiceBroker.getService failed", (Throwable)deadObjectException);
      zzcc(1);
      return;
    } catch (SecurityException securityException) {
      throw securityException;
    } catch (RemoteException remoteException) {
      Log.w("GmsClient", "IGmsServiceBroker.getService failed", (Throwable)remoteException);
      zza(8, (IBinder)null, (Bundle)null, this.zzfti.get());
      return;
    } catch (RuntimeException runtimeException) {
      Log.w("GmsClient", "IGmsServiceBroker.getService failed", runtimeException);
      zza(8, (IBinder)null, (Bundle)null, this.zzfti.get());
      return;
    } 
  }
  
  public void zza(@NonNull zzj paramzzj) {
    this.zzfsx = zzbp.<zzj>zzb(paramzzj, "Connection progress callbacks cannot be null.");
    zza(2, (T)null);
  }
  
  protected final void zza(@NonNull zzj paramzzj, int paramInt, @Nullable PendingIntent paramPendingIntent) {
    this.zzfsx = zzbp.<zzj>zzb(paramzzj, "Connection progress callbacks cannot be null.");
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(3, this.zzfti.get(), paramInt, paramPendingIntent));
  }
  
  public boolean zzaac() {
    return false;
  }
  
  public boolean zzaal() {
    return false;
  }
  
  public Intent zzaam() {
    throw new UnsupportedOperationException("Not a sign in API");
  }
  
  public Bundle zzaeh() {
    return null;
  }
  
  public boolean zzaff() {
    return true;
  }
  
  @Nullable
  public final IBinder zzafg() {
    synchronized (this.zzfsv) {
      zzax zzax1 = this.zzfsw;
      if (zzax1 == null)
        return null; 
      return zzax1.asBinder();
    } 
  }
  
  protected String zzajd() {
    return "com.google.android.gms";
  }
  
  public final void zzajf() {
    int i = this.zzfko.isGooglePlayServicesAvailable(this.mContext);
    if (i != 0) {
      zza(1, (T)null);
      zza(new zzm(this), i, (PendingIntent)null);
      return;
    } 
    zza(new zzm(this));
  }
  
  public zzc[] zzajh() {
    return new zzc[0];
  }
  
  protected final void zzaji() {
    if (isConnected())
      return; 
    throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
  }
  
  public final T zzajj() throws DeadObjectException {
    synchronized (this.mLock) {
      if (this.zzftb != 5) {
        boolean bool;
        zzaji();
        if (this.zzfsy != null) {
          bool = true;
        } else {
          bool = false;
        } 
        zzbp.zza(bool, "Client is connected but service is null");
        return this.zzfsy;
      } 
      DeadObjectException deadObjectException = new DeadObjectException();
      this();
      throw deadObjectException;
    } 
  }
  
  public boolean zzajk() {
    return false;
  }
  
  protected Set<Scope> zzajl() {
    return Collections.EMPTY_SET;
  }
  
  public final void zzcc(int paramInt) {
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(6, this.zzfti.get(), paramInt));
  }
  
  @Nullable
  protected abstract T zzd(IBinder paramIBinder);
  
  @NonNull
  protected abstract String zzhc();
  
  @NonNull
  protected abstract String zzhd();
  
  protected Bundle zzzu() {
    return new Bundle();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */