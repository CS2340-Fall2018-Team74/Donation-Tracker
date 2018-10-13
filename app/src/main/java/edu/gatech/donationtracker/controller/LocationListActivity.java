package edu.gatech.donationtracker.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Location;
import edu.gatech.donationtracker.model.Model;

//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.app.AppCompatActivity;
//
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import 	android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.List;
//
//import edu.gatech.donationtracker.R;
//import edu.gatech.donationtracker.model.Location;
//import edu.gatech.donationtracker.model.Model;
//
public class LocationListActivity extends AppCompatActivity {


//
//    private FloatingActionButton mFab;
//    private RecyclerView mRecyclerView;
//    private SwipeRefreshLayout mSwipeRefreshLayout;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_location_list);
//
//        //Step 1.  Setup the recycler view by getting it from our layout in the main window
//        View recyclerView = findViewById(R.id.location_list);
//        assert recyclerView != null;
//        //Step 2.  Hook up the adapter to the view
//        setupRecyclerView((RecyclerView) recyclerView);
//        Log.d("LocationListActivity", Model.getInstance().getLocations().get(0).getCity());
//
//    }
//
//    /**
//     * Set up an adapter and hook it to the provided view
//     * @param recyclerView  the view that needs this adapter
//     */
//    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
//        Model model = Model.getInstance();
//        recyclerView.setAdapter(new SimpleLocationRecyclerViewAdapter(model.getLocations()));
//    }
//
//    /**
//     * This inner class is our custom adapter.  It takes our basic model information and
//     * converts it to the correct layout for this view.
//     *
//     * In this case, we are just mapping the toString of the Location object to a text field.
//     */
//    public class SimpleLocationRecyclerViewAdapter
//            extends RecyclerView.Adapter<SimpleLocationRecyclerViewAdapter.ViewHolder> {
//
//        /**
//         * Collection of the items to be shown in this list.
//         */
//        private final List<Location> mLocations;
//
//        /**
//         * set the items to be used by the adapter
//         *
//         * @param items the list of items to be displayed in the recycler view
//         */
//        public SimpleLocationRecyclerViewAdapter(List<Location> items) {
//            mLocations = items;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            /*
//
//              This sets up the view for each individual item in the recycler display
//              To edit the actual layout, we would look at: res/layout/location_list_content.xml
//              If you look at the example file, you will see it currently just 2 TextView elements
//             */
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.location_list_content, parent, false);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final ViewHolder holder, int position) {
//
//            final Model model = Model.getInstance();
//            Log.d("ghh", String.valueOf(model.getLocations().size()));
//            /*
//            This is where we have to bind each data element in the list (given by position parameter)
//            to an element in the view (which is one of our two TextView widgets
//             */
//            //start by getting the element at the correct position
//            holder.mLocation = mLocations.get(position);
//            /*
//              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
//              textview and the string rep of a location in the other.
//             */
//            holder.mNameView.setText("" + mLocations.get(position).getName());
//            holder.mTypeView.setText(mLocations.get(position).getType());
//
//            /*
//             * set up a listener to handle if the user clicks on this list item, what should happen?
//             */
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //on a phone, we need to change windows to the detail view
//                    Context context = v.getContext();
//                    //create our new intent with the new screen (activity)
//                    Intent intent = new Intent(context, LocationDetailActivity.class);
//                        /*
//                            pass along the id of the location so we can retrieve the correct data in
//                            the next window
//                         */
//                    intent.putExtra(LocationDetailFragment.ARG_COURSE_ID, holder.mLocation.getName());
//
//                    model.setCurrentLocation(holder.mLocation);
//
//                    //now just display the new window
//                    context.startActivity(intent);
//
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return mLocations.size();
//        }
//
//        /**
//         * This inner class represents a ViewHolder which provides us a way to cache information
//         * about the binding between the model element (in this case a Location) and the widgets in
//         * the list view (in this case the two TextView)
//         */
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            public final View mView;
//            public final TextView mNameView;
//            public final TextView mTypeView;
//            public Location mLocation;
//
//            public ViewHolder(View view) {
//                super(view);
//                mView = view;
//                mNameView = (TextView) view.findViewById(R.id.name);
//                mTypeView = (TextView) view.findViewById(R.id.type);
//            }
//
//            @Override
//            public String toString() {
//                return super.toString() + " " + mTypeView.getText() + "";
//            }
//        }
//    }
//}




/**
 * THIS IS OUR TOP_LEVEL WINDOW THAT THE USER FIRST SEES IN THE APPLICATION!
 *
 * An activity representing a list of Locations. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link LocationDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 *
 * This is using a RecyclerView, which is the preferred standard for displaying
 * lists of things like our locations.
 */


    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.  For 2340, this is optional, since multi-display support is extra credit.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        

        //Step 1.  Setup the recycler view by getting it from our layout in the main window
        View recyclerView = findViewById(R.id.location_list);
        assert recyclerView != null;
        //Step 2.  Hook up the adapter to the view
        setupRecyclerView((RecyclerView) recyclerView);

        //this is only needed if you are doing an optional support for multiple display sizes
        if (findViewById(R.id.location_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
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
     * In this case, we are just mapping the toString of the Location object to a text field.
     */
    public class SimpleLocationRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleLocationRecyclerViewAdapter.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private final List<Location> mLocations;

        /**
         * set the items to be used by the adapter
         * @param items the list of items to be displayed in the recycler view
         */
        public SimpleLocationRecyclerViewAdapter(List<Location> items) {
            mLocations = items;
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
            Log.d("ghh", String.valueOf(model.getLocations().size()));

            /*
            This is where we have to bind each data element in the list (given by position parameter)
            to an element in the view (which is one of our two TextView widgets
             */
            //start by getting the element at the correct position
            holder.mLocation = mLocations.get(position);
            /*
              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
              textview and the string rep of a location in the other.
             */
            holder.mNameView.setText("" + mLocations.get(position).getName());
            holder.mTypeView.setText(mLocations.get(position).getType());

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        //if a two pane window, we change the contents on the main screen
                        Bundle arguments = new Bundle();
                        arguments.putInt(LocationDetailFragment.ARG_LOCATION_KEY, Integer.parseInt(holder.mLocation.getKey()));

                        LocationDetailFragment fragment = new LocationDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.location_detail_container, fragment)
                                .commit();
                    } else {
                        //on a phone, we need to change windows to the detail view
                        Context context = v.getContext();
                        //create our new intent with the new screen (activity)
                        Intent intent = new Intent(context, LocationDetailActivity.class);
                        /*
                            pass along the id of the location so we can retrieve the correct data in
                            the next window
                         */
                        intent.putExtra(LocationDetailFragment.ARG_LOCATION_KEY, holder.mLocation.getKey());

                        model.setCurrentCourse(holder.mLocation);

                        //now just display the new window
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mLocations.size();
        }

        /**
         * This inner class represents a ViewHolder which provides us a way to cache information
         * about the binding between the model element (in this case a Location) and the widgets in
         * the list view (in this case the two TextView)
         */

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mNameView;
            public final TextView mTypeView;
            public Location mLocation;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mNameView = view.findViewById(R.id.name);
                mTypeView = view.findViewById(R.id.type);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTypeView.getText() + "'";
            }
        }
    }
}
