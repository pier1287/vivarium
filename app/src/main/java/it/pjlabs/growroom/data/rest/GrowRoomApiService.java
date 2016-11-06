package it.pjlabs.growroom.data.rest;

import java.util.List;

import it.pjlabs.growroom.data.entities.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Pj on 06/11/16.
 */

public interface GrowRoomApiService {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{idUser}/?format=json")
    Call<User> getUser(@Path("idUser") Long idUser);
}
