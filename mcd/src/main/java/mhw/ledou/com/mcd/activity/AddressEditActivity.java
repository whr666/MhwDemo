package mhw.ledou.com.mcd.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import mhw.ledou.com.mcd.HomeActivity;
import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.httputlis.HttpUrl;
import mhw.ledou.com.mcd.view.widget.TostUtlis;
import okhttp3.Call;

public class AddressEditActivity extends Activity implements OnClickListener {
    public static AddressEditActivity mactivity;
    String da_name, da_phone, da_province_id, da_city_id, da_county_id, da_address, da_id_card, defaults;
    ImageView chooseimg, editbackimg;
    EditText shenfen, dizhi, dianhua, mingzi;
    TextView shengshiqu, baocun;
    int index = 0;
    ProgressDialog pd;
    TostUtlis tostUtlis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit);
        mactivity = this;
        tostUtlis = TostUtlis.getTost(this);
        chooseimg = (ImageView) this.findViewById(R.id.chooseimg);
        editbackimg = (ImageView) this.findViewById(R.id.editbackimg);
        shenfen = (EditText) this.findViewById(R.id.shenfen);
        dizhi = (EditText) this.findViewById(R.id.dizhi);
        dianhua = (EditText) this.findViewById(R.id.dianhua);
        mingzi = (EditText) this.findViewById(R.id.mingzi);
        shengshiqu = (TextView) this.findViewById(R.id.shengshiqu);
        baocun = (TextView) this.findViewById(R.id.baocun);

        chooseimg.setOnClickListener(this);
        shengshiqu.setOnClickListener(this);
        baocun.setOnClickListener(this);
        editbackimg.setOnClickListener(this);

        if (index == 0) {
            chooseimg.setImageResource(R.mipmap.uncheck);
        } else {
            chooseimg.setImageResource(R.mipmap.pitch);
        }


    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.chooseimg:
                if (index == 1) {
                    chooseimg.setImageResource(R.mipmap.uncheck);
                    index = 0;
                } else {
                    chooseimg.setImageResource(R.mipmap.pitch);
                    index = 1;
                }
                break;
            case R.id.baocun:
                if (
                        dizhi.getText().toString().equals("") |
                        mingzi.getText().toString().equals("") |
                        shengshiqu.getText().toString().equals("")
                        ) {
                    tostUtlis.setString("数据不能为空");
                } else {
//				da_name, da_phone, da_province_id, da_city_id, da_county_id, da_address, da_id_card, defaults;
                    da_name = mingzi.getText().toString();
                    da_phone = dianhua.getText().toString();
                    da_address = dizhi.getText().toString();
                    da_province_id = HttpUrl.shengid;
                    da_city_id = HttpUrl.shiid;
                    da_county_id = HttpUrl.quid;
                    da_id_card = shenfen.getText().toString();
                    defaults = index + "";
                    setdata();
                }
                break;
            case R.id.shengshiqu:

                intent.setClass(mactivity, AddActivity.class);
                startActivity(intent);

                break;
            case R.id.editbackimg:
                finish();

            default:
                break;
        }
    }


    private void setdata() {
        // TODO Auto-generated method stub
        OkHttpUtils.get()
                .url(HttpUrl.Host
                        + "user?act=edit-address&" +
                        "true_name=" + da_name
                        + "&access_token=" + HomeActivity.activity.user.getString("token", "")
                        + "&province_id=" + da_province_id
                        + "&city_id=" + da_city_id
                        + "&county_id=" + da_county_id
                        + "&address=" + da_address
                        + "&mob_phone=" + da_phone
                )
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tostUtlis.setString(call.request() + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            tostUtlis.setString(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub

        shengshiqu.setText(HttpUrl.sheng + HttpUrl.shi + HttpUrl.qu);


        super.onResume();
    }


}
