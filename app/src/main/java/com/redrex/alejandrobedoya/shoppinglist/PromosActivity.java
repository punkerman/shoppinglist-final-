package com.redrex.alejandrobedoya.shoppinglist;

import java.sql.SQLException;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.PointF;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.redrex.alejandrobedoya.shoppinglist.BdConexion.MyListContract;
import com.redrex.alejandrobedoya.shoppinglist.BdConexion.MyListCursorAdapter;
import com.redrex.alejandrobedoya.shoppinglist.BdConexion.MyListDbAdapter;
import com.redrex.alejandrobedoya.shoppinglist.BdConexion.MyPointsPricesCursorAdapter;
import com.redrex.alejandrobedoya.shoppinglist.BdConexion.PromPricesCursorAdapter;


public class PromosActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promos);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_promos, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment fragment;
            if(position ==1){
                return  PlaceholderFragment2.newInstance(position+1);

            }else if (position==2) {
                return PlaceholderFragment3.newInstance(position + 1);
            }
            else {return PlaceholderFragment.newInstance(position + 1);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements QRCodeReaderView.OnQRCodeReadListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        MyListDbAdapter ldbAdapter;
        private QRCodeReaderView mydecoderview;
        private TextView myTextView;
        private TextView myTextView2;
        private TextView myTextView3;
        private TextView myTextView4;
        private ImageButton boton1;


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_promos, container, false);

            mydecoderview = (QRCodeReaderView) rootView.findViewById(R.id.qrdecoderview);
            mydecoderview.setOnQRCodeReadListener(this);
            mydecoderview.setVisibility(View.GONE);
            myTextView = (TextView) rootView.findViewById(R.id.exampleTextView);
            myTextView2 = (TextView) rootView.findViewById(R.id.exampleTextView2);
            myTextView3 = (TextView) rootView.findViewById(R.id.textViewA1);
            myTextView4 = (TextView) rootView.findViewById(R.id.textViewA2);

            myTextView3.setVisibility(View.GONE);
            myTextView4.setVisibility(View.GONE);
            boton1 = (ImageButton) rootView.findViewById(R.id.imageButtonQR);
            boton1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    boton1.setVisibility(View.GONE);
                    mydecoderview.setVisibility(View.VISIBLE);
                    mydecoderview.getCameraManager().startPreview();

                }
            });

            return rootView;
        }

        @Override
        public void onQRCodeRead(String text, PointF[] points) {
            ldbAdapter = new MyListDbAdapter(getActivity().getApplicationContext());
             ContentValues reg = new ContentValues();
            String[] textoPuntos = text.split(",");
            myTextView3.setVisibility(View.VISIBLE);
            myTextView4.setVisibility(View.VISIBLE);
            myTextView.setText(textoPuntos[0]+ " puntos");
            myTextView2.setText(textoPuntos[1]);
            mydecoderview.getCameraManager().stopPreview();
            mydecoderview.setVisibility(View.GONE);
            reg.put(MyListContract.MyPointsResultEntry.Column_Points,Integer.parseInt(textoPuntos[0]));
            reg.put(MyListContract.MyPointsResultEntry.Column_Date,textoPuntos[1]);
            try {
                ldbAdapter.insertPoints(reg);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Toast.makeText(getActivity().getApplicationContext(), "puntos agregados" , Toast.LENGTH_LONG).show();

        }


        // Called when your device have no camera
        @Override
        public void cameraNotFound() {

        }

        // Called when there's no QR codes in the camera preview image
        @Override
        public void QRCodeNotFoundOnCamImage() {

        }

        @Override
        public void onResume() {
            super.onResume();
            mydecoderview.getCameraManager().startPreview();
        }

        @Override
        public void onPause() {
            super.onPause();
            mydecoderview.getCameraManager().stopPreview();
        }





    }

    public static class PlaceholderFragment2 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        MyListDbAdapter pdbAdapter;
        ListView lv;
        PromPricesCursorAdapter pCursor;
        Cursor cursor;
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment2 newInstance(int sectionNumber) {
            PlaceholderFragment2 fragment = new PlaceholderFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment2() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_prices, container, false);
            lv =(ListView) rootView.findViewById(R.id.promPricesLV);
            pdbAdapter= new MyListDbAdapter(getActivity().getApplicationContext());

            try {
                pdbAdapter.abrir();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                consultar();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return rootView;
        }

        private void consultar() throws SQLException {


            cursor=pdbAdapter.getProPricesCursor();
            getActivity().startManagingCursor(cursor);

            pCursor =new PromPricesCursorAdapter(getActivity().getApplicationContext(),cursor);
            lv.setAdapter(pCursor);
        }
    }
    public static class PlaceholderFragment3 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        MyListDbAdapter dbAdapter;
        MyPointsPricesCursorAdapter mpCursorAdaper;
        Cursor cursor;
        ListView lv;
        TextView tv;
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment3 newInstance(int sectionNumber) {
            PlaceholderFragment3 fragment = new PlaceholderFragment3();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment3() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.my_points_resultview, container, false);
           dbAdapter = new MyListDbAdapter(getActivity().getApplicationContext());
            tv =(TextView) rootView.findViewById(R.id.textViewPoints);
            try {
                dbAdapter.abrir();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                consultar();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rootView;
        }

        private void consultar() throws SQLException{
           Integer crsor = dbAdapter.getMypointsCursor();
            tv.setText(crsor+ "PUNTOS");
            /*
            getActivity().startManagingCursor(cursor);
            mpCursorAdaper = new MyPointsPricesCursorAdapter(getActivity().getApplicationContext(),cursor);
            lv.setAdapter(mpCursorAdaper);
            */
        }
    }

}
