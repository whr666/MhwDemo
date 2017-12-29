package mhw.ledou.com.mcd;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mhw.ledou.com.mcd.httputlis.HttpUrl;
import mhw.ledou.com.mcd.status.Status;
import mhw.ledou.com.mcd.view.operation.BindView;
import mhw.ledou.com.mcd.view.operation.NoteView;
import mhw.ledou.com.mcd.view.widget.TostUtlis;
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
        tostUtlis = TostUtlis.getTost(this);
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
                if (username.getText().toString().equals("") || psw.getText().toString().equals("")) {
                    tostUtlis.setString("账号/密码不能为空");
                } else {

                    setdata();

                }
                break;
            case R.id.newuserlogin:
                intent.setClass(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.forgot:
                tostUtlis.setString("请联系客服");
                break;

            default:

                break;
        }
    }


    public void setdata() {
        http:
//caigou.mcdsh.com/app/login?act=login-pwd&phone=[手机号]&passwd=[密码]
        OkHttpUtils.get()
                .url(HttpUrl.Host + "login?act=login-pwd&phone=" + username.getText().toString() + "&passwd=" + psw.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tostUtlis.setString(call.request() + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        if (TextUtils.isEmpty(response)) {
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String error = jsonObject.getString("error");
                                if (error.equals("0")) {
                                    JSONObject info = jsonObject.getJSONObject("info");
                                    String access_token = info.getString("access_token");
                                    String uid = info.getString("uid");
                                    HomeActivity.activity.user.edit().putString("token", access_token).commit();
                                    HomeActivity.activity.user.edit().putString("uid", uid).commit();
                                    finish();
                                } else {
                                    tostUtlis.setString(jsonObject.getString("msg"));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });


    }


}