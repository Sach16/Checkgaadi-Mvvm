package com.skpissay.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.skpissay.model.api.entity.Tag;
import com.skpissay.view.base.BaseFragment;
import com.skpissay.view.fragments.MediaListFragment;

/**
 * Created by S.K. Pissay on 04,July,2018.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {

    int m_cNumOfTabs;
    Tag mTag;
    private Object mObject;

    //write inner fragment items below

    public BaseFragment m_cObjFragmentBase;

    public MainPagerAdapter(FragmentManager pFragment) {
        super(pFragment);
    }

    public void setArgs(BaseFragment pObjFragmentBase, int pNumOfTabs, Tag pTag, Object pObject) {
        this.m_cObjFragmentBase = pObjFragmentBase;
        this.m_cNumOfTabs = pNumOfTabs;
        this.mTag = pTag;
        this.mObject = pObject;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                m_cObjFragmentBase = MediaListFragment.newInstance(position, mTag, "Popular");
                return m_cObjFragmentBase;
            case 1:
                m_cObjFragmentBase = MediaListFragment.newInstance(position, mTag, "Recent");
                return m_cObjFragmentBase;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return m_cNumOfTabs;
    }
}
