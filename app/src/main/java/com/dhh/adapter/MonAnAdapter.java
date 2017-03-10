package com.dhh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dhh.monngon.R;
import com.dhh.object.MonAn;

import java.util.ArrayList;

import duong.ChucNangPhu;

/**
 * Created by Hong on 3/8/2017.
 */

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.DanhMucViewHolder> {
    private ArrayList<MonAn> monAns;
    private Context context;
    private LayoutInflater inflater;

    public MonAnAdapter(ArrayList<MonAn> monAns, Context context) {
        this.monAns = monAns;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public DanhMucViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_danhmuc_recyclerview, parent, false);
        return new DanhMucViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(DanhMucViewHolder holder, int position) {

        MonAn monAn=monAns.get(position);
        Glide.with(context)
                .load(monAn.getLink_img()).placeholder(R.drawable.ic_menu_share)
                .into(holder.imgDanhMuc);
        holder.tvTenDanhMuc.setText(monAn.getTen());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChucNangPhu.showLog("onClick");
            }
        });

    }


    @Override
    public int getItemCount() {
        return monAns.size();
    }



    class DanhMucViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgDanhMuc;
        TextView tvTenDanhMuc;

        public DanhMucViewHolder(View itemView) {
            super(itemView);
            imgDanhMuc = (ImageView) itemView.findViewById(R.id.img_danhmuc);
            tvTenDanhMuc = (TextView) itemView.findViewById(R.id.txt_danhmuc);

        }


        @Override
        public void onClick(View view) {
            view =imgDanhMuc;

        }
    }


}
