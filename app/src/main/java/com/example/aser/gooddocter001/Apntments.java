package com.example.aser.gooddocter001;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.sinch.android.rtc.ClientRegistration;
import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchClientListener;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;
import com.sinch.android.rtc.calling.CallListener;

import java.util.List;

public class Apntments extends AppCompatActivity {
    private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apntments);
        tablayout=(TabLayout) findViewById(R.id.tablayout_id);
        appBarLayout=( AppBarLayout )findViewById(R.id.appbar_id);
        viewPager=(ViewPager)findViewById(R.id.viewpager_id);
        ViewpageAdapter adapter;
        adapter = new ViewpageAdapter(getSupportFragmentManager());
        //adding fragment
        adapter.Addfragment(new apnmnts_history(), "Previous Appointments");
        adapter.Addfragment(new Booked_apntments(), "Booked");
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);

    }
    public void open_homepage(){
        Intent intentdocter = new Intent(this, homepage.class);
        startActivity(intentdocter);

    }

    @Override
    public void onBackPressed() {
        open_homepage();
        super.onBackPressed();
    }


}
