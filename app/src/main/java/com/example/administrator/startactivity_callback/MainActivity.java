package com.example.administrator.startactivity_callback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private TextView mSetText;
    private Button mGetData;
    private static int mRequest_code = 5;
    private static final String TAG = "MainActivity";

    public static OnCityChangeCallBack OnCityChangeCallBack;


    private String string;


    private TextView mSetText2;
    private Button mGetData2;

    private TextView txt_display3;

    private Button button_eventbus;


    private TextView txt_display4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getInstance().register(this);

        mGetData = (Button) findViewById(R.id.button_get);
        mSetText = (TextView) findViewById(R.id.txt_display);

        mGetData2 = (Button) findViewById(R.id.button_get2);
        mSetText2 = (TextView) findViewById(R.id.txt_display2);

        txt_display3= (TextView) findViewById(R.id.txt_display3);
        button_eventbus= (Button) findViewById(R.id.button_eventbus);

        txt_display4= (TextView) findViewById(R.id.txt_display4);

        mGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivityForResult(intent,mRequest_code);

            }
        });


        mGetData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

                OnCityChangeCallBack = new OnCityChangeCallBack() {

                    @Override
                    public void onCityChange(String area) {


                        mSetText2.setText(area);
                        //  Log.d(area, "adskljjjjjjjjjjjjjjjjjjf");


                    }
                    @Override
                    public void onCityChange2(String area2) {


                       /* mSetText2.setText(area2);
                        Log.d(area2, "adskljjjjjjjjjjjjjjjjjjf");*/


                    }
                };
            }
        });




        button_eventbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });

    }

    public void onEvent(String i){
        Log.i("cky", i);
    }

    public void onEventMain(String i){
        txt_display3.setText(i);
    }




    public void onEventMain(Info i){
        txt_display4.setText(i.msg+i.str3+i.str2);
    }

  /*  public void onEventMain(Info2 i){
        txt_display3.setText(txt_display3.getText()+i.msg);
    }*/




    @Override
    //dispaly the message received from intent
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String value = String.valueOf(resultCode);
        String dispaly = data.getStringExtra("message");
        Log.e(TAG, value);

        if (resultCode == mRequest_code) {
            mSetText.setText(dispaly);

        } else {
            Toast.makeText(MainActivity.this, "Request code wrong", Toast.LENGTH_SHORT).show();
        }


    }


    public interface OnCityChangeCallBack {

        void onCityChange(String area);
        void onCityChange2(String area2);
    }


}
