package com.google.android.gms.auth.api.signin.internal;

public final class zzo {
  private static int zzeda = 31;
  
  private int zzedb = 1;
  
  public final int zzaao() {
    return this.zzedb;
  }
  
  public final zzo zzaq(boolean paramBoolean) {
    this.zzedb = zzeda * this.zzedb + paramBoolean;
    return this;
  }
  
  public final zzo zzo(Object paramObject) {
    int k;
    int i = zzeda;
    int j = this.zzedb;
    if (paramObject == null) {
      k = 0;
    } else {
      k = paramObject.hashCode();
    } 
    this.zzedb = i * j + k;
    return this;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/auth/api/signin/internal/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */