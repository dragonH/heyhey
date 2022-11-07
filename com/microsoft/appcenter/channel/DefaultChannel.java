package com.microsoft.appcenter.channel;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.microsoft.appcenter.CancellationException;
import com.microsoft.appcenter.http.ServiceCallback;
import com.microsoft.appcenter.ingestion.Ingestion;
import com.microsoft.appcenter.ingestion.IngestionHttp;
import com.microsoft.appcenter.ingestion.models.Device;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.json.LogSerializer;
import com.microsoft.appcenter.persistence.DatabasePersistence;
import com.microsoft.appcenter.persistence.Persistence;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.IdHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DefaultChannel implements Channel {
  @VisibleForTesting
  static final int CLEAR_BATCH_SIZE = 100;
  
  private final Handler mAppCenterHandler;
  
  private final String mAppSecret;
  
  private final Context mContext;
  
  private int mCurrentState;
  
  private Device mDevice;
  
  private boolean mDiscardLogs;
  
  private boolean mEnabled;
  
  private final Map<String, GroupState> mGroupStates;
  
  private final Ingestion mIngestion;
  
  private final Handler mIngestionHandler;
  
  private final UUID mInstallId;
  
  private final Collection<Channel.Listener> mListeners;
  
  private final Persistence mPersistence;
  
  public DefaultChannel(@NonNull Context paramContext, @NonNull String paramString, @NonNull LogSerializer paramLogSerializer, @NonNull Handler paramHandler) {
    this(paramContext, paramString, buildDefaultPersistence(paramLogSerializer), (Ingestion)new IngestionHttp(paramContext, paramLogSerializer), paramHandler);
  }
  
  @VisibleForTesting
  DefaultChannel(@NonNull Context paramContext, @NonNull String paramString, @NonNull Persistence paramPersistence, @NonNull Ingestion paramIngestion, @NonNull Handler paramHandler) {
    this.mContext = paramContext;
    this.mAppSecret = paramString;
    this.mInstallId = IdHelper.getInstallId();
    this.mIngestionHandler = new Handler(Looper.getMainLooper());
    this.mGroupStates = new HashMap<String, GroupState>();
    this.mListeners = new HashSet<Channel.Listener>();
    this.mPersistence = paramPersistence;
    this.mIngestion = paramIngestion;
    this.mAppCenterHandler = paramHandler;
    this.mEnabled = true;
  }
  
  private static Persistence buildDefaultPersistence(@NonNull LogSerializer paramLogSerializer) {
    DatabasePersistence databasePersistence = new DatabasePersistence();
    databasePersistence.setLogSerializer(paramLogSerializer);
    return (Persistence)databasePersistence;
  }
  
  private void cancelTimer(GroupState paramGroupState) {
    if (paramGroupState.mScheduled) {
      paramGroupState.mScheduled = false;
      this.mIngestionHandler.removeCallbacks(paramGroupState.mRunnable);
    } 
  }
  
  private void checkPendingLogs(@NonNull String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mGroupStates : Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   12: checkcast com/microsoft/appcenter/channel/DefaultChannel$GroupState
    //   15: astore_2
    //   16: aload_2
    //   17: getfield mPendingLogCount : I
    //   20: i2l
    //   21: lstore_3
    //   22: new java/lang/StringBuilder
    //   25: astore #5
    //   27: aload #5
    //   29: invokespecial <init> : ()V
    //   32: aload #5
    //   34: ldc 'checkPendingLogs('
    //   36: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: pop
    //   40: aload #5
    //   42: aload_1
    //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: pop
    //   47: aload #5
    //   49: ldc ') pendingLogCount='
    //   51: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: pop
    //   55: aload #5
    //   57: lload_3
    //   58: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   61: pop
    //   62: ldc 'AppCenter'
    //   64: aload #5
    //   66: invokevirtual toString : ()Ljava/lang/String;
    //   69: invokestatic debug : (Ljava/lang/String;Ljava/lang/String;)V
    //   72: lload_3
    //   73: aload_2
    //   74: getfield mMaxLogsPerBatch : I
    //   77: i2l
    //   78: lcmp
    //   79: iflt -> 90
    //   82: aload_0
    //   83: aload_1
    //   84: invokespecial triggerIngestion : (Ljava/lang/String;)V
    //   87: goto -> 124
    //   90: lload_3
    //   91: lconst_0
    //   92: lcmp
    //   93: ifle -> 124
    //   96: aload_2
    //   97: getfield mScheduled : Z
    //   100: ifne -> 124
    //   103: aload_2
    //   104: iconst_1
    //   105: putfield mScheduled : Z
    //   108: aload_0
    //   109: getfield mIngestionHandler : Landroid/os/Handler;
    //   112: aload_2
    //   113: getfield mRunnable : Ljava/lang/Runnable;
    //   116: aload_2
    //   117: getfield mBatchTimeInterval : J
    //   120: invokevirtual postDelayed : (Ljava/lang/Runnable;J)Z
    //   123: pop
    //   124: aload_0
    //   125: monitorexit
    //   126: return
    //   127: astore_1
    //   128: aload_0
    //   129: monitorexit
    //   130: aload_1
    //   131: athrow
    // Exception table:
    //   from	to	target	type
    //   2	87	127	finally
    //   96	124	127	finally
  }
  
  private void checkPendingLogsAfterPost(@NonNull GroupState paramGroupState, int paramInt) {
    if (checkStateDidNotChange(paramGroupState, paramInt))
      checkPendingLogs(paramGroupState.mName); 
  }
  
  private boolean checkStateDidNotChange(GroupState paramGroupState, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iload_2
    //   3: aload_0
    //   4: getfield mCurrentState : I
    //   7: if_icmpne -> 35
    //   10: aload_0
    //   11: getfield mGroupStates : Ljava/util/Map;
    //   14: aload_1
    //   15: getfield mName : Ljava/lang/String;
    //   18: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   23: astore_3
    //   24: aload_1
    //   25: aload_3
    //   26: if_acmpne -> 35
    //   29: iconst_1
    //   30: istore #4
    //   32: goto -> 38
    //   35: iconst_0
    //   36: istore #4
    //   38: aload_0
    //   39: monitorexit
    //   40: iload #4
    //   42: ireturn
    //   43: astore_1
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_1
    //   47: athrow
    // Exception table:
    //   from	to	target	type
    //   2	24	43	finally
  }
  
  private void deleteLogsOnSuspended(GroupState paramGroupState) {
    ArrayList arrayList = new ArrayList();
    this.mPersistence.getLogs(paramGroupState.mName, 100, arrayList);
    if (arrayList.size() > 0 && paramGroupState.mListener != null)
      for (Log log : arrayList) {
        paramGroupState.mListener.onBeforeSending(log);
        paramGroupState.mListener.onFailure(log, (Exception)new CancellationException());
      }  
    if (arrayList.size() >= 100 && paramGroupState.mListener != null) {
      deleteLogsOnSuspended(paramGroupState);
    } else {
      this.mPersistence.deleteLogs(paramGroupState.mName);
    } 
  }
  
  private void handleSendingFailure(@NonNull GroupState paramGroupState, int paramInt, @NonNull String paramString, @NonNull Exception paramException) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: iload_2
    //   5: invokespecial checkStateDidNotChange : (Lcom/microsoft/appcenter/channel/DefaultChannel$GroupState;I)Z
    //   8: ifeq -> 192
    //   11: aload_1
    //   12: getfield mName : Ljava/lang/String;
    //   15: astore #5
    //   17: new java/lang/StringBuilder
    //   20: astore #6
    //   22: aload #6
    //   24: invokespecial <init> : ()V
    //   27: aload #6
    //   29: ldc_w 'Sending logs groupName='
    //   32: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: pop
    //   36: aload #6
    //   38: aload #5
    //   40: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: pop
    //   44: aload #6
    //   46: ldc_w ' id='
    //   49: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: pop
    //   53: aload #6
    //   55: aload_3
    //   56: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: pop
    //   60: aload #6
    //   62: ldc_w ' failed'
    //   65: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: pop
    //   69: ldc 'AppCenter'
    //   71: aload #6
    //   73: invokevirtual toString : ()Ljava/lang/String;
    //   76: aload #4
    //   78: invokestatic error : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   81: aload_1
    //   82: getfield mSendingBatches : Ljava/util/Map;
    //   85: aload_3
    //   86: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   91: checkcast java/util/List
    //   94: astore_3
    //   95: aload #4
    //   97: invokestatic isRecoverableError : (Ljava/lang/Throwable;)Z
    //   100: istore #7
    //   102: iload #7
    //   104: ifeq -> 125
    //   107: aload_1
    //   108: aload_1
    //   109: getfield mPendingLogCount : I
    //   112: aload_3
    //   113: invokeinterface size : ()I
    //   118: iadd
    //   119: putfield mPendingLogCount : I
    //   122: goto -> 170
    //   125: aload_1
    //   126: getfield mListener : Lcom/microsoft/appcenter/channel/Channel$GroupListener;
    //   129: astore_1
    //   130: aload_1
    //   131: ifnull -> 170
    //   134: aload_3
    //   135: invokeinterface iterator : ()Ljava/util/Iterator;
    //   140: astore_3
    //   141: aload_3
    //   142: invokeinterface hasNext : ()Z
    //   147: ifeq -> 170
    //   150: aload_1
    //   151: aload_3
    //   152: invokeinterface next : ()Ljava/lang/Object;
    //   157: checkcast com/microsoft/appcenter/ingestion/models/Log
    //   160: aload #4
    //   162: invokeinterface onFailure : (Lcom/microsoft/appcenter/ingestion/models/Log;Ljava/lang/Exception;)V
    //   167: goto -> 141
    //   170: iload #7
    //   172: ifne -> 181
    //   175: iconst_1
    //   176: istore #7
    //   178: goto -> 184
    //   181: iconst_0
    //   182: istore #7
    //   184: aload_0
    //   185: iload #7
    //   187: aload #4
    //   189: invokespecial suspend : (ZLjava/lang/Exception;)V
    //   192: aload_0
    //   193: monitorexit
    //   194: return
    //   195: astore_1
    //   196: aload_0
    //   197: monitorexit
    //   198: goto -> 203
    //   201: aload_1
    //   202: athrow
    //   203: goto -> 201
    // Exception table:
    //   from	to	target	type
    //   2	102	195	finally
    //   107	122	195	finally
    //   125	130	195	finally
    //   134	141	195	finally
    //   141	167	195	finally
    //   184	192	195	finally
  }
  
  private void handleSendingSuccess(@NonNull GroupState paramGroupState, int paramInt, @NonNull String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: iload_2
    //   5: invokespecial checkStateDidNotChange : (Lcom/microsoft/appcenter/channel/DefaultChannel$GroupState;I)Z
    //   8: ifeq -> 90
    //   11: aload_1
    //   12: getfield mName : Ljava/lang/String;
    //   15: astore #4
    //   17: aload_0
    //   18: getfield mPersistence : Lcom/microsoft/appcenter/persistence/Persistence;
    //   21: aload #4
    //   23: aload_3
    //   24: invokevirtual deleteLogs : (Ljava/lang/String;Ljava/lang/String;)V
    //   27: aload_1
    //   28: getfield mSendingBatches : Ljava/util/Map;
    //   31: aload_3
    //   32: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   37: checkcast java/util/List
    //   40: astore_3
    //   41: aload_1
    //   42: getfield mListener : Lcom/microsoft/appcenter/channel/Channel$GroupListener;
    //   45: astore_1
    //   46: aload_1
    //   47: ifnull -> 84
    //   50: aload_3
    //   51: invokeinterface iterator : ()Ljava/util/Iterator;
    //   56: astore_3
    //   57: aload_3
    //   58: invokeinterface hasNext : ()Z
    //   63: ifeq -> 84
    //   66: aload_1
    //   67: aload_3
    //   68: invokeinterface next : ()Ljava/lang/Object;
    //   73: checkcast com/microsoft/appcenter/ingestion/models/Log
    //   76: invokeinterface onSuccess : (Lcom/microsoft/appcenter/ingestion/models/Log;)V
    //   81: goto -> 57
    //   84: aload_0
    //   85: aload #4
    //   87: invokespecial checkPendingLogs : (Ljava/lang/String;)V
    //   90: aload_0
    //   91: monitorexit
    //   92: return
    //   93: astore_1
    //   94: aload_0
    //   95: monitorexit
    //   96: goto -> 101
    //   99: aload_1
    //   100: athrow
    //   101: goto -> 99
    // Exception table:
    //   from	to	target	type
    //   2	46	93	finally
    //   50	57	93	finally
    //   57	81	93	finally
    //   84	90	93	finally
  }
  
  @MainThread
  private void sendLogs(GroupState paramGroupState, int paramInt, List<Log> paramList, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: iload_2
    //   5: invokespecial checkStateDidNotChange : (Lcom/microsoft/appcenter/channel/DefaultChannel$GroupState;I)Z
    //   8: ifeq -> 99
    //   11: new com/microsoft/appcenter/ingestion/models/LogContainer
    //   14: astore #5
    //   16: aload #5
    //   18: invokespecial <init> : ()V
    //   21: aload #5
    //   23: aload_3
    //   24: invokevirtual setLogs : (Ljava/util/List;)V
    //   27: aload_0
    //   28: getfield mIngestion : Lcom/microsoft/appcenter/ingestion/Ingestion;
    //   31: astore #6
    //   33: aload_0
    //   34: getfield mAppSecret : Ljava/lang/String;
    //   37: astore_3
    //   38: aload_0
    //   39: getfield mInstallId : Ljava/util/UUID;
    //   42: astore #7
    //   44: new com/microsoft/appcenter/channel/DefaultChannel$2
    //   47: astore #8
    //   49: aload #8
    //   51: aload_0
    //   52: aload_1
    //   53: iload_2
    //   54: aload #4
    //   56: invokespecial <init> : (Lcom/microsoft/appcenter/channel/DefaultChannel;Lcom/microsoft/appcenter/channel/DefaultChannel$GroupState;ILjava/lang/String;)V
    //   59: aload #6
    //   61: aload_3
    //   62: aload #7
    //   64: aload #5
    //   66: aload #8
    //   68: invokeinterface sendAsync : (Ljava/lang/String;Ljava/util/UUID;Lcom/microsoft/appcenter/ingestion/models/LogContainer;Lcom/microsoft/appcenter/http/ServiceCallback;)Lcom/microsoft/appcenter/http/ServiceCall;
    //   73: pop
    //   74: aload_0
    //   75: getfield mAppCenterHandler : Landroid/os/Handler;
    //   78: astore_3
    //   79: new com/microsoft/appcenter/channel/DefaultChannel$3
    //   82: astore #4
    //   84: aload #4
    //   86: aload_0
    //   87: aload_1
    //   88: iload_2
    //   89: invokespecial <init> : (Lcom/microsoft/appcenter/channel/DefaultChannel;Lcom/microsoft/appcenter/channel/DefaultChannel$GroupState;I)V
    //   92: aload_3
    //   93: aload #4
    //   95: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   98: pop
    //   99: aload_0
    //   100: monitorexit
    //   101: return
    //   102: astore_1
    //   103: aload_0
    //   104: monitorexit
    //   105: aload_1
    //   106: athrow
    // Exception table:
    //   from	to	target	type
    //   2	99	102	finally
  }
  
  private void suspend(boolean paramBoolean, Exception paramException) {
    this.mEnabled = false;
    this.mDiscardLogs = paramBoolean;
    this.mCurrentState++;
    for (GroupState groupState : this.mGroupStates.values()) {
      cancelTimer(groupState);
      Iterator<Map.Entry> iterator = groupState.mSendingBatches.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry entry = iterator.next();
        List list = groupState.mSendingBatches.get(entry.getKey());
        iterator.remove();
        if (paramBoolean) {
          Channel.GroupListener groupListener = groupState.mListener;
          if (groupListener != null) {
            Iterator<Log> iterator1 = list.iterator();
            while (iterator1.hasNext())
              groupListener.onFailure(iterator1.next(), paramException); 
          } 
        } 
      } 
    } 
    try {
      this.mIngestion.close();
    } catch (IOException iOException) {
      AppCenterLog.error("AppCenter", "Failed to close ingestion", iOException);
    } 
    if (paramBoolean) {
      Iterator<GroupState> iterator = this.mGroupStates.values().iterator();
      while (iterator.hasNext())
        deleteLogsOnSuspended(iterator.next()); 
    } else {
      this.mPersistence.clearPendingLogState();
    } 
  }
  
  private void triggerIngestion(@NonNull String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mEnabled : Z
    //   6: istore_2
    //   7: iload_2
    //   8: ifne -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: getfield mGroupStates : Ljava/util/Map;
    //   18: aload_1
    //   19: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   24: checkcast com/microsoft/appcenter/channel/DefaultChannel$GroupState
    //   27: astore_3
    //   28: new java/lang/StringBuilder
    //   31: astore #4
    //   33: aload #4
    //   35: invokespecial <init> : ()V
    //   38: aload #4
    //   40: ldc_w 'triggerIngestion('
    //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: pop
    //   47: aload #4
    //   49: aload_1
    //   50: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: pop
    //   54: aload #4
    //   56: ldc ') pendingLogCount='
    //   58: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: pop
    //   62: aload #4
    //   64: aload_3
    //   65: getfield mPendingLogCount : I
    //   68: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   71: pop
    //   72: ldc 'AppCenter'
    //   74: aload #4
    //   76: invokevirtual toString : ()Ljava/lang/String;
    //   79: invokestatic debug : (Ljava/lang/String;Ljava/lang/String;)V
    //   82: aload_0
    //   83: aload_3
    //   84: invokespecial cancelTimer : (Lcom/microsoft/appcenter/channel/DefaultChannel$GroupState;)V
    //   87: aload_3
    //   88: getfield mSendingBatches : Ljava/util/Map;
    //   91: invokeinterface size : ()I
    //   96: aload_3
    //   97: getfield mMaxParallelBatches : I
    //   100: if_icmpne -> 148
    //   103: new java/lang/StringBuilder
    //   106: astore_1
    //   107: aload_1
    //   108: invokespecial <init> : ()V
    //   111: aload_1
    //   112: ldc_w 'Already sending '
    //   115: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: pop
    //   119: aload_1
    //   120: aload_3
    //   121: getfield mMaxParallelBatches : I
    //   124: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   127: pop
    //   128: aload_1
    //   129: ldc_w ' batches of analytics data to the server.'
    //   132: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: pop
    //   136: ldc 'AppCenter'
    //   138: aload_1
    //   139: invokevirtual toString : ()Ljava/lang/String;
    //   142: invokestatic debug : (Ljava/lang/String;Ljava/lang/String;)V
    //   145: aload_0
    //   146: monitorexit
    //   147: return
    //   148: new java/util/ArrayList
    //   151: astore #4
    //   153: aload #4
    //   155: aload_3
    //   156: getfield mMaxLogsPerBatch : I
    //   159: invokespecial <init> : (I)V
    //   162: aload_0
    //   163: getfield mCurrentState : I
    //   166: istore #5
    //   168: aload_0
    //   169: getfield mPersistence : Lcom/microsoft/appcenter/persistence/Persistence;
    //   172: aload_1
    //   173: aload_3
    //   174: getfield mMaxLogsPerBatch : I
    //   177: aload #4
    //   179: invokevirtual getLogs : (Ljava/lang/String;ILjava/util/List;)Ljava/lang/String;
    //   182: astore_1
    //   183: aload_1
    //   184: ifnonnull -> 190
    //   187: aload_0
    //   188: monitorexit
    //   189: return
    //   190: aload_3
    //   191: getfield mListener : Lcom/microsoft/appcenter/channel/Channel$GroupListener;
    //   194: ifnull -> 242
    //   197: aload #4
    //   199: invokeinterface iterator : ()Ljava/util/Iterator;
    //   204: astore #6
    //   206: aload #6
    //   208: invokeinterface hasNext : ()Z
    //   213: ifeq -> 242
    //   216: aload #6
    //   218: invokeinterface next : ()Ljava/lang/Object;
    //   223: checkcast com/microsoft/appcenter/ingestion/models/Log
    //   226: astore #7
    //   228: aload_3
    //   229: getfield mListener : Lcom/microsoft/appcenter/channel/Channel$GroupListener;
    //   232: aload #7
    //   234: invokeinterface onBeforeSending : (Lcom/microsoft/appcenter/ingestion/models/Log;)V
    //   239: goto -> 206
    //   242: aload_3
    //   243: aload_3
    //   244: getfield mPendingLogCount : I
    //   247: aload #4
    //   249: invokeinterface size : ()I
    //   254: isub
    //   255: putfield mPendingLogCount : I
    //   258: new java/lang/StringBuilder
    //   261: astore #7
    //   263: aload #7
    //   265: invokespecial <init> : ()V
    //   268: aload #7
    //   270: ldc_w 'ingestLogs('
    //   273: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: pop
    //   277: aload #7
    //   279: aload_3
    //   280: getfield mName : Ljava/lang/String;
    //   283: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: pop
    //   287: aload #7
    //   289: ldc_w ','
    //   292: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   295: pop
    //   296: aload #7
    //   298: aload_1
    //   299: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   302: pop
    //   303: aload #7
    //   305: ldc ') pendingLogCount='
    //   307: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: pop
    //   311: aload #7
    //   313: aload_3
    //   314: getfield mPendingLogCount : I
    //   317: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   320: pop
    //   321: ldc 'AppCenter'
    //   323: aload #7
    //   325: invokevirtual toString : ()Ljava/lang/String;
    //   328: invokestatic debug : (Ljava/lang/String;Ljava/lang/String;)V
    //   331: aload_3
    //   332: getfield mSendingBatches : Ljava/util/Map;
    //   335: aload_1
    //   336: aload #4
    //   338: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   343: pop
    //   344: new com/microsoft/appcenter/channel/DefaultChannel$1
    //   347: astore #7
    //   349: aload #7
    //   351: aload_0
    //   352: aload_3
    //   353: iload #5
    //   355: aload #4
    //   357: aload_1
    //   358: invokespecial <init> : (Lcom/microsoft/appcenter/channel/DefaultChannel;Lcom/microsoft/appcenter/channel/DefaultChannel$GroupState;ILjava/util/List;Ljava/lang/String;)V
    //   361: aload #7
    //   363: invokestatic runOnUiThread : (Ljava/lang/Runnable;)V
    //   366: aload_0
    //   367: monitorexit
    //   368: return
    //   369: astore_1
    //   370: aload_0
    //   371: monitorexit
    //   372: goto -> 377
    //   375: aload_1
    //   376: athrow
    //   377: goto -> 375
    // Exception table:
    //   from	to	target	type
    //   2	7	369	finally
    //   14	145	369	finally
    //   148	183	369	finally
    //   190	206	369	finally
    //   206	239	369	finally
    //   242	366	369	finally
  }
  
  public void addGroup(String paramString, int paramInt1, long paramLong, int paramInt2, Channel.GroupListener paramGroupListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new java/lang/StringBuilder
    //   5: astore #7
    //   7: aload #7
    //   9: invokespecial <init> : ()V
    //   12: aload #7
    //   14: ldc_w 'addGroup('
    //   17: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: pop
    //   21: aload #7
    //   23: aload_1
    //   24: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: pop
    //   28: aload #7
    //   30: ldc_w ')'
    //   33: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: pop
    //   37: ldc 'AppCenter'
    //   39: aload #7
    //   41: invokevirtual toString : ()Ljava/lang/String;
    //   44: invokestatic debug : (Ljava/lang/String;Ljava/lang/String;)V
    //   47: new com/microsoft/appcenter/channel/DefaultChannel$GroupState
    //   50: astore #7
    //   52: aload #7
    //   54: aload_0
    //   55: aload_1
    //   56: iload_2
    //   57: lload_3
    //   58: iload #5
    //   60: aload #6
    //   62: invokespecial <init> : (Lcom/microsoft/appcenter/channel/DefaultChannel;Ljava/lang/String;IJILcom/microsoft/appcenter/channel/Channel$GroupListener;)V
    //   65: aload_0
    //   66: getfield mGroupStates : Ljava/util/Map;
    //   69: aload_1
    //   70: aload #7
    //   72: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   77: pop
    //   78: aload #7
    //   80: aload_0
    //   81: getfield mPersistence : Lcom/microsoft/appcenter/persistence/Persistence;
    //   84: aload_1
    //   85: invokevirtual countLogs : (Ljava/lang/String;)I
    //   88: putfield mPendingLogCount : I
    //   91: aload_0
    //   92: aload #7
    //   94: getfield mName : Ljava/lang/String;
    //   97: invokespecial checkPendingLogs : (Ljava/lang/String;)V
    //   100: aload_0
    //   101: monitorexit
    //   102: return
    //   103: astore_1
    //   104: aload_0
    //   105: monitorexit
    //   106: aload_1
    //   107: athrow
    // Exception table:
    //   from	to	target	type
    //   2	100	103	finally
  }
  
  public void addListener(Channel.Listener paramListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mListeners : Ljava/util/Collection;
    //   6: aload_1
    //   7: invokeinterface add : (Ljava/lang/Object;)Z
    //   12: pop
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	16	finally
  }
  
  public void clear(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mPersistence : Lcom/microsoft/appcenter/persistence/Persistence;
    //   6: aload_1
    //   7: invokevirtual deleteLogs : (Ljava/lang/String;)V
    //   10: aload_0
    //   11: monitorexit
    //   12: return
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	13	finally
  }
  
  public void enqueue(@NonNull Log paramLog, @NonNull String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mGroupStates : Ljava/util/Map;
    //   6: aload_2
    //   7: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   12: checkcast com/microsoft/appcenter/channel/DefaultChannel$GroupState
    //   15: astore_3
    //   16: aload_3
    //   17: ifnonnull -> 54
    //   20: new java/lang/StringBuilder
    //   23: astore_1
    //   24: aload_1
    //   25: invokespecial <init> : ()V
    //   28: aload_1
    //   29: ldc_w 'Invalid group name:'
    //   32: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: pop
    //   36: aload_1
    //   37: aload_2
    //   38: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: pop
    //   42: ldc 'AppCenter'
    //   44: aload_1
    //   45: invokevirtual toString : ()Ljava/lang/String;
    //   48: invokestatic error : (Ljava/lang/String;Ljava/lang/String;)V
    //   51: aload_0
    //   52: monitorexit
    //   53: return
    //   54: aload_0
    //   55: getfield mDiscardLogs : Z
    //   58: ifeq -> 109
    //   61: ldc 'AppCenter'
    //   63: ldc_w 'Channel is disabled, log are discarded.'
    //   66: invokestatic warn : (Ljava/lang/String;Ljava/lang/String;)V
    //   69: aload_3
    //   70: getfield mListener : Lcom/microsoft/appcenter/channel/Channel$GroupListener;
    //   73: astore_2
    //   74: aload_2
    //   75: ifnull -> 106
    //   78: aload_2
    //   79: aload_1
    //   80: invokeinterface onBeforeSending : (Lcom/microsoft/appcenter/ingestion/models/Log;)V
    //   85: aload_3
    //   86: getfield mListener : Lcom/microsoft/appcenter/channel/Channel$GroupListener;
    //   89: astore_3
    //   90: new com/microsoft/appcenter/CancellationException
    //   93: astore_2
    //   94: aload_2
    //   95: invokespecial <init> : ()V
    //   98: aload_3
    //   99: aload_1
    //   100: aload_2
    //   101: invokeinterface onFailure : (Lcom/microsoft/appcenter/ingestion/models/Log;Ljava/lang/Exception;)V
    //   106: aload_0
    //   107: monitorexit
    //   108: return
    //   109: aload_0
    //   110: getfield mListeners : Ljava/util/Collection;
    //   113: invokeinterface iterator : ()Ljava/util/Iterator;
    //   118: astore #4
    //   120: aload #4
    //   122: invokeinterface hasNext : ()Z
    //   127: ifeq -> 150
    //   130: aload #4
    //   132: invokeinterface next : ()Ljava/lang/Object;
    //   137: checkcast com/microsoft/appcenter/channel/Channel$Listener
    //   140: aload_1
    //   141: aload_2
    //   142: invokeinterface onEnqueuingLog : (Lcom/microsoft/appcenter/ingestion/models/Log;Ljava/lang/String;)V
    //   147: goto -> 120
    //   150: aload_1
    //   151: invokeinterface getDevice : ()Lcom/microsoft/appcenter/ingestion/models/Device;
    //   156: ifnonnull -> 207
    //   159: aload_0
    //   160: getfield mDevice : Lcom/microsoft/appcenter/ingestion/models/Device;
    //   163: astore #4
    //   165: aload #4
    //   167: ifnonnull -> 197
    //   170: aload_0
    //   171: aload_0
    //   172: getfield mContext : Landroid/content/Context;
    //   175: invokestatic getDeviceInfo : (Landroid/content/Context;)Lcom/microsoft/appcenter/ingestion/models/Device;
    //   178: putfield mDevice : Lcom/microsoft/appcenter/ingestion/models/Device;
    //   181: goto -> 197
    //   184: astore_1
    //   185: ldc 'AppCenter'
    //   187: ldc_w 'Device log cannot be generated'
    //   190: aload_1
    //   191: invokestatic error : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   194: aload_0
    //   195: monitorexit
    //   196: return
    //   197: aload_1
    //   198: aload_0
    //   199: getfield mDevice : Lcom/microsoft/appcenter/ingestion/models/Device;
    //   202: invokeinterface setDevice : (Lcom/microsoft/appcenter/ingestion/models/Device;)V
    //   207: aload_1
    //   208: invokeinterface getTimestamp : ()Ljava/util/Date;
    //   213: ifnonnull -> 234
    //   216: new java/util/Date
    //   219: astore #4
    //   221: aload #4
    //   223: invokespecial <init> : ()V
    //   226: aload_1
    //   227: aload #4
    //   229: invokeinterface setTimestamp : (Ljava/util/Date;)V
    //   234: aload_0
    //   235: getfield mPersistence : Lcom/microsoft/appcenter/persistence/Persistence;
    //   238: aload_2
    //   239: aload_1
    //   240: invokevirtual putLog : (Ljava/lang/String;Lcom/microsoft/appcenter/ingestion/models/Log;)V
    //   243: aload_3
    //   244: aload_3
    //   245: getfield mPendingLogCount : I
    //   248: iconst_1
    //   249: iadd
    //   250: putfield mPendingLogCount : I
    //   253: new java/lang/StringBuilder
    //   256: astore_1
    //   257: aload_1
    //   258: invokespecial <init> : ()V
    //   261: aload_1
    //   262: ldc_w 'enqueue('
    //   265: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   268: pop
    //   269: aload_1
    //   270: aload_3
    //   271: getfield mName : Ljava/lang/String;
    //   274: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   277: pop
    //   278: aload_1
    //   279: ldc ') pendingLogCount='
    //   281: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   284: pop
    //   285: aload_1
    //   286: aload_3
    //   287: getfield mPendingLogCount : I
    //   290: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   293: pop
    //   294: ldc 'AppCenter'
    //   296: aload_1
    //   297: invokevirtual toString : ()Ljava/lang/String;
    //   300: invokestatic debug : (Ljava/lang/String;Ljava/lang/String;)V
    //   303: aload_0
    //   304: getfield mEnabled : Z
    //   307: ifeq -> 321
    //   310: aload_0
    //   311: aload_3
    //   312: getfield mName : Ljava/lang/String;
    //   315: invokespecial checkPendingLogs : (Ljava/lang/String;)V
    //   318: goto -> 367
    //   321: ldc 'AppCenter'
    //   323: ldc_w 'Channel is temporarily disabled, log was saved to disk.'
    //   326: invokestatic warn : (Ljava/lang/String;Ljava/lang/String;)V
    //   329: goto -> 367
    //   332: astore_1
    //   333: new java/lang/StringBuilder
    //   336: astore_2
    //   337: aload_2
    //   338: invokespecial <init> : ()V
    //   341: aload_2
    //   342: ldc_w 'Error persisting log with exception: '
    //   345: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: pop
    //   349: aload_2
    //   350: aload_1
    //   351: invokevirtual toString : ()Ljava/lang/String;
    //   354: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: pop
    //   358: ldc 'AppCenter'
    //   360: aload_2
    //   361: invokevirtual toString : ()Ljava/lang/String;
    //   364: invokestatic error : (Ljava/lang/String;Ljava/lang/String;)V
    //   367: aload_0
    //   368: monitorexit
    //   369: return
    //   370: astore_1
    //   371: aload_0
    //   372: monitorexit
    //   373: goto -> 378
    //   376: aload_1
    //   377: athrow
    //   378: goto -> 376
    // Exception table:
    //   from	to	target	type
    //   2	16	370	finally
    //   20	51	370	finally
    //   54	74	370	finally
    //   78	106	370	finally
    //   109	120	370	finally
    //   120	147	370	finally
    //   150	165	370	finally
    //   170	181	184	com/microsoft/appcenter/utils/DeviceInfoHelper$DeviceInfoException
    //   170	181	370	finally
    //   185	194	370	finally
    //   197	207	370	finally
    //   207	234	370	finally
    //   234	318	332	com/microsoft/appcenter/persistence/Persistence$PersistenceException
    //   234	318	370	finally
    //   321	329	332	com/microsoft/appcenter/persistence/Persistence$PersistenceException
    //   321	329	370	finally
    //   333	367	370	finally
  }
  
  @VisibleForTesting
  int getCounter(@NonNull String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mGroupStates : Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   12: checkcast com/microsoft/appcenter/channel/DefaultChannel$GroupState
    //   15: getfield mPendingLogCount : I
    //   18: istore_2
    //   19: aload_0
    //   20: monitorexit
    //   21: iload_2
    //   22: ireturn
    //   23: astore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_1
    //   27: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	23	finally
  }
  
  public void invalidateDeviceCache() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aconst_null
    //   4: putfield mDevice : Lcom/microsoft/appcenter/ingestion/models/Device;
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  public boolean isEnabled() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mEnabled : Z
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public void removeGroup(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mGroupStates : Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   12: checkcast com/microsoft/appcenter/channel/DefaultChannel$GroupState
    //   15: astore_1
    //   16: aload_1
    //   17: ifnull -> 25
    //   20: aload_0
    //   21: aload_1
    //   22: invokespecial cancelTimer : (Lcom/microsoft/appcenter/channel/DefaultChannel$GroupState;)V
    //   25: aload_0
    //   26: monitorexit
    //   27: return
    //   28: astore_1
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_1
    //   32: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	28	finally
    //   20	25	28	finally
  }
  
  public void removeListener(Channel.Listener paramListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mListeners : Ljava/util/Collection;
    //   6: aload_1
    //   7: invokeinterface remove : (Ljava/lang/Object;)Z
    //   12: pop
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	16	finally
  }
  
  public void setEnabled(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mEnabled : Z
    //   6: istore_2
    //   7: iload_2
    //   8: iload_1
    //   9: if_icmpne -> 15
    //   12: aload_0
    //   13: monitorexit
    //   14: return
    //   15: iload_1
    //   16: ifeq -> 79
    //   19: aload_0
    //   20: iconst_1
    //   21: putfield mEnabled : Z
    //   24: aload_0
    //   25: iconst_0
    //   26: putfield mDiscardLogs : Z
    //   29: aload_0
    //   30: aload_0
    //   31: getfield mCurrentState : I
    //   34: iconst_1
    //   35: iadd
    //   36: putfield mCurrentState : I
    //   39: aload_0
    //   40: getfield mGroupStates : Ljava/util/Map;
    //   43: invokeinterface keySet : ()Ljava/util/Set;
    //   48: invokeinterface iterator : ()Ljava/util/Iterator;
    //   53: astore_3
    //   54: aload_3
    //   55: invokeinterface hasNext : ()Z
    //   60: ifeq -> 93
    //   63: aload_0
    //   64: aload_3
    //   65: invokeinterface next : ()Ljava/lang/Object;
    //   70: checkcast java/lang/String
    //   73: invokespecial checkPendingLogs : (Ljava/lang/String;)V
    //   76: goto -> 54
    //   79: new com/microsoft/appcenter/CancellationException
    //   82: astore_3
    //   83: aload_3
    //   84: invokespecial <init> : ()V
    //   87: aload_0
    //   88: iconst_1
    //   89: aload_3
    //   90: invokespecial suspend : (ZLjava/lang/Exception;)V
    //   93: aload_0
    //   94: monitorexit
    //   95: return
    //   96: astore_3
    //   97: aload_0
    //   98: monitorexit
    //   99: goto -> 104
    //   102: aload_3
    //   103: athrow
    //   104: goto -> 102
    // Exception table:
    //   from	to	target	type
    //   2	7	96	finally
    //   19	54	96	finally
    //   54	76	96	finally
    //   79	93	96	finally
  }
  
  public void setLogUrl(String paramString) {
    this.mIngestion.setLogUrl(paramString);
  }
  
  public void shutdown() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/CancellationException
    //   5: astore_1
    //   6: aload_1
    //   7: invokespecial <init> : ()V
    //   10: aload_0
    //   11: iconst_0
    //   12: aload_1
    //   13: invokespecial suspend : (ZLjava/lang/Exception;)V
    //   16: aload_0
    //   17: monitorexit
    //   18: return
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	19	finally
  }
  
  private class GroupState {
    final long mBatchTimeInterval;
    
    final Channel.GroupListener mListener;
    
    final int mMaxLogsPerBatch;
    
    final int mMaxParallelBatches;
    
    final String mName;
    
    int mPendingLogCount;
    
    final Runnable mRunnable = new Runnable() {
        public void run() {
          DefaultChannel.GroupState groupState = DefaultChannel.GroupState.this;
          groupState.mScheduled = false;
          DefaultChannel.this.mAppCenterHandler.post(new Runnable() {
                public void run() {
                  DefaultChannel.GroupState groupState = DefaultChannel.GroupState.this;
                  DefaultChannel.this.triggerIngestion(groupState.mName);
                }
              });
        }
      };
    
    boolean mScheduled;
    
    final Map<String, List<Log>> mSendingBatches = new HashMap<String, List<Log>>();
    
    GroupState(String param1String, int param1Int1, long param1Long, int param1Int2, Channel.GroupListener param1GroupListener) {
      this.mName = param1String;
      this.mMaxLogsPerBatch = param1Int1;
      this.mBatchTimeInterval = param1Long;
      this.mMaxParallelBatches = param1Int2;
      this.mListener = param1GroupListener;
    }
  }
  
  class null implements Runnable {
    public void run() {
      DefaultChannel.GroupState groupState = this.this$1;
      groupState.mScheduled = false;
      DefaultChannel.this.mAppCenterHandler.post(new Runnable() {
            public void run() {
              DefaultChannel.GroupState groupState = this.this$2.this$1;
              DefaultChannel.this.triggerIngestion(groupState.mName);
            }
          });
    }
  }
  
  class null implements Runnable {
    public void run() {
      DefaultChannel.GroupState groupState = this.this$2.this$1;
      DefaultChannel.this.triggerIngestion(groupState.mName);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/channel/DefaultChannel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */