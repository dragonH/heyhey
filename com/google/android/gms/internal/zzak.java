package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public final class zzak implements zzan {
  private HttpClient zzcb;
  
  public zzak(HttpClient paramHttpClient) {
    this.zzcb = paramHttpClient;
  }
  
  private static void zza(HttpEntityEnclosingRequestBase paramHttpEntityEnclosingRequestBase, zzp<?> paramzzp) throws zza {
    byte[] arrayOfByte = paramzzp.zzg();
    if (arrayOfByte != null)
      paramHttpEntityEnclosingRequestBase.setEntity((HttpEntity)new ByteArrayEntity(arrayOfByte)); 
  }
  
  private static void zza(HttpUriRequest paramHttpUriRequest, Map<String, String> paramMap) {
    for (String str : paramMap.keySet())
      paramHttpUriRequest.setHeader(str, paramMap.get(str)); 
  }
  
  public final HttpResponse zza(zzp<?> paramzzp, Map<String, String> paramMap) throws IOException, zza {
    zzal zzal;
    HttpTrace httpTrace;
    HttpOptions httpOptions;
    HttpHead httpHead;
    HttpDelete httpDelete;
    HttpPut httpPut;
    HttpPost httpPost;
    switch (paramzzp.getMethod()) {
      default:
        throw new IllegalStateException("Unknown request method.");
      case 7:
        zzal = new zzal(paramzzp.getUrl());
        zzal.addHeader("Content-Type", zzp.zzf());
        zza(zzal, paramzzp);
        zza((HttpUriRequest)zzal, paramMap);
        zza((HttpUriRequest)zzal, paramzzp.getHeaders());
        httpParams = zzal.getParams();
        i = paramzzp.zzi();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
        HttpConnectionParams.setSoTimeout(httpParams, i);
        return this.zzcb.execute((HttpUriRequest)zzal);
      case 6:
        httpTrace = new HttpTrace(paramzzp.getUrl());
        zza((HttpUriRequest)httpTrace, (Map<String, String>)httpParams);
        zza((HttpUriRequest)httpTrace, paramzzp.getHeaders());
        httpParams = httpTrace.getParams();
        i = paramzzp.zzi();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
        HttpConnectionParams.setSoTimeout(httpParams, i);
        return this.zzcb.execute((HttpUriRequest)httpTrace);
      case 5:
        httpOptions = new HttpOptions(paramzzp.getUrl());
        zza((HttpUriRequest)httpOptions, (Map<String, String>)httpParams);
        zza((HttpUriRequest)httpOptions, paramzzp.getHeaders());
        httpParams = httpOptions.getParams();
        i = paramzzp.zzi();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
        HttpConnectionParams.setSoTimeout(httpParams, i);
        return this.zzcb.execute((HttpUriRequest)httpOptions);
      case 4:
        httpHead = new HttpHead(paramzzp.getUrl());
        zza((HttpUriRequest)httpHead, (Map<String, String>)httpParams);
        zza((HttpUriRequest)httpHead, paramzzp.getHeaders());
        httpParams = httpHead.getParams();
        i = paramzzp.zzi();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
        HttpConnectionParams.setSoTimeout(httpParams, i);
        return this.zzcb.execute((HttpUriRequest)httpHead);
      case 3:
        httpDelete = new HttpDelete(paramzzp.getUrl());
        zza((HttpUriRequest)httpDelete, (Map<String, String>)httpParams);
        zza((HttpUriRequest)httpDelete, paramzzp.getHeaders());
        httpParams = httpDelete.getParams();
        i = paramzzp.zzi();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
        HttpConnectionParams.setSoTimeout(httpParams, i);
        return this.zzcb.execute((HttpUriRequest)httpDelete);
      case 2:
        httpPut = new HttpPut(paramzzp.getUrl());
        httpPut.addHeader("Content-Type", zzp.zzf());
        zza((HttpEntityEnclosingRequestBase)httpPut, paramzzp);
        zza((HttpUriRequest)httpPut, (Map<String, String>)httpParams);
        zza((HttpUriRequest)httpPut, paramzzp.getHeaders());
        httpParams = httpPut.getParams();
        i = paramzzp.zzi();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
        HttpConnectionParams.setSoTimeout(httpParams, i);
        return this.zzcb.execute((HttpUriRequest)httpPut);
      case 1:
        httpPost = new HttpPost(paramzzp.getUrl());
        httpPost.addHeader("Content-Type", zzp.zzf());
        zza((HttpEntityEnclosingRequestBase)httpPost, paramzzp);
        zza((HttpUriRequest)httpPost, (Map<String, String>)httpParams);
        zza((HttpUriRequest)httpPost, paramzzp.getHeaders());
        httpParams = httpPost.getParams();
        i = paramzzp.zzi();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
        HttpConnectionParams.setSoTimeout(httpParams, i);
        return this.zzcb.execute((HttpUriRequest)httpPost);
      case 0:
        httpGet = new HttpGet(paramzzp.getUrl());
        zza((HttpUriRequest)httpGet, (Map<String, String>)httpParams);
        zza((HttpUriRequest)httpGet, paramzzp.getHeaders());
        httpParams = httpGet.getParams();
        i = paramzzp.zzi();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
        HttpConnectionParams.setSoTimeout(httpParams, i);
        return this.zzcb.execute((HttpUriRequest)httpGet);
      case -1:
        break;
    } 
    HttpGet httpGet = new HttpGet(paramzzp.getUrl());
    zza((HttpUriRequest)httpGet, (Map<String, String>)httpParams);
    zza((HttpUriRequest)httpGet, paramzzp.getHeaders());
    HttpParams httpParams = httpGet.getParams();
    int i = paramzzp.zzi();
    HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
    HttpConnectionParams.setSoTimeout(httpParams, i);
    return this.zzcb.execute((HttpUriRequest)httpGet);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */