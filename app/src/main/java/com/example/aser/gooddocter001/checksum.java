package com.example.aser.gooddocter001;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class checksum extends AppCompatActivity implements PaytmPaymentTransactionCallback {

    String dID="",uID="",status="",etime="",stime="",date="",dname="",uname="",aId="",mid="",fee="";
    DatabaseReference mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        dID = intent.getExtras().getString("dID");
        uID = intent.getExtras().getString("uID");
        status=intent.getExtras().getString("status");
        etime=intent.getExtras().getString("etime");
        stime=intent.getExtras().getString("stime");
        date = intent.getExtras().getString("date");
        dname=intent.getExtras().getString("dname");
        uname=intent.getExtras().getString("uname");
        aId = intent.getExtras().getString("aID");
        fee=intent.getExtras().getString("fee");


        mid = "KSUZnb58654541693485";
        sendUserDetailTOServerdd dl = new sendUserDetailTOServerdd();
        dl.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    public class sendUserDetailTOServerdd extends AsyncTask<ArrayList<String>, Void, String> {

        private ProgressDialog dialog = new ProgressDialog(checksum.this);


        String url ="https://tenderfresssh.000webhostapp.com/paytm/generateChecksum.php";//TODO your server's url here (www.xyz/checksumGenerate.php)
        String varifyurl = "https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp";
        String CHECKSUMHASH ="";

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }

        protected String doInBackground(ArrayList<String>... alldata) {
            JSONparse jsonParser = new JSONparse(checksum.this);
            String param=
                    "MID="+mid+
                            "&ORDER_ID=" + aId+
                            "&CUST_ID="+uID+
                            "&CHANNEL_ID=WAP&TXN_AMOUNT="+fee+"&WEBSITE=WEBSTAGING"+
                            "&CALLBACK_URL="+ varifyurl+"&INDUSTRY_TYPE_ID=Retail";

            JSONObject jsonObject = jsonParser.makeHttpRequest(url,"POST",param);
            Log.e("CheckSum result >>",jsonObject.toString());
            if(jsonObject != null){
                Log.e("CheckSum result >>",jsonObject.toString());
                try {

                    CHECKSUMHASH=jsonObject.has("CHECKSUMHASH")?jsonObject.getString("CHECKSUMHASH"):"";
                    Log.e("CheckSum result >>",CHECKSUMHASH);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return CHECKSUMHASH;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e(" setup acc ","  signup result  " + result);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            PaytmPGService Service = PaytmPGService.getStagingService();



            HashMap<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("MID", mid);
            paramMap.put("ORDER_ID", aId);
            paramMap.put("CUST_ID", uID);
            paramMap.put("CHANNEL_ID", "WAP");
            paramMap.put("TXN_AMOUNT", fee);
            paramMap.put("WEBSITE", "WEBSTAGING");
            paramMap.put("CALLBACK_URL" ,varifyurl);


            paramMap.put("CHECKSUMHASH" ,CHECKSUMHASH);

            paramMap.put("INDUSTRY_TYPE_ID", "Retail");

            PaytmOrder Order = new PaytmOrder(paramMap);
            Log.e("checksum ", "param "+ paramMap.toString());
            Service.initialize(Order,null);

            Service.startPaymentTransaction(checksum.this, true, true,
                    checksum.this  );


        }

    }


    @Override
    public void onTransactionResponse(Bundle bundle) {
        Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
        Log.e("checksum ", " respon true " + bundle.toString());
        book_apt();


    }
    public void book_apt(){

        mref=FirebaseDatabase.getInstance().getReference("Appointments").child("Booked");
        Apntments_Model apoinmentsModel=new Apntments_Model(dID,uID,status,etime,stime,date,dname,uname,aId);
        mref.child(aId).setValue(apoinmentsModel);
        mref.child(aId).child("active").setValue("0");
        Toast.makeText(checksum.this, "Appointment Booked", Toast.LENGTH_LONG).show();
        Intent intent=new Intent(checksum.this,homepage.class);
        finishAffinity();
        startActivity(intent);
    }
    public void createorder(){
//        mref= FirebaseDatabase.getInstance().getReference("Orders");
//        Order_Model orderModel =new Order_Model(address,"0",cart,Rnam,custid,"booked",orderId,Cnumber);
//        mref.child(orderId).setValue(orderModel).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                new Database(getBaseContext()).cleanCart();
//                //Delete Cart
//
//                Toast.makeText(checksum.this, "Thank you! Order Placed", Toast.LENGTH_LONG).show();
//                Intent intent=new Intent(checksum.this,Home_Page.class);
//                finishAffinity();
//                startActivity(intent);
//            }
//
//        });


    }


    @Override
    public void networkNotAvailable() {
                finish();
    }

    @Override
    public void clientAuthenticationFailed(String s) {

    }

    @Override
    public void someUIErrorOccurred(String s) {
        Log.e("checksum ", " ui fail respon  "+ s );
    }

    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        Log.e("checksum ", " error loading pagerespon true "+ s + "  s1 " + s1);
    }

    @Override
    public void onBackPressedCancelTransaction() {
        Log.e("checksum ", " cancel call back respon  " );
    }

    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        Log.e("checksum ", "  transaction cancel " );
    }


}