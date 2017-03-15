package com.dhh.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dhh.activity.MainActivity;
import com.dhh.fragment.FragmentChiTietMonAn;
import com.dhh.monngon.R;
import com.dhh.object.MonAn;

import java.util.ArrayList;

/**
 * Created by Hong on 3/8/2017.
 */

public class MonAnAdapter extends ArrayAdapter<MonAn> {
    public static final  String KEY_CHI_TIET="key_chi_tiet";
    private ArrayList<MonAn> monAns;
    private Context context;
    private LayoutInflater inflater;
    private MainActivity mainActivity;
    public MonAnAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<MonAn> monAns) {
        super(context, resource, monAns);
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.monAns = monAns;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View view, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_mon_an_grid, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgDanhMuc = (ImageView) view.findViewById(R.id.img_danhmuc);
            viewHolder.tvTenDanhMuc = (TextView) view.findViewById(R.id.txt_danhmuc);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();
        Glide.with(context)
                .load(monAns.get(position).getLink_img()).placeholder(R.drawable.im_loading)
                .into(viewHolder.imgDanhMuc);
        viewHolder.tvTenDanhMuc.setText(monAns.get(position).getTen());
        viewHolder.imgDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity=(MainActivity)getContext();
                FragmentChiTietMonAn fragmentChiTietMonAn =new FragmentChiTietMonAn();
                FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString(KEY_CHI_TIET,monAns.get(position).getContent_html() );
                fragmentChiTietMonAn.setArguments(bundle);
                transaction.replace(R.id.frame_fragment,fragmentChiTietMonAn);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

    class ViewHolder {
       ImageView imgDanhMuc;
        TextView tvTenDanhMuc;
    }


}
