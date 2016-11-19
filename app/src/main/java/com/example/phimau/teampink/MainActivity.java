package com.example.phimau.teampink;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private Dialog dialog;
    private AutoCompleteTextView autoCompleteTextView;
    private ArrayList<Product> listProduct;
    private ArrayList<String> listProductID;
    private  ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadapi();

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(),"dialo",Toast.LENGTH_SHORT).show();
                InvoiceDetail invoiceDetail = new InvoiceDetail(1,listProduct.get(i));
                detailArrayList.add(invoiceDetail);
                productApdater.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();
            }
        });
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

    private void loadapi() {
        Ion.with(getBaseContext())
                .load(ApiContent.URL_GETPRODUCT)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray array = jsonObject.getJSONArray("result");
                            for (int i=0;i<array.length();i++){
                                JSONObject jsonObject1 = array.getJSONObject(i);
                                String id = jsonObject1.getString("id");
                                Product product = new Product(jsonObject1.getString("id"),jsonObject1.getString("product_name"),jsonObject1.getInt("price"));
                                listProductID.add(product.getId());
                                listProduct.add(product);
                            }

                            Toast.makeText(getBaseContext(),listProductID.get(1)+"",Toast.LENGTH_SHORT).show();
                            arrayAdapter.notifyDataSetChanged();
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
    }

    private void init() {
        etNumphone = (AutoCompleteTextView) findViewById(R.id.atvNumphone);
        etAdress = (EditText) findViewById(R.id.etAdress);
        etName= (EditText) findViewById(R.id.etName);
        btnAddProduct = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.list);
        listProduct = new ArrayList<>();
        listProductID = new ArrayList<String>();

        dialog = new Dialog(MainActivity.this);

        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Chọn id sản phẩm");
        autoCompleteTextView = (AutoCompleteTextView) dialog.findViewById(R.id.etIdPro);
        arrayAdapter = new ArrayAdapter(getBaseContext(),android.R.layout.simple_list_item_1,listProductID);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setThreshold(1);

        detailArrayList = new ArrayList<>();
        productApdater = new ProductApdater(getBaseContext(),R.layout.item_product,detailArrayList);
        listView.setAdapter(productApdater);
    }
    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };
}
