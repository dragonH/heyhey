package crc64b2ef0e749573f9e4;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class LeScanCallback implements IGCUserPeer, BluetoothAdapter.LeScanCallback {
  public static final String __md_methods = "n_onLeScan:(Landroid/bluetooth/BluetoothDevice;I[B)V:GetOnLeScan_Landroid_bluetooth_BluetoothDevice_IarrayBHandler:Android.Bluetooth.BluetoothAdapter/ILeScanCallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Plugin.Beahat.LeScanCallback, Plugin.Beahat", LeScanCallback.class, "n_onLeScan:(Landroid/bluetooth/BluetoothDevice;I[B)V:GetOnLeScan_Landroid_bluetooth_BluetoothDevice_IarrayBHandler:Android.Bluetooth.BluetoothAdapter/ILeScanCallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public LeScanCallback() {
    if (getClass() == LeScanCallback.class)
      TypeManager.Activate("Plugin.Beahat.LeScanCallback, Plugin.Beahat", "", this, new Object[0]); 
  }
  
  private native void n_onLeScan(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfbyte);
  
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
  
  public void onLeScan(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfbyte) {
    n_onLeScan(paramBluetoothDevice, paramInt, paramArrayOfbyte);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64b2ef0e749573f9e4/LeScanCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */