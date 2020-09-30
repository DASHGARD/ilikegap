package com.gapprint.ilikegap.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gapprint.ilikegap.R;

public class LoginActivity extends AppCompatActivity {
    Button googleloginbt,loginbt;
    TextView forgottv,registertv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        googleloginbt = (Button) findViewById(R.id.google_button);
        loginbt = (Button)findViewById(R.id.loginbutton);
        forgottv = (TextView) findViewById(R.id.forgotpasssword);
        registertv = (TextView)findViewById(R.id.registerbutton);

        googleloginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(LoginActivity.this,"Dalam Pengerjaan",Toast.LENGTH_SHORT).show();
            }
        });
        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"Dalam Pengerjaan",Toast.LENGTH_SHORT).show();
            }
        });

        forgottv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"Dalam Pengerjaan",Toast.LENGTH_SHORT).show();
            }
        });
        registertv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(a);
                Toast.makeText(LoginActivity.this,"Dalam Pengerjaan",Toast.LENGTH_SHORT).show();
            }
        });

    }
}