package com.example.aser.gooddocter001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.vidyo.VidyoClient.Connector.Connector;
import com.vidyo.VidyoClient.Connector.ConnectorPkg;

public class Calling extends AppCompatActivity implements Connector.IConnect {
    private Connector vc;
    private FrameLayout videoFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);
        ConnectorPkg.setApplicationUIContext(this);
        ConnectorPkg.initialize();
        videoFrame = (FrameLayout)findViewById(R.id.videoFrame);
    }
    public void Start(View v) {
        vc = new Connector(videoFrame, Connector.ConnectorViewStyle.VIDYO_CONNECTORVIEWSTYLE_Default, 2, "warning info@VidyoClient info@VidyoConnector", "", 0);
        vc.showViewAt(videoFrame, 0, 0, videoFrame.getWidth(), videoFrame.getHeight());
    }
    public void Connect(View v) {
        String token = "cHJvdmlzaW9uAHVzZXIyQGZjZmQwOC52aWR5by5pbwA2MzcyMTI4MjIwNQAAZWZhM2RhZmJlNjg4OWNkYTBmOTgxNWNhOWUxNzM0YTc1MjA0YWY0NzExMGI4ZmY1Zjk0NzU1MDhjNmZiN2IwNjE2MjMzOTA4ZjRmZjRiN2VmOTZmOTllNjVjNjA5NmJi";
        vc.connect("prod.vidyo.io", token, "Muneeb", "Room", this);
    }

    public void Disconnect(View v) {
        vc.disconnect();
        Intent intent=new Intent(this,Apntments.class);
        startActivity(intent);
    };
    @Override
    public void onSuccess() {

    };

    @Override
    public void onFailure(Connector.ConnectorFailReason connectorFailReason) {

    };

    @Override
    public void onDisconnected(Connector.ConnectorDisconnectReason connectorDisconnectReason) {

    };
};
