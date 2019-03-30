package com.example.aser.gooddocter001;

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
        vc = new Connector(videoFrame, Connector.ConnectorViewStyle.VIDYO_CONNECTORVIEWSTYLE_Default, 15, "warning info@VidyoClient info@VidyoConnector", "", 0);
        vc.showViewAt(videoFrame, 0, 0, videoFrame.getWidth(), videoFrame.getHeight());
    }
    public void Connect(View v) {
        String token = "cHJvdmlzaW9uAHVzZXIyQGZjZmQwOC52aWR5by5pbwA2MzcyMTI1NjYwNQAANTlhN2Q5ZmI4ZWFkM2RkZjNmNGU5MGVlOTg4MzkwODQxNDBjODAyMjBlZjVhN2NiODA0NjlkM2JmNDBhOTZiMGZlNTViY2FhZmY2NWI2Yjc5MzZjMGQ0ZTM0ZDhkYmE0";
        vc.connect("prod.vidyo.io", token, "DemoUser", "Room", this);
    }

    public void Disconnect(View v) {
        vc.disconnect();
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
