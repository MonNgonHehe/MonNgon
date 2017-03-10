package com.dhh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhh.activity.MainActivity;
import com.dhh.adapter.MonAnAdapter;
import com.dhh.monngon.R;
import com.dhh.object.MonAn;

import java.util.ArrayList;

import duong.ChucNangPhu;

/**
 * Created by Hong on 3/8/2017.
 */

public class FragmentMonAn extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_monan,null);
        recyclerView =(RecyclerView)view.findViewById(R.id.rv_item_mon_an);
        mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        ArrayList<MonAn> monAns=(ArrayList<MonAn>) getArguments().getSerializable(MainActivity.KEY_MON_AN);
        MonAnAdapter monAnAdapter =new MonAnAdapter(monAns,getActivity());
        ChucNangPhu.showLog("FragmentMonAn "+monAns.size());
        recyclerView.setAdapter(monAnAdapter);
        return  view;
    }
}
