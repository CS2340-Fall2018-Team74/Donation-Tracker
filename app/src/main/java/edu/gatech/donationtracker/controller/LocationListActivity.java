package edu.gatech.donationtracker.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Locations;
import edu.gatech.donationtracker.model.Model;


public class LocationListActivity extends AppCompatActivity {

    static String[] listHeader = {"No.", "Locations name", "Type"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        

        //Step 1.  Setup the recycler view by getting it from our layout in the main window
        View recyclerView = findViewById(R.id.location_list);
        assert recyclerView != null;
        //Step 2.  Hook up the adapter to the view
        setupRecyclerView((RecyclerView) recyclerView);
    }

    /**
     * Set up an adapter and hook it to the provided view
     * @param recyclerView  the view that needs this adapter
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Model model = Model.getInstance();
        recyclerView.setAdapter(new SimpleLocationRecyclerViewAdapter(model.getLocations()));
    }

    /**
     * This inner class is our custom adapter.  It takes our basic model information and
     * converts it to the correct layout for this view.
     *
     * In this case, we are just mapping the toString of the Locations object to a text field.
     */
    public class SimpleLocationRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleLocationRecyclerViewAdapter.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private final List<Locations> mLocations;


        /**
         * set the items to be used by the adapter
         * @param locations the list of locations to be displayed in the recycler view
         */
        public SimpleLocationRecyclerViewAdapter(List<Locations> locations) {
            mLocations = locations;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            /*

              This sets up the view for each individual item in the recycler display
              To edit the actual layout, we would look at: res/layout/location_list_content.xml
              If you look at the example file, you will see it currently just 2 TextView elements
             */
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.location_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            final Model model = Model.getInstance();
            /*
            This is where we have to bind each data element in the list (given by position parameter)
            to an element in the view (which is one of our two TextView widgets
             */
            //start by getting the element at the correct position
            holder.mLocation = mLocations.get(position);
            /*
              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
              textView and the string rep of a location in the other.
             */
            holder.mNameView.setText(mLocations.get(position).getName());
            holder.mTypeView.setText(mLocations.get(position).getType());

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //on a phone, we need to change windows to the detail view
                    Context context = v.getContext();
                    //create our new intent with the new screen (activity)
                    Intent intent = new Intent(context, LocationDetailActivity.class);
                    /*
                        pass along the id of the location so we can retrieve the correct data in
                        the next window
                     */
                    model.setCurrentLocation(holder.mLocation);
                    //now just display the new window
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mLocations.size();
        }

        /**
         * This inner class represents a ViewHolder which provides us a way to cache information
         * about the binding between the model element (in this case a Locations) and the widgets in
         * the list view (in this case the two TextView)
         */

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mNameView;
            public final TextView mTypeView;
            public Locations mLocation;

            /** constructor **/
            public ViewHolder(View view) {
                super(view);
                mView = view;
                mNameView = view.findViewById(R.id.location_list_name);
                mTypeView = view.findViewById(R.id.location_list_type);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTypeView.getText() + "'";
            }
        }
    }
}
