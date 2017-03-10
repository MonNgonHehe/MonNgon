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
    private List<ArrayList> arrMonAns;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ChucNangPhu.showLog("onCreateView FragmentAction  ");
        this.inflater = inflater;
        mainActivity = (MainActivity) getActivity();
        String idDanhMucTo=getArguments().getString(MainActivity.KEY_DANH_MUC_TO);
        rootView = inflater.inflate(R.layout.tab_layout, container, false);
        danhMucConsFrag= mainActivity.getDanhMucCons(idDanhMucTo);
         monAnFrag=new ArrayList<>();
        for (DanhMucCon danhMucCon:danhMucConsFrag) {
            ChucNangPhu.showLog(mainActivity.getDanhMucCons(idDanhMucTo).size()+" getDanhMucCons");
            startMonAn(danhMucCon.getId());
        }

        initView();
        return rootView;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        adapter.notifyDataSetChanged();
//    }

    public void startMonAn(String idDanhMucCon) {
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if ((ArrayList<MonAn>) msg.obj != null){
                    ChucNangPhu.showLog("handleMessage "+((ArrayList<MonAn>) msg.obj).size());
                    monAnFrag.addAll((ArrayList<MonAn>) msg.obj);
                }
                //  else initViewIntro();
            }
        };
        TaskMonAn taskMonAn =new TaskMonAn(getActivity(),handler);
        taskMonAn.execute(idDanhMucCon);
    }
     public  ArrayList<DanhMucCon> getDanhMucConsId(String id){
         ArrayList<DanhMucCon> danhMucCons = new ArrayList<>();
         for(DanhMucCon danhMucCon:danhMucConsFrag)
             if(danhMucCon.getId().equals(id))
                danhMucCons.add(danhMucCon);
         return  danhMucCons;
     }
    public  ArrayList<MonAn> getMonAnsId(String id){
        ArrayList<MonAn> monAns = new ArrayList<>();
        for(MonAn monAn:monAnFrag){
            if(monAn.getId_danhmuccon().equals(id))
                monAns.add(monAn);
            ChucNangPhu.showLog(monAn.getId_danhmuccon().equals(id)+" id");
        }

        return  monAns;
    }
    private void initView() {
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        adapter =new ViewPagerAdapter(getActivity().getSupportFragmentManager(),danhMucConsFrag,monAnFrag);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
