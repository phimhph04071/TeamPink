package com.example.phimau.teampink;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText etId;
    private EditText etPassword;
    private Button btnLogin;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                String id = etId.getText().toString().trim();
                String pw = etPassword.getText().toString().trim();
                Log.d("ID",id);
                Log.d("PW",pw);
                Ion.with(getBaseContext())
                        .load(ApiContent.URL_LOGIN)
                        .setBodyParameter("id",id)
                        .setBodyParameter("password",pw)
                        .asString()
                        .setCallback(new FutureCallback<String>() {
                            @Override
                            public void onCompleted(Exception e, String result) {
                                try {

                                    JSONObject json = new JSONObject(result);
                                    if (json.getInt("suscess")==1){
                                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (json.getInt("suscess")==2){
                                        Toast.makeText(getBaseContext(),"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                                dialog.dismiss();
                            }
                        });
            }
        });
    }

    private void init() {
        etId = (EditText) findViewById(R.id.etId);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage("Loadding");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
    }
}
