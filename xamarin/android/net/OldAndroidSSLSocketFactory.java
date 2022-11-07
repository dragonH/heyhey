package xamarin.android.net;

import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import javax.net.ssl.SSLSocketFactory;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class OldAndroidSSLSocketFactory extends SSLSocketFactory implements IGCUserPeer {
  public static final String __md_methods = "n_getDefaultCipherSuites:()[Ljava/lang/String;:GetGetDefaultCipherSuitesHandler\nn_getSupportedCipherSuites:()[Ljava/lang/String;:GetGetSupportedCipherSuitesHandler\nn_createSocket:(Ljava/net/InetAddress;ILjava/net/InetAddress;I)Ljava/net/Socket;:GetCreateSocket_Ljava_net_InetAddress_ILjava_net_InetAddress_IHandler\nn_createSocket:(Ljava/net/InetAddress;I)Ljava/net/Socket;:GetCreateSocket_Ljava_net_InetAddress_IHandler\nn_createSocket:(Ljava/lang/String;ILjava/net/InetAddress;I)Ljava/net/Socket;:GetCreateSocket_Ljava_lang_String_ILjava_net_InetAddress_IHandler\nn_createSocket:(Ljava/lang/String;I)Ljava/net/Socket;:GetCreateSocket_Ljava_lang_String_IHandler\nn_createSocket:(Ljava/net/Socket;Ljava/lang/String;IZ)Ljava/net/Socket;:GetCreateSocket_Ljava_net_Socket_Ljava_lang_String_IZHandler\nn_createSocket:()Ljava/net/Socket;:GetCreateSocketHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Android.Net.OldAndroidSSLSocketFactory, Mono.Android", OldAndroidSSLSocketFactory.class, "n_getDefaultCipherSuites:()[Ljava/lang/String;:GetGetDefaultCipherSuitesHandler\nn_getSupportedCipherSuites:()[Ljava/lang/String;:GetGetSupportedCipherSuitesHandler\nn_createSocket:(Ljava/net/InetAddress;ILjava/net/InetAddress;I)Ljava/net/Socket;:GetCreateSocket_Ljava_net_InetAddress_ILjava_net_InetAddress_IHandler\nn_createSocket:(Ljava/net/InetAddress;I)Ljava/net/Socket;:GetCreateSocket_Ljava_net_InetAddress_IHandler\nn_createSocket:(Ljava/lang/String;ILjava/net/InetAddress;I)Ljava/net/Socket;:GetCreateSocket_Ljava_lang_String_ILjava_net_InetAddress_IHandler\nn_createSocket:(Ljava/lang/String;I)Ljava/net/Socket;:GetCreateSocket_Ljava_lang_String_IHandler\nn_createSocket:(Ljava/net/Socket;Ljava/lang/String;IZ)Ljava/net/Socket;:GetCreateSocket_Ljava_net_Socket_Ljava_lang_String_IZHandler\nn_createSocket:()Ljava/net/Socket;:GetCreateSocketHandler\n");
  }
  
  public OldAndroidSSLSocketFactory() {
    if (getClass() == OldAndroidSSLSocketFactory.class)
      TypeManager.Activate("Xamarin.Android.Net.OldAndroidSSLSocketFactory, Mono.Android", "", this, new Object[0]); 
  }
  
  private native Socket n_createSocket();
  
  private native Socket n_createSocket(String paramString, int paramInt);
  
  private native Socket n_createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2);
  
  private native Socket n_createSocket(InetAddress paramInetAddress, int paramInt);
  
  private native Socket n_createSocket(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2);
  
  private native Socket n_createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean);
  
  private native String[] n_getDefaultCipherSuites();
  
  private native String[] n_getSupportedCipherSuites();
  
  public Socket createSocket() {
    return n_createSocket();
  }
  
  public Socket createSocket(String paramString, int paramInt) {
    return n_createSocket(paramString, paramInt);
  }
  
  public Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2) {
    return n_createSocket(paramString, paramInt1, paramInetAddress, paramInt2);
  }
  
  public Socket createSocket(InetAddress paramInetAddress, int paramInt) {
    return n_createSocket(paramInetAddress, paramInt);
  }
  
  public Socket createSocket(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2) {
    return n_createSocket(paramInetAddress1, paramInt1, paramInetAddress2, paramInt2);
  }
  
  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean) {
    return n_createSocket(paramSocket, paramString, paramInt, paramBoolean);
  }
  
  public String[] getDefaultCipherSuites() {
    return n_getDefaultCipherSuites();
  }
  
  public String[] getSupportedCipherSuites() {
    return n_getSupportedCipherSuites();
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
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/xamarin/android/net/OldAndroidSSLSocketFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */