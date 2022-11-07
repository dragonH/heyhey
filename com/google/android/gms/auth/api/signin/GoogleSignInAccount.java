package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInAccount extends zzbck implements ReflectedParcelable {
  public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new zzb();
  
  private static zzd zzebv = zzh.zzalc();
  
  private static Comparator<Scope> zzecc = new zza();
  
  private int versionCode;
  
  private String zzbsx;
  
  private List<Scope> zzdxx;
  
  private String zzeaf;
  
  private String zzeag;
  
  private String zzeaw;
  
  private String zzebw;
  
  private String zzebx;
  
  private Uri zzeby;
  
  private String zzebz;
  
  private long zzeca;
  
  private String zzecb;
  
  GoogleSignInAccount(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, Uri paramUri, String paramString5, long paramLong, String paramString6, List<Scope> paramList, String paramString7, String paramString8) {
    this.versionCode = paramInt;
    this.zzbsx = paramString1;
    this.zzeaw = paramString2;
    this.zzebw = paramString3;
    this.zzebx = paramString4;
    this.zzeby = paramUri;
    this.zzebz = paramString5;
    this.zzeca = paramLong;
    this.zzecb = paramString6;
    this.zzdxx = paramList;
    this.zzeaf = paramString7;
    this.zzeag = paramString8;
  }
  
  private final JSONObject toJsonObject() {
    JSONObject jSONObject = new JSONObject();
    try {
      if (getId() != null)
        jSONObject.put("id", getId()); 
      if (getIdToken() != null)
        jSONObject.put("tokenId", getIdToken()); 
      if (getEmail() != null)
        jSONObject.put("email", getEmail()); 
      if (getDisplayName() != null)
        jSONObject.put("displayName", getDisplayName()); 
      if (getGivenName() != null)
        jSONObject.put("givenName", getGivenName()); 
      if (getFamilyName() != null)
        jSONObject.put("familyName", getFamilyName()); 
      if (getPhotoUrl() != null)
        jSONObject.put("photoUrl", getPhotoUrl().toString()); 
      if (getServerAuthCode() != null)
        jSONObject.put("serverAuthCode", getServerAuthCode()); 
      jSONObject.put("expirationTime", this.zzeca);
      jSONObject.put("obfuscatedIdentifier", this.zzecb);
      JSONArray jSONArray = new JSONArray();
      this();
      Collections.sort(this.zzdxx, zzecc);
      Iterator<Scope> iterator = this.zzdxx.iterator();
      while (iterator.hasNext())
        jSONArray.put(((Scope)iterator.next()).zzaft()); 
      jSONObject.put("grantedScopes", jSONArray);
      return jSONObject;
    } catch (JSONException jSONException) {
      RuntimeException runtimeException = new RuntimeException((Throwable)jSONException);
      throw runtimeException;
    } 
  }
  
  @Nullable
  public static GoogleSignInAccount zzem(@Nullable String paramString) throws JSONException {
    if (TextUtils.isEmpty(paramString))
      return null; 
    JSONObject jSONObject = new JSONObject(paramString);
    paramString = jSONObject.optString("photoUrl", null);
    if (!TextUtils.isEmpty(paramString)) {
      Uri uri = Uri.parse(paramString);
    } else {
      paramString = null;
    } 
    long l = Long.parseLong(jSONObject.getString("expirationTime"));
    HashSet<Scope> hashSet = new HashSet();
    JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
    int i = jSONArray.length();
    for (byte b = 0; b < i; b++)
      hashSet.add(new Scope(jSONArray.getString(b))); 
    String str1 = jSONObject.optString("id");
    String str2 = jSONObject.optString("tokenId", null);
    String str3 = jSONObject.optString("email", null);
    String str4 = jSONObject.optString("displayName", null);
    String str5 = jSONObject.optString("givenName", null);
    String str6 = jSONObject.optString("familyName", null);
    Long long_2 = Long.valueOf(l);
    String str7 = jSONObject.getString("obfuscatedIdentifier");
    Long long_1 = long_2;
    if (long_2 == null)
      long_1 = Long.valueOf(zzebv.currentTimeMillis() / 1000L); 
    GoogleSignInAccount googleSignInAccount = new GoogleSignInAccount(3, str1, str2, str3, str4, (Uri)paramString, null, long_1.longValue(), zzbp.zzgg(str7), new ArrayList<Scope>((Collection<? extends Scope>)zzbp.zzu(hashSet)), str5, str6);
    googleSignInAccount.zzebz = jSONObject.optString("serverAuthCode", null);
    return googleSignInAccount;
  }
  
  public boolean equals(Object paramObject) {
    return !(paramObject instanceof GoogleSignInAccount) ? false : ((GoogleSignInAccount)paramObject).toJsonObject().toString().equals(toJsonObject().toString());
  }
  
  @Nullable
  public Account getAccount() {
    return (this.zzebw == null) ? null : new Account(this.zzebw, "com.google");
  }
  
  @Nullable
  public String getDisplayName() {
    return this.zzebx;
  }
  
  @Nullable
  public String getEmail() {
    return this.zzebw;
  }
  
  @Nullable
  public String getFamilyName() {
    return this.zzeag;
  }
  
  @Nullable
  public String getGivenName() {
    return this.zzeaf;
  }
  
  @NonNull
  public Set<Scope> getGrantedScopes() {
    return new HashSet<Scope>(this.zzdxx);
  }
  
  @Nullable
  public String getId() {
    return this.zzbsx;
  }
  
  @Nullable
  public String getIdToken() {
    return this.zzeaw;
  }
  
  @Nullable
  public Uri getPhotoUrl() {
    return this.zzeby;
  }
  
  @Nullable
  public String getServerAuthCode() {
    return this.zzebz;
  }
  
  public int hashCode() {
    return toJsonObject().toString().hashCode();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.versionCode);
    zzbcn.zza(paramParcel, 2, getId(), false);
    zzbcn.zza(paramParcel, 3, getIdToken(), false);
    zzbcn.zza(paramParcel, 4, getEmail(), false);
    zzbcn.zza(paramParcel, 5, getDisplayName(), false);
    zzbcn.zza(paramParcel, 6, (Parcelable)getPhotoUrl(), paramInt, false);
    zzbcn.zza(paramParcel, 7, getServerAuthCode(), false);
    zzbcn.zza(paramParcel, 8, this.zzeca);
    zzbcn.zza(paramParcel, 9, this.zzecb, false);
    zzbcn.zzc(paramParcel, 10, this.zzdxx, false);
    zzbcn.zza(paramParcel, 11, getGivenName(), false);
    zzbcn.zza(paramParcel, 12, getFamilyName(), false);
    zzbcn.zzai(paramParcel, i);
  }
  
  public final boolean zzaad() {
    return (zzebv.currentTimeMillis() / 1000L >= this.zzeca - 300L);
  }
  
  @NonNull
  public final String zzaae() {
    return this.zzecb;
  }
  
  public final String zzaaf() {
    JSONObject jSONObject = toJsonObject();
    jSONObject.remove("serverAuthCode");
    return jSONObject.toString();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/auth/api/signin/GoogleSignInAccount.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */