package me.tesis.ihappy.presentacion.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.tesis.ihappy.R;
import me.tesis.ihappy.presentacion.adapter.PagerAdapter;

public class MainActivity extends AppCompatActivity {



    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    MainActivity activity = null;


    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        Log.e("LIFE_CYCLE", "OnCreate");

       /* mTabLayout = (TabLayout) findViewById(R.id.tablayout);*/
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final TabLayout.Tab libros = mTabLayout.newTab();
        final TabLayout.Tab historial = mTabLayout.newTab();
        final TabLayout.Tab perfil = mTabLayout.newTab();
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        libros.setIcon(R.drawable.ic_sintomas);
        historial.setIcon(R.drawable.ic_stadistic_unselected);
        perfil.setIcon(R.drawable.ic_schedule_unselected);

        mTabLayout.addTab(libros, 0);
        mTabLayout.addTab(historial, 1);
        mTabLayout.addTab(perfil, 2);
        // Set up the ViewPager with the sections adapter.
       /* mViewPager = (ViewPager) findViewById(R.id.viewPager);*/
        mViewPager.setOffscreenPageLimit(2);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        if (getApplicationContext() != null) {
            mViewPager.setAdapter(adapter);
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setScrollPosition(position, 0, true);
                switch (position) {
                    case 0: {
                        libros.setIcon(R.drawable.ic_sintomas);
                        historial.setIcon(R.drawable.ic_stadistic_unselected);
                        perfil.setIcon(R.drawable.ic_schedule_unselected);
                        break;
                    }
                    case 1: {
                        libros.setIcon(R.drawable.ic_sintomas_unselected);
                        historial.setIcon(R.drawable.ic_stadistic);
                        perfil.setIcon(R.drawable.ic_schedule_unselected);
                        break;
                    }
                    case 2: {
                        libros.setIcon(R.drawable.ic_sintomas_unselected);
                        historial.setIcon(R.drawable.ic_stadistic_unselected);
                        perfil.setIcon(R.drawable.ic_schedule);
                    }

                }
                mTabLayout.setSelected(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

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
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
