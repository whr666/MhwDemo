package com.ledou.mhw.html;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ledou.mhw.httputlis.HttpUrl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by XIAOXIN on 2017/9/27.
 * 暂时作废
 */

public class HtmlUtlis {
    public static void getHtml(final WebView webview,final String html){
        final Handler handler = new Handler()  {
            public void handleMessage(Message msg)
            {
                switch(msg.what)
                {
                    case 1:
                        WebSettings webSettings = webview.getSettings();
                        webSettings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
//                        webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
                        webview.getSettings().setJavaScriptEnabled(true);
                        webview.getSettings().setBuiltInZoomControls(false);
                        webview.getSettings().setDisplayZoomControls(false);
                        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //取消滚动条白边效果
                        webview.setWebChromeClient(new WebChromeClient());
                        webview.setWebViewClient(new WebViewClient());
                        webview.getSettings().setDefaultTextEncodingName("UTF-8") ;
                        webview.getSettings().setBlockNetworkImage(false);
//                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//                            webview.getSettings().setMixedContentMode(webview.getSettings().MIXED_CONTENT_ALWAYS_ALLOW);  //注意安卓5.0以上的权限
//                        }
                        webview.loadDataWithBaseURL(HttpUrl.Host,getNewContent(html),"text/html", "UTF-8", null);
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message message=new Message();
                message.what=1;
                handler.sendMessage(message);
            }
        });
        thread.start();

    }

    private static String getNewContent(String html) {
        Document doc= Jsoup.parse(html);
        Elements elements=doc.getElementsByTag("img");
        for (Element element : elements) {
                element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    };


}
