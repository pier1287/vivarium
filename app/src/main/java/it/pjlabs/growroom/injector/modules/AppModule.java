package it.pjlabs.growroom.injector.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Pj on 06/11/16.
 */

@Module
public class AppModule {

    Application mApplication;

    public AppModule (Application application){
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return  mApplication;
    }
}
