package crc64720bb2db43a66fe9;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FormsFragmentPagerAdapter_1 extends FragmentPagerAdapter implements IGCUserPeer {
  public static final String __md_methods = "n_getCount:()I:GetGetCountHandler\nn_getItem:(I)Landroid/support/v4/app/Fragment;:GetGetItem_IHandler\nn_getItemId:(I)J:GetGetItemId_IHandler\nn_getItemPosition:(Ljava/lang/Object;)I:GetGetItemPosition_Ljava_lang_Object_Handler\nn_getPageTitle:(I)Ljava/lang/CharSequence;:GetGetPageTitle_IHandler\nn_restoreState:(Landroid/os/Parcelable;Ljava/lang/ClassLoader;)V:GetRestoreState_Landroid_os_Parcelable_Ljava_lang_ClassLoader_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.AppCompat.FormsFragmentPagerAdapter`1, Xamarin.Forms.Platform.Android", FormsFragmentPagerAdapter_1.class, "n_getCount:()I:GetGetCountHandler\nn_getItem:(I)Landroid/support/v4/app/Fragment;:GetGetItem_IHandler\nn_getItemId:(I)J:GetGetItemId_IHandler\nn_getItemPosition:(Ljava/lang/Object;)I:GetGetItemPosition_Ljava_lang_Object_Handler\nn_getPageTitle:(I)Ljava/lang/CharSequence;:GetGetPageTitle_IHandler\nn_restoreState:(Landroid/os/Parcelable;Ljava/lang/ClassLoader;)V:GetRestoreState_Landroid_os_Parcelable_Ljava_lang_ClassLoader_Handler\n");
  }
  
  public FormsFragmentPagerAdapter_1(FragmentManager paramFragmentManager) {
    super(paramFragmentManager);
    if (getClass() == FormsFragmentPagerAdapter_1.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.AppCompat.FormsFragmentPagerAdapter`1, Xamarin.Forms.Platform.Android", "Android.Support.V4.App.FragmentManager, Xamarin.Android.Support.Fragment", this, new Object[] { paramFragmentManager }); 
  }
  
  private native int n_getCount();
  
  private native Fragment n_getItem(int paramInt);
  
  private native long n_getItemId(int paramInt);
  
  private native int n_getItemPosition(Object paramObject);
  
  private native CharSequence n_getPageTitle(int paramInt);
  
  private native void n_restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader);
  
  public int getCount() {
    return n_getCount();
  }
  
  public Fragment getItem(int paramInt) {
    return n_getItem(paramInt);
  }
  
  public long getItemId(int paramInt) {
    return n_getItemId(paramInt);
  }
  
  public int getItemPosition(Object paramObject) {
    return n_getItemPosition(paramObject);
  }
  
  public CharSequence getPageTitle(int paramInt) {
    return n_getPageTitle(paramInt);
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
  
  public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader) {
    n_restoreState(paramParcelable, paramClassLoader);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64720bb2db43a66fe9/FormsFragmentPagerAdapter_1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */