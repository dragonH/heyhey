package com.google.android.gms.internal;

public enum zzexu {
  zzory, zzorz, zzosa, zzosb, zzosc, zzosd, zzose, zzosf, zzosg, zzosh, zzosi, zzosj, zzosk, zzosl, zzosm, zzosn, zzoso, zzosp;
  
  private final zzexz zzosq;
  
  private final int zzosr;
  
  static {
    zzexu zzexu1 = new zzexu("DOUBLE", 0, zzexz.zzosw, 1);
    zzory = zzexu1;
    zzexu zzexu2 = new zzexu("FLOAT", 1, zzexz.zzosv, 5);
    zzorz = zzexu2;
    zzexz zzexz1 = zzexz.zzosu;
    zzexu zzexu4 = new zzexu("INT64", 2, zzexz1, 0);
    zzosa = zzexu4;
    zzexu zzexu5 = new zzexu("UINT64", 3, zzexz1, 0);
    zzosb = zzexu5;
    zzexz zzexz2 = zzexz.zzost;
    zzexu zzexu7 = new zzexu("INT32", 4, zzexz2, 0);
    zzosc = zzexu7;
    zzexu zzexu8 = new zzexu("FIXED64", 5, zzexz1, 1);
    zzosd = zzexu8;
    zzexu zzexu9 = new zzexu("FIXED32", 6, zzexz2, 5);
    zzose = zzexu9;
    zzexu zzexu10 = new zzexu("BOOL", 7, zzexz.zzosx, 0);
    zzosf = zzexu10;
    zzexv zzexv = new zzexv("STRING", 8, zzexz.zzosy, 2);
    zzosg = zzexv;
    zzexz zzexz3 = zzexz.zzotb;
    zzexw zzexw = new zzexw("GROUP", 9, zzexz3, 3);
    zzosh = zzexw;
    zzexx zzexx = new zzexx("MESSAGE", 10, zzexz3, 2);
    zzosi = zzexx;
    zzexy zzexy = new zzexy("BYTES", 11, zzexz.zzosz, 2);
    zzosj = zzexy;
    zzexu zzexu11 = new zzexu("UINT32", 12, zzexz2, 0);
    zzosk = zzexu11;
    zzexu zzexu12 = new zzexu("ENUM", 13, zzexz.zzota, 0);
    zzosl = zzexu12;
    zzexu zzexu13 = new zzexu("SFIXED32", 14, zzexz2, 5);
    zzosm = zzexu13;
    zzexu zzexu14 = new zzexu("SFIXED64", 15, zzexz1, 1);
    zzosn = zzexu14;
    zzexu zzexu6 = new zzexu("SINT32", 16, zzexz2, 0);
    zzoso = zzexu6;
    zzexu zzexu3 = new zzexu("SINT64", 17, zzexz1, 0);
    zzosp = zzexu3;
    zzoss = new zzexu[] { 
        zzexu1, zzexu2, zzexu4, zzexu5, zzexu7, zzexu8, zzexu9, zzexu10, zzexv, zzexw, 
        zzexx, zzexy, zzexu11, zzexu12, zzexu13, zzexu14, zzexu6, zzexu3 };
  }
  
  zzexu(zzexz paramzzexz, int paramInt1) {
    this.zzosq = paramzzexz;
    this.zzosr = paramInt1;
  }
  
  public final zzexz zzcvx() {
    return this.zzosq;
  }
  
  public final int zzcvy() {
    return this.zzosr;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzexu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */