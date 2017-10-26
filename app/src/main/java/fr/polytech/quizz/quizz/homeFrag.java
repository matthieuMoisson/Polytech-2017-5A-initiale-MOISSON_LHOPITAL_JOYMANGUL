package fr.polytech.quizz.quizz;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import static fr.polytech.quizz.quizz.R.id.buttonVsIA;

/**
 * Created by Sachouw on 11/10/2017.
 */

public class homeFrag extends Fragment {

    public interface onButtonClick {
        void onClick(int indiceButton);
    }

    private onButtonClick click;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        onClickEventDo(view, buttonVsIA);
        return view;
    }

    private void onClickEventDo(View view, final int idButton) {
        Button button = (Button) view.findViewById(idButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                click.onClick(idButton);
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            click = (onButtonClick) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(String.valueOf(context) + " must implement onClickEventDo");
        }
    }
}



