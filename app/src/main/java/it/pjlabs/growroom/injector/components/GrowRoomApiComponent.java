package it.pjlabs.growroom.injector.components;

import javax.inject.Singleton;

import dagger.Component;
import it.pjlabs.growroom.activities.MainActivity;
import it.pjlabs.growroom.injector.modules.AppModule;
import it.pjlabs.growroom.injector.modules.GrowRoomApiModule;

/**
 * Created by Pj on 06/11/16.
 */

@Singleton
@Component(modules = {AppModule.class, GrowRoomApiModule.class})
public interface GrowRoomApiComponent {
    void inject(MainActivity mainActivity);
}
