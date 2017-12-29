package mhw.ledou.com.mcd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import mhw.ledou.com.mcd.httputlis.HttpUrl;
import mhw.ledou.com.mcd.status.Status;
import mhw.ledou.com.mcd.view.widget.TostUtlis;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static MainActivity activity;
ImageView go_btn;
    EditText editText;
TostUtlis tostUtlis;
     SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Status.setstatus(this);
        activity = this;
        sharedPreferences = getSharedPreferences("role", Context.MODE_APPEND);
        sharedPreferences.edit().commit();
        tostUtlis = new TostUtlis(this);
        editText = (EditText) this.findViewById(R.id.bianma);
        go_btn = (ImageView) this.findViewById(R.id.go_btn);
        setoclik();

    }

    private void setoclik() {
        editText.setOnClickListener(this);
        go_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_btn:
                if (TextUtils.isEmpty(editText.getText().toString())) {
tostUtlis.setString("编码不能为空");
                } else {
                    setdata();
                }
                break;


            default:

                break;
        }
    }


    public void setdata(){
        OkHttpUtils
                .get()
                .url(HttpUrl.Host+HttpUrl.dengjima+editText.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
tostUtlis.setString(call.request()+"");
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        try {
                            if (TextUtils.isEmpty(response)){
tostUtlis.setString("网络异常");
                            }else {
                                JSONObject jsonObject = new JSONObject(response);
                                Log.i("json",jsonObject+"");
                                String error = jsonObject.getString("error");
                                if (error.equals("0")){
                                    JSONObject info=   jsonObject.getJSONObject("info");
                                    String role = info.getString("role");
                                    sharedPreferences.edit().putString("role",role).commit();
                                    Intent  intent = new Intent(MainActivity.this,HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                tostUtlis.setString(jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }




}
