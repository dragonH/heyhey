package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PrintHelper {
  public static final int COLOR_MODE_COLOR = 2;
  
  public static final int COLOR_MODE_MONOCHROME = 1;
  
  public static final int ORIENTATION_LANDSCAPE = 1;
  
  public static final int ORIENTATION_PORTRAIT = 2;
  
  public static final int SCALE_MODE_FILL = 2;
  
  public static final int SCALE_MODE_FIT = 1;
  
  private final PrintHelperVersionImpl mImpl;
  
  public PrintHelper(Context paramContext) {
    int i = Build.VERSION.SDK_INT;
    if (i >= 24) {
      this.mImpl = new PrintHelperApi24(paramContext);
    } else if (i >= 23) {
      this.mImpl = new PrintHelperApi23(paramContext);
    } else if (i >= 20) {
      this.mImpl = new PrintHelperApi20(paramContext);
    } else if (i >= 19) {
      this.mImpl = new PrintHelperApi19(paramContext);
    } else {
      this.mImpl = new PrintHelperStub();
    } 
  }
  
  public static boolean systemSupportsPrint() {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 19) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int getColorMode() {
    return this.mImpl.getColorMode();
  }
  
  public int getOrientation() {
    return this.mImpl.getOrientation();
  }
  
  public int getScaleMode() {
    return this.mImpl.getScaleMode();
  }
  
  public void printBitmap(String paramString, Bitmap paramBitmap) {
    this.mImpl.printBitmap(paramString, paramBitmap, (OnPrintFinishCallback)null);
  }
  
  public void printBitmap(String paramString, Bitmap paramBitmap, OnPrintFinishCallback paramOnPrintFinishCallback) {
    this.mImpl.printBitmap(paramString, paramBitmap, paramOnPrintFinishCallback);
  }
  
  public void printBitmap(String paramString, Uri paramUri) throws FileNotFoundException {
    this.mImpl.printBitmap(paramString, paramUri, (OnPrintFinishCallback)null);
  }
  
  public void printBitmap(String paramString, Uri paramUri, OnPrintFinishCallback paramOnPrintFinishCallback) throws FileNotFoundException {
    this.mImpl.printBitmap(paramString, paramUri, paramOnPrintFinishCallback);
  }
  
  public void setColorMode(int paramInt) {
    this.mImpl.setColorMode(paramInt);
  }
  
  public void setOrientation(int paramInt) {
    this.mImpl.setOrientation(paramInt);
  }
  
  public void setScaleMode(int paramInt) {
    this.mImpl.setScaleMode(paramInt);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface ColorMode {}
  
  public static interface OnPrintFinishCallback {
    void onFinish();
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface Orientation {}
  
  @RequiresApi(19)
  private static class PrintHelperApi19 implements PrintHelperVersionImpl {
    private static final String LOG_TAG = "PrintHelperApi19";
    
    private static final int MAX_PRINT_SIZE = 3500;
    
    int mColorMode = 2;
    
    final Context mContext;
    
    BitmapFactory.Options mDecodeOptions = null;
    
    protected boolean mIsMinMarginsHandlingCorrect = true;
    
    private final Object mLock = new Object();
    
    int mOrientation;
    
    protected boolean mPrintActivityRespectsOrientation = true;
    
    int mScaleMode = 2;
    
    PrintHelperApi19(Context param1Context) {
      this.mContext = param1Context;
    }
    
    private Bitmap convertBitmapForColorMode(Bitmap param1Bitmap, int param1Int) {
      if (param1Int != 1)
        return param1Bitmap; 
      Bitmap bitmap = Bitmap.createBitmap(param1Bitmap.getWidth(), param1Bitmap.getHeight(), Bitmap.Config.ARGB_8888);
      Canvas canvas = new Canvas(bitmap);
      Paint paint = new Paint();
      ColorMatrix colorMatrix = new ColorMatrix();
      colorMatrix.setSaturation(0.0F);
      paint.setColorFilter((ColorFilter)new ColorMatrixColorFilter(colorMatrix));
      canvas.drawBitmap(param1Bitmap, 0.0F, 0.0F, paint);
      canvas.setBitmap(null);
      return bitmap;
    }
    
    private Matrix getMatrix(int param1Int1, int param1Int2, RectF param1RectF, int param1Int3) {
      Matrix matrix = new Matrix();
      float f1 = param1RectF.width();
      float f2 = param1Int1;
      f1 /= f2;
      if (param1Int3 == 2) {
        f1 = Math.max(f1, param1RectF.height() / param1Int2);
      } else {
        f1 = Math.min(f1, param1RectF.height() / param1Int2);
      } 
      matrix.postScale(f1, f1);
      matrix.postTranslate((param1RectF.width() - f2 * f1) / 2.0F, (param1RectF.height() - param1Int2 * f1) / 2.0F);
      return matrix;
    }
    
    private static boolean isPortrait(Bitmap param1Bitmap) {
      boolean bool;
      if (param1Bitmap.getWidth() <= param1Bitmap.getHeight()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private Bitmap loadBitmap(Uri param1Uri, BitmapFactory.Options param1Options) throws FileNotFoundException {
      if (param1Uri != null) {
        Context context = this.mContext;
        if (context != null) {
          BitmapFactory.Options options = null;
          try {
            InputStream inputStream = context.getContentResolver().openInputStream(param1Uri);
          } finally {
            param1Uri = null;
          } 
          if (param1Options != null)
            try {
              param1Options.close();
            } catch (IOException iOException) {
              Log.w("PrintHelperApi19", "close fail ", iOException);
            }  
          throw param1Uri;
        } 
      } 
      throw new IllegalArgumentException("bad argument to loadBitmap");
    }
    
    private Bitmap loadConstrainedBitmap(Uri param1Uri) throws FileNotFoundException {
      if (param1Uri != null && this.mContext != null) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        loadBitmap(param1Uri, options);
        int i = options.outWidth;
        int j = options.outHeight;
        if (i > 0 && j > 0) {
          int k = Math.max(i, j);
          int m;
          for (m = 1; k > 3500; m <<= 1)
            k >>>= 1; 
          if (m > 0 && Math.min(i, j) / m > 0)
            synchronized (this.mLock) {
              BitmapFactory.Options options1 = new BitmapFactory.Options();
              this();
              this.mDecodeOptions = options1;
              options1.inMutable = true;
              options1.inSampleSize = m;
              try {
                null = loadBitmap(param1Uri, options1);
              } finally {
                null = null;
              } 
            }  
        } 
        return null;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException("bad argument to getScaledBitmap");
      throw illegalArgumentException;
    }
    
    private void writeBitmap(final PrintAttributes attributes, final int fittingMode, final Bitmap bitmap, final ParcelFileDescriptor fileDescriptor, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
      final PrintAttributes pdfAttributes;
      if (this.mIsMinMarginsHandlingCorrect) {
        printAttributes = attributes;
      } else {
        printAttributes = copyAttributes(attributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
      } 
      (new AsyncTask<Void, Void, Throwable>() {
          protected Throwable doInBackground(Void... param2VarArgs) {
            try {
              if (cancellationSignal.isCanceled())
                return null; 
              PrintedPdfDocument printedPdfDocument = new PrintedPdfDocument();
              this(PrintHelper.PrintHelperApi19.this.mContext, pdfAttributes);
              Bitmap bitmap = PrintHelper.PrintHelperApi19.this.convertBitmapForColorMode(bitmap, pdfAttributes.getColorMode());
              boolean bool = cancellationSignal.isCanceled();
              if (bool)
                return null; 
              try {
                RectF rectF;
                PdfDocument.Page page = printedPdfDocument.startPage(1);
                if (PrintHelper.PrintHelperApi19.this.mIsMinMarginsHandlingCorrect) {
                  rectF = new RectF();
                  this(page.getInfo().getContentRect());
                } else {
                  PrintedPdfDocument printedPdfDocument1 = new PrintedPdfDocument();
                  this(PrintHelper.PrintHelperApi19.this.mContext, attributes);
                  PdfDocument.Page page1 = printedPdfDocument1.startPage(1);
                  rectF = new RectF();
                  this(page1.getInfo().getContentRect());
                  printedPdfDocument1.finishPage(page1);
                  printedPdfDocument1.close();
                } 
                Matrix matrix = PrintHelper.PrintHelperApi19.this.getMatrix(bitmap.getWidth(), bitmap.getHeight(), rectF, fittingMode);
                if (!PrintHelper.PrintHelperApi19.this.mIsMinMarginsHandlingCorrect) {
                  matrix.postTranslate(rectF.left, rectF.top);
                  page.getCanvas().clipRect(rectF);
                } 
                page.getCanvas().drawBitmap(bitmap, matrix, null);
                printedPdfDocument.finishPage(page);
                bool = cancellationSignal.isCanceled();
                if (bool)
                  return null; 
                FileOutputStream fileOutputStream = new FileOutputStream();
                this(fileDescriptor.getFileDescriptor());
                printedPdfDocument.writeTo(fileOutputStream);
                return null;
              } finally {
                printedPdfDocument.close();
                ParcelFileDescriptor parcelFileDescriptor = fileDescriptor;
                if (parcelFileDescriptor != null)
                  try {
                    parcelFileDescriptor.close();
                  } catch (IOException iOException) {} 
                if (bitmap != bitmap)
                  bitmap.recycle(); 
              } 
            } finally {}
          }
          
          protected void onPostExecute(Throwable param2Throwable) {
            if (cancellationSignal.isCanceled()) {
              writeResultCallback.onWriteCancelled();
            } else if (param2Throwable == null) {
              writeResultCallback.onWriteFinished(new PageRange[] { PageRange.ALL_PAGES });
            } else {
              Log.e("PrintHelperApi19", "Error writing printed content", param2Throwable);
              writeResultCallback.onWriteFailed(null);
            } 
          }
        }).execute((Object[])new Void[0]);
    }
    
    protected PrintAttributes.Builder copyAttributes(PrintAttributes param1PrintAttributes) {
      PrintAttributes.Builder builder = (new PrintAttributes.Builder()).setMediaSize(param1PrintAttributes.getMediaSize()).setResolution(param1PrintAttributes.getResolution()).setMinMargins(param1PrintAttributes.getMinMargins());
      if (param1PrintAttributes.getColorMode() != 0)
        builder.setColorMode(param1PrintAttributes.getColorMode()); 
      return builder;
    }
    
    public int getColorMode() {
      return this.mColorMode;
    }
    
    public int getOrientation() {
      int i = this.mOrientation;
      int j = i;
      if (i == 0)
        j = 1; 
      return j;
    }
    
    public int getScaleMode() {
      return this.mScaleMode;
    }
    
    public void printBitmap(final String jobName, final Bitmap bitmap, final PrintHelper.OnPrintFinishCallback callback) {
      PrintAttributes.MediaSize mediaSize;
      if (bitmap == null)
        return; 
      final int fittingMode = this.mScaleMode;
      PrintManager printManager = (PrintManager)this.mContext.getSystemService("print");
      if (isPortrait(bitmap)) {
        mediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
      } else {
        mediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
      } 
      PrintAttributes printAttributes = (new PrintAttributes.Builder()).setMediaSize(mediaSize).setColorMode(this.mColorMode).build();
      printManager.print(jobName, new PrintDocumentAdapter() {
            private PrintAttributes mAttributes;
            
            public void onFinish() {
              PrintHelper.OnPrintFinishCallback onPrintFinishCallback = callback;
              if (onPrintFinishCallback != null)
                onPrintFinishCallback.onFinish(); 
            }
            
            public void onLayout(PrintAttributes param2PrintAttributes1, PrintAttributes param2PrintAttributes2, CancellationSignal param2CancellationSignal, PrintDocumentAdapter.LayoutResultCallback param2LayoutResultCallback, Bundle param2Bundle) {
              this.mAttributes = param2PrintAttributes2;
              param2LayoutResultCallback.onLayoutFinished((new PrintDocumentInfo.Builder(jobName)).setContentType(1).setPageCount(1).build(), param2PrintAttributes2.equals(param2PrintAttributes1) ^ true);
            }
            
            public void onWrite(PageRange[] param2ArrayOfPageRange, ParcelFileDescriptor param2ParcelFileDescriptor, CancellationSignal param2CancellationSignal, PrintDocumentAdapter.WriteResultCallback param2WriteResultCallback) {
              PrintHelper.PrintHelperApi19.this.writeBitmap(this.mAttributes, fittingMode, bitmap, param2ParcelFileDescriptor, param2CancellationSignal, param2WriteResultCallback);
            }
          }printAttributes);
    }
    
    public void printBitmap(final String jobName, final Uri imageFile, final PrintHelper.OnPrintFinishCallback callback) throws FileNotFoundException {
      PrintDocumentAdapter printDocumentAdapter = new PrintDocumentAdapter() {
          private PrintAttributes mAttributes;
          
          Bitmap mBitmap = null;
          
          AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;
          
          private void cancelLoad() {
            synchronized (PrintHelper.PrintHelperApi19.this.mLock) {
              BitmapFactory.Options options = PrintHelper.PrintHelperApi19.this.mDecodeOptions;
              if (options != null) {
                options.requestCancelDecode();
                PrintHelper.PrintHelperApi19.this.mDecodeOptions = null;
              } 
              return;
            } 
          }
          
          public void onFinish() {
            super.onFinish();
            cancelLoad();
            AsyncTask<Uri, Boolean, Bitmap> asyncTask = this.mLoadBitmap;
            if (asyncTask != null)
              asyncTask.cancel(true); 
            PrintHelper.OnPrintFinishCallback onPrintFinishCallback = callback;
            if (onPrintFinishCallback != null)
              onPrintFinishCallback.onFinish(); 
            Bitmap bitmap = this.mBitmap;
            if (bitmap != null) {
              bitmap.recycle();
              this.mBitmap = null;
            } 
          }
          
          public void onLayout(PrintAttributes param2PrintAttributes1, PrintAttributes param2PrintAttributes2, CancellationSignal param2CancellationSignal, PrintDocumentAdapter.LayoutResultCallback param2LayoutResultCallback, Bundle param2Bundle) {
            // Byte code:
            //   0: aload_0
            //   1: monitorenter
            //   2: aload_0
            //   3: aload_2
            //   4: putfield mAttributes : Landroid/print/PrintAttributes;
            //   7: aload_0
            //   8: monitorexit
            //   9: aload_3
            //   10: invokevirtual isCanceled : ()Z
            //   13: ifeq -> 22
            //   16: aload #4
            //   18: invokevirtual onLayoutCancelled : ()V
            //   21: return
            //   22: aload_0
            //   23: getfield mBitmap : Landroid/graphics/Bitmap;
            //   26: ifnull -> 64
            //   29: aload #4
            //   31: new android/print/PrintDocumentInfo$Builder
            //   34: dup
            //   35: aload_0
            //   36: getfield val$jobName : Ljava/lang/String;
            //   39: invokespecial <init> : (Ljava/lang/String;)V
            //   42: iconst_1
            //   43: invokevirtual setContentType : (I)Landroid/print/PrintDocumentInfo$Builder;
            //   46: iconst_1
            //   47: invokevirtual setPageCount : (I)Landroid/print/PrintDocumentInfo$Builder;
            //   50: invokevirtual build : ()Landroid/print/PrintDocumentInfo;
            //   53: aload_2
            //   54: aload_1
            //   55: invokevirtual equals : (Ljava/lang/Object;)Z
            //   58: iconst_1
            //   59: ixor
            //   60: invokevirtual onLayoutFinished : (Landroid/print/PrintDocumentInfo;Z)V
            //   63: return
            //   64: aload_0
            //   65: new android/support/v4/print/PrintHelper$PrintHelperApi19$3$1
            //   68: dup
            //   69: aload_0
            //   70: aload_3
            //   71: aload_2
            //   72: aload_1
            //   73: aload #4
            //   75: invokespecial <init> : (Landroid/support/v4/print/PrintHelper$PrintHelperApi19$3;Landroid/os/CancellationSignal;Landroid/print/PrintAttributes;Landroid/print/PrintAttributes;Landroid/print/PrintDocumentAdapter$LayoutResultCallback;)V
            //   78: iconst_0
            //   79: anewarray android/net/Uri
            //   82: invokevirtual execute : ([Ljava/lang/Object;)Landroid/os/AsyncTask;
            //   85: putfield mLoadBitmap : Landroid/os/AsyncTask;
            //   88: return
            //   89: astore_1
            //   90: aload_0
            //   91: monitorexit
            //   92: aload_1
            //   93: athrow
            // Exception table:
            //   from	to	target	type
            //   2	9	89	finally
            //   90	92	89	finally
          }
          
          public void onWrite(PageRange[] param2ArrayOfPageRange, ParcelFileDescriptor param2ParcelFileDescriptor, CancellationSignal param2CancellationSignal, PrintDocumentAdapter.WriteResultCallback param2WriteResultCallback) {
            PrintHelper.PrintHelperApi19.this.writeBitmap(this.mAttributes, fittingMode, this.mBitmap, param2ParcelFileDescriptor, param2CancellationSignal, param2WriteResultCallback);
          }
        };
      PrintManager printManager = (PrintManager)this.mContext.getSystemService("print");
      PrintAttributes.Builder builder = new PrintAttributes.Builder();
      builder.setColorMode(this.mColorMode);
      int i = this.mOrientation;
      if (i == 1 || i == 0) {
        builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
      } else if (i == 2) {
        builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
      } 
      printManager.print(jobName, printDocumentAdapter, builder.build());
    }
    
    public void setColorMode(int param1Int) {
      this.mColorMode = param1Int;
    }
    
    public void setOrientation(int param1Int) {
      this.mOrientation = param1Int;
    }
    
    public void setScaleMode(int param1Int) {
      this.mScaleMode = param1Int;
    }
  }
  
  class null extends PrintDocumentAdapter {
    private PrintAttributes mAttributes;
    
    public void onFinish() {
      PrintHelper.OnPrintFinishCallback onPrintFinishCallback = callback;
      if (onPrintFinishCallback != null)
        onPrintFinishCallback.onFinish(); 
    }
    
    public void onLayout(PrintAttributes param1PrintAttributes1, PrintAttributes param1PrintAttributes2, CancellationSignal param1CancellationSignal, PrintDocumentAdapter.LayoutResultCallback param1LayoutResultCallback, Bundle param1Bundle) {
      this.mAttributes = param1PrintAttributes2;
      param1LayoutResultCallback.onLayoutFinished((new PrintDocumentInfo.Builder(jobName)).setContentType(1).setPageCount(1).build(), param1PrintAttributes2.equals(param1PrintAttributes1) ^ true);
    }
    
    public void onWrite(PageRange[] param1ArrayOfPageRange, ParcelFileDescriptor param1ParcelFileDescriptor, CancellationSignal param1CancellationSignal, PrintDocumentAdapter.WriteResultCallback param1WriteResultCallback) {
      this.this$0.writeBitmap(this.mAttributes, fittingMode, bitmap, param1ParcelFileDescriptor, param1CancellationSignal, param1WriteResultCallback);
    }
  }
  
  class null extends AsyncTask<Void, Void, Throwable> {
    protected Throwable doInBackground(Void... param1VarArgs) {
      try {
        if (cancellationSignal.isCanceled())
          return null; 
        PrintedPdfDocument printedPdfDocument = new PrintedPdfDocument();
        this(this.this$0.mContext, pdfAttributes);
        Bitmap bitmap = this.this$0.convertBitmapForColorMode(bitmap, pdfAttributes.getColorMode());
        boolean bool = cancellationSignal.isCanceled();
        if (bool)
          return null; 
        try {
          RectF rectF;
          PdfDocument.Page page = printedPdfDocument.startPage(1);
          if (this.this$0.mIsMinMarginsHandlingCorrect) {
            rectF = new RectF();
            this(page.getInfo().getContentRect());
          } else {
            PrintedPdfDocument printedPdfDocument1 = new PrintedPdfDocument();
            this(this.this$0.mContext, attributes);
            PdfDocument.Page page1 = printedPdfDocument1.startPage(1);
            rectF = new RectF();
            this(page1.getInfo().getContentRect());
            printedPdfDocument1.finishPage(page1);
            printedPdfDocument1.close();
          } 
          Matrix matrix = this.this$0.getMatrix(bitmap.getWidth(), bitmap.getHeight(), rectF, fittingMode);
          if (!this.this$0.mIsMinMarginsHandlingCorrect) {
            matrix.postTranslate(rectF.left, rectF.top);
            page.getCanvas().clipRect(rectF);
          } 
          page.getCanvas().drawBitmap(bitmap, matrix, null);
          printedPdfDocument.finishPage(page);
          bool = cancellationSignal.isCanceled();
          if (bool)
            return null; 
          FileOutputStream fileOutputStream = new FileOutputStream();
          this(fileDescriptor.getFileDescriptor());
          printedPdfDocument.writeTo(fileOutputStream);
          return null;
        } finally {
          printedPdfDocument.close();
          ParcelFileDescriptor parcelFileDescriptor = fileDescriptor;
          if (parcelFileDescriptor != null)
            try {
              parcelFileDescriptor.close();
            } catch (IOException iOException) {} 
          if (bitmap != bitmap)
            bitmap.recycle(); 
        } 
      } finally {}
    }
    
    protected void onPostExecute(Throwable param1Throwable) {
      if (cancellationSignal.isCanceled()) {
        writeResultCallback.onWriteCancelled();
      } else if (param1Throwable == null) {
        writeResultCallback.onWriteFinished(new PageRange[] { PageRange.ALL_PAGES });
      } else {
        Log.e("PrintHelperApi19", "Error writing printed content", param1Throwable);
        writeResultCallback.onWriteFailed(null);
      } 
    }
  }
  
  class null extends PrintDocumentAdapter {
    private PrintAttributes mAttributes;
    
    Bitmap mBitmap = null;
    
    AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;
    
    private void cancelLoad() {
      synchronized (this.this$0.mLock) {
        BitmapFactory.Options options = this.this$0.mDecodeOptions;
        if (options != null) {
          options.requestCancelDecode();
          this.this$0.mDecodeOptions = null;
        } 
        return;
      } 
    }
    
    public void onFinish() {
      super.onFinish();
      cancelLoad();
      AsyncTask<Uri, Boolean, Bitmap> asyncTask = this.mLoadBitmap;
      if (asyncTask != null)
        asyncTask.cancel(true); 
      PrintHelper.OnPrintFinishCallback onPrintFinishCallback = callback;
      if (onPrintFinishCallback != null)
        onPrintFinishCallback.onFinish(); 
      Bitmap bitmap = this.mBitmap;
      if (bitmap != null) {
        bitmap.recycle();
        this.mBitmap = null;
      } 
    }
    
    public void onLayout(PrintAttributes param1PrintAttributes1, PrintAttributes param1PrintAttributes2, CancellationSignal param1CancellationSignal, PrintDocumentAdapter.LayoutResultCallback param1LayoutResultCallback, Bundle param1Bundle) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: aload_2
      //   4: putfield mAttributes : Landroid/print/PrintAttributes;
      //   7: aload_0
      //   8: monitorexit
      //   9: aload_3
      //   10: invokevirtual isCanceled : ()Z
      //   13: ifeq -> 22
      //   16: aload #4
      //   18: invokevirtual onLayoutCancelled : ()V
      //   21: return
      //   22: aload_0
      //   23: getfield mBitmap : Landroid/graphics/Bitmap;
      //   26: ifnull -> 64
      //   29: aload #4
      //   31: new android/print/PrintDocumentInfo$Builder
      //   34: dup
      //   35: aload_0
      //   36: getfield val$jobName : Ljava/lang/String;
      //   39: invokespecial <init> : (Ljava/lang/String;)V
      //   42: iconst_1
      //   43: invokevirtual setContentType : (I)Landroid/print/PrintDocumentInfo$Builder;
      //   46: iconst_1
      //   47: invokevirtual setPageCount : (I)Landroid/print/PrintDocumentInfo$Builder;
      //   50: invokevirtual build : ()Landroid/print/PrintDocumentInfo;
      //   53: aload_2
      //   54: aload_1
      //   55: invokevirtual equals : (Ljava/lang/Object;)Z
      //   58: iconst_1
      //   59: ixor
      //   60: invokevirtual onLayoutFinished : (Landroid/print/PrintDocumentInfo;Z)V
      //   63: return
      //   64: aload_0
      //   65: new android/support/v4/print/PrintHelper$PrintHelperApi19$3$1
      //   68: dup
      //   69: aload_0
      //   70: aload_3
      //   71: aload_2
      //   72: aload_1
      //   73: aload #4
      //   75: invokespecial <init> : (Landroid/support/v4/print/PrintHelper$PrintHelperApi19$3;Landroid/os/CancellationSignal;Landroid/print/PrintAttributes;Landroid/print/PrintAttributes;Landroid/print/PrintDocumentAdapter$LayoutResultCallback;)V
      //   78: iconst_0
      //   79: anewarray android/net/Uri
      //   82: invokevirtual execute : ([Ljava/lang/Object;)Landroid/os/AsyncTask;
      //   85: putfield mLoadBitmap : Landroid/os/AsyncTask;
      //   88: return
      //   89: astore_1
      //   90: aload_0
      //   91: monitorexit
      //   92: aload_1
      //   93: athrow
      // Exception table:
      //   from	to	target	type
      //   2	9	89	finally
      //   90	92	89	finally
    }
    
    public void onWrite(PageRange[] param1ArrayOfPageRange, ParcelFileDescriptor param1ParcelFileDescriptor, CancellationSignal param1CancellationSignal, PrintDocumentAdapter.WriteResultCallback param1WriteResultCallback) {
      this.this$0.writeBitmap(this.mAttributes, fittingMode, this.mBitmap, param1ParcelFileDescriptor, param1CancellationSignal, param1WriteResultCallback);
    }
  }
  
  class null extends AsyncTask<Uri, Boolean, Bitmap> {
    protected Bitmap doInBackground(Uri... param1VarArgs) {
      try {
        PrintHelper.PrintHelperApi19.null  = this.this$1;
        return .this$0.loadConstrainedBitmap(imageFile);
      } catch (FileNotFoundException fileNotFoundException) {
        return null;
      } 
    }
    
    protected void onCancelled(Bitmap param1Bitmap) {
      layoutResultCallback.onLayoutCancelled();
      this.this$1.mLoadBitmap = null;
    }
    
    protected void onPostExecute(Bitmap param1Bitmap) {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: invokespecial onPostExecute : (Ljava/lang/Object;)V
      //   5: aload_1
      //   6: astore_2
      //   7: aload_1
      //   8: ifnull -> 109
      //   11: aload_0
      //   12: getfield this$1 : Landroid/support/v4/print/PrintHelper$PrintHelperApi19$3;
      //   15: getfield this$0 : Landroid/support/v4/print/PrintHelper$PrintHelperApi19;
      //   18: astore_3
      //   19: aload_3
      //   20: getfield mPrintActivityRespectsOrientation : Z
      //   23: ifeq -> 35
      //   26: aload_1
      //   27: astore_2
      //   28: aload_3
      //   29: getfield mOrientation : I
      //   32: ifne -> 109
      //   35: aload_0
      //   36: monitorenter
      //   37: aload_0
      //   38: getfield this$1 : Landroid/support/v4/print/PrintHelper$PrintHelperApi19$3;
      //   41: invokestatic access$500 : (Landroid/support/v4/print/PrintHelper$PrintHelperApi19$3;)Landroid/print/PrintAttributes;
      //   44: invokevirtual getMediaSize : ()Landroid/print/PrintAttributes$MediaSize;
      //   47: astore_3
      //   48: aload_0
      //   49: monitorexit
      //   50: aload_1
      //   51: astore_2
      //   52: aload_3
      //   53: ifnull -> 109
      //   56: aload_1
      //   57: astore_2
      //   58: aload_3
      //   59: invokevirtual isPortrait : ()Z
      //   62: aload_1
      //   63: invokestatic access$600 : (Landroid/graphics/Bitmap;)Z
      //   66: if_icmpeq -> 109
      //   69: new android/graphics/Matrix
      //   72: dup
      //   73: invokespecial <init> : ()V
      //   76: astore_2
      //   77: aload_2
      //   78: ldc 90.0
      //   80: invokevirtual postRotate : (F)Z
      //   83: pop
      //   84: aload_1
      //   85: iconst_0
      //   86: iconst_0
      //   87: aload_1
      //   88: invokevirtual getWidth : ()I
      //   91: aload_1
      //   92: invokevirtual getHeight : ()I
      //   95: aload_2
      //   96: iconst_1
      //   97: invokestatic createBitmap : (Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;
      //   100: astore_2
      //   101: goto -> 109
      //   104: astore_1
      //   105: aload_0
      //   106: monitorexit
      //   107: aload_1
      //   108: athrow
      //   109: aload_0
      //   110: getfield this$1 : Landroid/support/v4/print/PrintHelper$PrintHelperApi19$3;
      //   113: aload_2
      //   114: putfield mBitmap : Landroid/graphics/Bitmap;
      //   117: aload_2
      //   118: ifnull -> 175
      //   121: new android/print/PrintDocumentInfo$Builder
      //   124: dup
      //   125: aload_0
      //   126: getfield this$1 : Landroid/support/v4/print/PrintHelper$PrintHelperApi19$3;
      //   129: getfield val$jobName : Ljava/lang/String;
      //   132: invokespecial <init> : (Ljava/lang/String;)V
      //   135: iconst_1
      //   136: invokevirtual setContentType : (I)Landroid/print/PrintDocumentInfo$Builder;
      //   139: iconst_1
      //   140: invokevirtual setPageCount : (I)Landroid/print/PrintDocumentInfo$Builder;
      //   143: invokevirtual build : ()Landroid/print/PrintDocumentInfo;
      //   146: astore_1
      //   147: aload_0
      //   148: getfield val$newPrintAttributes : Landroid/print/PrintAttributes;
      //   151: aload_0
      //   152: getfield val$oldPrintAttributes : Landroid/print/PrintAttributes;
      //   155: invokevirtual equals : (Ljava/lang/Object;)Z
      //   158: istore #4
      //   160: aload_0
      //   161: getfield val$layoutResultCallback : Landroid/print/PrintDocumentAdapter$LayoutResultCallback;
      //   164: aload_1
      //   165: iconst_1
      //   166: iload #4
      //   168: ixor
      //   169: invokevirtual onLayoutFinished : (Landroid/print/PrintDocumentInfo;Z)V
      //   172: goto -> 183
      //   175: aload_0
      //   176: getfield val$layoutResultCallback : Landroid/print/PrintDocumentAdapter$LayoutResultCallback;
      //   179: aconst_null
      //   180: invokevirtual onLayoutFailed : (Ljava/lang/CharSequence;)V
      //   183: aload_0
      //   184: getfield this$1 : Landroid/support/v4/print/PrintHelper$PrintHelperApi19$3;
      //   187: aconst_null
      //   188: putfield mLoadBitmap : Landroid/os/AsyncTask;
      //   191: return
      // Exception table:
      //   from	to	target	type
      //   37	50	104	finally
      //   105	107	104	finally
    }
    
    protected void onPreExecute() {
      cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
            public void onCancel() {
              this.this$2.this$1.cancelLoad();
              PrintHelper.PrintHelperApi19.null.null.this.cancel(false);
            }
          });
    }
  }
  
  class null implements CancellationSignal.OnCancelListener {
    public void onCancel() {
      this.this$2.this$1.cancelLoad();
      this.this$2.cancel(false);
    }
  }
  
  @RequiresApi(20)
  private static class PrintHelperApi20 extends PrintHelperApi19 {
    PrintHelperApi20(Context param1Context) {
      super(param1Context);
      this.mPrintActivityRespectsOrientation = false;
    }
  }
  
  @RequiresApi(23)
  private static class PrintHelperApi23 extends PrintHelperApi20 {
    PrintHelperApi23(Context param1Context) {
      super(param1Context);
      this.mIsMinMarginsHandlingCorrect = false;
    }
    
    protected PrintAttributes.Builder copyAttributes(PrintAttributes param1PrintAttributes) {
      PrintAttributes.Builder builder = super.copyAttributes(param1PrintAttributes);
      if (param1PrintAttributes.getDuplexMode() != 0)
        builder.setDuplexMode(param1PrintAttributes.getDuplexMode()); 
      return builder;
    }
  }
  
  @RequiresApi(24)
  private static class PrintHelperApi24 extends PrintHelperApi23 {
    PrintHelperApi24(Context param1Context) {
      super(param1Context);
      this.mIsMinMarginsHandlingCorrect = true;
      this.mPrintActivityRespectsOrientation = true;
    }
  }
  
  private static final class PrintHelperStub implements PrintHelperVersionImpl {
    int mColorMode = 2;
    
    int mOrientation = 1;
    
    int mScaleMode = 2;
    
    private PrintHelperStub() {}
    
    public int getColorMode() {
      return this.mColorMode;
    }
    
    public int getOrientation() {
      return this.mOrientation;
    }
    
    public int getScaleMode() {
      return this.mScaleMode;
    }
    
    public void printBitmap(String param1String, Bitmap param1Bitmap, PrintHelper.OnPrintFinishCallback param1OnPrintFinishCallback) {}
    
    public void printBitmap(String param1String, Uri param1Uri, PrintHelper.OnPrintFinishCallback param1OnPrintFinishCallback) {}
    
    public void setColorMode(int param1Int) {
      this.mColorMode = param1Int;
    }
    
    public void setOrientation(int param1Int) {
      this.mOrientation = param1Int;
    }
    
    public void setScaleMode(int param1Int) {
      this.mScaleMode = param1Int;
    }
  }
  
  static interface PrintHelperVersionImpl {
    int getColorMode();
    
    int getOrientation();
    
    int getScaleMode();
    
    void printBitmap(String param1String, Bitmap param1Bitmap, PrintHelper.OnPrintFinishCallback param1OnPrintFinishCallback);
    
    void printBitmap(String param1String, Uri param1Uri, PrintHelper.OnPrintFinishCallback param1OnPrintFinishCallback) throws FileNotFoundException;
    
    void setColorMode(int param1Int);
    
    void setOrientation(int param1Int);
    
    void setScaleMode(int param1Int);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface ScaleMode {}
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/print/PrintHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */