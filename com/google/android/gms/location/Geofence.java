package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.internal.zzcac;

public interface Geofence {
  public static final int GEOFENCE_TRANSITION_DWELL = 4;
  
  public static final int GEOFENCE_TRANSITION_ENTER = 1;
  
  public static final int GEOFENCE_TRANSITION_EXIT = 2;
  
  public static final long NEVER_EXPIRE = -1L;
  
  String getRequestId();
  
  public static final class Builder {
    private String zzcjw = null;
    
    private int zzhxf = 0;
    
    private long zzhxg = Long.MIN_VALUE;
    
    private short zzhxh = (short)-1;
    
    private double zzhxi;
    
    private double zzhxj;
    
    private float zzhxk;
    
    private int zzhxl = 0;
    
    private int zzhxm = -1;
    
    public final Geofence build() {
      if (this.zzcjw != null) {
        int i = this.zzhxf;
        if (i != 0) {
          if ((i & 0x4) == 0 || this.zzhxm >= 0) {
            if (this.zzhxg != Long.MIN_VALUE) {
              if (this.zzhxh != -1) {
                if (this.zzhxl >= 0)
                  return (Geofence)new zzcac(this.zzcjw, this.zzhxf, (short)1, this.zzhxi, this.zzhxj, this.zzhxk, this.zzhxg, this.zzhxl, this.zzhxm); 
                throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
              } 
              throw new IllegalArgumentException("Geofence region not set.");
            } 
            throw new IllegalArgumentException("Expiration not set.");
          } 
          throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
        } 
        throw new IllegalArgumentException("Transitions types not set.");
      } 
      throw new IllegalArgumentException("Request ID not set.");
    }
    
    public final Builder setCircularRegion(double param1Double1, double param1Double2, float param1Float) {
      this.zzhxh = (short)1;
      this.zzhxi = param1Double1;
      this.zzhxj = param1Double2;
      this.zzhxk = param1Float;
      return this;
    }
    
    public final Builder setExpirationDuration(long param1Long) {
      if (param1Long < 0L) {
        this.zzhxg = -1L;
      } else {
        this.zzhxg = SystemClock.elapsedRealtime() + param1Long;
      } 
      return this;
    }
    
    public final Builder setLoiteringDelay(int param1Int) {
      this.zzhxm = param1Int;
      return this;
    }
    
    public final Builder setNotificationResponsiveness(int param1Int) {
      this.zzhxl = param1Int;
      return this;
    }
    
    public final Builder setRequestId(String param1String) {
      this.zzcjw = param1String;
      return this;
    }
    
    public final Builder setTransitionTypes(int param1Int) {
      this.zzhxf = param1Int;
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/Geofence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */