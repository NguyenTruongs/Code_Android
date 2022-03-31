package com.example.b10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.b10.Adapter.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL_ANIMAL = "http://192.168.1.107:81/Android_LOGIN_REGISTER/Animal_Content.php";

    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Animal> lstAnimal;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lstAnimal = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();
        //  getLocalIpAddress();

        //Context context = requireContext().getApplicationContext();
//        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
//        Toast.makeText(MainActivity.this, , Toast.LENGTH_SHORT).show();

    }

    // Json request : yêu cầu json
    private void jsonrequest() {

        // JsonArrayRequest request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>()){
        //
        // }
        request = new JsonArrayRequest(JSON_URL_ANIMAL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {

                    try {
                        jsonObject = response.getJSONObject(i);
                        Animal animal = new Animal();

                        //         anime.setId(jsonObject.getInt("id"));
                        animal.setTensv(jsonObject.getString("tensv"));
                        animal.setGia(jsonObject.getString("gia"));
                        animal.setNoidung(jsonObject.getString("noidung"));
                        animal.setHinh(jsonObject.getString("hinhanh"));
                        lstAnimal.add(animal);

                        Log.d("vvv",jsonObject.getString("tensv"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setuprecyclerview(lstAnimal);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);


    }

    // set up recyclerview : thiết lập chế độ xem lại
    private void setuprecyclerview(List<Animal> lstAnimal) {
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstAnimal);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }

//    public String getLocalIpAddress() {
//        try {
//            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
//                NetworkInterface intf = en.nextElement();
//                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
//                    InetAddress inetAddress = enumIpAddr.nextElement();
//                    if (!inetAddress.isLoopbackAddress()) {
//                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());
//                        Log.i("TAG", "***** IP="+ ip);
//                        return ip;
//                    }
//                }
//            }
//        } catch (SocketException ex) {
//            Log.e("TAG", ex.toString());
//        }
//        return null;
//    }
}