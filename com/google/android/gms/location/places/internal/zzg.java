package com.google.android.gms.location.places.internal;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import java.util.Collection;
import java.util.List;

public final class zzg {
  public static CharSequence zza(String paramString, List<zzb> paramList, CharacterStyle paramCharacterStyle) {
    if (paramCharacterStyle == null)
      return paramString; 
    SpannableString spannableString = new SpannableString(paramString);
    for (zzb zzb : paramList) {
      CharacterStyle characterStyle = CharacterStyle.wrap(paramCharacterStyle);
      int i = zzb.mOffset;
      spannableString.setSpan(characterStyle, i, zzb.mLength + i, 0);
    } 
    return (CharSequence)spannableString;
  }
  
  public static String zzj(Collection<String> paramCollection) {
    return (paramCollection == null || paramCollection.isEmpty()) ? null : TextUtils.join(", ", paramCollection);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */