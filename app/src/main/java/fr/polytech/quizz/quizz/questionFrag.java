package fr.polytech.quizz.quizz;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import fr.polytech.quizz.quizz.service.QuizzService;

import static fr.polytech.quizz.quizz.R.id.*;

/**
 * Created by Sachouw on 11/10/2017.
 */

public class questionFrag extends Fragment {
    private QuizzService quizzService;
    private Intent intent;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.question, container, false);

        Intent msgIntent = new Intent(getActivity(), QuizzService.class);
        msgIntent.putExtra(QuizzService.PARAM_IN_MSG, QuizzService.GET_QUESTION);
        getActivity().startService(msgIntent);

        // The filter's action is BROADCAST_ACTION
        IntentFilter filter = new IntentFilter(QuestionReceiver.ACTION_GET_QUESTION);
        QuestionReceiver receiver = new QuestionReceiver();
        getActivity().registerReceiver(receiver, filter);




        Button button = (Button) view.findViewById(b_r1);
        button.setText("Reponse A");
        onClickAnswer(button, QuizzService.GET_ANSWER_A);

        button = (Button) view.findViewById(b_r2);
        button.setText("Reponse B");
        onClickAnswer(button, QuizzService.GET_ANSWER_B);

        button = (Button) view.findViewById(b_r3);
        button.setText("Reponse C");
        onClickAnswer(button, QuizzService.GET_ANSWER_C);

        button = (Button) view.findViewById(b_r4);
        button.setText("Reponse D");
        onClickAnswer(button, QuizzService.GET_ANSWER_D);

        return view;
    }

    private void onClickAnswer(Button button, final String serviceAction) {
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent msgIntent = new Intent(getActivity(), QuizzService.class);
                msgIntent.putExtra(QuizzService.PARAM_IN_MSG, serviceAction);
                getActivity().startService(msgIntent);

                // The filter's action is BROADCAST_ACTION
                IntentFilter filter = new IntentFilter(QuestionReceiver.ACTION_CHECK_ANSWER);
                QuestionReceiver receiver = new QuestionReceiver();
                getActivity().registerReceiver(receiver, filter);
            }
        });
    }


    public class QuestionReceiver extends BroadcastReceiver {
        public static final String ACTION_GET_QUESTION =
                "fr.polytech.quizz.quizz.intent.action.GET_QUESTION";
        public static final String ACTION_CHECK_ANSWER =  "fr.polytech.quizz.quizz.intent.action.CHECK_ANSWER";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("OUT", intent.getAction());
            if(intent.getAction().equals(ACTION_GET_QUESTION)) {
                TextView result = (TextView) view.findViewById(tv_question);
                String text = intent.getStringExtra(QuizzService.PARAM_OUT_MSG);
                result.setText(text);
            } else if(intent.getAction().equals(ACTION_CHECK_ANSWER)) {
                Log.d("ENETER", "DEAD");
                TextView answerText = (TextView) view.findViewById(answerTextView);
                String text = intent.getStringExtra(QuizzService.PARAM_OUT_MSG);
                answerText.setText(text);
            }

        }
    }

}


