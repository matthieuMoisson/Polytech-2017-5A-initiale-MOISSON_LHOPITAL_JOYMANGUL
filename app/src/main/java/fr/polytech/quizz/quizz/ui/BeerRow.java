package fr.polytech.quizz.quizz.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import fr.polytech.quizz.quizz.R;
import fr.polytech.quizz.quizz.model.Beer;

import java.util.List;

/**
 * Created by JOYMANGUL Jensen Selwyn
 * on 03-Nov-17.
 */
public class BeerRow extends BaseAdapter {
    Context context;
    List<Beer> beers;
    private static LayoutInflater inflater = null;

    public BeerRow(Context context, List beers) {
        this.context = context;
        this.beers = beers;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return beers.size();
    }

    @Override
    public Object getItem(int position) {
        return beers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return beers.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.beer_row, null);
        TextView text = (TextView) vi.findViewById(R.id.text);
        TextView text_2 =  (TextView) vi.findViewById(R.id.text_2);
        text.setText(beers.get(position).getName());
        text_2.setText(String.valueOf(beers.get(position).getAbv()));
        return vi;
    }
}
