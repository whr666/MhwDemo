package com.ledou.mhw.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ledou.mhw.R;
import com.ledou.mhw.view.operation.BindOnclik;
import com.ledou.mhw.view.operation.BindView;
import com.ledou.mhw.view.operation.NoteView;
import com.ledou.mhw.view.widget.Myeditext;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    public static SearchActivity activity;
    @BindView(R.id.indexSearch2)
    Myeditext indexSearch2;
    @BindView(R.id.scanback)
    TextView scanback;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        activity = this;
        NoteView.inject(this);

        indexSearch2.once(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // TODO Auto-generated method stub
                if (indexSearch2.getContent().toString().equals("")) {
                    Toast.makeText(activity, "搜索内容不能为空", 0).show();
                } else {
                    Intent intent = new Intent(activity, SearchinfoActivity.class);
                    intent.putExtra("neirong", indexSearch2.getContent());
                    intent.putExtra("title", indexSearch2.getContent());
                    intent.putExtra("id", "");
                    startActivity(intent);
                }
                return false;
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @BindOnclik({R.id.scanback})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scanback:
                finish();
                break;


            default:

                break;
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Search Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
