package fr.polytech.quizz.quizz.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import fr.polytech.quizz.quizz.R;

public class MainActivity extends FragmentActivity implements BeersFragment.OnFragmentInteractionListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            BeersFragment fragment = new BeersFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            fragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            fragmentManager = getFragmentManager();

            FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
    }


    @Override
    public void onBeerSelected(int beerId) {
        // The user selected a beer of from the beers Fragmenr

        /*
        // Capture the article fragment from the activity layout
        BeerFragment articleFrag = (BeerFragment)
                getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        if (articleFrag != null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
            articleFrag.updateArticleView(position);

        } */

        // If the frag is not available, we're in the one-pane layout and must swap frags...

        // Create fragment and give it an argument for the selected article
        BeerFragment newFragment = new BeerFragment();
        Bundle args = new Bundle();
        args.putInt(BeerFragment.ARG_POSITION, beerId);
        newFragment.setArguments(args);

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, newFragment);
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();
    }
}
