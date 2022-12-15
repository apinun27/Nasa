package com.example.nasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ShowData extends AppCompatActivity {
TextView t1,t2,t3,t4,t5;
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
    t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t5=findViewById(R.id.t5);
        img=findViewById(R.id.img);
        Intent in=getIntent();
        t1.setText(in.getStringExtra("date"));
        t2.setText(in.getStringExtra("explanation"));
        t3.setText(in.getStringExtra("media_type"));
        t4.setText(in.getStringExtra("copyright"));
        t5.setText(in.getStringExtra("title"));
        Picasso.get().load(in.getStringExtra("hdurl")).into(img);
    }
}