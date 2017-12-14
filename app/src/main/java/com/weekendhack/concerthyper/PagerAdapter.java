package com.weekendhack.concerthyper;

/**
 * Created by hedholm on 2017-09-30.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.weekendhack.concerthyper.concerts.AddConcertsFragment;
import com.weekendhack.concerthyper.concerts.MyConcertsFragment;
import com.weekendhack.concerthyper.playlist.MyPlaylistFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AddConcertsFragment tab1 = new AddConcertsFragment();
                return tab1;
            case 1:
                MyConcertsFragment tab2 = new MyConcertsFragment();
                return tab2;
            case 2:
                MyPlaylistFragment tab3 = new MyPlaylistFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
