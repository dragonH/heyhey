package com.microsoft.appcenter.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.microsoft.appcenter.ingestion.models.Device;
import com.microsoft.appcenter.ingestion.models.WrapperSdk;

public class DeviceInfoHelper {
  private static final String OS_NAME = "Android";
  
  private static WrapperSdk sWrapperSdk;
  
  public static Device getDeviceInfo(Context paramContext) throws DeviceInfoException {
    // Byte code:
    //   0: ldc com/microsoft/appcenter/utils/DeviceInfoHelper
    //   2: monitorenter
    //   3: new com/microsoft/appcenter/ingestion/models/Device
    //   6: astore_1
    //   7: aload_1
    //   8: invokespecial <init> : ()V
    //   11: aload_0
    //   12: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   15: aload_0
    //   16: invokevirtual getPackageName : ()Ljava/lang/String;
    //   19: iconst_0
    //   20: invokevirtual getPackageInfo : (Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   23: astore_2
    //   24: aload_1
    //   25: aload_2
    //   26: getfield versionName : Ljava/lang/String;
    //   29: invokevirtual setAppVersion : (Ljava/lang/String;)V
    //   32: aload_1
    //   33: aload_2
    //   34: getfield versionCode : I
    //   37: invokestatic valueOf : (I)Ljava/lang/String;
    //   40: invokevirtual setAppBuild : (Ljava/lang/String;)V
    //   43: aload_1
    //   44: aload_0
    //   45: invokevirtual getPackageName : ()Ljava/lang/String;
    //   48: invokevirtual setAppNamespace : (Ljava/lang/String;)V
    //   51: aload_0
    //   52: ldc 'phone'
    //   54: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   57: checkcast android/telephony/TelephonyManager
    //   60: astore_2
    //   61: aload_2
    //   62: invokevirtual getNetworkCountryIso : ()Ljava/lang/String;
    //   65: astore_3
    //   66: aload_3
    //   67: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   70: ifne -> 78
    //   73: aload_1
    //   74: aload_3
    //   75: invokevirtual setCarrierCountry : (Ljava/lang/String;)V
    //   78: aload_2
    //   79: invokevirtual getNetworkOperatorName : ()Ljava/lang/String;
    //   82: astore_2
    //   83: aload_2
    //   84: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   87: ifne -> 107
    //   90: aload_1
    //   91: aload_2
    //   92: invokevirtual setCarrierName : (Ljava/lang/String;)V
    //   95: goto -> 107
    //   98: astore_2
    //   99: ldc 'AppCenter'
    //   101: ldc 'Cannot retrieve carrier info'
    //   103: aload_2
    //   104: invokestatic error : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   107: aload_1
    //   108: invokestatic getDefault : ()Ljava/util/Locale;
    //   111: invokevirtual toString : ()Ljava/lang/String;
    //   114: invokevirtual setLocale : (Ljava/lang/String;)V
    //   117: aload_1
    //   118: getstatic android/os/Build.MODEL : Ljava/lang/String;
    //   121: invokevirtual setModel : (Ljava/lang/String;)V
    //   124: aload_1
    //   125: getstatic android/os/Build.MANUFACTURER : Ljava/lang/String;
    //   128: invokevirtual setOemName : (Ljava/lang/String;)V
    //   131: aload_1
    //   132: getstatic android/os/Build$VERSION.SDK_INT : I
    //   135: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   138: invokevirtual setOsApiLevel : (Ljava/lang/Integer;)V
    //   141: aload_1
    //   142: ldc 'Android'
    //   144: invokevirtual setOsName : (Ljava/lang/String;)V
    //   147: aload_1
    //   148: getstatic android/os/Build$VERSION.RELEASE : Ljava/lang/String;
    //   151: invokevirtual setOsVersion : (Ljava/lang/String;)V
    //   154: aload_1
    //   155: getstatic android/os/Build.ID : Ljava/lang/String;
    //   158: invokevirtual setOsBuild : (Ljava/lang/String;)V
    //   161: aload_1
    //   162: aload_0
    //   163: invokestatic getScreenSize : (Landroid/content/Context;)Ljava/lang/String;
    //   166: invokevirtual setScreenSize : (Ljava/lang/String;)V
    //   169: goto -> 181
    //   172: astore_0
    //   173: ldc 'AppCenter'
    //   175: ldc 'Cannot retrieve screen size'
    //   177: aload_0
    //   178: invokestatic error : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   181: aload_1
    //   182: ldc 'appcenter.android'
    //   184: invokevirtual setSdkName : (Ljava/lang/String;)V
    //   187: aload_1
    //   188: ldc '1.0.0'
    //   190: invokevirtual setSdkVersion : (Ljava/lang/String;)V
    //   193: aload_1
    //   194: invokestatic getDefault : ()Ljava/util/TimeZone;
    //   197: invokestatic currentTimeMillis : ()J
    //   200: invokevirtual getOffset : (J)I
    //   203: bipush #60
    //   205: idiv
    //   206: sipush #1000
    //   209: idiv
    //   210: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   213: invokevirtual setTimeZoneOffset : (Ljava/lang/Integer;)V
    //   216: getstatic com/microsoft/appcenter/utils/DeviceInfoHelper.sWrapperSdk : Lcom/microsoft/appcenter/ingestion/models/WrapperSdk;
    //   219: astore_0
    //   220: aload_0
    //   221: ifnull -> 282
    //   224: aload_1
    //   225: aload_0
    //   226: invokevirtual getWrapperSdkVersion : ()Ljava/lang/String;
    //   229: invokevirtual setWrapperSdkVersion : (Ljava/lang/String;)V
    //   232: aload_1
    //   233: getstatic com/microsoft/appcenter/utils/DeviceInfoHelper.sWrapperSdk : Lcom/microsoft/appcenter/ingestion/models/WrapperSdk;
    //   236: invokevirtual getWrapperSdkName : ()Ljava/lang/String;
    //   239: invokevirtual setWrapperSdkName : (Ljava/lang/String;)V
    //   242: aload_1
    //   243: getstatic com/microsoft/appcenter/utils/DeviceInfoHelper.sWrapperSdk : Lcom/microsoft/appcenter/ingestion/models/WrapperSdk;
    //   246: invokevirtual getWrapperRuntimeVersion : ()Ljava/lang/String;
    //   249: invokevirtual setWrapperRuntimeVersion : (Ljava/lang/String;)V
    //   252: aload_1
    //   253: getstatic com/microsoft/appcenter/utils/DeviceInfoHelper.sWrapperSdk : Lcom/microsoft/appcenter/ingestion/models/WrapperSdk;
    //   256: invokevirtual getLiveUpdateReleaseLabel : ()Ljava/lang/String;
    //   259: invokevirtual setLiveUpdateReleaseLabel : (Ljava/lang/String;)V
    //   262: aload_1
    //   263: getstatic com/microsoft/appcenter/utils/DeviceInfoHelper.sWrapperSdk : Lcom/microsoft/appcenter/ingestion/models/WrapperSdk;
    //   266: invokevirtual getLiveUpdateDeploymentKey : ()Ljava/lang/String;
    //   269: invokevirtual setLiveUpdateDeploymentKey : (Ljava/lang/String;)V
    //   272: aload_1
    //   273: getstatic com/microsoft/appcenter/utils/DeviceInfoHelper.sWrapperSdk : Lcom/microsoft/appcenter/ingestion/models/WrapperSdk;
    //   276: invokevirtual getLiveUpdatePackageHash : ()Ljava/lang/String;
    //   279: invokevirtual setLiveUpdatePackageHash : (Ljava/lang/String;)V
    //   282: ldc com/microsoft/appcenter/utils/DeviceInfoHelper
    //   284: monitorexit
    //   285: aload_1
    //   286: areturn
    //   287: astore_1
    //   288: ldc 'AppCenter'
    //   290: ldc 'Cannot retrieve package info'
    //   292: aload_1
    //   293: invokestatic error : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   296: new com/microsoft/appcenter/utils/DeviceInfoHelper$DeviceInfoException
    //   299: astore_0
    //   300: aload_0
    //   301: ldc 'Cannot retrieve package info'
    //   303: aload_1
    //   304: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   307: aload_0
    //   308: athrow
    //   309: astore_0
    //   310: ldc com/microsoft/appcenter/utils/DeviceInfoHelper
    //   312: monitorexit
    //   313: aload_0
    //   314: athrow
    // Exception table:
    //   from	to	target	type
    //   3	11	309	finally
    //   11	43	287	java/lang/Exception
    //   11	43	309	finally
    //   43	51	309	finally
    //   51	78	98	java/lang/Exception
    //   51	78	309	finally
    //   78	95	98	java/lang/Exception
    //   78	95	309	finally
    //   99	107	309	finally
    //   107	161	309	finally
    //   161	169	172	java/lang/Exception
    //   161	169	309	finally
    //   173	181	309	finally
    //   181	220	309	finally
    //   224	282	309	finally
    //   288	309	309	finally
  }
  
  @SuppressLint({"SwitchIntDef"})
  private static String getScreenSize(Context paramContext) {
    int j;
    Display display = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    Point point = new Point();
    display.getSize(point);
    int i = display.getRotation();
    if (i != 1 && i != 3) {
      j = point.x;
      i = point.y;
    } else {
      i = point.x;
      j = point.y;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(j);
    stringBuilder.append("x");
    stringBuilder.append(i);
    return stringBuilder.toString();
  }
  
  public static void setWrapperSdk(WrapperSdk paramWrapperSdk) {
    // Byte code:
    //   0: ldc com/microsoft/appcenter/utils/DeviceInfoHelper
    //   2: monitorenter
    //   3: aload_0
    //   4: putstatic com/microsoft/appcenter/utils/DeviceInfoHelper.sWrapperSdk : Lcom/microsoft/appcenter/ingestion/models/WrapperSdk;
    //   7: ldc com/microsoft/appcenter/utils/DeviceInfoHelper
    //   9: monitorexit
    //   10: return
    //   11: astore_0
    //   12: ldc com/microsoft/appcenter/utils/DeviceInfoHelper
    //   14: monitorexit
    //   15: aload_0
    //   16: athrow
    // Exception table:
    //   from	to	target	type
    //   3	7	11	finally
  }
  
  public static class DeviceInfoException extends Exception {
    public DeviceInfoException(String param1String, Throwable param1Throwable) {
      super(param1String, param1Throwable);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/DeviceInfoHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */