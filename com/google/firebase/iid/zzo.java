package com.google.firebase.iid;

import android.content.Intent;
import android.os.ConditionVariable;
import android.util.Log;
import java.io.IOException;

final class zzo implements zzp {
  private Intent intent;
  
  private final ConditionVariable zznfy = new ConditionVariable();
  
  private String zznfz;
  
  private zzo() {}
  
  public final void onError(String paramString) {
    this.zznfz = paramString;
    this.zznfy.open();
  }
  
  public final Intent zzcgd() throws IOException {
    if (this.zznfy.block(30000L)) {
      if (this.zznfz == null)
        return this.intent; 
      throw new IOException(this.zznfz);
    } 
    Log.w("InstanceID/Rpc", "No response");
    throw new IOException("TIMEOUT");
  }
  
  public final void zzq(Intent paramIntent) {
    this.intent = paramIntent;
    this.zznfy.open();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */