package android.support.v4.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.IconCompat;
import android.text.TextUtils;
import java.util.Arrays;

public class ShortcutInfoCompat {
  private ComponentName mActivity;
  
  private Context mContext;
  
  private CharSequence mDisabledMessage;
  
  private IconCompat mIcon;
  
  private String mId;
  
  private Intent[] mIntents;
  
  private CharSequence mLabel;
  
  private CharSequence mLongLabel;
  
  private ShortcutInfoCompat() {}
  
  Intent addToIntent(Intent paramIntent) {
    Intent[] arrayOfIntent = this.mIntents;
    paramIntent.putExtra("android.intent.extra.shortcut.INTENT", (Parcelable)arrayOfIntent[arrayOfIntent.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.mLabel.toString());
    IconCompat iconCompat = this.mIcon;
    if (iconCompat != null)
      iconCompat.addToShortcutIntent(paramIntent); 
    return paramIntent;
  }
  
  @Nullable
  public ComponentName getActivity() {
    return this.mActivity;
  }
  
  @Nullable
  public CharSequence getDisabledMessage() {
    return this.mDisabledMessage;
  }
  
  @NonNull
  public String getId() {
    return this.mId;
  }
  
  @NonNull
  public Intent getIntent() {
    Intent[] arrayOfIntent = this.mIntents;
    return arrayOfIntent[arrayOfIntent.length - 1];
  }
  
  @NonNull
  public Intent[] getIntents() {
    Intent[] arrayOfIntent = this.mIntents;
    return Arrays.<Intent>copyOf(arrayOfIntent, arrayOfIntent.length);
  }
  
  @Nullable
  public CharSequence getLongLabel() {
    return this.mLongLabel;
  }
  
  @NonNull
  public CharSequence getShortLabel() {
    return this.mLabel;
  }
  
  @RequiresApi(26)
  ShortcutInfo toShortcutInfo() {
    ShortcutInfo.Builder builder = (new ShortcutInfo.Builder(this.mContext, this.mId)).setShortLabel(this.mLabel).setIntents(this.mIntents);
    IconCompat iconCompat = this.mIcon;
    if (iconCompat != null)
      builder.setIcon(iconCompat.toIcon()); 
    if (!TextUtils.isEmpty(this.mLongLabel))
      builder.setLongLabel(this.mLongLabel); 
    if (!TextUtils.isEmpty(this.mDisabledMessage))
      builder.setDisabledMessage(this.mDisabledMessage); 
    ComponentName componentName = this.mActivity;
    if (componentName != null)
      builder.setActivity(componentName); 
    return builder.build();
  }
  
  public static class Builder {
    private final ShortcutInfoCompat mInfo;
    
    public Builder(@NonNull Context param1Context, @NonNull String param1String) {
      ShortcutInfoCompat shortcutInfoCompat = new ShortcutInfoCompat();
      this.mInfo = shortcutInfoCompat;
      ShortcutInfoCompat.access$102(shortcutInfoCompat, param1Context);
      ShortcutInfoCompat.access$202(shortcutInfoCompat, param1String);
    }
    
    @NonNull
    public ShortcutInfoCompat build() {
      if (!TextUtils.isEmpty(this.mInfo.mLabel)) {
        if (this.mInfo.mIntents != null && this.mInfo.mIntents.length != 0)
          return this.mInfo; 
        throw new IllegalArgumentException("Shortcut much have an intent");
      } 
      throw new IllegalArgumentException("Shortcut much have a non-empty label");
    }
    
    @NonNull
    public Builder setActivity(@NonNull ComponentName param1ComponentName) {
      ShortcutInfoCompat.access$802(this.mInfo, param1ComponentName);
      return this;
    }
    
    @NonNull
    public Builder setDisabledMessage(@NonNull CharSequence param1CharSequence) {
      ShortcutInfoCompat.access$502(this.mInfo, param1CharSequence);
      return this;
    }
    
    @NonNull
    public Builder setIcon(@DrawableRes int param1Int) {
      return setIcon(IconCompat.createWithResource(this.mInfo.mContext, param1Int));
    }
    
    @NonNull
    public Builder setIcon(@NonNull Bitmap param1Bitmap) {
      return setIcon(IconCompat.createWithBitmap(param1Bitmap));
    }
    
    @NonNull
    public Builder setIcon(IconCompat param1IconCompat) {
      ShortcutInfoCompat.access$702(this.mInfo, param1IconCompat);
      return this;
    }
    
    @NonNull
    public Builder setIntent(@NonNull Intent param1Intent) {
      return setIntents(new Intent[] { param1Intent });
    }
    
    @NonNull
    public Builder setIntents(@NonNull Intent[] param1ArrayOfIntent) {
      ShortcutInfoCompat.access$602(this.mInfo, param1ArrayOfIntent);
      return this;
    }
    
    @NonNull
    public Builder setLongLabel(@NonNull CharSequence param1CharSequence) {
      ShortcutInfoCompat.access$402(this.mInfo, param1CharSequence);
      return this;
    }
    
    @NonNull
    public Builder setShortLabel(@NonNull CharSequence param1CharSequence) {
      ShortcutInfoCompat.access$302(this.mInfo, param1CharSequence);
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/content/pm/ShortcutInfoCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */