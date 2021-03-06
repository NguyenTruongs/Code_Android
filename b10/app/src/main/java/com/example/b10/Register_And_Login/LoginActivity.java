package com.example.b10.Register_And_Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.b10.ContentActivity.HomeActivity;
import com.example.b10.MainActivity;
import com.example.b10.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button btn_login;
    private Button btn_register;
    // private TextView link_register;
    private ProgressBar loading;
    private static String URL_LOGIN = "http://192.168.1.107:81/Android_LOGIN_REGISTER/login.php";
   // private static String URL_LOGIN = "./Android_LOGIN_REGISTER/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AnhXa();
        btn_Login();
        btn_Resgister();

    }

    private void AnhXa(){
        loading = findViewById(R.id.loading);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        btn_login = findViewById(R.id.button_Login);
        btn_register = findViewById(R.id.button_Register);
        //  link_register = findViewById(R.id.link_register);
    }

    // ????ng nh???p
    private void btn_Login(){
        // N??t login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String mPass = password.getText().toString().trim();
                Log.d("zzz","Email : "+mEmail +" , password : "+ mPass);

                if (!mEmail.isEmpty() || !mPass.isEmpty()) {  // isEmpty ki???m tra chu???i email v?? password c?? r???ng hay kh??ng n???u != null
                    Login(mEmail,mPass);
                    Log.d("zzz"," != null  hi???n th??? Email : "+mEmail +", != null  hi???n th??? password : "+ mPass);
                    Log.d("XXX","V??o Login");
                } else { // n???u == null nh???p v??o
                    email.setError("Please insert email");
                    password.setError("Please insert password");
                    Log.d("XXX","Ch??a v??o Login");
                }
            }
        });
    }

    // ????ng k??
    private void btn_Resgister(){
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void Login(String email, String password) {

        loading.setVisibility(View.GONE);
        btn_login.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.d("NNN",stringRequest);
                        Log.d("QQQ: ", "V??o 1");
                        Log.d("RRR: ","RRR 1 : "+response);


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("RRR","RRR 2  : "+response);
//                           Log.d("RRR","rrr 1 : "+response.)
                            Log.d("RRR","RRR 3  : "+jsonObject);
                            String success = jsonObject.getString("success");
                            Log.d("RRR","RRR 4  : "+success);
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            Log.d("QQQ: ", "V??o 2");
                            if (success.equals("1")) {
                                Log.d("QQQ: ", "V??o 3");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();

                                    Toast.makeText(LoginActivity.this, "Success Login. \nYour Name : " + name + "\nYour Email : " + email, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("name",name);
                                    intent.putExtra("email",email);
                                    startActivity(intent);
                                    loading.setVisibility(View.GONE);
                                    Log.d("BBB", response);
                                    Log.d("QQQ: ", "V??o 4");
                                }
                            }

                        } catch (JSONException e) {
                            Log.d("QQQ: ", "V??o 5");
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Error 1 " + e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);
                            Log.d("CCC", e.getMessage());
                            Log.d("DDD", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);

//                        if (error instanceof NetworkError) {
//                        } else if (error instanceof ServerError) {
//                        } else if (error instanceof AuthFailureError) {
//                        } else if (error instanceof ParseError) {
//                        } else if (error instanceof NoConnectionError) {
//                        } else if (error instanceof TimeoutError) {
//                            Toast.makeText(getApplicationContext(),
//                                    "Oops. Timeout error!",
//                                    Toast.LENGTH_LONG).show();
//                            Toast.makeText(LoginActivity.this, "Register Error2!"+error.toString(), Toast.LENGTH_SHORT).show();
//                            Log.d("MMM",error.toString());
//                        }

                    }
                }
                ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                Log.d("EEE","Email : "+email +" , "+"Password : "+password);
                return params;
            }

        };

       RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


//        stringRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return 50000;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return 50000;
//            }
//
//            @Override
//            public void retry(VolleyError error) throws VolleyError {
//
//            }
//        });
    }
}