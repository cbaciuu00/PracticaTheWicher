package com.thewitcherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.thewitcherapp.webview.Loader;

public class MonsterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster);

        String  titol = getIntent().getStringExtra("EXTRA_TITLE");
        String  intro = getIntent().getStringExtra("EXTRA_INTRO");
        Integer img = getIntent().getIntExtra("EXTRA_IMG",0);
        String  author = getIntent().getStringExtra("EXTRA_AUTHOR");
        String  detail = getIntent().getStringExtra("EXTRA_DETAIL");
        String  monsterType = getIntent().getStringExtra("EXTRA_MONSTER_TYPE");

        ImageView imageView = findViewById(R.id.imvMonster);
        //TextView textViewType = findViewById(R.id.txvMonsterType);
        TextView textMonsterType = findViewById(R.id.txvMonsterType);
        TextView txvMonsterName = findViewById(R.id.txvMonsterName);
        //textViewType.setText(monsterType);
        imageView.setImageResource(img);
        textMonsterType.setText(monsterType);
        txvMonsterName.setText(titol);

        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        String html = Loader.loadFileFromAssets(this, "plantilla.html");
        html = html.replaceAll("\\{\\{INTRO\\}\\}",intro);
        html = html.replaceAll("\\{\\{AUTHOR\\}\\}",author);
        html = html.replaceAll("\\{\\{DETAIL\\}\\}",detail);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.loadDataWithBaseURL("file:///android_asset/",
                html, "text/html", "UTF-8", null);
    }

    public void cerrarMonster(View view) {
        finish();
    }
}