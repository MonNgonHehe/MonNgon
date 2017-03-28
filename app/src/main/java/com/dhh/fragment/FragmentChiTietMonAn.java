package com.dhh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.dhh.adapter.MonAnAdapter;
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
        WebSettings webSettings =webViewChiTiet.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // cải thiện hiệu suất load webview
        webSettings.setUserAgentString("Android");
//        webSettings.setUserAgentString("Mozilla/5.0 (Linux; U; Android 2.2; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String linkCss= "<link rel=\"stylesheet\" type=\"text/css\" href=\"monan.css\" />";
        String html =linkCss+ getArguments().getString(MonAnAdapter.KEY_CHI_TIET);
        webViewChiTiet.loadDataWithBaseURL("file:///android_asset/",html,  "text/html; charset=utf-8", "utf-8",null);
        return view;
    }
}
