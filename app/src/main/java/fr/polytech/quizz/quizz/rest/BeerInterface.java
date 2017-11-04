package fr.polytech.quizz.quizz.rest;

import fr.polytech.quizz.quizz.model.Beer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Created by JOYMANGUL Jensen Selwyn
 * on 03-Nov-17.
 */
public interface BeerInterface {
    @GET("beers")
    Call<List<Beer>> getBeers();

    @GET("beers/{id}")
    Call<List<Beer>> getBeer(@Path("id") String id);
}
