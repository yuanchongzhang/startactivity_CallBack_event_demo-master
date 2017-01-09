package com.example.administrator.startactivity_callback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



/**
 * Created by bridgeit on 25/10/16.
 */

public class SecondActivity extends Activity {
    private EditText mMessage;
    private Button mSendMsg;

    private EditText mMessage2;
    private Button mSendMsg2;


    private EditText mMessage3;
    private Button mSendMsg3;



    private EditText mMessage4;
    private Button mSendMsg4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mMessage = (EditText)findViewById(R.id.edit_msg);
        mSendMsg = (Button)findViewById(R.id.button_submit);

        mMessage2 = (EditText)findViewById(R.id.edit_msg2);
        mSendMsg2 = (Button)findViewById(R.id.button_submit2);

        mMessage3 = (EditText)findViewById(R.id.edit_msg3);
        mSendMsg3 = (Button)findViewById(R.id.button_submit3);


        mMessage4 = (EditText)findViewById(R.id.edit_msg4);
        mSendMsg4 = (Button)findViewById(R.id.button_submit4);

        mSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            //send data to the main Activity
            public void onClick(View v) {
                String message = mMessage.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("message",message);
                setResult(5,intent);

              //  MainActivity.OnCityChangeCallBack.onCityChange2(message);

                finish();
            }
        });


        mSendMsg2.setOnClickListener(new View.OnClickListener() {
            @Override
            //send data to the main Activity
            public void onClick(View v) {
                String message = mMessage2.getText().toString();
                MainActivity.OnCityChangeCallBack.onCityChange(message);

                finish();
            }
        });




        mSendMsg3.setOnClickListener(new View.OnClickListener() {
            @Override
            //send data to the main Activity
            public void onClick(View v) {
                String message = mMessage3.getText().toString();
//                EventBus.getDefault().post(message);
                EventBus.getInstance().post(message);
                finish();

            }
        });




        mSendMsg4.setOnClickListener(new View.OnClickListener() {
            @Override
            //send data to the main Activity
            public void onClick(View v) {
                String message = mMessage3.getText().toString();
//                EventBus.getDefault().post(message);
                EventBus.getInstance().post(new Info("aaa","bbb","ccc"));
                finish();

            }
        });


    }
}
