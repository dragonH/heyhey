package android.support.v7.graphics;

import android.support.annotation.FloatRange;

public final class Target {
  public static final Target DARK_MUTED;
  
  public static final Target DARK_VIBRANT;
  
  static final int INDEX_MAX = 2;
  
  static final int INDEX_MIN = 0;
  
  static final int INDEX_TARGET = 1;
  
  static final int INDEX_WEIGHT_LUMA = 1;
  
  static final int INDEX_WEIGHT_POP = 2;
  
  static final int INDEX_WEIGHT_SAT = 0;
  
  public static final Target LIGHT_MUTED;
  
  public static final Target LIGHT_VIBRANT;
  
  private static final float MAX_DARK_LUMA = 0.45F;
  
  private static final float MAX_MUTED_SATURATION = 0.4F;
  
  private static final float MAX_NORMAL_LUMA = 0.7F;
  
  private static final float MIN_LIGHT_LUMA = 0.55F;
  
  private static final float MIN_NORMAL_LUMA = 0.3F;
  
  private static final float MIN_VIBRANT_SATURATION = 0.35F;
  
  public static final Target MUTED;
  
  private static final float TARGET_DARK_LUMA = 0.26F;
  
  private static final float TARGET_LIGHT_LUMA = 0.74F;
  
  private static final float TARGET_MUTED_SATURATION = 0.3F;
  
  private static final float TARGET_NORMAL_LUMA = 0.5F;
  
  private static final float TARGET_VIBRANT_SATURATION = 1.0F;
  
  public static final Target VIBRANT;
  
  private static final float WEIGHT_LUMA = 0.52F;
  
  private static final float WEIGHT_POPULATION = 0.24F;
  
  private static final float WEIGHT_SATURATION = 0.24F;
  
  boolean mIsExclusive;
  
  final float[] mLightnessTargets;
  
  final float[] mSaturationTargets;
  
  final float[] mWeights;
  
  static {
    Target target = new Target();
    LIGHT_VIBRANT = target;
    setDefaultLightLightnessValues(target);
    setDefaultVibrantSaturationValues(target);
    target = new Target();
    VIBRANT = target;
    setDefaultNormalLightnessValues(target);
    setDefaultVibrantSaturationValues(target);
    target = new Target();
    DARK_VIBRANT = target;
    setDefaultDarkLightnessValues(target);
    setDefaultVibrantSaturationValues(target);
    target = new Target();
    LIGHT_MUTED = target;
    setDefaultLightLightnessValues(target);
    setDefaultMutedSaturationValues(target);
    target = new Target();
    MUTED = target;
    setDefaultNormalLightnessValues(target);
    setDefaultMutedSaturationValues(target);
    target = new Target();
    DARK_MUTED = target;
    setDefaultDarkLightnessValues(target);
    setDefaultMutedSaturationValues(target);
  }
  
  Target() {
    float[] arrayOfFloat1 = new float[3];
    this.mSaturationTargets = arrayOfFloat1;
    float[] arrayOfFloat2 = new float[3];
    this.mLightnessTargets = arrayOfFloat2;
    this.mWeights = new float[3];
    this.mIsExclusive = true;
    setTargetDefaultValues(arrayOfFloat1);
    setTargetDefaultValues(arrayOfFloat2);
    setDefaultWeights();
  }
  
  Target(Target paramTarget) {
    float[] arrayOfFloat1 = new float[3];
    this.mSaturationTargets = arrayOfFloat1;
    float[] arrayOfFloat2 = new float[3];
    this.mLightnessTargets = arrayOfFloat2;
    float[] arrayOfFloat3 = new float[3];
    this.mWeights = arrayOfFloat3;
    this.mIsExclusive = true;
    System.arraycopy(paramTarget.mSaturationTargets, 0, arrayOfFloat1, 0, arrayOfFloat1.length);
    System.arraycopy(paramTarget.mLightnessTargets, 0, arrayOfFloat2, 0, arrayOfFloat2.length);
    System.arraycopy(paramTarget.mWeights, 0, arrayOfFloat3, 0, arrayOfFloat3.length);
  }
  
  private static void setDefaultDarkLightnessValues(Target paramTarget) {
    float[] arrayOfFloat = paramTarget.mLightnessTargets;
    arrayOfFloat[1] = 0.26F;
    arrayOfFloat[2] = 0.45F;
  }
  
  private static void setDefaultLightLightnessValues(Target paramTarget) {
    float[] arrayOfFloat = paramTarget.mLightnessTargets;
    arrayOfFloat[0] = 0.55F;
    arrayOfFloat[1] = 0.74F;
  }
  
  private static void setDefaultMutedSaturationValues(Target paramTarget) {
    float[] arrayOfFloat = paramTarget.mSaturationTargets;
    arrayOfFloat[1] = 0.3F;
    arrayOfFloat[2] = 0.4F;
  }
  
  private static void setDefaultNormalLightnessValues(Target paramTarget) {
    float[] arrayOfFloat = paramTarget.mLightnessTargets;
    arrayOfFloat[0] = 0.3F;
    arrayOfFloat[1] = 0.5F;
    arrayOfFloat[2] = 0.7F;
  }
  
  private static void setDefaultVibrantSaturationValues(Target paramTarget) {
    float[] arrayOfFloat = paramTarget.mSaturationTargets;
    arrayOfFloat[0] = 0.35F;
    arrayOfFloat[1] = 1.0F;
  }
  
  private void setDefaultWeights() {
    float[] arrayOfFloat = this.mWeights;
    arrayOfFloat[0] = 0.24F;
    arrayOfFloat[1] = 0.52F;
    arrayOfFloat[2] = 0.24F;
  }
  
  private static void setTargetDefaultValues(float[] paramArrayOffloat) {
    paramArrayOffloat[0] = 0.0F;
    paramArrayOffloat[1] = 0.5F;
    paramArrayOffloat[2] = 1.0F;
  }
  
  public float getLightnessWeight() {
    return this.mWeights[1];
  }
  
  @FloatRange(from = 0.0D, to = 1.0D)
  public float getMaximumLightness() {
    return this.mLightnessTargets[2];
  }
  
  @FloatRange(from = 0.0D, to = 1.0D)
  public float getMaximumSaturation() {
    return this.mSaturationTargets[2];
  }
  
  @FloatRange(from = 0.0D, to = 1.0D)
  public float getMinimumLightness() {
    return this.mLightnessTargets[0];
  }
  
  @FloatRange(from = 0.0D, to = 1.0D)
  public float getMinimumSaturation() {
    return this.mSaturationTargets[0];
  }
  
  public float getPopulationWeight() {
    return this.mWeights[2];
  }
  
  public float getSaturationWeight() {
    return this.mWeights[0];
  }
  
  @FloatRange(from = 0.0D, to = 1.0D)
  public float getTargetLightness() {
    return this.mLightnessTargets[1];
  }
  
  @FloatRange(from = 0.0D, to = 1.0D)
  public float getTargetSaturation() {
    return this.mSaturationTargets[1];
  }
  
  public boolean isExclusive() {
    return this.mIsExclusive;
  }
  
  void normalizeWeights() {
    int i = this.mWeights.length;
    boolean bool = false;
    byte b = 0;
    float f;
    for (f = 0.0F; b < i; f = f2) {
      float f1 = this.mWeights[b];
      float f2 = f;
      if (f1 > 0.0F)
        f2 = f + f1; 
      b++;
    } 
    if (f != 0.0F) {
      i = this.mWeights.length;
      for (b = bool; b < i; b++) {
        float[] arrayOfFloat = this.mWeights;
        float f1 = arrayOfFloat[b];
        if (f1 > 0.0F)
          arrayOfFloat[b] = f1 / f; 
      } 
    } 
  }
  
  public static final class Builder {
    private final Target mTarget = new Target();
    
    public Builder() {}
    
    public Builder(Target param1Target) {}
    
    public Target build() {
      return this.mTarget;
    }
    
    public Builder setExclusive(boolean param1Boolean) {
      this.mTarget.mIsExclusive = param1Boolean;
      return this;
    }
    
    public Builder setLightnessWeight(@FloatRange(from = 0.0D) float param1Float) {
      this.mTarget.mWeights[1] = param1Float;
      return this;
    }
    
    public Builder setMaximumLightness(@FloatRange(from = 0.0D, to = 1.0D) float param1Float) {
      this.mTarget.mLightnessTargets[2] = param1Float;
      return this;
    }
    
    public Builder setMaximumSaturation(@FloatRange(from = 0.0D, to = 1.0D) float param1Float) {
      this.mTarget.mSaturationTargets[2] = param1Float;
      return this;
    }
    
    public Builder setMinimumLightness(@FloatRange(from = 0.0D, to = 1.0D) float param1Float) {
      this.mTarget.mLightnessTargets[0] = param1Float;
      return this;
    }
    
    public Builder setMinimumSaturation(@FloatRange(from = 0.0D, to = 1.0D) float param1Float) {
      this.mTarget.mSaturationTargets[0] = param1Float;
      return this;
    }
    
    public Builder setPopulationWeight(@FloatRange(from = 0.0D) float param1Float) {
      this.mTarget.mWeights[2] = param1Float;
      return this;
    }
    
    public Builder setSaturationWeight(@FloatRange(from = 0.0D) float param1Float) {
      this.mTarget.mWeights[0] = param1Float;
      return this;
    }
    
    public Builder setTargetLightness(@FloatRange(from = 0.0D, to = 1.0D) float param1Float) {
      this.mTarget.mLightnessTargets[1] = param1Float;
      return this;
    }
    
    public Builder setTargetSaturation(@FloatRange(from = 0.0D, to = 1.0D) float param1Float) {
      this.mTarget.mSaturationTargets[1] = param1Float;
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/graphics/Target.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */