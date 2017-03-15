package com.dhh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

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
    private GridView gridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_monan,null);
        gridView =(GridView) view.findViewById(R.id.grid_monan);
        ArrayList<MonAn> monAns=(ArrayList<MonAn>) getArguments().getSerializable(MainActivity.KEY_MON_AN);
        MonAnAdapter monAnAdapter =new MonAnAdapter(getActivity(),android.R.layout.simple_list_item_1,monAns);
        ChucNangPhu.showLog("FragmentMonAn "+monAns.size());
        gridView.setAdapter(monAnAdapter);
        return  view;
    }
}
