package com.mike.triggasounds.triggasounds.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.mike.triggasounds.triggasounds.R;
import com.mike.triggasounds.triggasounds.adapter.MenuAdapter;
import com.mike.triggasounds.triggasounds.adapter.TrackAdapter;
import com.mike.triggasounds.triggasounds.model.Track;

import java.util.Locale;

public class MainActivity extends ActionBarActivity {

    //tracklist vars
    private ListView mTrackListView;
    Track trackList[] = new Track[] {
            new Track(R.drawable.logo, "Mashup 1"),
            new Track(R.drawable.logo, "Mashup 2"),
            new Track(R.drawable.logo, "Mashup 3"),
            new Track(R.drawable.logo, "Mix 1"),
            new Track(R.drawable.logo, "Mix 2"),
            new Track(R.drawable.logo, "Mix 3")
    };
    //drawer vars
    private String[] mMenuOptionsArray;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mBaseTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        mBaseTitle = mDrawerTitle = getTitle();
        mMenuOptionsArray = getResources().getStringArray(R.array.menuOptionsArray);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mTrackListView = (ListView) findViewById(R.id.lvTracks);

        //Set adapter for list view AND click listener
        MenuAdapter menuAdapter = new MenuAdapter(this, R.layout.menu_item, mMenuOptionsArray);
        mDrawerList.setAdapter(menuAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        //TrackAdapter trackAdapter = new TrackAdapter(this, R.layout.listview_row, trackList);
        //mTrackListView.setAdapter(trackAdapter);

        //Shadow over main content when drawer open
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        //Enable action_bar app-icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            //Called when drawer is completely closed
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mBaseTitle);
                invalidateOptionsMenu(); //creates call to onPrepareOptionsMenu()
            }
            //Called when drawer is completely open
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };

        //Set the drawer_layout toggle as the listener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }

        initMenu();
        initTrackList();
        modifyActionBarWhenDrawerOpen(savedInstanceState);

    }

    private void initMenu() {
    }

    private void initTrackList() {
    }

    private void modifyActionBarWhenDrawerOpen(Bundle savedInstanceState) {
    }

    //MAIN ACTIVITY METHODS
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //Pass any config change to drawer toggle
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Sync toggle state after restoreInstance
        mDrawerToggle.syncState();

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    //DRAWER LISTENER
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    private void selectItem(int position) {
        FragmentManager fragmentManager = getFragmentManager();

        switch (position) {
            case 0:
                Fragment streamFrag = new StreamFragment();
                Bundle streamArgs = new Bundle();
                streamArgs.putInt(EventFragment.ARG_MENU_OPTION, position);
                streamFrag.setArguments(streamArgs);

                fragmentManager.beginTransaction().replace(R.id.content_frame, streamFrag).commit();

                // update selected item and title, then close the drawer
                mDrawerList.setItemChecked(position, true);
                setTitle(mMenuOptionsArray[position]);
                mDrawerLayout.closeDrawer(mDrawerList);
                break;

            case 1:
                // update the main content by replacing fragments
                Fragment eventFrag = new EventFragment();
                Bundle eventArgs = new Bundle();
                eventArgs.putInt(EventFragment.ARG_MENU_OPTION, position);
                eventFrag.setArguments(eventArgs);

                fragmentManager.beginTransaction().replace(R.id.content_frame, eventFrag).commit();

                // update selected item and title, then close the drawer
                mDrawerList.setItemChecked(position, true);
                setTitle(mMenuOptionsArray[position]);
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
            case 2:
                // update the main content by replacing fragments
                /*Fragment galleryFrag = new GalleryFragment();
                Bundle galleryArgs = new Bundle();
                galleryArgs.putInt(GalleryFragment.ARG_MENU_OPTION, position);
                galleryFrag.setArguments(galleryArgs);

                fragmentManager.beginTransaction().replace(R.id.content_frame, galleryFrag).commit();

                // update selected item and title, then close the drawer
                mDrawerList.setItemChecked(position, true);
                setTitle(mMenuOptionsArray[position]);
                mDrawerLayout.closeDrawer(mDrawerList);*/
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;

        }
    }
    public static class EventFragment extends ListFragment {
        public static final String ARG_MENU_OPTION = "menu_option";
        public EventFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            String[] eventAdapter = new String[]{
                    "Event 1",
                    "Event 2",
            };

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),
                    android.R.layout.simple_list_item_1,eventAdapter);

            setListAdapter(adapter);

            return super.onCreateView(inflater, container, savedInstanceState);

        }
    }
    public static class StreamFragment extends ListFragment {
        public static final String ARG_MENU_OPTION = "menu_option";
        public StreamFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            String[] streamAdapter = new String[]{
                    "Mix 1",
                    "Mix 2",
                    "Mashup 1",
            };

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),
                    android.R.layout.simple_list_item_1,streamAdapter);

            setListAdapter(adapter);

            return super.onCreateView(inflater, container, savedInstanceState);

        }
    }
    //MENU FRAGMENT
    public static class MenuFragment extends Fragment {
        public static final String MENU_ITEM_NUM = "item_number";

        public MenuFragment() {
            // Empty constructor REQUIRED for fragment subclasses
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.activity_main, container, false);

            int menuOptionNum = getArguments().getInt(MENU_ITEM_NUM);

            String menuItemName = getResources().getStringArray(R.array.menuOptionsArray)[menuOptionNum];

            int resourceRnum = getResources()
                    .getIdentifier(menuItemName.toLowerCase(Locale.getDefault()), "drawable", getActivity().getPackageName());

            ((ListView) rootView.findViewById(R.id.lvTracks)).setSelection(resourceRnum);
            //((ImageView) rootView.findViewById(R.id.imageId)).setImageResource(resourceRnum);

            getActivity().setTitle(menuItemName);

            return rootView;
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mBaseTitle = title;
        getActionBar().setTitle(mBaseTitle);
    }


}
