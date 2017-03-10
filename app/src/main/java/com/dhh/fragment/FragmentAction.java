package com.dhh.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhh.activity.MainActivity;
import com.dhh.adapter.ViewPagerAdapter;
import com.dhh.asynctask.TaskMonAn;
import com.dhh.monngon.R;
import com.dhh.object.DanhMucCon;
import com.dhh.object.MonAn;

import java.util.ArrayList;

import duong.ChucNangPhu;

/**
 * Created by Hong on 3/8/2017.
 */

public class FragmentAction extends Fragment {
    public static final String ARG_SECTION_NUMBER = "section_number";
    private MainActivity mainActivity;
    private View rootView;
    private ArrayList<DanhMucCon> danhMucCons;
    private ArrayList<MonAn> monAns;
    private String idDanhMucTo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
         idDanhMucTo=getArguments().getString(MainActivity.KEY_DANH_MUC_TO);
        ChucNangPhu.showLog("onCreateView idDanhMucTo  "+idDanhMucTo);
        rootView = inflater.inflate(R.layout.tab_layout,null);
        danhMucCons=mainActivity.getDanhMucCons();
        startTaskGetMonAns(danhMucCons);
        return rootView;
    }

    private void startTaskGetMonAns(ArrayList<DanhMucCon> danhMucCons) {
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if ((ArrayList<MonAn>) msg.obj != null){
                    monAns=new ArrayList<>();
                    monAns=(ArrayList<MonAn>) msg.obj;
                    ChucNangPhu.showLog("handleMessage  monAns "+monAns.size());
                    initView();
                }
            }
        };
        TaskMonAn taskMonAn =new TaskMonAn(getActivity(),handler,danhMucCons);
        taskMonAn.execute(idDanhMucTo);
    }

    private void initView() {
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
         ArrayList<DanhMucCon> danhMucConSelects=new ArrayList<>();
        for (DanhMucCon danhMucCon:danhMucCons) if (danhMucCon.getId_danh_muc().equals(idDanhMucTo)) danhMucConSelects.add(danhMucCon);
        ChucNangPhu.showLog("initView ViewPagerAdapter "+monAns.size());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), danhMucConSelects, monAns);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

}
