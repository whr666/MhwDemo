package mhw.ledou.com.mcd;

import android.content.Context;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XIAOXIN on 2017/10/18.
 */

public class ListViewUtlis {



    public static void setlist(ListView listview, Context context){

        List<String> leimustring = new ArrayList<String>();
        leimustring.add("");
        IndexListendAdapter endadapter = new IndexListendAdapter(leimustring, context);
        listview.setAdapter(endadapter);
//	        endadapter.notifyDataSetChanged();
//	        IndexView.setListViewHeightBasedOnChildren(listview);

    }



}
