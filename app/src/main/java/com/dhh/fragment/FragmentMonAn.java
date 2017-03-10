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

/**
 * Created by Hong on 3/8/2017.
 */

public class FragmentMonAn extends Fragment {
    private MainActivity mainActivity;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_monan,container,false);
        recyclerView =(RecyclerView)view.findViewById(R.id.rv_item_mon_an);
        mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        MonAnAdapter monAnAdapter =new MonAnAdapter((ArrayList<MonAn>) getArguments().getSerializable(MainActivity.KEY_MON_AN),getActivity());
        recyclerView.setAdapter(monAnAdapter);
       
        return  view;
    }
}