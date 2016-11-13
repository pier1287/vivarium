package it.pjlabs.vivarium.injector.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.pjlabs.vivarium.R;
import it.pjlabs.vivarium.data.rest.VivariumApiService;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pj on 06/11/16.
 */

@Module
public class VivariumApiModule {


    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences (Application application){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache (Application application){
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return  cache;
    }

    @Provides
    @Singleton
    Gson provideGson(){
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setLenient()
                .create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        return  okHttpClient;
    }


    @Provides
    @Singleton
    Retrofit provideVivariumRestAdapter (Application application, Gson gson, OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(application.getString(R.string.grow_room_api_base_url))
                .client(okHttpClient)
                .build();
        return  retrofit;
    }

    @Provides
    @Singleton
    VivariumApiService provideVivariumRestApi (Retrofit growRestAdapter){
        return growRestAdapter.create(VivariumApiService.class);
    }

}
