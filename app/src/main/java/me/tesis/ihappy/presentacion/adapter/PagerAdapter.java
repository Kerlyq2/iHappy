package me.tesis.ihappy.presentacion.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import me.tesis.ihappy.presentacion.Fragments.DiagnosticoFragment;
import me.tesis.ihappy.presentacion.Fragments.ScheduleFragment;
import me.tesis.ihappy.presentacion.Fragments.SintomasFragment;


public class PagerAdapter extends FragmentPagerAdapter {
    private int numberOfTabs;


    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs=numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SintomasFragment();
            case 1:
                return new DiagnosticoFragment();
            case 2:
                return  new ScheduleFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
