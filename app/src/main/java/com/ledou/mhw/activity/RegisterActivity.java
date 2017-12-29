package com.ledou.mhw.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ledou.mhw.R;
import com.ledou.mhw.status.Status;
import com.ledou.mhw.view.operation.BindView;
import com.ledou.mhw.view.operation.NoteView;
import com.ledou.mhw.view.widget.TostUtlis;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.yanzhengma)
    TextView yanzhengma;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.psw)
    EditText psw;

    TostUtlis tostUtlis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Status.setstatus(this);
        NoteView.inject(this);
        tostUtlis = new TostUtlis(this);
        setonclik();
    }
    private void setonclik() {
        login.setOnClickListener(this);
        yanzhengma.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
        case R.id.login :
            if (username.getText().toString().equals("") || TextUtils.isEmpty(username.getText().toString())) {
                tostUtlis.setString("账号不能为空");
            }else{
                intent.setClass(this,Register2Activity.class);
                intent.putExtra("name",username.getText().toString());
                startActivity(intent);
                finish();
            }

        break;


        default :

        break;
        }
    }
}
