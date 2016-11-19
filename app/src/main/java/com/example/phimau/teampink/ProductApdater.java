package com.example.phimau.teampink;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Entity.InvoiceDetail;
import Entity.Product;

/**
 * Created by phimau on 11/19/2016.
 */

public class ProductApdater extends ArrayAdapter<InvoiceDetail> {
    Context mContext ;
    ArrayList<InvoiceDetail> mlList;
    public ProductApdater(Context context, int resource, ArrayList<InvoiceDetail> objects) {
        super(context, resource, objects);
        mContext = context;
        mlList= objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.item_product,null);

        final InvoiceDetail product = getItem(position);
        TextView  tvId = (TextView) convertView.findViewById(R.id.tvIdPro);
        TextView  tvName = (TextView) convertView.findViewById(R.id.tvNamePro);
        TextView  tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
        final TextView  tvCount = (TextView) convertView.findViewById(R.id.tvCount);
        ImageView btnUp = (ImageView) convertView.findViewById(R.id.btnUp);
        ImageView btnDown = (ImageView) convertView.findViewById(R.id.btnUp);
        btnDown.setClickable(true);
        btnUp.setClickable(true);

        tvId.setText(product.getProduct_id().getId());
        tvName.setText(product.getProduct_id().getName());
        tvPrice.setText(product.getProduct_id().getPrice());
        tvCount.setText(1+"");
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(tvCount.getText().toString());
                count--;
                tvCount.setText(count+"");
                product.setNumber(count);
            }
        });
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(tvCount.getText().toString());
                count++;
                tvCount.setText(count+"");
                product.setNumber(count);
            }
        });
        return convertView;

    }
}
