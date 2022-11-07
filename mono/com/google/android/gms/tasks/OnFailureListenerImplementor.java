package mono.com.google.android.gms.tasks;

import com.google.android.gms.tasks.OnFailureListener;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class OnFailureListenerImplementor implements IGCUserPeer, OnFailureListener {
  public static final String __md_methods = "n_onFailure:(Ljava/lang/Exception;)V:GetOnFailure_Ljava_lang_Exception_Handler:Android.Gms.Tasks.IOnFailureListenerInvoker, Xamarin.GooglePlayServices.Tasks\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Tasks.IOnFailureListenerImplementor, Xamarin.GooglePlayServices.Tasks", OnFailureListenerImplementor.class, "n_onFailure:(Ljava/lang/Exception;)V:GetOnFailure_Ljava_lang_Exception_Handler:Android.Gms.Tasks.IOnFailureListenerInvoker, Xamarin.GooglePlayServices.Tasks\n");
  }
  
  public OnFailureListenerImplementor() {
    if (getClass() == OnFailureListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Tasks.IOnFailureListenerImplementor, Xamarin.GooglePlayServices.Tasks", "", this, new Object[0]); 
  }
  
  private native void n_onFailure(Exception paramException);
  
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
  
  public void onFailure(Exception paramException) {
    n_onFailure(paramException);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/tasks/OnFailureListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */