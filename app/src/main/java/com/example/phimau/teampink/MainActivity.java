package com.example.phimau.teampink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import Entity.Invoice;
import Entity.InvoiceDetail;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Ion.with(getBaseContext())
//                .load("http://192.168.9.103/PinkTeam/push-invoice.php")
//                .set
        Invoice invoice = new Invoice("iv1",10000l,"Ssss");
        InvoiceDetail invoiceDetail = new InvoiceDetail(2,"dasds");
        InvoiceDetail invoiceDetail1 = new InvoiceDetail(3,"dasds");
        InvoiceDetail invoiceDetail2 = new InvoiceDetail(4,"dasds");
        invoice.addProduct(invoiceDetail);
        invoice.addProduct(invoiceDetail1);
        invoice.addProduct(invoiceDetail2);
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
}
