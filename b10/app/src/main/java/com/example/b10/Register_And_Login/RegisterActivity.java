package com.example.b10.Register_And_Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.b10.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText name, email, password, RetypePasswrod;
    private Button btn_register;
    private ProgressBar loading;
    private static String URL_REGISTER = "http://192.168.1.107:81/Android_LOGIN_REGISTER/register.php";
   // private static String URL_REGISTER = "../Android_LOGIN_REGISTER/register.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loading = findViewById(R.id.loading);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        RetypePasswrod = findViewById(R.id.RetypePasswrod);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }

    private void Register() {
        loading.setVisibility(View.GONE);
        btn_register.setVisibility(View.VISIBLE);

        final String name = this.name.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        Log.d("TTT: "," Nam : "+name +"Email : "+email+" , Pass : "+password);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d( "WWW: ","Vào 1");
                        Log.d("GGG :response",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String test = jsonObject.getString("message");

                            Log.d("GGG :response",response);
                            Log.d("GGG 1","success : "+success);
                            Log.d( "WWW: ","Vào 2");

                            if (success.equals("1") && test.equals("success")){
                                Log.d( "GGG 1: ","success : "+success+" message : "+test);
                                Log.d( "WWW: ","Vào 3");
                                Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            Log.d( "WWW: ","Vào 4");
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Register Error1!"+e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_register.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Register Error2!"+error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_register.setVisibility(View.VISIBLE);
                        Log.d("AAA", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parans = new HashMap<>();
                parans.put("name",name);
                parans.put("email",email);
                parans.put("password",password);
                Log.d("ASD: "," Nam : "+name +"Email : "+email+" , Pass : "+password);
                return parans;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}