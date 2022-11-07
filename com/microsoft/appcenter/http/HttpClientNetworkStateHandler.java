package com.microsoft.appcenter.http;

import com.microsoft.appcenter.utils.NetworkStateHelper;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HttpClientNetworkStateHandler extends HttpClientDecorator implements NetworkStateHelper.Listener {
  private final Set<Call> mCalls = new HashSet<Call>();
  
  private final NetworkStateHelper mNetworkStateHelper;
  
  public HttpClientNetworkStateHandler(HttpClient paramHttpClient, NetworkStateHelper paramNetworkStateHelper) {
    super(paramHttpClient);
    this.mNetworkStateHelper = paramNetworkStateHelper;
    paramNetworkStateHelper.addListener(this);
  }
  
  private void callRunAsync(Call paramCall) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: aload_1
    //   4: getfield mDecoratedApi : Lcom/microsoft/appcenter/http/HttpClient;
    //   7: aload_1
    //   8: getfield mUrl : Ljava/lang/String;
    //   11: aload_1
    //   12: getfield mMethod : Ljava/lang/String;
    //   15: aload_1
    //   16: getfield mHeaders : Ljava/util/Map;
    //   19: aload_1
    //   20: getfield mCallTemplate : Lcom/microsoft/appcenter/http/HttpClient$CallTemplate;
    //   23: aload_1
    //   24: invokeinterface callAsync : (Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lcom/microsoft/appcenter/http/HttpClient$CallTemplate;Lcom/microsoft/appcenter/http/ServiceCallback;)Lcom/microsoft/appcenter/http/ServiceCall;
    //   29: putfield mServiceCall : Lcom/microsoft/appcenter/http/ServiceCall;
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: astore_1
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_1
    //   39: athrow
    // Exception table:
    //   from	to	target	type
    //   2	32	35	finally
  }
  
  private void cancelCall(Call paramCall) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCalls : Ljava/util/Set;
    //   6: aload_1
    //   7: invokeinterface remove : (Ljava/lang/Object;)Z
    //   12: pop
    //   13: aload_0
    //   14: aload_1
    //   15: invokespecial pauseCall : (Lcom/microsoft/appcenter/http/HttpClientNetworkStateHandler$Call;)V
    //   18: aload_0
    //   19: monitorexit
    //   20: return
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	21	finally
  }
  
  private void onCallFailed(Call paramCall, Exception paramException) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCalls : Ljava/util/Set;
    //   6: aload_1
    //   7: invokeinterface contains : (Ljava/lang/Object;)Z
    //   12: ifeq -> 36
    //   15: aload_1
    //   16: getfield mServiceCallback : Lcom/microsoft/appcenter/http/ServiceCallback;
    //   19: aload_2
    //   20: invokeinterface onCallFailed : (Ljava/lang/Exception;)V
    //   25: aload_0
    //   26: getfield mCalls : Ljava/util/Set;
    //   29: aload_1
    //   30: invokeinterface remove : (Ljava/lang/Object;)Z
    //   35: pop
    //   36: aload_0
    //   37: monitorexit
    //   38: return
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: athrow
    // Exception table:
    //   from	to	target	type
    //   2	36	39	finally
  }
  
  private void onCallSucceeded(Call paramCall, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCalls : Ljava/util/Set;
    //   6: aload_1
    //   7: invokeinterface contains : (Ljava/lang/Object;)Z
    //   12: ifeq -> 36
    //   15: aload_1
    //   16: getfield mServiceCallback : Lcom/microsoft/appcenter/http/ServiceCallback;
    //   19: aload_2
    //   20: invokeinterface onCallSucceeded : (Ljava/lang/String;)V
    //   25: aload_0
    //   26: getfield mCalls : Ljava/util/Set;
    //   29: aload_1
    //   30: invokeinterface remove : (Ljava/lang/Object;)Z
    //   35: pop
    //   36: aload_0
    //   37: monitorexit
    //   38: return
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: athrow
    // Exception table:
    //   from	to	target	type
    //   2	36	39	finally
  }
  
  private void pauseCall(Call paramCall) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: getfield mServiceCall : Lcom/microsoft/appcenter/http/ServiceCall;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull -> 17
    //   11: aload_1
    //   12: invokeinterface cancel : ()V
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	20	finally
    //   11	17	20	finally
  }
  
  public ServiceCall callAsync(String paramString1, String paramString2, Map<String, String> paramMap, HttpClient.CallTemplate paramCallTemplate, ServiceCallback paramServiceCallback) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/http/HttpClientNetworkStateHandler$Call
    //   5: astore #6
    //   7: aload #6
    //   9: aload_0
    //   10: aload_0
    //   11: getfield mDecoratedApi : Lcom/microsoft/appcenter/http/HttpClient;
    //   14: aload_1
    //   15: aload_2
    //   16: aload_3
    //   17: aload #4
    //   19: aload #5
    //   21: invokespecial <init> : (Lcom/microsoft/appcenter/http/HttpClientNetworkStateHandler;Lcom/microsoft/appcenter/http/HttpClient;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lcom/microsoft/appcenter/http/HttpClient$CallTemplate;Lcom/microsoft/appcenter/http/ServiceCallback;)V
    //   24: aload_0
    //   25: getfield mCalls : Ljava/util/Set;
    //   28: aload #6
    //   30: invokeinterface add : (Ljava/lang/Object;)Z
    //   35: pop
    //   36: aload_0
    //   37: getfield mNetworkStateHelper : Lcom/microsoft/appcenter/utils/NetworkStateHelper;
    //   40: invokevirtual isNetworkConnected : ()Z
    //   43: ifeq -> 54
    //   46: aload #6
    //   48: invokevirtual run : ()V
    //   51: goto -> 61
    //   54: ldc 'AppCenter'
    //   56: ldc 'Call triggered with no network connectivity, waiting network to become available...'
    //   58: invokestatic debug : (Ljava/lang/String;Ljava/lang/String;)V
    //   61: aload_0
    //   62: monitorexit
    //   63: aload #6
    //   65: areturn
    //   66: astore_1
    //   67: aload_0
    //   68: monitorexit
    //   69: aload_1
    //   70: athrow
    // Exception table:
    //   from	to	target	type
    //   2	51	66	finally
    //   54	61	66	finally
  }
  
  public void close() throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mNetworkStateHelper : Lcom/microsoft/appcenter/utils/NetworkStateHelper;
    //   6: aload_0
    //   7: invokevirtual removeListener : (Lcom/microsoft/appcenter/utils/NetworkStateHelper$Listener;)V
    //   10: aload_0
    //   11: getfield mCalls : Ljava/util/Set;
    //   14: invokeinterface iterator : ()Ljava/util/Iterator;
    //   19: astore_1
    //   20: aload_1
    //   21: invokeinterface hasNext : ()Z
    //   26: ifeq -> 45
    //   29: aload_0
    //   30: aload_1
    //   31: invokeinterface next : ()Ljava/lang/Object;
    //   36: checkcast com/microsoft/appcenter/http/HttpClientNetworkStateHandler$Call
    //   39: invokespecial pauseCall : (Lcom/microsoft/appcenter/http/HttpClientNetworkStateHandler$Call;)V
    //   42: goto -> 20
    //   45: aload_0
    //   46: getfield mCalls : Ljava/util/Set;
    //   49: invokeinterface clear : ()V
    //   54: aload_0
    //   55: invokespecial close : ()V
    //   58: aload_0
    //   59: monitorexit
    //   60: return
    //   61: astore_1
    //   62: aload_0
    //   63: monitorexit
    //   64: goto -> 69
    //   67: aload_1
    //   68: athrow
    //   69: goto -> 67
    // Exception table:
    //   from	to	target	type
    //   2	20	61	finally
    //   20	42	61	finally
    //   45	58	61	finally
  }
  
  public void onNetworkStateUpdated(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iload_1
    //   3: ifeq -> 54
    //   6: new java/lang/StringBuilder
    //   9: astore_2
    //   10: aload_2
    //   11: invokespecial <init> : ()V
    //   14: aload_2
    //   15: ldc 'Network is available. '
    //   17: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: pop
    //   21: aload_2
    //   22: aload_0
    //   23: getfield mCalls : Ljava/util/Set;
    //   26: invokeinterface size : ()I
    //   31: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   34: pop
    //   35: aload_2
    //   36: ldc ' pending call(s) to submit now.'
    //   38: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: pop
    //   42: ldc 'AppCenter'
    //   44: aload_2
    //   45: invokevirtual toString : ()Ljava/lang/String;
    //   48: invokestatic debug : (Ljava/lang/String;Ljava/lang/String;)V
    //   51: goto -> 99
    //   54: new java/lang/StringBuilder
    //   57: astore_2
    //   58: aload_2
    //   59: invokespecial <init> : ()V
    //   62: aload_2
    //   63: ldc 'Network is down. Pausing '
    //   65: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: pop
    //   69: aload_2
    //   70: aload_0
    //   71: getfield mCalls : Ljava/util/Set;
    //   74: invokeinterface size : ()I
    //   79: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   82: pop
    //   83: aload_2
    //   84: ldc ' network call(s).'
    //   86: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: pop
    //   90: ldc 'AppCenter'
    //   92: aload_2
    //   93: invokevirtual toString : ()Ljava/lang/String;
    //   96: invokestatic debug : (Ljava/lang/String;Ljava/lang/String;)V
    //   99: aload_0
    //   100: getfield mCalls : Ljava/util/Set;
    //   103: invokeinterface iterator : ()Ljava/util/Iterator;
    //   108: astore_3
    //   109: aload_3
    //   110: invokeinterface hasNext : ()Z
    //   115: ifeq -> 147
    //   118: aload_3
    //   119: invokeinterface next : ()Ljava/lang/Object;
    //   124: checkcast com/microsoft/appcenter/http/HttpClientNetworkStateHandler$Call
    //   127: astore_2
    //   128: iload_1
    //   129: ifeq -> 139
    //   132: aload_2
    //   133: invokevirtual run : ()V
    //   136: goto -> 109
    //   139: aload_0
    //   140: aload_2
    //   141: invokespecial pauseCall : (Lcom/microsoft/appcenter/http/HttpClientNetworkStateHandler$Call;)V
    //   144: goto -> 109
    //   147: aload_0
    //   148: monitorexit
    //   149: return
    //   150: astore_2
    //   151: aload_0
    //   152: monitorexit
    //   153: goto -> 158
    //   156: aload_2
    //   157: athrow
    //   158: goto -> 156
    // Exception table:
    //   from	to	target	type
    //   6	51	150	finally
    //   54	99	150	finally
    //   99	109	150	finally
    //   109	128	150	finally
    //   132	136	150	finally
    //   139	144	150	finally
  }
  
  private class Call extends HttpClientCallDecorator implements Runnable, ServiceCallback {
    Call(HttpClient param1HttpClient, String param1String1, String param1String2, Map<String, String> param1Map, HttpClient.CallTemplate param1CallTemplate, ServiceCallback param1ServiceCallback) {
      super(param1HttpClient, param1String1, param1String2, param1Map, param1CallTemplate, param1ServiceCallback);
    }
    
    public void cancel() {
      HttpClientNetworkStateHandler.this.cancelCall(this);
    }
    
    public void onCallFailed(Exception param1Exception) {
      HttpClientNetworkStateHandler.this.onCallFailed(this, param1Exception);
    }
    
    public void onCallSucceeded(String param1String) {
      HttpClientNetworkStateHandler.this.onCallSucceeded(this, param1String);
    }
    
    public void run() {
      HttpClientNetworkStateHandler.this.callRunAsync(this);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/http/HttpClientNetworkStateHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */