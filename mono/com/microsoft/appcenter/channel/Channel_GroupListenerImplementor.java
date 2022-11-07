package mono.com.microsoft.appcenter.channel;

import com.microsoft.appcenter.channel.Channel;
import com.microsoft.appcenter.ingestion.models.Log;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Channel_GroupListenerImplementor implements IGCUserPeer, Channel.GroupListener {
  public static final String __md_methods = "n_onBeforeSending:(Lcom/microsoft/appcenter/ingestion/models/Log;)V:GetOnBeforeSending_Lcom_microsoft_appcenter_ingestion_models_Log_Handler:Com.Microsoft.Appcenter.Channel.IChannelGroupListenerInvoker, Microsoft.AppCenter.Android.Bindings\nn_onFailure:(Lcom/microsoft/appcenter/ingestion/models/Log;Ljava/lang/Exception;)V:GetOnFailure_Lcom_microsoft_appcenter_ingestion_models_Log_Ljava_lang_Exception_Handler:Com.Microsoft.Appcenter.Channel.IChannelGroupListenerInvoker, Microsoft.AppCenter.Android.Bindings\nn_onSuccess:(Lcom/microsoft/appcenter/ingestion/models/Log;)V:GetOnSuccess_Lcom_microsoft_appcenter_ingestion_models_Log_Handler:Com.Microsoft.Appcenter.Channel.IChannelGroupListenerInvoker, Microsoft.AppCenter.Android.Bindings\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Com.Microsoft.Appcenter.Channel.IChannelGroupListenerImplementor, Microsoft.AppCenter.Android.Bindings", Channel_GroupListenerImplementor.class, "n_onBeforeSending:(Lcom/microsoft/appcenter/ingestion/models/Log;)V:GetOnBeforeSending_Lcom_microsoft_appcenter_ingestion_models_Log_Handler:Com.Microsoft.Appcenter.Channel.IChannelGroupListenerInvoker, Microsoft.AppCenter.Android.Bindings\nn_onFailure:(Lcom/microsoft/appcenter/ingestion/models/Log;Ljava/lang/Exception;)V:GetOnFailure_Lcom_microsoft_appcenter_ingestion_models_Log_Ljava_lang_Exception_Handler:Com.Microsoft.Appcenter.Channel.IChannelGroupListenerInvoker, Microsoft.AppCenter.Android.Bindings\nn_onSuccess:(Lcom/microsoft/appcenter/ingestion/models/Log;)V:GetOnSuccess_Lcom_microsoft_appcenter_ingestion_models_Log_Handler:Com.Microsoft.Appcenter.Channel.IChannelGroupListenerInvoker, Microsoft.AppCenter.Android.Bindings\n");
  }
  
  public Channel_GroupListenerImplementor() {
    if (getClass() == Channel_GroupListenerImplementor.class)
      TypeManager.Activate("Com.Microsoft.Appcenter.Channel.IChannelGroupListenerImplementor, Microsoft.AppCenter.Android.Bindings", "", this, new Object[0]); 
  }
  
  private native void n_onBeforeSending(Log paramLog);
  
  private native void n_onFailure(Log paramLog, Exception paramException);
  
  private native void n_onSuccess(Log paramLog);
  
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
  
  public void onFailure(Log paramLog, Exception paramException) {
    n_onFailure(paramLog, paramException);
  }
  
  public void onSuccess(Log paramLog) {
    n_onSuccess(paramLog);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/microsoft/appcenter/channel/Channel_GroupListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */