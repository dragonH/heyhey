package android.support.v4.net;

import android.net.TrafficStats;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

public final class TrafficStatsCompat {
  private static final TrafficStatsCompatBaseImpl IMPL;
  
  static {
    if (Build.VERSION.SDK_INT >= 24) {
      IMPL = new TrafficStatsCompatApi24Impl();
    } else {
      IMPL = new TrafficStatsCompatBaseImpl();
    } 
  }
  
  @Deprecated
  public static void clearThreadStatsTag() {
    TrafficStats.clearThreadStatsTag();
  }
  
  @Deprecated
  public static int getThreadStatsTag() {
    return TrafficStats.getThreadStatsTag();
  }
  
  @Deprecated
  public static void incrementOperationCount(int paramInt) {
    TrafficStats.incrementOperationCount(paramInt);
  }
  
  @Deprecated
  public static void incrementOperationCount(int paramInt1, int paramInt2) {
    TrafficStats.incrementOperationCount(paramInt1, paramInt2);
  }
  
  @Deprecated
  public static void setThreadStatsTag(int paramInt) {
    TrafficStats.setThreadStatsTag(paramInt);
  }
  
  public static void tagDatagramSocket(DatagramSocket paramDatagramSocket) throws SocketException {
    IMPL.tagDatagramSocket(paramDatagramSocket);
  }
  
  @Deprecated
  public static void tagSocket(Socket paramSocket) throws SocketException {
    TrafficStats.tagSocket(paramSocket);
  }
  
  public static void untagDatagramSocket(DatagramSocket paramDatagramSocket) throws SocketException {
    IMPL.untagDatagramSocket(paramDatagramSocket);
  }
  
  @Deprecated
  public static void untagSocket(Socket paramSocket) throws SocketException {
    TrafficStats.untagSocket(paramSocket);
  }
  
  @RequiresApi(24)
  static class TrafficStatsCompatApi24Impl extends TrafficStatsCompatBaseImpl {
    public void tagDatagramSocket(DatagramSocket param1DatagramSocket) throws SocketException {
      TrafficStats.tagDatagramSocket(param1DatagramSocket);
    }
    
    public void untagDatagramSocket(DatagramSocket param1DatagramSocket) throws SocketException {
      TrafficStats.untagDatagramSocket(param1DatagramSocket);
    }
  }
  
  static class TrafficStatsCompatBaseImpl {
    public void tagDatagramSocket(DatagramSocket param1DatagramSocket) throws SocketException {
      ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.fromDatagramSocket(param1DatagramSocket);
      TrafficStats.tagSocket(new DatagramSocketWrapper(param1DatagramSocket, parcelFileDescriptor.getFileDescriptor()));
      parcelFileDescriptor.detachFd();
    }
    
    public void untagDatagramSocket(DatagramSocket param1DatagramSocket) throws SocketException {
      ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.fromDatagramSocket(param1DatagramSocket);
      TrafficStats.untagSocket(new DatagramSocketWrapper(param1DatagramSocket, parcelFileDescriptor.getFileDescriptor()));
      parcelFileDescriptor.detachFd();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/net/TrafficStatsCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */