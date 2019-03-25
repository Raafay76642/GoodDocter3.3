package com.example.aser.gooddocter001;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
}
