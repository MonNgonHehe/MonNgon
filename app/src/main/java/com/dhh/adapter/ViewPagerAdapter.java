package com.dhh.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.dhh.activity.MainActivity;
import com.dhh.fragment.FragmentMonAn;
import com.dhh.object.DanhMucCon;
import com.dhh.object.MonAn;

import java.util.ArrayList;

import duong.ChucNangPhu;

/**
 * Created by Hong on 3/8/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<DanhMucCon> danhMucConsAdapter;
    private ArrayList<MonAn> listmonAnAdapter;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<DanhMucCon> danhMucConsAdapter, ArrayList<MonAn> listmonAnAdapter) {
        super(fm);
        this.danhMucConsAdapter=danhMucConsAdapter;
        this.listmonAnAdapter=listmonAnAdapter;
    }

    @Override
    public Fragment getItem(int position) {
        ChucNangPhu.showLog("vị trí"+position);
        String id_danh_muc_con= danhMucConsAdapter.get(position).getId();
        ChucNangPhu.showLog("getItem Hong"+danhMucConsAdapter.get(position).getId());
        ChucNangPhu.showLog("getItem Hong hhhhh"+listmonAnAdapter.size());
        ArrayList<MonAn> arrayMonAn= new ArrayList<>();
        for(MonAn monAn :listmonAnAdapter){
           if(monAn.getId_danhmuccon().equals(id_danh_muc_con)){
               arrayMonAn.add(monAn);
           }
        }
        FragmentMonAn fragmentMonAn =new FragmentMonAn();
        Bundle bundle=new Bundle();
        bundle.putSerializable(MainActivity.KEY_MON_AN,arrayMonAn);
        ChucNangPhu.showLog("getItem  ArrMonAn" +arrayMonAn.size());
        ChucNangPhu.showLog("getItem " +listmonAnAdapter.size());
        fragmentMonAn.setArguments(bundle);
        return fragmentMonAn;
    }

    @Override
    public int getCount() {
        return danhMucConsAdapter.size();

    }
    @Override
    public CharSequence getPageTitle(int position) {
        return danhMucConsAdapter.get(position).getTen_danh_muc();
    }
}
