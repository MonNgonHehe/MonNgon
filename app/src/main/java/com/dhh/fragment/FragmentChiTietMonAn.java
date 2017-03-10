package com.dhh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.dhh.monngon.R;

/**
 * Created by Hong on 3/10/2017.
 */

public class FragmentChiTietMonAn extends Fragment {
    private WebView webViewChiTiet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.webview_mon_ngon,container,false);
        webViewChiTiet =(WebView)view.findViewById(R.id.web_mon_an);
        webViewChiTiet.getSettings().setJavaScriptEnabled(true);
        String html = "<html><body><h1>Chào mừng bạn đến với blog GOCLAPTRINH.COM</h1></body></html>";
        webViewChiTiet.loadData(html, "text/html", "UTF-8");
        return view;
    }
}
