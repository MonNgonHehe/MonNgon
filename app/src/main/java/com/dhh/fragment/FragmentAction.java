package com.dhh.fragment;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhh.activity.MainActivity;
import com.dhh.adapter.ViewPagerAdapter;
import com.dhh.monngon.R;
import com.dhh.object.DanhMuc;
import com.dhh.object.DanhMucCon;
import com.dhh.object.MonAn;

import java.util.ArrayList;
import java.util.List;

import duong.ChucNangPhu;

/**
 * Created by Hong on 3/8/2017.
 */

public class FragmentAction extends Fragment {
    public static final String ARG_SECTION_NUMBER = "section_number";
    private MainActivity mainActivity;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private LayoutInflater inflater;
    private ViewPagerAdapter adapter;
    private View rootView;
    private ArrayList<DanhMucCon> danhMucConsFrag;
    private ArrayList<MonAn> monAnFrag;
    private ArrayList<DanhMucCon> danhMucCons;
    private ArrayList<MonAn> monAns;
    private FragmentMonAn fragmentMonAn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        getArguments();
        mainActivity = (MainActivity) getActivity();
        rootView = inflater.inflate(R.layout.tab_layout, container, false);
        // danhMucConsFrag= mainActivity.getDanhMucCons();
         ChucNangPhu.showLog(mainActivity.getDanhMucCons()+"");
         monAnFrag=mainActivity.getMonAns();
         danhMucCons =new ArrayList<>();
         monAns =new ArrayList<>();
         initView();
        return rootView;
    }
     public  ArrayList<DanhMucCon> getDanhMucConsId(String id){
         for(DanhMucCon danhMucCon:danhMucConsFrag){
             if(danhMucCon.getId().equals(id)){
                danhMucCons.add(danhMucCon);
             }
         }
         return  danhMucCons;
     }
    public  ArrayList<MonAn> getMonAnsId(String id){
        for(MonAn monAn:monAnFrag){
            if(monAn.getId_danhmuccon().equals(id)){
                monAns.add(monAn);
            }
        }
        return  monAns;
    }
    private void initView() {
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        FragmentMonAn fragmentMonAn =new FragmentMonAn();
        adapter =new ViewPagerAdapter(getActivity().getSupportFragmentManager(),getDanhMucConsId("0"),getMonAnsId("00"),fragmentMonAn);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
