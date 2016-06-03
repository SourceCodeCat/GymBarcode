package com.makers.intel.gymbarcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class MainActivity extends AppCompatActivity {



    private static Button btn;
    private static Button btnSignLocker;
    private static TextView tv;
    private static TextView nextInstructions;
    private CompoundButton autoFocus;
    private CompoundButton useFlash;
    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final int RC_SIGN_FOR_LOCKER = 9002;
    private static final String TAG = "BarcodeMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.textView);
        nextInstructions = (TextView)findViewById(R.id.nextInstructions);
        autoFocus = (CompoundButton) findViewById(R.id.auto_focus);
        useFlash = (CompoundButton) findViewById(R.id.use_flash);
        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchBarcodeCaptureActivity();
            }
        });
        btnSignLocker = (Button)findViewById(R.id.lockers);
        btnSignLocker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSignForLocker();
            }
        });






    }
    void launchSignForLocker()
    {
        Intent intent = new Intent(this, SignForLockerActivity.class);
        startActivityForResult(intent, RC_SIGN_FOR_LOCKER);

    }
    void launchBarcodeCaptureActivity()
    {
        // launch barcode activity.
        Intent intent = new Intent(this, BarcodeCaptureActivity.class);
        intent.putExtra(BarcodeCaptureActivity.AutoFocus, autoFocus.isChecked());
        intent.putExtra(BarcodeCaptureActivity.UseFlash, useFlash.isChecked());
        startActivityForResult(intent, RC_BARCODE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    tv.setText(R.string.barcode_success);
                    tv.setText(barcodeToName(barcode.displayValue));
                    Log.d(TAG, "Barcode read: " + barcodeToName(barcode.displayValue));
                    nextInstructions.setText("Si tu nombre es correcto da click en 'Sign For a Locker!', de lo contrario da click en 'Read Barcode' para intentar leer de nuevo tu batch");
                    btnSignLocker.setVisibility(View.VISIBLE);

                } else {
                    tv.setText(R.string.barcode_failure);
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                tv.setText(String.format(getString(R.string.barcode_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    String barcodeToName(String val)
    {
        int v;
        try {
            v = Integer.parseInt(val);
        }
        catch(Exception e)
        {
            Log.d(TAG, "ID should only contain numbers, returning the string value...\n");
            return val;
        }

        switch (v)
        {
            case 11:
                return "Marco Antonio";
            case 12:
                return "Lynette Layne";
            case 13:
                return "Abraham Arce Moreno";
            default:
                return val;
        }

    }
}
