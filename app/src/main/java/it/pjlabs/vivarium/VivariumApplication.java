package it.pjlabs.vivarium;

import android.app.Application;

import it.pjlabs.vivarium.injector.components.DaggerVivariumApiComponent;
import it.pjlabs.vivarium.injector.components.VivariumApiComponent;
import it.pjlabs.vivarium.injector.modules.AppModule;
import it.pjlabs.vivarium.injector.modules.VivariumApiModule;

/**
 * Created by Pj on 06/11/16.
 */

public class VivariumApplication extends Application {


    VivariumApiComponent mVivariumApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //Dagger Inizializzazione
        mVivariumApiComponent = DaggerVivariumApiComponent.builder()
                .appModule(new AppModule(this))
                .vivariumApiModule(new VivariumApiModule())
                .build();

    }

    public VivariumApiComponent getVivariumApiComponent() {
        return mVivariumApiComponent;
    }
}
