package android.support.v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.mediarouter.R;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MediaRouteChooserDialog extends AppCompatDialog {
  static final int MSG_UPDATE_ROUTES = 1;
  
  static final String TAG = "MediaRouteChooserDialog";
  
  private static final long UPDATE_ROUTES_DELAY_MS = 300L;
  
  private RouteAdapter mAdapter;
  
  private boolean mAttachedToWindow;
  
  private final MediaRouterCallback mCallback = new MediaRouterCallback();
  
  private final Handler mHandler = new Handler() {
      public void handleMessage(Message param1Message) {
        if (param1Message.what == 1)
          MediaRouteChooserDialog.this.updateRoutes((List<MediaRouter.RouteInfo>)param1Message.obj); 
      }
    };
  
  private long mLastUpdateTime;
  
  private ListView mListView;
  
  private final MediaRouter mRouter = MediaRouter.getInstance(getContext());
  
  private ArrayList<MediaRouter.RouteInfo> mRoutes;
  
  private MediaRouteSelector mSelector = MediaRouteSelector.EMPTY;
  
  private TextView mTitleView;
  
  public MediaRouteChooserDialog(Context paramContext) {
    this(paramContext, 0);
  }
  
  public MediaRouteChooserDialog(Context paramContext, int paramInt) {
    super(context, i);
  }
  
  @NonNull
  public MediaRouteSelector getRouteSelector() {
    return this.mSelector;
  }
  
  public void onAttachedToWindow() {
    super.onAttachedToWindow();
    this.mAttachedToWindow = true;
    this.mRouter.addCallback(this.mSelector, this.mCallback, 1);
    refreshRoutes();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(R.layout.mr_chooser_dialog);
    this.mRoutes = new ArrayList<MediaRouter.RouteInfo>();
    this.mAdapter = new RouteAdapter(getContext(), this.mRoutes);
    ListView listView = findViewById(R.id.mr_chooser_list);
    this.mListView = listView;
    listView.setAdapter((ListAdapter)this.mAdapter);
    this.mListView.setOnItemClickListener(this.mAdapter);
    this.mListView.setEmptyView(findViewById(16908292));
    this.mTitleView = findViewById(R.id.mr_chooser_title);
    updateLayout();
  }
  
  public void onDetachedFromWindow() {
    this.mAttachedToWindow = false;
    this.mRouter.removeCallback(this.mCallback);
    this.mHandler.removeMessages(1);
    super.onDetachedFromWindow();
  }
  
  public boolean onFilterRoute(@NonNull MediaRouter.RouteInfo paramRouteInfo) {
    boolean bool;
    if (!paramRouteInfo.isDefaultOrBluetooth() && paramRouteInfo.isEnabled() && paramRouteInfo.matchesSelector(this.mSelector)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void onFilterRoutes(@NonNull List<MediaRouter.RouteInfo> paramList) {
    int i = paramList.size();
    while (true) {
      int j = i - 1;
      if (i > 0) {
        if (!onFilterRoute(paramList.get(j)))
          paramList.remove(j); 
        i = j;
        continue;
      } 
      break;
    } 
  }
  
  public void refreshRoutes() {
    if (this.mAttachedToWindow) {
      ArrayList<MediaRouter.RouteInfo> arrayList = new ArrayList(this.mRouter.getRoutes());
      onFilterRoutes(arrayList);
      Collections.sort(arrayList, RouteComparator.sInstance);
      if (SystemClock.uptimeMillis() - this.mLastUpdateTime >= 300L) {
        updateRoutes(arrayList);
      } else {
        this.mHandler.removeMessages(1);
        Handler handler = this.mHandler;
        handler.sendMessageAtTime(handler.obtainMessage(1, arrayList), this.mLastUpdateTime + 300L);
      } 
    } 
  }
  
  public void setRouteSelector(@NonNull MediaRouteSelector paramMediaRouteSelector) {
    if (paramMediaRouteSelector != null) {
      if (!this.mSelector.equals(paramMediaRouteSelector)) {
        this.mSelector = paramMediaRouteSelector;
        if (this.mAttachedToWindow) {
          this.mRouter.removeCallback(this.mCallback);
          this.mRouter.addCallback(paramMediaRouteSelector, this.mCallback, 1);
        } 
        refreshRoutes();
      } 
      return;
    } 
    throw new IllegalArgumentException("selector must not be null");
  }
  
  public void setTitle(int paramInt) {
    this.mTitleView.setText(paramInt);
  }
  
  public void setTitle(CharSequence paramCharSequence) {
    this.mTitleView.setText(paramCharSequence);
  }
  
  void updateLayout() {
    getWindow().setLayout(MediaRouteDialogHelper.getDialogWidth(getContext()), -2);
  }
  
  void updateRoutes(List<MediaRouter.RouteInfo> paramList) {
    this.mLastUpdateTime = SystemClock.uptimeMillis();
    this.mRoutes.clear();
    this.mRoutes.addAll(paramList);
    this.mAdapter.notifyDataSetChanged();
  }
  
  private final class MediaRouterCallback extends MediaRouter.Callback {
    public void onRouteAdded(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteChooserDialog.this.refreshRoutes();
    }
    
    public void onRouteChanged(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteChooserDialog.this.refreshRoutes();
    }
    
    public void onRouteRemoved(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteChooserDialog.this.refreshRoutes();
    }
    
    public void onRouteSelected(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteChooserDialog.this.dismiss();
    }
  }
  
  private final class RouteAdapter extends ArrayAdapter<MediaRouter.RouteInfo> implements AdapterView.OnItemClickListener {
    private final Drawable mDefaultIcon;
    
    private final LayoutInflater mInflater;
    
    private final Drawable mSpeakerGroupIcon;
    
    private final Drawable mSpeakerIcon;
    
    private final Drawable mTvIcon;
    
    public RouteAdapter(Context param1Context, List<MediaRouter.RouteInfo> param1List) {
      super(param1Context, 0, param1List);
      this.mInflater = LayoutInflater.from(param1Context);
      TypedArray typedArray = getContext().obtainStyledAttributes(new int[] { R.attr.mediaRouteDefaultIconDrawable, R.attr.mediaRouteTvIconDrawable, R.attr.mediaRouteSpeakerIconDrawable, R.attr.mediaRouteSpeakerGroupIconDrawable });
      this.mDefaultIcon = typedArray.getDrawable(0);
      this.mTvIcon = typedArray.getDrawable(1);
      this.mSpeakerIcon = typedArray.getDrawable(2);
      this.mSpeakerGroupIcon = typedArray.getDrawable(3);
      typedArray.recycle();
    }
    
    private Drawable getDefaultIconDrawable(MediaRouter.RouteInfo param1RouteInfo) {
      int i = param1RouteInfo.getDeviceType();
      return (i != 1) ? ((i != 2) ? ((param1RouteInfo instanceof MediaRouter.RouteGroup) ? this.mSpeakerGroupIcon : this.mDefaultIcon) : this.mSpeakerIcon) : this.mTvIcon;
    }
    
    private Drawable getIconDrawable(MediaRouter.RouteInfo param1RouteInfo) {
      Uri uri = param1RouteInfo.getIconUri();
      if (uri != null)
        try {
          Drawable drawable = Drawable.createFromStream(getContext().getContentResolver().openInputStream(uri), null);
          if (drawable != null)
            return drawable; 
        } catch (IOException iOException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Failed to load ");
          stringBuilder.append(uri);
          Log.w("MediaRouteChooserDialog", stringBuilder.toString(), iOException);
        }  
      return getDefaultIconDrawable(param1RouteInfo);
    }
    
    public boolean areAllItemsEnabled() {
      return false;
    }
    
    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
      View view = param1View;
      if (param1View == null)
        view = this.mInflater.inflate(R.layout.mr_chooser_list_item, param1ViewGroup, false); 
      MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo)getItem(param1Int);
      TextView textView1 = (TextView)view.findViewById(R.id.mr_chooser_route_name);
      TextView textView2 = (TextView)view.findViewById(R.id.mr_chooser_route_desc);
      textView1.setText(routeInfo.getName());
      String str = routeInfo.getDescription();
      int i = routeInfo.getConnectionState();
      boolean bool = true;
      param1Int = bool;
      if (i != 2)
        if (routeInfo.getConnectionState() == 1) {
          param1Int = bool;
        } else {
          param1Int = 0;
        }  
      if (param1Int != 0 && !TextUtils.isEmpty(str)) {
        textView1.setGravity(80);
        textView2.setVisibility(0);
        textView2.setText(str);
      } else {
        textView1.setGravity(16);
        textView2.setVisibility(8);
        textView2.setText("");
      } 
      view.setEnabled(routeInfo.isEnabled());
      ImageView imageView = (ImageView)view.findViewById(R.id.mr_chooser_route_icon);
      if (imageView != null)
        imageView.setImageDrawable(getIconDrawable(routeInfo)); 
      return view;
    }
    
    public boolean isEnabled(int param1Int) {
      return ((MediaRouter.RouteInfo)getItem(param1Int)).isEnabled();
    }
    
    public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
      MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo)getItem(param1Int);
      if (routeInfo.isEnabled()) {
        routeInfo.select();
        MediaRouteChooserDialog.this.dismiss();
      } 
    }
  }
  
  static final class RouteComparator implements Comparator<MediaRouter.RouteInfo> {
    public static final RouteComparator sInstance = new RouteComparator();
    
    public int compare(MediaRouter.RouteInfo param1RouteInfo1, MediaRouter.RouteInfo param1RouteInfo2) {
      return param1RouteInfo1.getName().compareToIgnoreCase(param1RouteInfo2.getName());
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/MediaRouteChooserDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */