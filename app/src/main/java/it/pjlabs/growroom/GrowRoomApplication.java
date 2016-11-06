package it.pjlabs.growroom;

import android.app.Application;
import android.content.res.Configuration;

import it.pjlabs.growroom.injector.components.DaggerGrowRoomApiComponent;
import it.pjlabs.growroom.injector.components.GrowRoomApiComponent;
import it.pjlabs.growroom.injector.modules.AppModule;
import it.pjlabs.growroom.injector.modules.GrowRoomApiModule;

/**
 * Created by Pj on 06/11/16.
 */

public class GrowRoomApplication extends Application {

    private static final String GROW_ROOM_API_BASEURL = "localhost/api";

    GrowRoomApiComponent mGrowRoomApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //Dagger Inizializzazione
        mGrowRoomApiComponent = DaggerGrowRoomApiComponent.builder()
                .appModule(new AppModule(this))
                .growRoomApiModule(new GrowRoomApiModule())
                .build();

    }

    public GrowRoomApiComponent getGrowRoomApiComponent() {
        return mGrowRoomApiComponent;
    }
}
