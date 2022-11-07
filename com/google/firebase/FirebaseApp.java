package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.internal.zzk;
import com.google.android.gms.common.api.internal.zzo$;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzr;
import com.google.android.gms.internal.zzeks;
import com.google.android.gms.internal.zzekt;
import com.google.android.gms.internal.zzeku;
import com.google.android.gms.internal.zzekv;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.GetTokenResult;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FirebaseApp {
  public static final String DEFAULT_APP_NAME = "[DEFAULT]";
  
  private static final Object zzaqd;
  
  static final Map<String, FirebaseApp> zzhtn;
  
  private static final List<String> zzlid = Arrays.asList(new String[] { "com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId" });
  
  private static final List<String> zzlie = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
  
  private static final List<String> zzlif = Arrays.asList(new String[] { "com.google.android.gms.measurement.AppMeasurement" });
  
  private static final List<String> zzlig = Arrays.asList(new String[0]);
  
  private static final Set<String> zzlih = Collections.emptySet();
  
  private final Context mApplicationContext;
  
  private final String mName;
  
  private final FirebaseOptions zzlii;
  
  private final AtomicBoolean zzlij = new AtomicBoolean(false);
  
  private final AtomicBoolean zzlik = new AtomicBoolean();
  
  private final List<zzb> zzlil = new CopyOnWriteArrayList<zzb>();
  
  private final List<zza> zzlim = new CopyOnWriteArrayList<zza>();
  
  private final List<Object> zzlin = new CopyOnWriteArrayList();
  
  private zzeku zzlio;
  
  private zzc zzlip;
  
  static {
    zzaqd = new Object();
    zzhtn = (Map<String, FirebaseApp>)new ArrayMap();
  }
  
  private FirebaseApp(Context paramContext, String paramString, FirebaseOptions paramFirebaseOptions) {
    this.mApplicationContext = (Context)zzbp.zzu(paramContext);
    this.mName = zzbp.zzgg(paramString);
    this.zzlii = (FirebaseOptions)zzbp.zzu(paramFirebaseOptions);
    this.zzlip = (zzc)new zzeks();
  }
  
  public static List<FirebaseApp> getApps(Context paramContext) {
    zzekt.zzep(paramContext);
    synchronized (zzaqd) {
      ArrayList<FirebaseApp> arrayList = new ArrayList();
      Map<String, FirebaseApp> map = zzhtn;
      this((Collection)map.values());
      zzekt.zzcgg();
      Set set = zzekt.zzcgh();
      set.removeAll(map.keySet());
      for (String str : set) {
        zzekt.zzrh(str);
        arrayList.add(initializeApp(paramContext, null, str));
      } 
      return arrayList;
    } 
  }
  
  @Nullable
  public static FirebaseApp getInstance() {
    synchronized (zzaqd) {
      FirebaseApp firebaseApp = zzhtn.get("[DEFAULT]");
      if (firebaseApp != null)
        return firebaseApp; 
      IllegalStateException illegalStateException = new IllegalStateException();
      String str = zzr.zzalk();
      int i = String.valueOf(str).length();
      StringBuilder stringBuilder = new StringBuilder();
      this(i + 116);
      stringBuilder.append("Default FirebaseApp is not initialized in this process ");
      stringBuilder.append(str);
      stringBuilder.append(". Make sure to call FirebaseApp.initializeApp(Context) first.");
      this(stringBuilder.toString());
      throw illegalStateException;
    } 
  }
  
  public static FirebaseApp getInstance(@NonNull String paramString) {
    synchronized (zzaqd) {
      String str;
      FirebaseApp firebaseApp = zzhtn.get(paramString.trim());
      if (firebaseApp != null)
        return firebaseApp; 
      List<String> list = zzbny();
      if (!list.isEmpty()) {
        str = String.valueOf(TextUtils.join(", ", list));
        if (str.length() != 0) {
          str = "Available app names: ".concat(str);
        } else {
          str = new String("Available app names: ");
        } 
      } else {
        str = "";
      } 
      paramString = String.format("FirebaseApp with name %s doesn't exist. %s", new Object[] { paramString, str });
      IllegalStateException illegalStateException = new IllegalStateException();
      this(paramString);
      throw illegalStateException;
    } 
  }
  
  @Nullable
  public static FirebaseApp initializeApp(Context paramContext) {
    synchronized (zzaqd) {
      if (zzhtn.containsKey("[DEFAULT]")) {
        firebaseApp = getInstance();
        return firebaseApp;
      } 
      FirebaseOptions firebaseOptions = FirebaseOptions.fromResource((Context)firebaseApp);
      if (firebaseOptions == null)
        return null; 
      FirebaseApp firebaseApp = initializeApp((Context)firebaseApp, firebaseOptions);
      return firebaseApp;
    } 
  }
  
  public static FirebaseApp initializeApp(Context paramContext, FirebaseOptions paramFirebaseOptions) {
    return initializeApp(paramContext, paramFirebaseOptions, "[DEFAULT]");
  }
  
  public static FirebaseApp initializeApp(Context paramContext, FirebaseOptions paramFirebaseOptions, String paramString) {
    zzekt.zzep(paramContext);
    if (paramContext.getApplicationContext() instanceof Application) {
      zzk.zza((Application)paramContext.getApplicationContext());
      zzk.zzafz().zza(new zza());
    } 
    paramString = paramString.trim();
    if (paramContext.getApplicationContext() != null)
      paramContext = paramContext.getApplicationContext(); 
    synchronized (zzaqd) {
      boolean bool;
      Map<String, FirebaseApp> map = zzhtn;
      if (!map.containsKey(paramString)) {
        bool = true;
      } else {
        bool = false;
      } 
      int i = String.valueOf(paramString).length();
      StringBuilder stringBuilder = new StringBuilder();
      this(i + 33);
      stringBuilder.append("FirebaseApp name ");
      stringBuilder.append(paramString);
      stringBuilder.append(" already exists!");
      zzbp.zza(bool, stringBuilder.toString());
      zzbp.zzb(paramContext, "Application context cannot be null.");
      FirebaseApp firebaseApp = new FirebaseApp();
      this(paramContext, paramString, paramFirebaseOptions);
      map.put(paramString, firebaseApp);
      zzekt.zzf(firebaseApp);
      firebaseApp.zza(FirebaseApp.class, firebaseApp, zzlid);
      if (firebaseApp.zzbnw()) {
        firebaseApp.zza(FirebaseApp.class, firebaseApp, zzlie);
        firebaseApp.zza(Context.class, firebaseApp.getApplicationContext(), zzlif);
      } 
      return firebaseApp;
    } 
  }
  
  private final <T> void zza(Class<T> paramClass, T paramT, Iterable<String> paramIterable) {
    boolean bool = ContextCompat.isDeviceProtectedStorage(this.mApplicationContext);
    if (bool)
      zzd.zzeg(this.mApplicationContext); 
    Iterator<String> iterator = paramIterable.iterator();
    while (true) {
      while (true)
        break; 
      if (Modifier.isPublic(SYNTHETIC_LOCAL_VARIABLE_7) && Modifier.isStatic(SYNTHETIC_LOCAL_VARIABLE_7))
        SYNTHETIC_LOCAL_VARIABLE_6.invoke(null, new Object[] { paramT }); 
    } 
  }
  
  public static void zzbe(boolean paramBoolean) {
    synchronized (zzaqd) {
      ArrayList<Object> arrayList = new ArrayList();
      this((Collection)zzhtn.values());
      int i = arrayList.size();
      int j = 0;
      while (j < i) {
        FirebaseApp firebaseApp = (FirebaseApp)arrayList.get(j);
        int k = j + 1;
        firebaseApp = firebaseApp;
        j = k;
        if (firebaseApp.zzlij.get()) {
          firebaseApp.zzcb(paramBoolean);
          j = k;
        } 
      } 
      return;
    } 
  }
  
  private final void zzbnv() {
    zzbp.zza(this.zzlik.get() ^ true, "FirebaseApp was deleted");
  }
  
  private static List<String> zzbny() {
    ArraySet<String> arraySet = new ArraySet();
    synchronized (zzaqd) {
      Iterator<FirebaseApp> iterator = zzhtn.values().iterator();
      while (iterator.hasNext())
        arraySet.add(((FirebaseApp)iterator.next()).getName()); 
      if (zzekt.zzcgg() != null)
        arraySet.addAll(zzekt.zzcgh()); 
      null = (Object<String>)new ArrayList<String>((Collection<? extends String>)arraySet);
      Collections.sort((List<String>)null);
      return (List<String>)null;
    } 
  }
  
  private final void zzbnz() {
    zza(FirebaseApp.class, this, zzlid);
    if (zzbnw()) {
      zza(FirebaseApp.class, this, zzlie);
      zza(Context.class, this.mApplicationContext, zzlif);
    } 
  }
  
  private final void zzcb(boolean paramBoolean) {
    Log.d("FirebaseApp", "Notifying background state change listeners.");
    Iterator<zza> iterator = this.zzlim.iterator();
    while (iterator.hasNext())
      ((zza)iterator.next()).zzbe(paramBoolean); 
  }
  
  public boolean equals(Object paramObject) {
    return !(paramObject instanceof FirebaseApp) ? false : this.mName.equals(((FirebaseApp)paramObject).getName());
  }
  
  @NonNull
  public Context getApplicationContext() {
    zzbnv();
    return this.mApplicationContext;
  }
  
  @NonNull
  public String getName() {
    zzbnv();
    return this.mName;
  }
  
  @NonNull
  public FirebaseOptions getOptions() {
    zzbnv();
    return this.zzlii;
  }
  
  public final Task<GetTokenResult> getToken(boolean paramBoolean) {
    zzbnv();
    zzeku zzeku1 = this.zzlio;
    return (zzeku1 == null) ? Tasks.forException(new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode.")) : zzeku1.zzcc(paramBoolean);
  }
  
  @Nullable
  public final String getUid() throws FirebaseApiNotAvailableException {
    zzbnv();
    zzeku zzeku1 = this.zzlio;
    if (zzeku1 != null)
      return zzeku1.getUid(); 
    throw new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode.");
  }
  
  public int hashCode() {
    return this.mName.hashCode();
  }
  
  public void setAutomaticResourceManagementEnabled(boolean paramBoolean) {
    zzbnv();
    if (this.zzlij.compareAndSet(paramBoolean ^ true, paramBoolean)) {
      boolean bool = zzk.zzafz().zzaga();
      if (paramBoolean && bool) {
        zzcb(true);
        return;
      } 
      if (!paramBoolean && bool)
        zzcb(false); 
    } 
  }
  
  public String toString() {
    return zzbf.zzt(this).zzg("name", this.mName).zzg("options", this.zzlii).toString();
  }
  
  public final void zza(@NonNull zzeku paramzzeku) {
    this.zzlio = (zzeku)zzbp.zzu(paramzzeku);
  }
  
  @UiThread
  public final void zza(@NonNull zzekv paramzzekv) {
    Log.d("FirebaseApp", "Notifying auth state listeners.");
    Iterator<zzb> iterator = this.zzlil.iterator();
    byte b;
    for (b = 0; iterator.hasNext(); b++)
      ((zzb)iterator.next()).zzb(paramzzekv); 
    Log.d("FirebaseApp", String.format("Notified %d auth state listeners.", new Object[] { Integer.valueOf(b) }));
  }
  
  public final void zza(zza paramzza) {
    zzbnv();
    if (this.zzlij.get() && zzk.zzafz().zzaga())
      paramzza.zzbe(true); 
    this.zzlim.add(paramzza);
  }
  
  public final void zza(@NonNull zzb paramzzb) {
    zzbnv();
    zzbp.zzu(paramzzb);
    this.zzlil.add(paramzzb);
    this.zzlil.size();
  }
  
  public final boolean zzbnw() {
    return "[DEFAULT]".equals(getName());
  }
  
  public final String zzbnx() {
    String str1 = com.google.android.gms.common.util.zzb.zzl(getName().getBytes());
    String str2 = com.google.android.gms.common.util.zzb.zzl(getOptions().getApplicationId().getBytes());
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 1 + String.valueOf(str2).length());
    stringBuilder.append(str1);
    stringBuilder.append("+");
    stringBuilder.append(str2);
    return stringBuilder.toString();
  }
  
  public static interface zza {
    void zzbe(boolean param1Boolean);
  }
  
  public static interface zzb {
    void zzb(@NonNull zzekv param1zzekv);
  }
  
  public static interface zzc {}
  
  @TargetApi(24)
  static final class zzd extends BroadcastReceiver {
    private static AtomicReference<zzd> zzliq = new AtomicReference<zzd>();
    
    private final Context mApplicationContext;
    
    private zzd(Context param1Context) {
      this.mApplicationContext = param1Context;
    }
    
    private static void zzef(Context param1Context) {
      if (zzliq.get() == null) {
        zzd zzd1 = new zzd(param1Context);
        if (zzo$.ExternalSyntheticBackportWithForwarding0.m(zzliq, null, zzd1))
          param1Context.registerReceiver(zzd1, new IntentFilter("android.intent.action.USER_UNLOCKED")); 
      } 
    }
    
    public final void onReceive(Context param1Context, Intent param1Intent) {
      synchronized (FirebaseApp.zzbfb()) {
        Iterator<FirebaseApp> iterator = FirebaseApp.zzhtn.values().iterator();
        while (iterator.hasNext())
          FirebaseApp.zza(iterator.next()); 
        this.mApplicationContext.unregisterReceiver(this);
        return;
      } 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/FirebaseApp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */