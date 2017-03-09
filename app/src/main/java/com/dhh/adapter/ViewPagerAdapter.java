package com.dhh.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.dhh.activity.MainActivity;
import com.dhh.fragment.FragmentMonAn;
import com.dhh.object.DanhMucCon;
import com.dhh.object.MonAn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Hong on 3/8/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<DanhMucCon> danhMucConsAdapter;
    private ArrayList<MonAn> listmonAnAdapter;
    private FragmentMonAn fragmentMonAn;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<DanhMucCon> danhMucConsAdapter,ArrayList<MonAn> listmonAnAdapter,FragmentMonAn fragmentMonAn) {
        super(fm);
        this.danhMucConsAdapter=danhMucConsAdapter;
        this.fragmentMonAn=fragmentMonAn;
        this.listmonAnAdapter=listmonAnAdapter;
    }

    @Override
    public Fragment getItem(int position) {
        fragmentMonAn =new FragmentMonAn();
        return fragmentMonAn;
    }

    @Override
    public int getCount() {
        Log.e("ViewPagerAdapter ",danhMucConsAdapter.size()+"");
        return danhMucConsAdapter.size();

    }
    @Override
    public CharSequence getPageTitle(int position) {
        return danhMucConsAdapter.get(position).getTen_danh_muc();
    }
}
