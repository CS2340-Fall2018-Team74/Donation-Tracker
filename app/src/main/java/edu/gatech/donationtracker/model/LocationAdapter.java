package edu.gatech.donationtracker.model;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gatech.donationtracker.R;


public class LocationAdapter  extends ArrayAdapter<Location> {



    public LocationAdapter(Context context, ArrayList<Location> locations) {
        super(context, 0, locations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
//        if (listItemView == null) {
//            listItemView = LayoutInflater.from(getContext()).inflate(
//                    R.layout.list_location, parent, false);
//        }

        // Get the {@link Location} object located at this position in the list
        Location currentLocation = getItem(position);


//        TextView numberTextView = (TextView) listItemView.findViewById(R.id.number);
//        numberTextView.setText(currentLocation.get_key());

        // Find the TextView in the list_item.xml layout with the ID text_view.
        TextView nameTextView = listItemView.findViewById(R.id.name_text_view);
        // Get the Location translation from the currentLocation object and set this text on
        // the TextView.
        nameTextView.setText(currentLocation.getName());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
//        TextView typeTextView = (TextView) listItemView.findViewById(R.id.type_text_view);
//        // Get the default translation from the currentWord object and set this text on
//        // the default TextView.
//        typeTextView.setText(currentLocation.getType());

//

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}

