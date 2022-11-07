package com.google.android.gms.common.images;

import android.net.Uri;
import com.google.android.gms.common.internal.zzbf;
import java.util.Arrays;

final class zzb {
  public final Uri uri;
  
  public zzb(Uri paramUri) {
    this.uri = paramUri;
  }
  
  public final boolean equals(Object paramObject) {
    return !(paramObject instanceof zzb) ? false : ((this == paramObject) ? true : zzbf.equal(((zzb)paramObject).uri, this.uri));
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.uri });
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/images/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */