package com.example.window10edu.aroneincement;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerController extends FragmentPagerAdapter {
    int tabcounts;

    public PagerController(FragmentManager fm,int tabcounts) {
        super(fm);
        this.tabcounts = tabcounts;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new mix();
            case 1:
                return new cal();
            case 2:
                return new check();
            case 3:
                return new chat();

                default:
                    return null;

        }
    }

    @Override
    public int getCount() {
        return tabcounts;
    }
}
