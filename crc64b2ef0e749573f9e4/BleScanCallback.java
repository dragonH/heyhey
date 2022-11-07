package crc64b2ef0e749573f9e4;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class BleScanCallback extends ScanCallback implements IGCUserPeer {
  public static final String __md_methods = "n_onScanResult:(ILandroid/bluetooth/le/ScanResult;)V:GetOnScanResult_ILandroid_bluetooth_le_ScanResult_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Plugin.Beahat.BleScanCallback, Plugin.Beahat", BleScanCallback.class, "n_onScanResult:(ILandroid/bluetooth/le/ScanResult;)V:GetOnScanResult_ILandroid_bluetooth_le_ScanResult_Handler\n");
  }
  
  public BleScanCallback() {
    if (getClass() == BleScanCallback.class)
      TypeManager.Activate("Plugin.Beahat.BleScanCallback, Plugin.Beahat", "", this, new Object[0]); 
  }
  
  private native void n_onScanResult(int paramInt, ScanResult paramScanResult);
  
  public void monodroidAddReference(Object paramObject) {
    if (this.refList == null)
      this.refList = new ArrayList(); 
    this.refList.add(paramObject);
  }
  
  public void monodroidClearReferences() {
    ArrayList arrayList = this.refList;
    if (arrayList != null)
      arrayList.clear(); 
  }
  
  public void onScanResult(int paramInt, ScanResult paramScanResult) {
    n_onScanResult(paramInt, paramScanResult);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64b2ef0e749573f9e4/BleScanCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */