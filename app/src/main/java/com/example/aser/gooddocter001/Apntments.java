package com.example.aser.gooddocter001;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sinch.android.rtc.ClientRegistration;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchClientListener;
import com.sinch.android.rtc.SinchError;

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
    public void sinchstart(){
        // Instantiate a SinchClient using the SinchClientBuilder.
        android.content.Context context = this.getApplicationContext();
        SinchClient sinchClient = Sinch.getSinchClientBuilder().context(context)
                .applicationKey("c54d5c2b-3f3a-4ed8-8fe3-4cbdee39a664")
                .applicationSecret("xTGt8qS7Z0SaxeheDFuQAA==")
                .environmentHost("clientapi.sinch.com")
                .userId("11")
                .build();
        // Specify the client capabilities.
// At least one of the messaging or calling capabilities should be enabled.
        sinchClient.setSupportCalling(true);
        sinchClient.setSupportManagedPush(true);
        sinchClient.addSinchClientListener(new SinchClientListener() {

            public void onClientStarted(SinchClient client) { }

            public void onClientStopped(SinchClient client) { }

            public void onClientFailed(SinchClient client, SinchError error) { }

            public void onRegistrationCredentialsRequired(SinchClient client, ClientRegistration registrationCallback) { }

            public void onLogMessage(int level, String area, String message) { }
        });

        sinchClient.start();
        sinchClient.stopListeningOnActiveConnection();
        sinchClient.terminate();
    }
}
