package com.example.phimau.teampink;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
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

import Entity.Custumer;
import Entity.Employee;
import Entity.Invoice;
import Entity.InvoiceDetail;
import Entity.Product;

public class MainActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etAdress;
    private EditText etNumphone;
    private FloatingActionButton btnAddProduct;
    private ProductApdater productApdater;
    private ArrayList<InvoiceDetail> detailArrayList;
    private ListView listView;
    private Dialog dialog;
    private AutoCompleteTextView autoCompleteTextView;
    private ArrayList<Product> listProduct;
    private ArrayList<String> listProductID;
    private ArrayAdapter<String> arrayAdapter;
    private ProgressDialog loadDialog;
    private PreferenceAccount preferenceAccount;
    private Employee employee;
    private Invoice invoice;
    private Toolbar mToolbar;
    private int isCustomer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
        setSupportActionBar(mToolbar);
        loadapi();
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), "dialo", Toast.LENGTH_SHORT).show();
                InvoiceDetail invoiceDetail = new InvoiceDetail(1, listProduct.get(i));
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
        etNumphone.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_RIGHT = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getRawX() >= (etNumphone.getRight() - etNumphone.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        final String numphone = etNumphone.getText().toString();
                        loadDialog.show();
                        Ion.with(getBaseContext())
                                .load(ApiContent.FIND_CUSTUMOER)
                                .setBodyParameter("phone", numphone)
                                .asString()
                                .setCallback(new FutureCallback<String>() {
                                    @Override
                                    public void onCompleted(Exception e, String result) {
                                        int res = 0;
                                        try {
                                            JSONObject jsonObject = new JSONObject(result);
                                            res = jsonObject.getInt("success");
                                            if (res == 1) {
                                                JSONObject jsonResult = jsonObject.getJSONArray("result").getJSONObject(0);
                                                isCustomer = 1;
                                                invoice.setCustomerID(jsonResult.getString("id"));
                                                etNumphone.setText(jsonResult.getString("phonenumber"));
                                                etAdress.setText(jsonResult.getString("address"));
                                                etName.setText(jsonResult.getString("name"));
                                                etAdress.setEnabled(false);
                                                etName.setEnabled(false);
                                            } else if (res == 0) {
                                                isCustomer = 0;
                                            }
                                        } catch (JSONException e1) {
                                            e1.printStackTrace();
                                        }

                                        Toast.makeText(getBaseContext(), numphone + "", Toast.LENGTH_SHORT).show();
                                        loadDialog.dismiss();
                                    }
                                });
                        return true;
                    }
                }
                return false;
            }
        });

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
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject1 = array.getJSONObject(i);
                                String id = jsonObject1.getString("id");
                                Product product = new Product(jsonObject1.getString("id"), jsonObject1.getString("product_name"), jsonObject1.getInt("price"));
                                listProductID.add(product.getId());
                                listProduct.add(product);
                            }

                            Toast.makeText(getBaseContext(), listProductID.get(1) + "", Toast.LENGTH_SHORT).show();
                            arrayAdapter.notifyDataSetChanged();
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
    }

    private void init() {
        etNumphone = (EditText) findViewById(R.id.atvNumphone);
        etAdress = (EditText) findViewById(R.id.etAdress);
        etName = (EditText) findViewById(R.id.etName);
        btnAddProduct = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        listProduct = new ArrayList<>();
        listProductID = new ArrayList<String>();
        preferenceAccount = new PreferenceAccount(getBaseContext());
        employee = preferenceAccount.getEmployee();
        invoice = new Invoice("", employee.getId());

        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Chọn id sản phẩm");
        autoCompleteTextView = (AutoCompleteTextView) dialog.findViewById(R.id.etIdPro);
        loadDialog = new ProgressDialog(MainActivity.this);
        loadDialog.setMessage("Xin chờ");
        arrayAdapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, listProductID);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setThreshold(1);

        detailArrayList = new ArrayList<>();
        productApdater = new ProductApdater(getBaseContext(), R.layout.item_product, detailArrayList, MainActivity.this);
        listView.setAdapter(productApdater);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_share:
                pushInvoice();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void pushInvoice() {

        if (checkvadiate()) {
            String name = etName.getText().toString();
            String numPhone = etNumphone.getText().toString();
            String address = etAdress.getText().toString();
            for (int i = 0; i < productApdater.getCount(); i++) {
                invoice.addProduct(productApdater.getItem(i));
            }
            loadDialog.show();
            Ion.with(getBaseContext())
                    .load(ApiContent.URL_INVOICE)
                    .setBodyParameter("json", invoice.maketStringJson())
                    .setBodyParameter("isCustomer", isCustomer + "")
                    .setBodyParameter("name", name)
                    .setBodyParameter("numPhone", numPhone)
                    .setBodyParameter("address", address)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            loadDialog.dismiss();
                            clearfield();
                        }
                    });
        }
    }

    private void clearfield() {
        etName.setText("");
        etNumphone.setText("");
        etAdress.setText("");
        productApdater.clear();
    }

    private boolean checkvadiate() {
        if (etNumphone.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Nhập đủ trường", Toast.LENGTH_SHORT).show();
            etNumphone.requestFocus();
            return false;
        }
        if (etName.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Nhập đủ trường", Toast.LENGTH_SHORT).show();
            etName.requestFocus();
            return false;
        }
        if (etAdress.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Nhập đủ trường", Toast.LENGTH_SHORT).show();
            etAdress.requestFocus();
            return false;
        }
        if (productApdater.getCount()==0){
            Toast.makeText(getBaseContext(), "Chưa có sản phẩm nào", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
