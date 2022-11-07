package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.internal.zzbd;
import com.google.android.gms.common.api.internal.zzcf;
import com.google.android.gms.common.api.internal.zzcj;
import com.google.android.gms.common.api.internal.zzcv;
import com.google.android.gms.common.api.internal.zzdg;
import com.google.android.gms.common.api.internal.zzi;
import com.google.android.gms.common.api.internal.zzw;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.internal.zzcpp;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GoogleApiClient {
  public static final int SIGN_IN_MODE_OPTIONAL = 2;
  
  public static final int SIGN_IN_MODE_REQUIRED = 1;
  
  private static final Set<GoogleApiClient> zzfha = Collections.newSetFromMap(new WeakHashMap<GoogleApiClient, Boolean>());
  
  public static void dumpAll(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    // Byte code:
    //   0: getstatic com/google/android/gms/common/api/GoogleApiClient.zzfha : Ljava/util/Set;
    //   3: astore #4
    //   5: aload #4
    //   7: monitorenter
    //   8: iconst_0
    //   9: istore #5
    //   11: aload_0
    //   12: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   15: ldc '  '
    //   17: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   20: astore #6
    //   22: aload #4
    //   24: invokeinterface iterator : ()Ljava/util/Iterator;
    //   29: astore #7
    //   31: aload #7
    //   33: invokeinterface hasNext : ()Z
    //   38: ifeq -> 84
    //   41: aload #7
    //   43: invokeinterface next : ()Ljava/lang/Object;
    //   48: checkcast com/google/android/gms/common/api/GoogleApiClient
    //   51: astore #8
    //   53: aload_2
    //   54: aload_0
    //   55: invokevirtual append : (Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
    //   58: ldc 'GoogleApiClient#'
    //   60: invokevirtual append : (Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
    //   63: iload #5
    //   65: invokevirtual println : (I)V
    //   68: aload #8
    //   70: aload #6
    //   72: aload_1
    //   73: aload_2
    //   74: aload_3
    //   75: invokevirtual dump : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   78: iinc #5, 1
    //   81: goto -> 31
    //   84: aload #4
    //   86: monitorexit
    //   87: return
    //   88: astore_0
    //   89: aload #4
    //   91: monitorexit
    //   92: goto -> 97
    //   95: aload_0
    //   96: athrow
    //   97: goto -> 95
    // Exception table:
    //   from	to	target	type
    //   11	31	88	finally
    //   31	78	88	finally
    //   84	87	88	finally
    //   89	92	88	finally
  }
  
  public static Set<GoogleApiClient> zzafo() {
    synchronized (zzfha) {
      return null;
    } 
  }
  
  public abstract ConnectionResult blockingConnect();
  
  public abstract ConnectionResult blockingConnect(long paramLong, @NonNull TimeUnit paramTimeUnit);
  
  public abstract PendingResult<Status> clearDefaultAccountAndReconnect();
  
  public abstract void connect();
  
  public void connect(int paramInt) {
    throw new UnsupportedOperationException();
  }
  
  public abstract void disconnect();
  
  public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  @NonNull
  public abstract ConnectionResult getConnectionResult(@NonNull Api<?> paramApi);
  
  public Context getContext() {
    throw new UnsupportedOperationException();
  }
  
  public Looper getLooper() {
    throw new UnsupportedOperationException();
  }
  
  public abstract boolean hasConnectedApi(@NonNull Api<?> paramApi);
  
  public abstract boolean isConnected();
  
  public abstract boolean isConnecting();
  
  public abstract boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public abstract void reconnect();
  
  public abstract void registerConnectionCallbacks(@NonNull ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public abstract void stopAutoManage(@NonNull FragmentActivity paramFragmentActivity);
  
  public abstract void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener paramOnConnectionFailedListener);
  
  @NonNull
  public <C extends Api.zze> C zza(@NonNull Api.zzc<C> paramzzc) {
    throw new UnsupportedOperationException();
  }
  
  public void zza(zzdg paramzzdg) {
    throw new UnsupportedOperationException();
  }
  
  public boolean zza(@NonNull Api<?> paramApi) {
    throw new UnsupportedOperationException();
  }
  
  public boolean zza(zzcv paramzzcv) {
    throw new UnsupportedOperationException();
  }
  
  public void zzafp() {
    throw new UnsupportedOperationException();
  }
  
  public void zzb(zzdg paramzzdg) {
    throw new UnsupportedOperationException();
  }
  
  public <A extends Api.zzb, R extends Result, T extends com.google.android.gms.common.api.internal.zzm<R, A>> T zzd(@NonNull T paramT) {
    throw new UnsupportedOperationException();
  }
  
  public <A extends Api.zzb, T extends com.google.android.gms.common.api.internal.zzm<? extends Result, A>> T zze(@NonNull T paramT) {
    throw new UnsupportedOperationException();
  }
  
  public <L> zzcj<L> zzp(@NonNull L paramL) {
    throw new UnsupportedOperationException();
  }
  
  public static final class Builder {
    private final Context mContext;
    
    private Looper zzakg;
    
    private Account zzduz;
    
    private String zzdxc;
    
    private final Set<Scope> zzfhb = new HashSet<Scope>();
    
    private final Set<Scope> zzfhc = new HashSet<Scope>();
    
    private int zzfhd;
    
    private View zzfhe;
    
    private String zzfhf;
    
    private final Map<Api<?>, zzs> zzfhg = (Map<Api<?>, zzs>)new ArrayMap();
    
    private final Map<Api<?>, Api.ApiOptions> zzfhh = (Map<Api<?>, Api.ApiOptions>)new ArrayMap();
    
    private zzcf zzfhi;
    
    private int zzfhj = -1;
    
    private GoogleApiClient.OnConnectionFailedListener zzfhk;
    
    private GoogleApiAvailability zzfhl = GoogleApiAvailability.getInstance();
    
    private Api.zza<? extends zzcps, zzcpt> zzfhm = zzcpp.zzdwq;
    
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzfhn = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
    
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzfho = new ArrayList<GoogleApiClient.OnConnectionFailedListener>();
    
    private boolean zzfhp = false;
    
    public Builder(@NonNull Context param1Context) {
      this.mContext = param1Context;
      this.zzakg = param1Context.getMainLooper();
      this.zzdxc = param1Context.getPackageName();
      this.zzfhf = param1Context.getClass().getName();
    }
    
    public Builder(@NonNull Context param1Context, @NonNull GoogleApiClient.ConnectionCallbacks param1ConnectionCallbacks, @NonNull GoogleApiClient.OnConnectionFailedListener param1OnConnectionFailedListener) {
      this(param1Context);
      zzbp.zzb(param1ConnectionCallbacks, "Must provide a connected listener");
      this.zzfhn.add(param1ConnectionCallbacks);
      zzbp.zzb(param1OnConnectionFailedListener, "Must provide a connection failed listener");
      this.zzfho.add(param1OnConnectionFailedListener);
    }
    
    private final <O extends Api.ApiOptions> void zza(Api<O> param1Api, O param1O, Scope... param1VarArgs) {
      HashSet<Scope> hashSet = new HashSet<Scope>(param1Api.zzafc().zzn(param1O));
      int i = param1VarArgs.length;
      for (byte b = 0; b < i; b++)
        hashSet.add(param1VarArgs[b]); 
      this.zzfhg.put(param1Api, new zzs(hashSet));
    }
    
    public final Builder addApi(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> param1Api) {
      zzbp.zzb(param1Api, "Api must not be null");
      this.zzfhh.put(param1Api, null);
      List<Scope> list = param1Api.zzafc().zzn(null);
      this.zzfhc.addAll(list);
      this.zzfhb.addAll(list);
      return this;
    }
    
    public final <O extends Api.ApiOptions.HasOptions> Builder addApi(@NonNull Api<O> param1Api, @NonNull O param1O) {
      zzbp.zzb(param1Api, "Api must not be null");
      zzbp.zzb(param1O, "Null options are not permitted for this Api");
      this.zzfhh.put(param1Api, (Api.ApiOptions)param1O);
      List<Scope> list = param1Api.zzafc().zzn(param1O);
      this.zzfhc.addAll(list);
      this.zzfhb.addAll(list);
      return this;
    }
    
    public final <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(@NonNull Api<O> param1Api, @NonNull O param1O, Scope... param1VarArgs) {
      zzbp.zzb(param1Api, "Api must not be null");
      zzbp.zzb(param1O, "Null options are not permitted for this Api");
      this.zzfhh.put(param1Api, (Api.ApiOptions)param1O);
      zza((Api)param1Api, (Api.ApiOptions)param1O, param1VarArgs);
      return this;
    }
    
    public final Builder addApiIfAvailable(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> param1Api, Scope... param1VarArgs) {
      zzbp.zzb(param1Api, "Api must not be null");
      this.zzfhh.put(param1Api, null);
      zza(param1Api, null, param1VarArgs);
      return this;
    }
    
    public final Builder addConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks param1ConnectionCallbacks) {
      zzbp.zzb(param1ConnectionCallbacks, "Listener must not be null");
      this.zzfhn.add(param1ConnectionCallbacks);
      return this;
    }
    
    public final Builder addOnConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener param1OnConnectionFailedListener) {
      zzbp.zzb(param1OnConnectionFailedListener, "Listener must not be null");
      this.zzfho.add(param1OnConnectionFailedListener);
      return this;
    }
    
    public final Builder addScope(@NonNull Scope param1Scope) {
      zzbp.zzb(param1Scope, "Scope must not be null");
      this.zzfhb.add(param1Scope);
      return this;
    }
    
    public final GoogleApiClient build() {
      String str;
      StringBuilder stringBuilder;
      zzbp.zzb(this.zzfhh.isEmpty() ^ true, "must call addApi() to add at least one API");
      zzq zzq = zzafr();
      Api api = null;
      Map map = zzq.zzajt();
      ArrayMap<Api, Boolean> arrayMap = new ArrayMap();
      ArrayMap<Api.zzc<?>, zzw> arrayMap1 = new ArrayMap();
      ArrayList<zzw> arrayList = new ArrayList();
      Iterator<Api> iterator = this.zzfhh.keySet().iterator();
      boolean bool = false;
      while (iterator.hasNext()) {
        boolean bool1;
        Api api1 = iterator.next();
        Object object = this.zzfhh.get(api1);
        if (map.get(api1) != null) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        arrayMap.put(api1, Boolean.valueOf(bool1));
        zzw zzw = new zzw(api1, bool1);
        arrayList.add(zzw);
        Api.zza zza1 = api1.zzafd();
        zzw = (zzw)zza1.zza(this.mContext, this.zzakg, zzq, object, (GoogleApiClient.ConnectionCallbacks)zzw, (GoogleApiClient.OnConnectionFailedListener)zzw);
        arrayMap1.put(api1.zzafe(), zzw);
        boolean bool2 = bool;
        if (zza1.getPriority() == 1)
          if (object != null) {
            bool2 = true;
          } else {
            bool2 = false;
          }  
        bool = bool2;
        if (zzw.zzaal()) {
          if (api == null) {
            api = api1;
            bool = bool2;
            continue;
          } 
          String str1 = api1.getName();
          str = api.getName();
          stringBuilder = new StringBuilder(String.valueOf(str1).length() + 21 + String.valueOf(str).length());
          stringBuilder.append(str1);
          stringBuilder.append(" cannot be used with ");
          stringBuilder.append(str);
          throw new IllegalStateException(stringBuilder.toString());
        } 
      } 
      if (str != null)
        if (!bool) {
          boolean bool1;
          if (this.zzduz == null) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          zzbp.zza(bool1, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", new Object[] { str.getName() });
          zzbp.zza(this.zzfhb.equals(this.zzfhc), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", new Object[] { str.getName() });
        } else {
          String str1 = str.getName();
          StringBuilder stringBuilder1 = new StringBuilder(String.valueOf(str1).length() + 82);
          stringBuilder1.append("With using ");
          stringBuilder1.append(str1);
          stringBuilder1.append(", GamesOptions can only be specified within GoogleSignInOptions.Builder");
          throw new IllegalStateException(stringBuilder1.toString());
        }  
      int i = zzbd.zza(arrayMap1.values(), true);
      null = new zzbd(this.mContext, new ReentrantLock(), this.zzakg, zzq, this.zzfhl, this.zzfhm, (Map)stringBuilder, this.zzfhn, this.zzfho, (Map)arrayMap1, this.zzfhj, i, arrayList, false);
      synchronized (GoogleApiClient.zzafq()) {
        GoogleApiClient.zzafq().add(null);
        if (this.zzfhj >= 0)
          zzi.zza(this.zzfhi).zza(this.zzfhj, (GoogleApiClient)null, this.zzfhk); 
        return (GoogleApiClient)null;
      } 
    }
    
    public final Builder enableAutoManage(@NonNull FragmentActivity param1FragmentActivity, int param1Int, @Nullable GoogleApiClient.OnConnectionFailedListener param1OnConnectionFailedListener) {
      boolean bool;
      zzcf zzcf1 = new zzcf((Activity)param1FragmentActivity);
      if (param1Int >= 0) {
        bool = true;
      } else {
        bool = false;
      } 
      zzbp.zzb(bool, "clientId must be non-negative");
      this.zzfhj = param1Int;
      this.zzfhk = param1OnConnectionFailedListener;
      this.zzfhi = zzcf1;
      return this;
    }
    
    public final Builder enableAutoManage(@NonNull FragmentActivity param1FragmentActivity, @Nullable GoogleApiClient.OnConnectionFailedListener param1OnConnectionFailedListener) {
      return enableAutoManage(param1FragmentActivity, 0, param1OnConnectionFailedListener);
    }
    
    public final Builder setAccountName(String param1String) {
      Account account;
      if (param1String == null) {
        param1String = null;
      } else {
        account = new Account(param1String, "com.google");
      } 
      this.zzduz = account;
      return this;
    }
    
    public final Builder setGravityForPopups(int param1Int) {
      this.zzfhd = param1Int;
      return this;
    }
    
    public final Builder setHandler(@NonNull Handler param1Handler) {
      zzbp.zzb(param1Handler, "Handler must not be null");
      this.zzakg = param1Handler.getLooper();
      return this;
    }
    
    public final Builder setViewForPopups(@NonNull View param1View) {
      zzbp.zzb(param1View, "View must not be null");
      this.zzfhe = param1View;
      return this;
    }
    
    public final Builder useDefaultAccount() {
      return setAccountName("<<default account>>");
    }
    
    public final zzq zzafr() {
      zzcpt zzcpt = zzcpt.zzjno;
      Map<Api<?>, Api.ApiOptions> map = this.zzfhh;
      Api api = zzcpp.API;
      if (map.containsKey(api))
        zzcpt = (zzcpt)this.zzfhh.get(api); 
      return new zzq(this.zzduz, this.zzfhb, this.zzfhg, this.zzfhd, this.zzfhe, this.zzdxc, this.zzfhf, zzcpt);
    }
  }
  
  public static interface ConnectionCallbacks {
    public static final int CAUSE_NETWORK_LOST = 2;
    
    public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    
    void onConnected(@Nullable Bundle param1Bundle);
    
    void onConnectionSuspended(int param1Int);
  }
  
  public static interface OnConnectionFailedListener {
    void onConnectionFailed(@NonNull ConnectionResult param1ConnectionResult);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/GoogleApiClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */