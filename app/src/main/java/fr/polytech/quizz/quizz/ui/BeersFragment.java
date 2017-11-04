package fr.polytech.quizz.quizz.ui;

import android.app.ListFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.polytech.quizz.quizz.model.Beer;
import fr.polytech.quizz.quizz.rest.BeerInterface;
import fr.polytech.quizz.quizz.service.BeerService;

import java.util.List;


public class BeersFragment extends ListFragment {
    private OnFragmentInteractionListener mListener;
    private BeerInterface beerInterface;

    public BeersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        BeerService.startActionGetAllBeers(getContext(), new BeersReceiver());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        /*
        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the parent activity of selected item
        mListener.onBeerSelected((int) l.getAdapter().getItemId(position));

        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onBeerSelected(int position);
    }

    private void updateList(String strBeers) {
        Gson gson = new Gson();
        List<Beer> beers = gson.fromJson(strBeers, new TypeToken<List<Beer>>(){}.getType());
        setListAdapter(new BeerRow(getActivity(), beers));
    }

    /**
     * The receiver when the service had completed its tasks
     * Put here the different tasks to carried out when the service has finished
     */
    public class BeersReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(BeerService.ACTION_GET_ALL_BEERS)) {
                updateList(intent.getStringExtra(BeerService.EXTRA_RESULT));
            }
        }
    }
}
