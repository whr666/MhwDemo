package com.ledou.mhw.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ledou.mhw.HomeActivity;
import com.ledou.mhw.R;
import com.ledou.mhw.httputlis.HttpUtlis;
import com.ledou.mhw.status.Status;
import com.ledou.mhw.view.operation.BindOnclik;
import com.ledou.mhw.view.operation.BindView;
import com.ledou.mhw.view.operation.NoteView;
import com.ledou.mhw.view.widget.TostUtlis;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.newuserlogin)
    TextView newuserlogin;
    @BindView(R.id.forgot)
    TextView forgot;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.psw)
    EditText psw;

    TostUtlis tostUtlis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Status.setstatus(this);
        NoteView.inject(this);
        tostUtlis = new TostUtlis(this);
        setonclik();

    }

    private void setonclik() {
        login.setOnClickListener(this);
        newuserlogin.setOnClickListener(this);
        forgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.login:
                if (username.getText().toString().equals("")||psw.getText().toString().equals("")) {
                    tostUtlis.setString("账号/密码不能为空");
                }else{
                    setdata();
                }
                break;
            case R.id.newuserlogin:
            intent.setClass(this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.forgot:
                tostUtlis.setString("请联系客服");
                break;

            default:

                break;
        }
    }


    public void setdata(){
        Map map = new HashMap();
        map.put("mob_phone", username.getText().toString());
        map.put("password", psw.getText().toString());
        map.put("platform", "android");
        map.put("device_name", "360 Android 10.1系统 叼不叼？6不6？");
        HttpUtlis.setjson()
                .pub_args(map)
                .method("account.login")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tostUtlis.setString(call.request().body().toString());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String access_token = jsonObject.getString("access_token");
                            String token_expire_at = jsonObject.getString("token_expire_at");
                            if (access_token.equals("")|| TextUtils.isEmpty(access_token)) {
                                tostUtlis.setString("登录失败");
                            }else{
                                HomeActivity.activity.user.edit().putString("token",access_token).commit();
                                HomeActivity.activity.user.edit().putString("tokenat",token_expire_at).commit();
                                tostUtlis.setString("登录成功");
                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                });



    }










}
