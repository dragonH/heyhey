package mono.com.microsoft.appcenter.analytics.channel;

import com.microsoft.appcenter.analytics.channel.AnalyticsListener;
import com.microsoft.appcenter.ingestion.models.Log;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AnalyticsListenerImplementor implements IGCUserPeer, AnalyticsListener {
  public static final String __md_methods = "n_onBeforeSending:(Lcom/microsoft/appcenter/ingestion/models/Log;)V:GetOnBeforeSending_Lcom_microsoft_appcenter_ingestion_models_Log_Handler:Com.Microsoft.Appcenter.Analytics.Channel.IAnalyticsListenerInvoker, Microsoft.AppCenter.Analytics.Android.Bindings\nn_onSendingFailed:(Lcom/microsoft/appcenter/ingestion/models/Log;Ljava/lang/Exception;)V:GetOnSendingFailed_Lcom_microsoft_appcenter_ingestion_models_Log_Ljava_lang_Exception_Handler:Com.Microsoft.Appcenter.Analytics.Channel.IAnalyticsListenerInvoker, Microsoft.AppCenter.Analytics.Android.Bindings\nn_onSendingSucceeded:(Lcom/microsoft/appcenter/ingestion/models/Log;)V:GetOnSendingSucceeded_Lcom_microsoft_appcenter_ingestion_models_Log_Handler:Com.Microsoft.Appcenter.Analytics.Channel.IAnalyticsListenerInvoker, Microsoft.AppCenter.Analytics.Android.Bindings\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Com.Microsoft.Appcenter.Analytics.Channel.IAnalyticsListenerImplementor, Microsoft.AppCenter.Analytics.Android.Bindings", AnalyticsListenerImplementor.class, "n_onBeforeSending:(Lcom/microsoft/appcenter/ingestion/models/Log;)V:GetOnBeforeSending_Lcom_microsoft_appcenter_ingestion_models_Log_Handler:Com.Microsoft.Appcenter.Analytics.Channel.IAnalyticsListenerInvoker, Microsoft.AppCenter.Analytics.Android.Bindings\nn_onSendingFailed:(Lcom/microsoft/appcenter/ingestion/models/Log;Ljava/lang/Exception;)V:GetOnSendingFailed_Lcom_microsoft_appcenter_ingestion_models_Log_Ljava_lang_Exception_Handler:Com.Microsoft.Appcenter.Analytics.Channel.IAnalyticsListenerInvoker, Microsoft.AppCenter.Analytics.Android.Bindings\nn_onSendingSucceeded:(Lcom/microsoft/appcenter/ingestion/models/Log;)V:GetOnSendingSucceeded_Lcom_microsoft_appcenter_ingestion_models_Log_Handler:Com.Microsoft.Appcenter.Analytics.Channel.IAnalyticsListenerInvoker, Microsoft.AppCenter.Analytics.Android.Bindings\n");
  }
  
  public AnalyticsListenerImplementor() {
    if (getClass() == AnalyticsListenerImplementor.class)
      TypeManager.Activate("Com.Microsoft.Appcenter.Analytics.Channel.IAnalyticsListenerImplementor, Microsoft.AppCenter.Analytics.Android.Bindings", "", this, new Object[0]); 
  }
  
  private native void n_onBeforeSending(Log paramLog);
  
  private native void n_onSendingFailed(Log paramLog, Exception paramException);
  
  private native void n_onSendingSucceeded(Log paramLog);
  
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
  
  public void onBeforeSending(Log paramLog) {
    n_onBeforeSending(paramLog);
  }
  
  public void onSendingFailed(Log paramLog, Exception paramException) {
    n_onSendingFailed(paramLog, paramException);
  }
  
  public void onSendingSucceeded(Log paramLog) {
    n_onSendingSucceeded(paramLog);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/microsoft/appcenter/analytics/channel/AnalyticsListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */