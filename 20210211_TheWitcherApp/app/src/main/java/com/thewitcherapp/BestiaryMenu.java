package com.thewitcherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BestiaryMenu extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestiary_menu);

        button =(Button) findViewById(R.id.btnToBestiary);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBestiaryActivity();
            }
        });
    }

    private void openBestiaryActivity() {
        Intent i = new Intent(this,BestiaryActivity.class);
        startActivity(i);
    }


}