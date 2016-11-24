package com.example.phimau.teampink;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Entity.InvoiceDetail;
import Entity.Product;

/**
 * Created by phimau on 11/19/2016.
 */

public class ProductApdater extends ArrayAdapter<InvoiceDetail> {
    private ArrayAdapter arrayAdapter;
    private Context mContext;
    private ArrayList<InvoiceDetail> mlList;
    private ArrayList<String> listProdcut;
    private Activity mActivity;

    public ProductApdater(Context context, int resource, ArrayList<InvoiceDetail> objects, Activity activity) {
        super(context, resource, objects);
        mContext = context;
        mlList = objects;
        listProdcut = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, listProdcut);
        mActivity = activity;


    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.item_product, null);

        final InvoiceDetail product = getItem(position);
        TextView tvId = (TextView) convertView.findViewById(R.id.tvIdPro);
        TextView tvName = (TextView) convertView.findViewById(R.id.tvNamePro);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
        final TextView tvCount = (TextView) convertView.findViewById(R.id.tvCount);
        ImageView btnUp = (ImageView) convertView.findViewById(R.id.btnUp);
        ImageView btnDown = (ImageView) convertView.findViewById(R.id.btnDown);

        btnDown.setClickable(true);
        btnUp.setClickable(true);
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setTitle("Xóa ?");
                builder.setMessage("Bạn có muốn Bỏ sản phẩm " + product.getProduct_id().getId());
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mlList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;

            }
        });

        tvId.setText(product.getProduct_id().getId());
        tvName.setText(product.getProduct_id().getName());
        tvPrice.setText(product.getProduct_id().getPrice() + "đ");
        tvCount.setText(1 + "");
        int num = Integer.parseInt(tvCount.getText().toString());
        product.setNumber(num);
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(tvCount.getText().toString());
                if (count > 1) {
                    count--;
                    tvCount.setText(count + "");
                    product.setNumber(count);
                }

            }
        });
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(tvCount.getText().toString());
                count++;
                tvCount.setText(count + "");
                product.setNumber(count);
            }
        });
        return convertView;
    }

    @Override
    public void clear() {
        mlList.clear();
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return mlList.size();
    }

    @Nullable
    @Override
    public InvoiceDetail getItem(int position) {
        return mlList.get(position);
    }
}
