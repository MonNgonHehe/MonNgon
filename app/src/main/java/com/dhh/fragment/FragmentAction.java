package com.dhh.fragment;

import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
    public static final String KEY_PRORESSBAR = "key_prorsessbar";
    private MainActivity mainActivity;
    private View rootView;
    private ArrayList<DanhMucCon> danhMucCons;
    private ArrayList<MonAn> monAns;
    private String idDanhMucTo;
    private Handler progressBarbHandler = new Handler();
    private ProgressDialog progressBar;
    private boolean isProgress = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        initProgessBar();
        idDanhMucTo = getArguments().getString(MainActivity.KEY_DANH_MUC_TO);
        ChucNangPhu.showLog("onCreateView idDanhMucTo  " + idDanhMucTo);
        rootView = inflater.inflate(R.layout.tab_layout, null);
        danhMucCons = mainActivity.getDanhMucCons();
        startTaskGetMonAns(danhMucCons);
        return rootView;
    }

    public void initProgessBar() {
        progressBar = new ProgressDialog(getContext());
        progressBar.setCancelable(true);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(false);
        progressBar.show();
        new Thread(new Runnable() {
            public void run() {
                while (isProgress) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progressBarbHandler.post(new Runnable() {
                        public void run() {
                            // progressBar.setProgress(progressBarStatus);
                        }
                    });
                }

                if (!isProgress) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.dismiss();
                }
            }
        }).start();

    }


    private void startTaskGetMonAns(ArrayList<DanhMucCon> danhMucCons) {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if ((ArrayList<MonAn>) msg.obj != null) {
                    monAns = new ArrayList<>();
                    monAns = (ArrayList<MonAn>) msg.obj;
                    ChucNangPhu.showLog("handleMessage  monAns " + monAns.size());
                    initView();
                }
            }
        };
        TaskMonAn taskMonAn = new TaskMonAn(getActivity(), handler, danhMucCons);
        taskMonAn.execute(idDanhMucTo);
        isProgress = true;

    }

    private void initView() {
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        ArrayList<DanhMucCon> danhMucConSelects = new ArrayList<>();
        for (DanhMucCon danhMucCon : danhMucCons)
            if (danhMucCon.getId_danh_muc().equals(idDanhMucTo)) danhMucConSelects.add(danhMucCon);
        ChucNangPhu.showLog("initView ViewPagerAdapter " + monAns.size());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), danhMucConSelects, monAns);
        viewPager.setAdapter(adapter);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
        tabLayout.setTabTextColors(Color.WHITE,Color.WHITE);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

}
