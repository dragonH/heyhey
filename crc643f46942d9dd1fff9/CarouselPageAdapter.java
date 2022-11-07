package crc643f46942d9dd1fff9;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class CarouselPageAdapter extends PagerAdapter implements IGCUserPeer, ViewPager.OnPageChangeListener {
  public static final String __md_methods = "n_getCount:()I:GetGetCountHandler\nn_destroyItem:(Landroid/view/ViewGroup;ILjava/lang/Object;)V:GetDestroyItem_Landroid_view_ViewGroup_ILjava_lang_Object_Handler\nn_getItemPosition:(Ljava/lang/Object;)I:GetGetItemPosition_Ljava_lang_Object_Handler\nn_instantiateItem:(Landroid/view/ViewGroup;I)Ljava/lang/Object;:GetInstantiateItem_Landroid_view_ViewGroup_IHandler\nn_isViewFromObject:(Landroid/view/View;Ljava/lang/Object;)Z:GetIsViewFromObject_Landroid_view_View_Ljava_lang_Object_Handler\nn_onPageScrollStateChanged:(I)V:GetOnPageScrollStateChanged_IHandler:Android.Support.V4.View.ViewPager/IOnPageChangeListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onPageScrolled:(IFI)V:GetOnPageScrolled_IFIHandler:Android.Support.V4.View.ViewPager/IOnPageChangeListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onPageSelected:(I)V:GetOnPageSelected_IHandler:Android.Support.V4.View.ViewPager/IOnPageChangeListenerInvoker, Xamarin.Android.Support.Core.UI\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.CarouselPageAdapter, Xamarin.Forms.Platform.Android", CarouselPageAdapter.class, "n_getCount:()I:GetGetCountHandler\nn_destroyItem:(Landroid/view/ViewGroup;ILjava/lang/Object;)V:GetDestroyItem_Landroid_view_ViewGroup_ILjava_lang_Object_Handler\nn_getItemPosition:(Ljava/lang/Object;)I:GetGetItemPosition_Ljava_lang_Object_Handler\nn_instantiateItem:(Landroid/view/ViewGroup;I)Ljava/lang/Object;:GetInstantiateItem_Landroid_view_ViewGroup_IHandler\nn_isViewFromObject:(Landroid/view/View;Ljava/lang/Object;)Z:GetIsViewFromObject_Landroid_view_View_Ljava_lang_Object_Handler\nn_onPageScrollStateChanged:(I)V:GetOnPageScrollStateChanged_IHandler:Android.Support.V4.View.ViewPager/IOnPageChangeListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onPageScrolled:(IFI)V:GetOnPageScrolled_IFIHandler:Android.Support.V4.View.ViewPager/IOnPageChangeListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onPageSelected:(I)V:GetOnPageSelected_IHandler:Android.Support.V4.View.ViewPager/IOnPageChangeListenerInvoker, Xamarin.Android.Support.Core.UI\n");
  }
  
  public CarouselPageAdapter() {
    if (getClass() == CarouselPageAdapter.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.CarouselPageAdapter, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
  }
  
  private native void n_destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject);
  
  private native int n_getCount();
  
  private native int n_getItemPosition(Object paramObject);
  
  private native Object n_instantiateItem(ViewGroup paramViewGroup, int paramInt);
  
  private native boolean n_isViewFromObject(View paramView, Object paramObject);
  
  private native void n_onPageScrollStateChanged(int paramInt);
  
  private native void n_onPageScrolled(int paramInt1, float paramFloat, int paramInt2);
  
  private native void n_onPageSelected(int paramInt);
  
  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject) {
    n_destroyItem(paramViewGroup, paramInt, paramObject);
  }
  
  public int getCount() {
    return n_getCount();
  }
  
  public int getItemPosition(Object paramObject) {
    return n_getItemPosition(paramObject);
  }
  
  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt) {
    return n_instantiateItem(paramViewGroup, paramInt);
  }
  
  public boolean isViewFromObject(View paramView, Object paramObject) {
    return n_isViewFromObject(paramView, paramObject);
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
  
  public void onPageScrollStateChanged(int paramInt) {
    n_onPageScrollStateChanged(paramInt);
  }
  
  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
    n_onPageScrolled(paramInt1, paramFloat, paramInt2);
  }
  
  public void onPageSelected(int paramInt) {
    n_onPageSelected(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/CarouselPageAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */