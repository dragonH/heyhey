package mono.com.google.android.gms.tasks;

import com.google.android.gms.tasks.OnSuccessListener;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class OnSuccessListenerImplementor implements IGCUserPeer, OnSuccessListener {
  public static final String __md_methods = "n_onSuccess:(Ljava/lang/Object;)V:GetOnSuccess_Ljava_lang_Object_Handler:Android.Gms.Tasks.IOnSuccessListenerInvoker, Xamarin.GooglePlayServices.Tasks\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Tasks.IOnSuccessListenerImplementor, Xamarin.GooglePlayServices.Tasks", OnSuccessListenerImplementor.class, "n_onSuccess:(Ljava/lang/Object;)V:GetOnSuccess_Ljava_lang_Object_Handler:Android.Gms.Tasks.IOnSuccessListenerInvoker, Xamarin.GooglePlayServices.Tasks\n");
  }
  
  public OnSuccessListenerImplementor() {
    if (getClass() == OnSuccessListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Tasks.IOnSuccessListenerImplementor, Xamarin.GooglePlayServices.Tasks", "", this, new Object[0]); 
  }
  
  private native void n_onSuccess(Object paramObject);
  
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
  
  public void onSuccess(Object paramObject) {
    n_onSuccess(paramObject);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/tasks/OnSuccessListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */