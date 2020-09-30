package com.gapprint.ilikegap.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gapprint.ilikegap.R;

public class RegisterActivity extends AppCompatActivity {
    Button registerbtn,registergooglebtn;
    TextView forgotpasswordtv,logintv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerbtn = (Button) findViewById(R.id.registerbutton);
        registergooglebtn = (Button)findViewById(R.id.google_button);
        forgotpasswordtv = (TextView)findViewById(R.id.forgotpasssword);
        logintv = (TextView) findViewById(R.id.loginbutton);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
       registergooglebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            Toast.makeText(RegisterActivity.this,"On progress",Toast.LENGTH_SHORT).show();
           }
       });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this,"On progress",Toast.LENGTH_SHORT).show();
            }
        });
        forgotpasswordtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this,"On progress",Toast.LENGTH_SHORT).show();
            }
        });
        logintv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}