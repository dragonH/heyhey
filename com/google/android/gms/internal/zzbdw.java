package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zza;
import com.google.android.gms.common.util.zzb;
import com.google.android.gms.common.util.zzo;
import com.google.android.gms.common.util.zzp;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class zzbdw extends zzbdo {
  public static final Parcelable.Creator<zzbdw> CREATOR = new zzbdx();
  
  private final String mClassName;
  
  private final int zzdxs;
  
  private final zzbdr zzfwy;
  
  private final Parcel zzfxf;
  
  private final int zzfxg;
  
  private int zzfxh;
  
  private int zzfxi;
  
  zzbdw(int paramInt, Parcel paramParcel, zzbdr paramzzbdr) {
    String str;
    this.zzdxs = paramInt;
    this.zzfxf = (Parcel)zzbp.zzu(paramParcel);
    this.zzfxg = 2;
    this.zzfwy = paramzzbdr;
    if (paramzzbdr == null) {
      paramParcel = null;
    } else {
      str = paramzzbdr.zzakv();
    } 
    this.mClassName = str;
    this.zzfxh = 2;
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, Object paramObject) {
    switch (paramInt) {
      default:
        paramStringBuilder = new StringBuilder(26);
        paramStringBuilder.append("Unknown type = ");
        paramStringBuilder.append(paramInt);
        throw new IllegalArgumentException(paramStringBuilder.toString());
      case 11:
        throw new IllegalArgumentException("Method does not accept concrete type.");
      case 10:
        zzp.zza(paramStringBuilder, (HashMap)paramObject);
        return;
      case 9:
        paramStringBuilder.append("\"");
        paramStringBuilder.append(zzb.zzk((byte[])paramObject));
        paramStringBuilder.append("\"");
        return;
      case 8:
        paramStringBuilder.append("\"");
        paramStringBuilder.append(zzb.zzj((byte[])paramObject));
        paramStringBuilder.append("\"");
        return;
      case 7:
        paramStringBuilder.append("\"");
        paramStringBuilder.append(zzo.zzgl(paramObject.toString()));
        paramStringBuilder.append("\"");
        return;
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
        break;
    } 
    paramStringBuilder.append(paramObject);
  }
  
  private final void zza(StringBuilder paramStringBuilder, zzbdm<?, ?> paramzzbdm, Parcel paramParcel, int paramInt) {
    int[] arrayOfInt;
    Bundle bundle;
    byte[] arrayOfByte;
    String str1;
    Parcel[] arrayOfParcel;
    Parcel parcel;
    Set set;
    String str2;
    Iterator<String> iterator;
    boolean bool = paramzzbdm.zzfwt;
    int i = 0;
    int j = 0;
    if (bool) {
      double[] arrayOfDouble1;
      BigInteger[] arrayOfBigInteger;
      paramStringBuilder.append("[");
      int k = paramzzbdm.zzfws;
      double[] arrayOfDouble2 = null;
      zzbdm zzbdm1 = null;
      switch (k) {
        default:
          throw new IllegalStateException("Unknown field type out.");
        case 11:
          arrayOfParcel = zzbcl.zzae(paramParcel, paramInt);
          j = arrayOfParcel.length;
          for (paramInt = 0; paramInt < j; paramInt++) {
            if (paramInt > 0)
              paramStringBuilder.append(","); 
            arrayOfParcel[paramInt].setDataPosition(0);
            zza(paramStringBuilder, paramzzbdm.zzakt(), arrayOfParcel[paramInt]);
          } 
          break;
        case 8:
        case 9:
        case 10:
          throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
        case 7:
          zza.zza(paramStringBuilder, zzbcl.zzaa((Parcel)arrayOfParcel, paramInt));
          break;
        case 6:
          zza.zza(paramStringBuilder, zzbcl.zzv((Parcel)arrayOfParcel, paramInt));
          break;
        case 5:
          zza.zza(paramStringBuilder, (Object[])zzbcl.zzz((Parcel)arrayOfParcel, paramInt));
          break;
        case 4:
          j = zzbcl.zza((Parcel)arrayOfParcel, paramInt);
          paramInt = arrayOfParcel.dataPosition();
          if (j == 0) {
            paramzzbdm = zzbdm1;
          } else {
            arrayOfDouble1 = arrayOfParcel.createDoubleArray();
            arrayOfParcel.setDataPosition(paramInt + j);
          } 
          zza.zza(paramStringBuilder, arrayOfDouble1);
          break;
        case 3:
          zza.zza(paramStringBuilder, zzbcl.zzy((Parcel)arrayOfParcel, paramInt));
          break;
        case 2:
          zza.zza(paramStringBuilder, zzbcl.zzx((Parcel)arrayOfParcel, paramInt));
          break;
        case 1:
          i = zzbcl.zza((Parcel)arrayOfParcel, paramInt);
          k = arrayOfParcel.dataPosition();
          if (i == 0) {
            arrayOfDouble1 = arrayOfDouble2;
          } else {
            int m = arrayOfParcel.readInt();
            arrayOfBigInteger = new BigInteger[m];
            for (paramInt = j; paramInt < m; paramInt++)
              arrayOfBigInteger[paramInt] = new BigInteger(arrayOfParcel.createByteArray()); 
            arrayOfParcel.setDataPosition(k + i);
          } 
          zza.zza(paramStringBuilder, (Object[])arrayOfBigInteger);
          break;
        case 0:
          arrayOfInt = zzbcl.zzw((Parcel)arrayOfParcel, paramInt);
          j = arrayOfInt.length;
          for (paramInt = i; paramInt < j; paramInt++) {
            if (paramInt != 0)
              paramStringBuilder.append(","); 
            paramStringBuilder.append(Integer.toString(arrayOfInt[paramInt]));
          } 
          break;
      } 
      paramStringBuilder.append("]");
      return;
    } 
    switch (((zzbdm)arrayOfInt).zzfws) {
      default:
        throw new IllegalStateException("Unknown field type out");
      case 11:
        parcel = zzbcl.zzad((Parcel)arrayOfParcel, paramInt);
        parcel.setDataPosition(0);
        zza(paramStringBuilder, arrayOfInt.zzakt(), parcel);
        return;
      case 10:
        bundle = zzbcl.zzs(parcel, paramInt);
        set = bundle.keySet();
        set.size();
        paramStringBuilder.append("{");
        iterator = set.iterator();
        for (paramInt = 1; iterator.hasNext(); paramInt = 0) {
          str2 = iterator.next();
          if (paramInt == 0)
            paramStringBuilder.append(","); 
          paramStringBuilder.append("\"");
          paramStringBuilder.append(str2);
          paramStringBuilder.append("\"");
          paramStringBuilder.append(":");
          paramStringBuilder.append("\"");
          paramStringBuilder.append(zzo.zzgl(bundle.getString(str2)));
          paramStringBuilder.append("\"");
        } 
        paramStringBuilder.append("}");
        return;
      case 9:
        arrayOfByte = zzbcl.zzt((Parcel)str2, paramInt);
        paramStringBuilder.append("\"");
        paramStringBuilder.append(zzb.zzk(arrayOfByte));
        paramStringBuilder.append("\"");
        return;
      case 8:
        arrayOfByte = zzbcl.zzt((Parcel)str2, paramInt);
        paramStringBuilder.append("\"");
        paramStringBuilder.append(zzb.zzj(arrayOfByte));
        paramStringBuilder.append("\"");
        return;
      case 7:
        str1 = zzbcl.zzq((Parcel)str2, paramInt);
        paramStringBuilder.append("\"");
        paramStringBuilder.append(zzo.zzgl(str1));
        paramStringBuilder.append("\"");
        return;
      case 6:
        paramStringBuilder.append(zzbcl.zzc((Parcel)str2, paramInt));
        return;
      case 5:
        paramStringBuilder.append(zzbcl.zzp((Parcel)str2, paramInt));
        return;
      case 4:
        paramStringBuilder.append(zzbcl.zzn((Parcel)str2, paramInt));
        return;
      case 3:
        paramStringBuilder.append(zzbcl.zzl((Parcel)str2, paramInt));
        return;
      case 2:
        paramStringBuilder.append(zzbcl.zzi((Parcel)str2, paramInt));
        return;
      case 1:
        paramStringBuilder.append(zzbcl.zzk((Parcel)str2, paramInt));
        return;
      case 0:
        break;
    } 
    paramStringBuilder.append(zzbcl.zzg((Parcel)str2, paramInt));
  }
  
  private final void zza(StringBuilder paramStringBuilder, Map<String, zzbdm<?, ?>> paramMap, Parcel paramParcel) {
    SparseArray sparseArray = new SparseArray();
    for (Map.Entry<String, zzbdm<?, ?>> entry : paramMap.entrySet())
      sparseArray.put(((zzbdm)entry.getValue()).zzfwv, entry); 
    paramStringBuilder.append('{');
    int i = zzbcl.zzd(paramParcel);
    int j = 0;
    while (paramParcel.dataPosition() < i) {
      int k = paramParcel.readInt();
      Map.Entry entry = (Map.Entry)sparseArray.get(0xFFFF & k);
      if (entry != null) {
        if (j)
          paramStringBuilder.append(","); 
        String str = (String)entry.getKey();
        zzbdm<?, ?> zzbdm = (zzbdm)entry.getValue();
        paramStringBuilder.append("\"");
        paramStringBuilder.append(str);
        paramStringBuilder.append("\":");
        if (zzbdm.zzaks()) {
          HashMap<String, String> hashMap;
          byte[] arrayOfByte;
          String str1;
          Boolean bool;
          BigDecimal bigDecimal;
          Double double_;
          Float float_;
          Long long_;
          BigInteger bigInteger;
          Integer integer;
          switch (zzbdm.zzfws) {
            default:
              j = zzbdm.zzfws;
              paramStringBuilder = new StringBuilder(36);
              paramStringBuilder.append("Unknown field out type = ");
              paramStringBuilder.append(j);
              throw new IllegalArgumentException(paramStringBuilder.toString());
            case 11:
              throw new IllegalArgumentException("Method does not accept concrete type.");
            case 10:
              hashMap = zzl(zzbcl.zzs(paramParcel, k));
              break;
            case 8:
            case 9:
              arrayOfByte = zzbcl.zzt(paramParcel, k);
              break;
            case 7:
              str1 = zzbcl.zzq(paramParcel, k);
              break;
            case 6:
              bool = Boolean.valueOf(zzbcl.zzc(paramParcel, k));
              break;
            case 5:
              bigDecimal = zzbcl.zzp(paramParcel, k);
              break;
            case 4:
              double_ = Double.valueOf(zzbcl.zzn(paramParcel, k));
              break;
            case 3:
              float_ = Float.valueOf(zzbcl.zzl(paramParcel, k));
              break;
            case 2:
              long_ = Long.valueOf(zzbcl.zzi(paramParcel, k));
              break;
            case 1:
              bigInteger = zzbcl.zzk(paramParcel, k);
              break;
            case 0:
              integer = Integer.valueOf(zzbcl.zzg(paramParcel, k));
              break;
          } 
          zzb(paramStringBuilder, zzbdm, zzbdl.zza(zzbdm, integer));
        } else {
          zza(paramStringBuilder, zzbdm, paramParcel, k);
        } 
        j = 1;
      } 
    } 
    if (paramParcel.dataPosition() == i) {
      paramStringBuilder.append('}');
      return;
    } 
    paramStringBuilder = new StringBuilder(37);
    paramStringBuilder.append("Overread allowed size end=");
    paramStringBuilder.append(i);
    zzbcm zzbcm = new zzbcm(paramStringBuilder.toString(), paramParcel);
    throw zzbcm;
  }
  
  private Parcel zzakx() {
    int i = this.zzfxh;
    if (i != 0) {
      if (i != 1)
        return this.zzfxf; 
    } else {
      this.zzfxi = zzbcn.zze(this.zzfxf);
    } 
    zzbcn.zzai(this.zzfxf, this.zzfxi);
    this.zzfxh = 2;
    return this.zzfxf;
  }
  
  private final void zzb(StringBuilder paramStringBuilder, zzbdm<?, ?> paramzzbdm, Object paramObject) {
    if (paramzzbdm.zzfwr) {
      paramObject = paramObject;
      paramStringBuilder.append("[");
      int i = paramObject.size();
      for (byte b = 0; b < i; b++) {
        if (b != 0)
          paramStringBuilder.append(","); 
        zza(paramStringBuilder, paramzzbdm.zzfwq, paramObject.get(b));
      } 
      paramStringBuilder.append("]");
      return;
    } 
    zza(paramStringBuilder, paramzzbdm.zzfwq, paramObject);
  }
  
  private static HashMap<String, String> zzl(Bundle paramBundle) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    for (String str : paramBundle.keySet())
      hashMap.put(str, paramBundle.getString(str)); 
    return (HashMap)hashMap;
  }
  
  public String toString() {
    zzbp.zzb(this.zzfwy, "Cannot convert to JSON on client side.");
    Parcel parcel = zzakx();
    parcel.setDataPosition(0);
    StringBuilder stringBuilder = new StringBuilder(100);
    zza(stringBuilder, this.zzfwy.zzgk(this.mClassName), parcel);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    StringBuilder stringBuilder;
    Parcelable parcelable;
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    zzbcn.zza(paramParcel, 2, zzakx(), false);
    int j = this.zzfxg;
    if (j != 0) {
      if (j == 1 || j == 2) {
        parcelable = this.zzfwy;
      } else {
        paramInt = this.zzfxg;
        stringBuilder = new StringBuilder(34);
        stringBuilder.append("Invalid creation type: ");
        stringBuilder.append(paramInt);
        throw new IllegalStateException(stringBuilder.toString());
      } 
    } else {
      parcelable = null;
    } 
    zzbcn.zza((Parcel)stringBuilder, 3, parcelable, paramInt, false);
    zzbcn.zzai((Parcel)stringBuilder, i);
  }
  
  public final Object zzgi(String paramString) {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }
  
  public final boolean zzgj(String paramString) {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }
  
  public final Map<String, zzbdm<?, ?>> zzzz() {
    zzbdr zzbdr1 = this.zzfwy;
    return (zzbdr1 == null) ? null : zzbdr1.zzgk(this.mClassName);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbdw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */