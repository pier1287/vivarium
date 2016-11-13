package it.pjlabs.vivarium.injector.components;

import javax.inject.Singleton;

import dagger.Component;
import it.pjlabs.vivarium.activities.MainActivity;
import it.pjlabs.vivarium.injector.modules.AppModule;
import it.pjlabs.vivarium.injector.modules.VivariumApiModule;

/**
 * Created by Pj on 06/11/16.
 */

@Singleton
@Component(modules = {AppModule.class, VivariumApiModule.class})
public interface VivariumApiComponent {
    void inject(MainActivity mainActivity);
}
