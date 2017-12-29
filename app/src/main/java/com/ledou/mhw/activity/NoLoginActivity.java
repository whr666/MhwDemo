package com.ledou.mhw.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ledou.mhw.R;

public class NoLoginActivity extends AppCompatActivity {

    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_login);
        login = (TextView) findViewById(R.id.gologin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoLoginActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
