package com.google.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzbz;
import com.google.android.gms.common.util.zzt;
import java.util.Arrays;

public final class FirebaseOptions {
  private final String zzehy;
  
  private final String zzlir;
  
  private final String zzlis;
  
  private final String zzlit;
  
  private final String zzliu;
  
  private final String zzliv;
  
  private final String zzliw;
  
  private FirebaseOptions(@NonNull String paramString1, @NonNull String paramString2, @Nullable String paramString3, @Nullable String paramString4, @Nullable String paramString5, @Nullable String paramString6, @Nullable String paramString7) {
    zzbp.zza(zzt.zzgm(paramString1) ^ true, "ApplicationId must be set.");
    this.zzehy = paramString1;
    this.zzlir = paramString2;
    this.zzlis = paramString3;
    this.zzlit = paramString4;
    this.zzliu = paramString5;
    this.zzliv = paramString6;
    this.zzliw = paramString7;
  }
  
  public static FirebaseOptions fromResource(Context paramContext) {
    zzbz zzbz = new zzbz(paramContext);
    String str = zzbz.getString("google_app_id");
    return TextUtils.isEmpty(str) ? null : new FirebaseOptions(str, zzbz.getString("google_api_key"), zzbz.getString("firebase_database_url"), zzbz.getString("ga_trackingId"), zzbz.getString("gcm_defaultSenderId"), zzbz.getString("google_storage_bucket"), zzbz.getString("project_id"));
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof FirebaseOptions))
      return false; 
    paramObject = paramObject;
    return (zzbf.equal(this.zzehy, ((FirebaseOptions)paramObject).zzehy) && zzbf.equal(this.zzlir, ((FirebaseOptions)paramObject).zzlir) && zzbf.equal(this.zzlis, ((FirebaseOptions)paramObject).zzlis) && zzbf.equal(this.zzlit, ((FirebaseOptions)paramObject).zzlit) && zzbf.equal(this.zzliu, ((FirebaseOptions)paramObject).zzliu) && zzbf.equal(this.zzliv, ((FirebaseOptions)paramObject).zzliv) && zzbf.equal(this.zzliw, ((FirebaseOptions)paramObject).zzliw));
  }
  
  public final String getApiKey() {
    return this.zzlir;
  }
  
  public final String getApplicationId() {
    return this.zzehy;
  }
  
  public final String getDatabaseUrl() {
    return this.zzlis;
  }
  
  public final String getGcmSenderId() {
    return this.zzliu;
  }
  
  public final String getProjectId() {
    return this.zzliw;
  }
  
  public final String getStorageBucket() {
    return this.zzliv;
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.zzehy, this.zzlir, this.zzlis, this.zzlit, this.zzliu, this.zzliv, this.zzliw });
  }
  
  public final String toString() {
    return zzbf.zzt(this).zzg("applicationId", this.zzehy).zzg("apiKey", this.zzlir).zzg("databaseUrl", this.zzlis).zzg("gcmSenderId", this.zzliu).zzg("storageBucket", this.zzliv).zzg("projectId", this.zzliw).toString();
  }
  
  public static final class Builder {
    private String zzehy;
    
    private String zzlir;
    
    private String zzlis;
    
    private String zzlit;
    
    private String zzliu;
    
    private String zzliv;
    
    private String zzliw;
    
    public Builder() {}
    
    public Builder(FirebaseOptions param1FirebaseOptions) {
      this.zzehy = FirebaseOptions.zza(param1FirebaseOptions);
      this.zzlir = FirebaseOptions.zzb(param1FirebaseOptions);
      this.zzlis = FirebaseOptions.zzc(param1FirebaseOptions);
      this.zzlit = FirebaseOptions.zzd(param1FirebaseOptions);
      this.zzliu = FirebaseOptions.zze(param1FirebaseOptions);
      this.zzliv = FirebaseOptions.zzf(param1FirebaseOptions);
      this.zzliw = FirebaseOptions.zzg(param1FirebaseOptions);
    }
    
    public final FirebaseOptions build() {
      return new FirebaseOptions(this.zzehy, this.zzlir, this.zzlis, this.zzlit, this.zzliu, this.zzliv, this.zzliw, null);
    }
    
    public final Builder setApiKey(@NonNull String param1String) {
      this.zzlir = zzbp.zzh(param1String, "ApiKey must be set.");
      return this;
    }
    
    public final Builder setApplicationId(@NonNull String param1String) {
      this.zzehy = zzbp.zzh(param1String, "ApplicationId must be set.");
      return this;
    }
    
    public final Builder setDatabaseUrl(@Nullable String param1String) {
      this.zzlis = param1String;
      return this;
    }
    
    public final Builder setGcmSenderId(@Nullable String param1String) {
      this.zzliu = param1String;
      return this;
    }
    
    public final Builder setProjectId(@Nullable String param1String) {
      this.zzliw = param1String;
      return this;
    }
    
    public final Builder setStorageBucket(@Nullable String param1String) {
      this.zzliv = param1String;
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/FirebaseOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */