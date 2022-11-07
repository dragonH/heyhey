package mono.android.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import mono.android.Runtime;

public class NotifyTimeZoneChanges extends BroadcastReceiver {
  public void onReceive(Context paramContext, Intent paramIntent) {
    Runtime.notifyTimeZoneChanged();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/NotifyTimeZoneChanges.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */