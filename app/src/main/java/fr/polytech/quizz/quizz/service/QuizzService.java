package fr.polytech.quizz.quizz.service;

import android.app.IntentService;
import android.content.Intent;
import fr.polytech.quizz.quizz.questionFrag;

/**
 * Created by Moisson Matthieu on 18/10/2017.
 */



public class QuizzService extends IntentService {
    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";

    //POSSIBLE ACTION IN THE SERVICE
    public static final String GET_QUESTION = "getQuestion";
    public static final String GET_ANSWER_A = "A";
    public static final String GET_ANSWER_B = "B";
    public static final String GET_ANSWER_C = "C";
    public static final String GET_ANSWER_D = "D";

    public QuizzService() {
        this(QuizzService.class.getName());
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public QuizzService(String name) {
        super(name);
    }

    protected void onHandleIntent(Intent intent) {
        // Gets data from the incoming Intent
        String msg = intent.getStringExtra(PARAM_IN_MSG);

        if(msg.equals(GET_QUESTION)) {
            String resultTxt = "Question From service";

            // processing done here….
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(questionFrag.QuestionReceiver.ACTION_GET_QUESTION);
            broadcastIntent.putExtra(PARAM_OUT_MSG, resultTxt);
            sendBroadcast(broadcastIntent);
        } else if(msg.equals(GET_ANSWER_A) || msg.equals(GET_ANSWER_B) || msg.equals(GET_ANSWER_C) || msg.equals(GET_ANSWER_D)) {
            String resultTxt = msg;

            // processing done here….
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(questionFrag.QuestionReceiver.ACTION_CHECK_ANSWER);
            if(msg.equals(GET_ANSWER_A)) {
                resultTxt += "   Gagné!!!";
            } else {
                resultTxt += "   Perdu!!!";
            }
            broadcastIntent.putExtra(PARAM_OUT_MSG, resultTxt);
            sendBroadcast(broadcastIntent);
        }

        /*String str = intent.getStringExtra("Key");
        switch(str) {
            case "Value":
                System.out.println("yes");
                //TODO broadcast message
                break;
            default:
                System.out.println("test");
        }*/
    }


}
