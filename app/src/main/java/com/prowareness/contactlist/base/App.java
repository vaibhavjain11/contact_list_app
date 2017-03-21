package com.prowareness.contactlist.base;

import android.app.Application;

import com.prowareness.contactlist.component.DaggerNetworkComponent;
import com.prowareness.contactlist.component.NetworkComponent;
import com.prowareness.contactlist.module.NetworkModule;

public class App extends Application {

     private static NetworkComponent daggerNetworkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        daggerNetworkComponent = DaggerNetworkComponent.builder().networkModule(new NetworkModule(this)).build();
    }

    public static NetworkComponent getComponent(){
        return daggerNetworkComponent;
    }

}
