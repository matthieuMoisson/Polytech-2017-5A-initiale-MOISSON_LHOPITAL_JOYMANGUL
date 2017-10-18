package fr.polytech.quizz.quizz;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static fr.polytech.quizz.quizz.R.id.buttonVsIA;

public class MainActivity extends AppCompatActivity implements homeFrag.onButtonClick {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        homeFrag fragment = new homeFrag();

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(int idButton) {
        switch(idButton) {
            case buttonVsIA:
                questionFrag question = new questionFrag();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, question);
                fragmentTransaction.commit();
                break;
            default :
                System.out.println("Erreur");
        }
    }


}
