package mono.com.microsoft.appcenter.crashes;

import com.microsoft.appcenter.crashes.CrashesListener;
import com.microsoft.appcenter.crashes.model.ErrorReport;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class CrashesListenerImplementor implements IGCUserPeer, CrashesListener {
  public static final String __md_methods = "n_getErrorAttachments:(Lcom/microsoft/appcenter/crashes/model/ErrorReport;)Ljava/lang/Iterable;:GetGetErrorAttachments_Lcom_microsoft_appcenter_crashes_model_ErrorReport_Handler:Com.Microsoft.Appcenter.Crashes.ICrashesListenerInvoker, Microsoft.AppCenter.Crashes.Android.Bindings\nn_onBeforeSending:(Lcom/microsoft/appcenter/crashes/model/ErrorReport;)V:GetOnBeforeSending_Lcom_microsoft_appcenter_crashes_model_ErrorReport_Handler:Com.Microsoft.Appcenter.Crashes.ICrashesListenerInvoker, Microsoft.AppCenter.Crashes.Android.Bindings\nn_onSendingFailed:(Lcom/microsoft/appcenter/crashes/model/ErrorReport;Ljava/lang/Exception;)V:GetOnSendingFailed_Lcom_microsoft_appcenter_crashes_model_ErrorReport_Ljava_lang_Exception_Handler:Com.Microsoft.Appcenter.Crashes.ICrashesListenerInvoker, Microsoft.AppCenter.Crashes.Android.Bindings\nn_onSendingSucceeded:(Lcom/microsoft/appcenter/crashes/model/ErrorReport;)V:GetOnSendingSucceeded_Lcom_microsoft_appcenter_crashes_model_ErrorReport_Handler:Com.Microsoft.Appcenter.Crashes.ICrashesListenerInvoker, Microsoft.AppCenter.Crashes.Android.Bindings\nn_shouldAwaitUserConfirmation:()Z:GetShouldAwaitUserConfirmationHandler:Com.Microsoft.Appcenter.Crashes.ICrashesListenerInvoker, Microsoft.AppCenter.Crashes.Android.Bindings\nn_shouldProcess:(Lcom/microsoft/appcenter/crashes/model/ErrorReport;)Z:GetShouldProcess_Lcom_microsoft_appcenter_crashes_model_ErrorReport_Handler:Com.Microsoft.Appcenter.Crashes.ICrashesListenerInvoker, Microsoft.AppCenter.Crashes.Android.Bindings\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Com.Microsoft.Appcenter.Crashes.ICrashesListenerImplementor, Microsoft.AppCenter.Crashes.Android.Bindings", CrashesListenerImplementor.class, "n_getErrorAttachments:(Lcom/microsoft/appcenter/crashes/model/ErrorReport;)Ljava/lang/Iterable;:GetGetErrorAttachments_Lcom_microsoft_appcenter_crashes_model_ErrorReport_Handler:Com.Microsoft.Appcenter.Crashes.ICrashesListenerInvoker, Microsoft.AppCenter.Crashes.Android.Bindings\nn_onBeforeSending:(Lcom/microsoft/appcenter/crashes/model/ErrorReport;)V:GetOnBeforeSending_Lcom_microsoft_appcenter_crashes_model_ErrorReport_Handler:Com.Microsoft.Appcenter.Crashes.ICrashesListenerInvoker, Microsoft.AppCenter.Crashes.Android.Bindings\nn_onSendingFailed:(Lcom/microsoft/appcenter/crashes/model/ErrorReport;Ljava/lang/Exception;)V:GetOnSendingFailed_Lcom_microsoft_appcenter_crashes_model_ErrorReport_Ljava_lang_Exception_Handler:Com.Microsoft.Appcenter.Crashes.ICrashesListenerInvoker, Microsoft.AppCenter.Crashes.Android.Bindings\nn_onSendingSucceeded:(Lcom/microsoft/appcenter/crashes/model/ErrorReport;)V:GetOnSendingSucceeded_Lcom_microsoft_appcenter_crashes_model_ErrorReport_Handler:Com.Microsoft.Appcenter.Crashes.ICrashesListenerInvoker, Microsoft.AppCenter.Crashes.Android.Bindings\nn_shouldAwaitUserConfirmation:()Z:GetShouldAwaitUserConfirmationHandler:Com.Microsoft.Appcenter.Crashes.ICrashesListenerInvoker, Microsoft.AppCenter.Crashes.Android.Bindings\nn_shouldProcess:(Lcom/microsoft/appcenter/crashes/model/ErrorReport;)Z:GetShouldProcess_Lcom_microsoft_appcenter_crashes_model_ErrorReport_Handler:Com.Microsoft.Appcenter.Crashes.ICrashesListenerInvoker, Microsoft.AppCenter.Crashes.Android.Bindings\n");
  }
  
  public CrashesListenerImplementor() {
    if (getClass() == CrashesListenerImplementor.class)
      TypeManager.Activate("Com.Microsoft.Appcenter.Crashes.ICrashesListenerImplementor, Microsoft.AppCenter.Crashes.Android.Bindings", "", this, new Object[0]); 
  }
  
  private native Iterable n_getErrorAttachments(ErrorReport paramErrorReport);
  
  private native void n_onBeforeSending(ErrorReport paramErrorReport);
  
  private native void n_onSendingFailed(ErrorReport paramErrorReport, Exception paramException);
  
  private native void n_onSendingSucceeded(ErrorReport paramErrorReport);
  
  private native boolean n_shouldAwaitUserConfirmation();
  
  private native boolean n_shouldProcess(ErrorReport paramErrorReport);
  
  public Iterable getErrorAttachments(ErrorReport paramErrorReport) {
    return n_getErrorAttachments(paramErrorReport);
  }
  
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
  
  public void onBeforeSending(ErrorReport paramErrorReport) {
    n_onBeforeSending(paramErrorReport);
  }
  
  public void onSendingFailed(ErrorReport paramErrorReport, Exception paramException) {
    n_onSendingFailed(paramErrorReport, paramException);
  }
  
  public void onSendingSucceeded(ErrorReport paramErrorReport) {
    n_onSendingSucceeded(paramErrorReport);
  }
  
  public boolean shouldAwaitUserConfirmation() {
    return n_shouldAwaitUserConfirmation();
  }
  
  public boolean shouldProcess(ErrorReport paramErrorReport) {
    return n_shouldProcess(paramErrorReport);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/microsoft/appcenter/crashes/CrashesListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */