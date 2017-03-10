package com.dhh.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dhh.activity.MainActivity;
import com.dhh.fragment.FragmentMonAn;
import com.dhh.object.DanhMucCon;
import com.dhh.object.MonAn;

import java.util.ArrayList;

import duong.ChucNangPhu;

/**
 * Created by Hong on 3/8/2017.
 */

/**
 * dùng FragmentStatePagerAdapter
 * đùng dại dùng FragmentPagerAdapter
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<DanhMucCon> danhMucCons;
    private ArrayList<MonAn> monAns;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<DanhMucCon> danhMucCons, ArrayList<MonAn> monAns) {
        super(fm);
        this.danhMucCons=danhMucCons;
        ChucNangPhu.showLog("monAns "+ monAns.size());
        ChucNangPhu.showLog("danhMucCons "+ danhMucCons.size());
        this.monAns=monAns;
    }

    /**
     * nó chỉ vào cái vẹo này 1 lần
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        String id_danh_muc_con= danhMucCons.get(position).getId();
        ArrayList<MonAn> arrayMonAn= new ArrayList<>();
        for(MonAn monAn :monAns) if(monAn.getId_danhmuccon().equals(id_danh_muc_con)) arrayMonAn.add(monAn);
        FragmentMonAn fragmentMonAn =new FragmentMonAn();
        Bundle bundle=new Bundle();
        ChucNangPhu.showLog("putSerializable "+arrayMonAn.size());
        bundle.putSerializable(MainActivity.KEY_MON_AN,arrayMonAn);
        fragmentMonAn.setArguments(bundle);
        return fragmentMonAn;
    }

    @Override
    public int getCount() {
        return danhMucCons.size();

    }
    @Override
    public CharSequence getPageTitle(int position) {
        return danhMucCons.get(position).getTen_danh_muc();
    }
}
