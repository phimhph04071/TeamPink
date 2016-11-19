package com.example.phimau.teampink;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import Entity.Invoice;
import Entity.InvoiceDetail;
import Entity.Product;

public class MainActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etAdress;
    private AutoCompleteTextView etNumphone;
    private FloatingActionButton btnAddProduct;
    private ProductApdater productApdater;
    private ArrayList<InvoiceDetail> detailArrayList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
//        Ion.with(getBaseContext())
//                .load("http://192.168.9.103/PinkTeam/push-invoice.php")
//                .set
        Invoice invoice = new Invoice("iv1", 10000l, "Ssss");

        InvoiceDetail invoiceDetail = new InvoiceDetail(3, new Product("sssdsd","Ssss",3123123));

        invoice.addProduct(invoiceDetail);

//
//        Ion.with(getBaseContext())
//                .load(ApiContent.URL_INVOICE)
//                .setBodyParameter("json",invoice.maketStringJson())
//                .asString()
//                .setCallback(new FutureCallback<String>() {
//                    @Override
//                    public void onCompleted(Exception e, String result) {
//                        Log.e("TESTPHP",result);
//                        Toast.makeText(getBaseContext(),result,Toast.LENGTH_SHORT).show();
//                    }
//                });
    }

    private void init() {
        etNumphone = (AutoCompleteTextView) findViewById(R.id.atvNumphone);
        etAdress = (EditText) findViewById(R.id.etAdress);
        etName= (EditText) findViewById(R.id.etName);
        btnAddProduct = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.list);
        detailArrayList = new ArrayList<>();
        productApdater = new ProductApdater(getBaseContext(),R.layout.item_product,detailArrayList);
        listView.setAdapter(productApdater);
    }
}
