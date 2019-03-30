package com.example.aser.gooddocter001;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DoctersList extends AppCompatActivity {

    private ArrayList<Doc_data> rentalProperties = new ArrayList<>();
    String NameStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docters_list);

        //create our property elements
        rentalProperties.add(new Doc_data("Ahmed Riaz","MBBS,FPCS,DErmatologist",500.00,"doctor",true));
        rentalProperties.add(new Doc_data("Hassan Majeed","MBBS,FPCS,DErmatologist",400.00,"doctor",true));
        rentalProperties.add(new Doc_data("Muneeb Ur Rehman","MBBS,FPCS,DErmatologist",300.00,"doctor",false));
        rentalProperties.add(new Doc_data("Shoib Mughal","MBBS,FPCS,DErmatologist",200.00,"doctor",true));

        //create our new array adapter
        ArrayAdapter<Doc_data> adapter = new propertyArrayAdapter(this, 0, rentalProperties);

        //Find list view and bind it with the custom adapter
        final ListView listView = (ListView) findViewById(R.id.customListView);
        listView.setAdapter(adapter);


        //add event listener so we can handle clicks
        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Doc_data docdata = rentalProperties.get(position);
                Intent intent = new Intent(DoctersList.this, DetailActivity.class);
                intent.putExtra("streetName", docdata.getDocterName());
                intent.putExtra("state", docdata.getStatus());
                intent.putExtra("image", docdata.getImage());
                startActivityForResult(intent, 1000);
            }
        };
        //set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);


    }

    //custom ArrayAdapater
    class propertyArrayAdapter extends ArrayAdapter<Doc_data>{

        private Context context;
        private List<Doc_data> rentalProperties;

        //constructor, call on creation
        public propertyArrayAdapter(Context context, int resource, ArrayList<Doc_data> objects) {
            super(context, resource, objects);

            this.context = context;
            this.rentalProperties = objects;
        }

        //called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent) {

            //get the docdata we are displaying
            Doc_data docdata = rentalProperties.get(position);

            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            //conditionally inflate either standard or special template
            View view;
            if(docdata.getStatus() == false){
                view = inflater.inflate(R.layout.property_layout_alt, null);
                 NameStatus = docdata.getDocterName()+" "+"(Online)";
            }else{
                view = inflater.inflate(R.layout.property_layout, null);
                NameStatus = docdata.getDocterName()+" "+"(Offline)";
            }

            TextView DocterName = (TextView) view.findViewById(R.id.address);
            TextView description = (TextView) view.findViewById(R.id.description);
            TextView price = (TextView) view.findViewById(R.id.price);
            ImageView image = (ImageView) view.findViewById(R.id.image);

            //set name and description
            DocterName.setText(NameStatus);

            //display  for description
            description.setText(docdata.getDescription());

            //set price and rental attributes
            price.setText("$" + String.valueOf(docdata.getFee()));

            //get the image associated with this docdata
            int imageID = context.getResources().getIdentifier(docdata.getImage(), "drawable", context.getPackageName());
            image.setImageResource(imageID);

            return view;
        }
    }

}
