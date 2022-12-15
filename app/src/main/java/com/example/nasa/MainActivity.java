package com.example.nasa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Date date;
    ListView list;
    ArrayList<DataNasa> data1 = new ArrayList<>();
    TextView selectm,selecy;
    String mm= "";
    String yy = "";
    final String[] items1 = {  "มกราคม",
            "กุมภาพันธ์",
            "มีนาคม",
            "เมษายน",
            "พฤษภาคม",
            "มิถุนายน",
            "กรกฎาคม",
            "สิงหาคม",
            "กันยายน",
            "ตุลาคม",
            "พฤศจิกายน",
            "ธันวาคม"};
    SimpleDateFormat formatter,formatter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = new Date();
        formatter = new SimpleDateFormat("yyyy");
        formatter2 = new SimpleDateFormat("MM");
        mm= formatter2.format(date);
        yy= formatter.format(date);
        selectm=findViewById(R.id.select11);
        selecy=findViewById(R.id.select2);
        list=findViewById(R.id.list);
        selectm.setText(items1[Integer.parseInt(mm)-1]);
        selecy.setText(formatter.format(date));
        loadData();
        selectm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("เลือกเดือน");
                builder.setItems(items1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        selectm.setText(items1[item] + "");
                        mm = (item+1)+"";

                        loadData();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
        selecy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] items = {"2022","2023","2024"};
                // final String[] items = (String[]) dataitem.toArray();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("เลือกปี");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        selecy.setText(items[item] + "");
                        yy= items[item] + "";

                        loadData();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }

    public void loadData() {
       // String mURL1="https://api.nasa.gov/planetary/apod?api_key=zPiZofaOm9UiH87B8uFcLciHuS0UsIPnn6AswvNu&start_date="+yy+"-"+mm+"-01&end_date="+yy+"-"+mm+"-30";
        String mURL1= "https://api.nasa.gov/planetary/apod?api_key=zPiZofaOm9UiH87B8uFcLciHuS0UsIPnn6AswvNu&start_date=2022-09-01&end_date=2022-09-30";
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest sr = new StringRequest(Request.Method.GET, mURL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("datasss",response);

                        data1=new ArrayList<>();

                        JSONArray mJsonArray = null;
                        try {
                            mJsonArray = new JSONArray(response);
                            for(int i=0;i<mJsonArray.length();i++) {
                                String sObject = mJsonArray.get(i).toString();
                                JSONObject mItemObject = new JSONObject(sObject);
                                String dates = mItemObject.getString("date");
                                String explanation = mItemObject.getString("explanation");
                                String hdurl = mItemObject.getString("url");
                              //  String hdurl ="";
                             //   String copyright = mItemObject.getString("copyright");
                                String copyright ="";
                                        String media_type = mItemObject.getString("media_type");
                                String title = mItemObject.getString("title");
                                data1.add(new DataNasa(dates,explanation,hdurl,copyright,media_type, title));

                            }

                            CustomNasaAdepter customAdapter = new CustomNasaAdepter(MainActivity.this,data1);
                            list.setAdapter(customAdapter);
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent in=new Intent(MainActivity.this,ShowData.class);
                                    in.putExtra("date",data1.get(i).getDate());
                                    in.putExtra("explanation",data1.get(i).getExplanation());
                                    in.putExtra("hdurl",data1.get(i).getHdurl());
                                    in.putExtra("copyright",data1.get(i).getCopyright());
                                    in.putExtra("media_type",data1.get(i).getMedia_type());
                                    in.putExtra("title",data1.get(i).getTitle());
                                    startActivity(in);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error ) {
                        Log.e("HttpClient", "error: " + error.toString());


                    }
                })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }


        };
        queue.add(sr);

    }
}