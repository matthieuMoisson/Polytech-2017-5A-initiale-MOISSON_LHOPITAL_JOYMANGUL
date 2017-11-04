package fr.polytech.quizz.quizz.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.gson.Gson;
import fr.polytech.quizz.quizz.model.Beer;
import fr.polytech.quizz.quizz.rest.BeerClient;
import fr.polytech.quizz.quizz.rest.BeerInterface;
import fr.polytech.quizz.quizz.ui.BeerFragment;
import fr.polytech.quizz.quizz.ui.BeersFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class BeerService extends IntentService {
    // IntentService can perform the following actions
    public static final String ACTION_GET_ALL_BEERS = "fr.polytech.quizz.quizz.service.action.FOO";
    public static final String ACTION_GET_BEER = "fr.polytech.quizz.quizz.service.action.BAZ";

    // Parameters
    private static final String EXTRA_BEER_ID = "fr.polytech.quizz.quizz.service.extra.beer.id";
    public static final String EXTRA_RESULT = "fr.polytech.quizz.quizz.service.extra.result";

    // Variables
    private BeerInterface beerInterface;

    public BeerService() {
        super("BeerService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionGetAllBeers(Context context, BeersFragment.BeersReceiver receiver) {
        Intent intent = new Intent(context, BeerService.class);
        intent.setAction(ACTION_GET_ALL_BEERS);
        context.startService(intent);

        // The filter's action is BROADCAST_ACTION
        IntentFilter filter = new IntentFilter(ACTION_GET_ALL_BEERS);
        context.registerReceiver(receiver, filter);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionGetBeer(Context context, BeerFragment.BeerReceiver receiver, int beerId) {
        Intent intent = new Intent(context, BeerService.class);
        intent.setAction(ACTION_GET_BEER);
        intent.putExtra(EXTRA_BEER_ID, String.valueOf(beerId));

        // The filter's action is BROADCAST_ACTION
        IntentFilter filter = new IntentFilter(ACTION_GET_BEER);
        context.registerReceiver(receiver, filter);

        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_ALL_BEERS.equals(action)) {
                handleActionGetAllBeers();
            } else if (ACTION_GET_BEER.equals(action)) {
                final String beerId = intent.getStringExtra(EXTRA_BEER_ID);
                handleActionGetBeer(beerId);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionGetAllBeers() {
        beerInterface = BeerClient.getClient().create(BeerInterface.class);
        Call<List<Beer>> call = beerInterface.getBeers();

        final Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_GET_ALL_BEERS);

        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<fr.polytech.quizz.quizz.model.Beer>> call, Response<List<Beer>> response) {
                //setListAdapter(new BeerRow(getActivity(), response.body()));
                Gson gson = new Gson();
                broadcastIntent.putExtra(EXTRA_RESULT,  gson.toJson(response.body()));
                sendBroadcast(broadcastIntent);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionGetBeer(String beerId) {
        beerInterface = BeerClient.getClient().create(BeerInterface.class);
        Call<List<Beer>> call = beerInterface.getBeer(beerId);

        final Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_GET_BEER);

        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                Gson gson = new Gson();
                broadcastIntent.putExtra(EXTRA_RESULT,  gson.toJson(response.body().get(0)));
                sendBroadcast(broadcastIntent);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }
}
