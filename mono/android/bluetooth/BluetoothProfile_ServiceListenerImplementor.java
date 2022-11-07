package mono.android.bluetooth;

import android.bluetooth.BluetoothProfile;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class BluetoothProfile_ServiceListenerImplementor implements IGCUserPeer, BluetoothProfile.ServiceListener {
  public static final String __md_methods = "n_onServiceConnected:(ILandroid/bluetooth/BluetoothProfile;)V:GetOnServiceConnected_ILandroid_bluetooth_BluetoothProfile_Handler:Android.Bluetooth.IBluetoothProfileServiceListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onServiceDisconnected:(I)V:GetOnServiceDisconnected_IHandler:Android.Bluetooth.IBluetoothProfileServiceListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Bluetooth.IBluetoothProfileServiceListenerImplementor, Mono.Android", BluetoothProfile_ServiceListenerImplementor.class, "n_onServiceConnected:(ILandroid/bluetooth/BluetoothProfile;)V:GetOnServiceConnected_ILandroid_bluetooth_BluetoothProfile_Handler:Android.Bluetooth.IBluetoothProfileServiceListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onServiceDisconnected:(I)V:GetOnServiceDisconnected_IHandler:Android.Bluetooth.IBluetoothProfileServiceListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public BluetoothProfile_ServiceListenerImplementor() {
    if (getClass() == BluetoothProfile_ServiceListenerImplementor.class)
      TypeManager.Activate("Android.Bluetooth.IBluetoothProfileServiceListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onServiceConnected(int paramInt, BluetoothProfile paramBluetoothProfile);
  
  private native void n_onServiceDisconnected(int paramInt);
  
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
  
  public void onServiceConnected(int paramInt, BluetoothProfile paramBluetoothProfile) {
    n_onServiceConnected(paramInt, paramBluetoothProfile);
  }
  
  public void onServiceDisconnected(int paramInt) {
    n_onServiceDisconnected(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/bluetooth/BluetoothProfile_ServiceListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */