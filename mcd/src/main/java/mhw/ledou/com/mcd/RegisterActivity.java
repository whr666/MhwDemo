package mhw.ledou.com.mcd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import mhw.ledou.com.mcd.httputlis.HttpUrl;
import mhw.ledou.com.mcd.status.Status;
import mhw.ledou.com.mcd.view.operation.BindView;
import mhw.ledou.com.mcd.view.operation.NoteView;
import mhw.ledou.com.mcd.view.widget.TostUtlis;
import okhttp3.Call;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

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
        tostUtlis = TostUtlis.getTost(this);
        setonclik();
    }

    private void setonclik() {
        login.setOnClickListener(this);
        yanzhengma.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.login:
                if (username.getText().toString().equals("") || TextUtils.isEmpty(username.getText().toString()) ||
                        psw.getText().toString().equals("") || TextUtils.isEmpty(psw.getText().toString())) {
                    tostUtlis.setString("账号/密码不能为空");
                } else {

                    setdata();

                }

                break;


            default:

                break;
        }
    }

    public void setdata() {

        OkHttpUtils.get()
                .url(HttpUrl.Host+"login?act=sign-up&phone=" + username.getText().toString() + "&passwd=" + psw.getText().toString())
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
                                    tostUtlis.setString(jsonObject.getString("msg"));
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
