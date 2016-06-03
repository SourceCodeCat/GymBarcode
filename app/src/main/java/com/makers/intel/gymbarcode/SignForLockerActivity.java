package com.makers.intel.gymbarcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignForLockerActivity extends AppCompatActivity {

    Button btnOK;
    Button btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_for_locker);

        btnCancel = (Button)findViewById(R.id.cancel_btn);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
        btnOK = (Button)findViewById(R.id.ok_btn);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
