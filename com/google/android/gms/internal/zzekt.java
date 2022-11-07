package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.internal.zzo$;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public final class zzekt {
  private static final AtomicReference<zzekt> zzliq = new AtomicReference<zzekt>();
  
  private zzekt(Context paramContext) {}
  
  @Nullable
  public static zzekt zzcgg() {
    return zzliq.get();
  }
  
  public static Set<String> zzcgh() {
    return Collections.emptySet();
  }
  
  public static zzekt zzep(Context paramContext) {
    AtomicReference<zzekt> atomicReference = zzliq;
    zzo$.ExternalSyntheticBackportWithForwarding0.m(atomicReference, null, new zzekt(paramContext));
    return atomicReference.get();
  }
  
  public static void zzf(@NonNull FirebaseApp paramFirebaseApp) {}
  
  public static FirebaseOptions zzrh(@NonNull String paramString) {
    return null;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzekt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */