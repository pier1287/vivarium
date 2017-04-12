package it.pjlabs.vivarium.data.rest;

import java.util.List;

import it.pjlabs.vivarium.data.entities.Measurement;
import it.pjlabs.vivarium.data.entities.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Pj on 06/11/16.
 */

public interface VivariumApiService {

    /**
     * Get the Users List
     * @return Users list
     */
    @GET("users")
    Observable<List<User>> getUsers();

    @GET("users/{idUser}/?format=json")
    Observable<User> getUser(@Path("idUser") Long idUser);

    @GET("measurements/temperatures/latest/?format=json")
    Observable<Measurement> getLatestTemperature();

    @GET("measurements/humidities/latest/?format=json")
    Observable<Measurement> getLatestHumidity();
}
