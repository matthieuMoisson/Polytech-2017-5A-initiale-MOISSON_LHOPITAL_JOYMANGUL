package fr.polytech.quizz.quizz.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JOYMANGUL Jensen Selwyn
 * on 03-Nov-17.
 */
public class BeerClient {
    private final static  String BASE_URL = "https://api.punkapi.com/v2/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
