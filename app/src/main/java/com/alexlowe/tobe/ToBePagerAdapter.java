package com.alexlowe.tobe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keyes on 6/10/2016.
 */
public class ToBePagerAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> mFragmentList = new ArrayList<>();

    public ToBePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment){
        mFragmentList.add(fragment);
    }

    public void removeFragment(int position){
        ToBe.shortTerm.remove(position);
        mFragmentList = recreateList();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public ArrayList<Fragment> recreateList(){
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new EntryFragment());
        for(ToBe toBe: ToBe.shortTerm){
            ToBeFragment fragment = ToBeFragment.newInstance(toBe);
            list.add(fragment);
        }
        return list;
    }
}
