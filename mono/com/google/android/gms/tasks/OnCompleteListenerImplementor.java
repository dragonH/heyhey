package mono.com.google.android.gms.tasks;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class OnCompleteListenerImplementor implements IGCUserPeer, OnCompleteListener {
  public static final String __md_methods = "n_onComplete:(Lcom/google/android/gms/tasks/Task;)V:GetOnComplete_Lcom_google_android_gms_tasks_Task_Handler:Android.Gms.Tasks.IOnCompleteListenerInvoker, Xamarin.GooglePlayServices.Tasks\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Tasks.IOnCompleteListenerImplementor, Xamarin.GooglePlayServices.Tasks", OnCompleteListenerImplementor.class, "n_onComplete:(Lcom/google/android/gms/tasks/Task;)V:GetOnComplete_Lcom_google_android_gms_tasks_Task_Handler:Android.Gms.Tasks.IOnCompleteListenerInvoker, Xamarin.GooglePlayServices.Tasks\n");
  }
  
  public OnCompleteListenerImplementor() {
    if (getClass() == OnCompleteListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Tasks.IOnCompleteListenerImplementor, Xamarin.GooglePlayServices.Tasks", "", this, new Object[0]); 
  }
  
  private native void n_onComplete(Task paramTask);
  
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
  
  public void onComplete(Task paramTask) {
    n_onComplete(paramTask);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/tasks/OnCompleteListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */