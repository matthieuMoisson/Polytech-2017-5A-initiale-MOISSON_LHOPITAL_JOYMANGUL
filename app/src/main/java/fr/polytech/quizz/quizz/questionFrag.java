package fr.polytech.quizz.quizz;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import static fr.polytech.quizz.quizz.R.id.b_r1;
import static fr.polytech.quizz.quizz.R.id.b_r2;
import static fr.polytech.quizz.quizz.R.id.b_r3;
import static fr.polytech.quizz.quizz.R.id.b_r4;

/**
 * Created by Sachouw on 11/10/2017.
 */

public class questionFrag extends Fragment {
    private QuizzService quizzService;
    private Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question, container, false);
        /*
        intent = new Intent(new Intent(this.getActivity(),QuizzService.class));
        quizzService.startActivity(intent);
       quizzService.onHandleIntent(intent);*/

        Intent it = new Intent(this.getContext(), QuizzService.class);
        it.putExtra("Key", "Value");
        getContext().startService(it);

        Button button = (Button) view.findViewById(b_r1);
        button.setText("Reponse A");

        button = (Button) view.findViewById(b_r2);
        button.setText("Reponse B");

        button = (Button) view.findViewById(b_r3);
        button.setText("Reponse C");

        button = (Button) view.findViewById(b_r4);
        button.setText("Reponse D");

        return view;
    }



}
