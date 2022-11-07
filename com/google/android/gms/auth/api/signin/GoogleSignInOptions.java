package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zzn;
import com.google.android.gms.auth.api.signin.internal.zzo;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInOptions extends zzbck implements Api.ApiOptions.Optional, ReflectedParcelable {
  public static final Parcelable.Creator<GoogleSignInOptions> CREATOR;
  
  public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
  
  public static final GoogleSignInOptions DEFAULT_SIGN_IN;
  
  private static Scope SCOPE_GAMES;
  
  private static Comparator<Scope> zzecc;
  
  public static final Scope zzecd = new Scope("profile");
  
  public static final Scope zzece = new Scope("email");
  
  public static final Scope zzecf = new Scope("openid");
  
  private int versionCode;
  
  private Account zzduz;
  
  private boolean zzeap;
  
  private String zzeaq;
  
  private final ArrayList<Scope> zzecg;
  
  private final boolean zzech;
  
  private final boolean zzeci;
  
  private String zzecj;
  
  private ArrayList<zzn> zzeck;
  
  private Map<Integer, zzn> zzecl;
  
  static {
    SCOPE_GAMES = new Scope("https://www.googleapis.com/auth/games");
    DEFAULT_SIGN_IN = (new Builder()).requestId().requestProfile().build();
    DEFAULT_GAMES_SIGN_IN = (new Builder()).requestScopes(SCOPE_GAMES, new Scope[0]).build();
    CREATOR = new zzd();
    zzecc = new zzc();
  }
  
  GoogleSignInOptions(int paramInt, ArrayList<Scope> paramArrayList, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, String paramString2, ArrayList<zzn> paramArrayList1) {
    this(paramInt, paramArrayList, paramAccount, paramBoolean1, paramBoolean2, paramBoolean3, paramString1, paramString2, zzu(paramArrayList1));
  }
  
  private GoogleSignInOptions(int paramInt, ArrayList<Scope> paramArrayList, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, String paramString2, Map<Integer, zzn> paramMap) {
    this.versionCode = paramInt;
    this.zzecg = paramArrayList;
    this.zzduz = paramAccount;
    this.zzeap = paramBoolean1;
    this.zzech = paramBoolean2;
    this.zzeci = paramBoolean3;
    this.zzeaq = paramString1;
    this.zzecj = paramString2;
    this.zzeck = new ArrayList<zzn>(paramMap.values());
    this.zzecl = paramMap;
  }
  
  private final JSONObject toJsonObject() {
    JSONObject jSONObject = new JSONObject();
    try {
      JSONArray jSONArray = new JSONArray();
      this();
      Collections.sort(this.zzecg, zzecc);
      ArrayList<Scope> arrayList = this.zzecg;
      int i = arrayList.size();
      byte b = 0;
      while (b < i) {
        Scope scope = (Scope)arrayList.get(b);
        b++;
        jSONArray.put(((Scope)scope).zzaft());
      } 
      jSONObject.put("scopes", jSONArray);
      Account account = this.zzduz;
      if (account != null)
        jSONObject.put("accountName", account.name); 
      jSONObject.put("idTokenRequested", this.zzeap);
      jSONObject.put("forceCodeForRefreshToken", this.zzeci);
      jSONObject.put("serverAuthRequested", this.zzech);
      if (!TextUtils.isEmpty(this.zzeaq))
        jSONObject.put("serverClientId", this.zzeaq); 
      if (!TextUtils.isEmpty(this.zzecj))
        jSONObject.put("hostedDomain", this.zzecj); 
      return jSONObject;
    } catch (JSONException jSONException) {
      RuntimeException runtimeException = new RuntimeException((Throwable)jSONException);
      throw runtimeException;
    } 
  }
  
  @Nullable
  public static GoogleSignInOptions zzen(@Nullable String paramString) throws JSONException {
    if (TextUtils.isEmpty(paramString))
      return null; 
    JSONObject jSONObject = new JSONObject(paramString);
    HashSet<Scope> hashSet = new HashSet();
    JSONArray jSONArray = jSONObject.getJSONArray("scopes");
    int i = jSONArray.length();
    for (byte b = 0; b < i; b++)
      hashSet.add(new Scope(jSONArray.getString(b))); 
    String str = jSONObject.optString("accountName", null);
    if (!TextUtils.isEmpty(str)) {
      Account account = new Account(str, "com.google");
    } else {
      str = null;
    } 
    return new GoogleSignInOptions(3, new ArrayList<Scope>(hashSet), (Account)str, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.optString("serverClientId", null), jSONObject.optString("hostedDomain", null), new HashMap<Integer, zzn>());
  }
  
  private static Map<Integer, zzn> zzu(@Nullable List<zzn> paramList) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    if (paramList == null)
      return (Map)hashMap; 
    for (zzn zzn : paramList)
      hashMap.put(Integer.valueOf(zzn.getType()), zzn); 
    return (Map)hashMap;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == null)
      return false; 
    try {
      GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions)paramObject;
      if (this.zzeck.size() <= 0 && googleSignInOptions.zzeck.size() <= 0 && this.zzecg.size() == googleSignInOptions.zzaag().size() && this.zzecg.containsAll(googleSignInOptions.zzaag())) {
        paramObject = this.zzduz;
        if (((paramObject == null) ? (googleSignInOptions.zzduz == null) : paramObject.equals(googleSignInOptions.zzduz)) && (TextUtils.isEmpty(this.zzeaq) ? TextUtils.isEmpty(googleSignInOptions.zzeaq) : this.zzeaq.equals(googleSignInOptions.zzeaq)) && this.zzeci == googleSignInOptions.zzeci && this.zzeap == googleSignInOptions.zzeap) {
          boolean bool1 = this.zzech;
          boolean bool2 = googleSignInOptions.zzech;
          if (bool1 == bool2)
            return true; 
        } 
      } 
    } catch (ClassCastException classCastException) {}
    return false;
  }
  
  public final Account getAccount() {
    return this.zzduz;
  }
  
  public Scope[] getScopeArray() {
    ArrayList<Scope> arrayList = this.zzecg;
    return arrayList.<Scope>toArray(new Scope[arrayList.size()]);
  }
  
  public final String getServerClientId() {
    return this.zzeaq;
  }
  
  public int hashCode() {
    ArrayList<String> arrayList = new ArrayList();
    ArrayList<Scope> arrayList1 = this.zzecg;
    int i = arrayList1.size();
    byte b = 0;
    while (b < i) {
      Scope scope = (Scope)arrayList1.get(b);
      b++;
      arrayList.add(((Scope)scope).zzaft());
    } 
    Collections.sort(arrayList);
    return (new zzo()).zzo(arrayList).zzo(this.zzduz).zzo(this.zzeaq).zzaq(this.zzeci).zzaq(this.zzeap).zzaq(this.zzech).zzaao();
  }
  
  public final boolean isIdTokenRequested() {
    return this.zzeap;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.versionCode);
    zzbcn.zzc(paramParcel, 2, zzaag(), false);
    zzbcn.zza(paramParcel, 3, (Parcelable)this.zzduz, paramInt, false);
    zzbcn.zza(paramParcel, 4, this.zzeap);
    zzbcn.zza(paramParcel, 5, this.zzech);
    zzbcn.zza(paramParcel, 6, this.zzeci);
    zzbcn.zza(paramParcel, 7, this.zzeaq, false);
    zzbcn.zza(paramParcel, 8, this.zzecj, false);
    zzbcn.zzc(paramParcel, 9, this.zzeck, false);
    zzbcn.zzai(paramParcel, i);
  }
  
  public final ArrayList<Scope> zzaag() {
    return new ArrayList<Scope>(this.zzecg);
  }
  
  public final boolean zzaah() {
    return this.zzech;
  }
  
  public final String zzaai() {
    return toJsonObject().toString();
  }
  
  public static final class Builder {
    private Account zzduz;
    
    private boolean zzeap;
    
    private String zzeaq;
    
    private boolean zzech;
    
    private boolean zzeci;
    
    private String zzecj;
    
    private Set<Scope> zzecm = new HashSet<Scope>();
    
    private Map<Integer, zzn> zzecn = new HashMap<Integer, zzn>();
    
    public Builder() {}
    
    public Builder(@NonNull GoogleSignInOptions param1GoogleSignInOptions) {
      zzbp.zzu(param1GoogleSignInOptions);
      this.zzecm = new HashSet<Scope>(GoogleSignInOptions.zza(param1GoogleSignInOptions));
      this.zzech = GoogleSignInOptions.zzb(param1GoogleSignInOptions);
      this.zzeci = GoogleSignInOptions.zzc(param1GoogleSignInOptions);
      this.zzeap = GoogleSignInOptions.zzd(param1GoogleSignInOptions);
      this.zzeaq = GoogleSignInOptions.zze(param1GoogleSignInOptions);
      this.zzduz = GoogleSignInOptions.zzf(param1GoogleSignInOptions);
      this.zzecj = GoogleSignInOptions.zzg(param1GoogleSignInOptions);
      this.zzecn = GoogleSignInOptions.zzv(GoogleSignInOptions.zzh(param1GoogleSignInOptions));
    }
    
    private final String zzeo(String param1String) {
      zzbp.zzgg(param1String);
      String str = this.zzeaq;
      if (str == null || str.equals(param1String)) {
        boolean bool1 = true;
        zzbp.zzb(bool1, "two different server client ids provided");
        return param1String;
      } 
      boolean bool = false;
      zzbp.zzb(bool, "two different server client ids provided");
      return param1String;
    }
    
    public final Builder addExtension(GoogleSignInOptionsExtension param1GoogleSignInOptionsExtension) {
      Map<Integer, zzn> map = this.zzecn;
      Integer integer = Integer.valueOf(1);
      if (!map.containsKey(integer)) {
        this.zzecn.put(integer, new zzn(param1GoogleSignInOptionsExtension));
        return this;
      } 
      throw new IllegalStateException("Only one extension per type may be added");
    }
    
    public final GoogleSignInOptions build() {
      if (this.zzeap && (this.zzduz == null || !this.zzecm.isEmpty()))
        requestId(); 
      return new GoogleSignInOptions(3, new ArrayList<Scope>(this.zzecm), this.zzduz, this.zzeap, this.zzech, this.zzeci, this.zzeaq, this.zzecj, this.zzecn, null);
    }
    
    public final Builder requestEmail() {
      this.zzecm.add(GoogleSignInOptions.zzece);
      return this;
    }
    
    public final Builder requestId() {
      this.zzecm.add(GoogleSignInOptions.zzecf);
      return this;
    }
    
    public final Builder requestIdToken(String param1String) {
      this.zzeap = true;
      this.zzeaq = zzeo(param1String);
      return this;
    }
    
    public final Builder requestProfile() {
      this.zzecm.add(GoogleSignInOptions.zzecd);
      return this;
    }
    
    public final Builder requestScopes(Scope param1Scope, Scope... param1VarArgs) {
      this.zzecm.add(param1Scope);
      this.zzecm.addAll(Arrays.asList(param1VarArgs));
      return this;
    }
    
    public final Builder requestServerAuthCode(String param1String) {
      return requestServerAuthCode(param1String, false);
    }
    
    public final Builder requestServerAuthCode(String param1String, boolean param1Boolean) {
      this.zzech = true;
      this.zzeaq = zzeo(param1String);
      this.zzeci = param1Boolean;
      return this;
    }
    
    public final Builder setAccountName(String param1String) {
      this.zzduz = new Account(zzbp.zzgg(param1String), "com.google");
      return this;
    }
    
    public final Builder setHostedDomain(String param1String) {
      this.zzecj = zzbp.zzgg(param1String);
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/auth/api/signin/GoogleSignInOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */