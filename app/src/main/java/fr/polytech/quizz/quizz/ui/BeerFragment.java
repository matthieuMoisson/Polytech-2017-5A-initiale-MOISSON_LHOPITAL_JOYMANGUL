package fr.polytech.quizz.quizz.ui;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import fr.polytech.quizz.quizz.R;
import fr.polytech.quizz.quizz.model.Beer;
import fr.polytech.quizz.quizz.service.BeerService;


public class BeerFragment extends Fragment {
    final static String ARG_POSITION = "position";
    private View view;
    private ProgressDialog progress;

    int mCurrentPosition = -1;

    public BeerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_beer, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        progress=new ProgressDialog(getContext());
        progress.setMessage( getString(R.string.loading));
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();


        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateArticleView(mCurrentPosition);
        }
    }

    public void updateArticleView(int beerId) {
        BeerService.startActionGetBeer(getContext(), new BeerReceiver(), beerId);
        mCurrentPosition = beerId;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    private void showBeer(String strBeer) {
        Gson gson = new Gson();
        Beer beer = gson.fromJson(strBeer, Beer.class);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView tv_name = (TextView) view.findViewById(R.id.name);
        TextView tv_tagline = (TextView) view.findViewById(R.id.tagline);
        TextView tv_alcohol = (TextView) view.findViewById(R.id.tv_alcohol);
        TextView tv_description = (TextView) view.findViewById(R.id.tv_description);
        tv_name.setText(beer.getName());
        tv_tagline.setText(beer.getTagline());
        tv_alcohol.setText(String.valueOf(beer.getAbv()) + " %");
        tv_description.setText(beer.getDescription());

        Picasso.with(getContext()).load(beer.getImageUrl()).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });



        // Hide the progress bar
        progress.hide();
    }

    public class BeerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(BeerService.ACTION_GET_BEER)) {
                showBeer(intent.getStringExtra(BeerService.EXTRA_RESULT));
            }
        }
    }
}
