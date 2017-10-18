package fr.polytech.quizz.quizz;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Moisson Matthieu on 18/10/2017.
 */

public class QuizzService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public QuizzService(String name) {
        super(name);
    }

    public QuizzService() {
        super("notrequizzservice");
    }

    protected void onHandleIntent(Intent intent) {

        String str = intent.getStringExtra("Key");
        switch(str) {
            case "Value":
                System.out.println("yes");
                //TODO broadcast message
                break;
            default:
                System.out.println("test");
        }
    }
}
