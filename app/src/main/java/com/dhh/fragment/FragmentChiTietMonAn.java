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
        webViewChiTiet.getSettings().setJavaScriptEnabled(true);
        webViewChiTiet.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3");
        String linkCss= "<link rel=\"stylesheet\" type=\"text/css\" href=\"monan.css\" />";
        String html =linkCss+ getArguments().getString(MonAnAdapter.KEY_CHI_TIET);
        webViewChiTiet.loadDataWithBaseURL("file:///android_asset/",html,  "text/html; charset=utf-8", "utf-8",null);
        return view;
    }
}
