package com.google.android.gms.internal;

import java.io.IOException;

public class zzevi<MessageType extends zzevh<MessageType, BuilderType>, BuilderType extends zzevi<MessageType, BuilderType>> extends zzeud<MessageType, BuilderType> {
  private final MessageType zzoog;
  
  protected MessageType zzooh;
  
  private boolean zzooi;
  
  protected zzevi(MessageType paramMessageType) {
    this.zzoog = paramMessageType;
    this.zzooh = (MessageType)paramMessageType.zza(zzevp.zzoos, (Object)null, (Object)null);
    this.zzooi = false;
  }
  
  private static void zza(MessageType paramMessageType1, MessageType paramMessageType2) {
    zzevo zzevo = zzevo.zzoon;
    paramMessageType1.zza(zzevp.zzoop, zzevo, paramMessageType2);
    ((zzevh)paramMessageType1).zzooe = zzevo.zza(((zzevh)paramMessageType1).zzooe, ((zzevh)paramMessageType2).zzooe);
  }
  
  private final BuilderType zzd(zzeut paramzzeut, zzevd paramzzevd) throws IOException {
    zzcud();
    try {
      this.zzooh.zza(zzevp.zzooq, paramzzeut, paramzzevd);
      return (BuilderType)this;
    } catch (RuntimeException runtimeException) {
      if (runtimeException.getCause() instanceof IOException)
        throw (IOException)runtimeException.getCause(); 
      throw runtimeException;
    } 
  }
  
  public final boolean isInitialized() {
    return (this.zzooh.zza(zzevp.zzooo, Boolean.FALSE, (Object)null) != null);
  }
  
  public final BuilderType zza(MessageType paramMessageType) {
    zzcud();
    zza(this.zzooh, paramMessageType);
    return (BuilderType)this;
  }
  
  protected final void zzcud() {
    if (this.zzooi) {
      zzevh zzevh1 = (zzevh)this.zzooh.zza(zzevp.zzoos, (Object)null, (Object)null);
      zza((MessageType)zzevh1, this.zzooh);
      this.zzooh = (MessageType)zzevh1;
      this.zzooi = false;
    } 
  }
  
  public final MessageType zzcue() {
    if (this.zzooi)
      return this.zzooh; 
    MessageType messageType = this.zzooh;
    messageType.zza(zzevp.zzoor, (Object)null, (Object)null);
    ((zzevh)messageType).zzooe.zzbhs();
    this.zzooi = true;
    return this.zzooh;
  }
  
  public final MessageType zzcuf() {
    boolean bool = this.zzooi;
    boolean bool1 = true;
    if (!bool) {
      MessageType messageType1 = this.zzooh;
      messageType1.zza(zzevp.zzoor, (Object)null, (Object)null);
      ((zzevh)messageType1).zzooe.zzbhs();
      this.zzooi = true;
    } 
    MessageType messageType = this.zzooh;
    if (messageType.zza(zzevp.zzooo, Boolean.TRUE, (Object)null) == null)
      bool1 = false; 
    if (bool1)
      return messageType; 
    throw new zzexk(messageType);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzevi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */