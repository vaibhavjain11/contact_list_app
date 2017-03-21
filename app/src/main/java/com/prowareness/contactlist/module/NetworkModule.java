package com.prowareness.contactlist.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.prowareness.contactlist.base.App;
import com.prowareness.contactlist.BuildConfig;
import com.prowareness.contactlist.service.RestService;
import com.prowareness.contactlist.service.RxService;

import java.util.HashMap;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vaibhavjain on 21/3/17.
 */

@Module
public class NetworkModule {

    App app;
    public NetworkModule(App app){
        this.app = app;
    }

    @Provides
    @Singleton
    SharedPreferences getSharedPreference(){
        return this.app.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    @Provides
    HashMap<String , String> getMapParams(){
        HashMap<String,String> mParams = new HashMap<>();
        mParams.put("token", "c149c4fac72d3a3678eefab5b0d3a85a");
        return mParams;
    }

    @Provides
    @Singleton
    Retrofit getRetrofit(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public RestService providesNetworkService(
            Retrofit retrofit) {
        return retrofit.create(RestService.class);
    }

    @Provides
    @Singleton
    public RxService provideRx(RestService restService){
        return new RxService(restService);
    }
}
