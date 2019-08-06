package com.example.yahyagungorur.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


/**
 * Created by YAHYA on 7/26/2019.
 */

public class CustomAdapter extends BaseAdapter {
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> price = new ArrayList<>();
    ArrayList<String> img = new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    public CustomAdapter(ArrayList<String> title,ArrayList<String> price,ArrayList<String> img ,Context context) {
        this.title = title;
        this.price = price;
        this.img = img;
        this.context = context;
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.row,null);
        TextView tvTitle = (TextView)row.findViewById(R.id.tvTitle);
        TextView tvPrice = (TextView)row.findViewById(R.id.tvPrice);
        ImageView imgProducts = (ImageView)row.findViewById(R.id.imgProducts);
        tvTitle.setText(title.get(i));
        tvPrice.setText(price.get(i));
        Picasso.with(context)
                .load(String.valueOf(img.get(i)))
                .resize(200,200)
                .into(imgProducts);
        return row;
    }
}
