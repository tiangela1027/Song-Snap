package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.androidtutorialshub.loginregister.MainActivity;
import com.androidtutorialshub.loginregister.R;

public class TipsActivity extends AppCompatActivity {
    private final AppCompatActivity activity = TipsActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tippage);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMain);
                Log.i("Content "," Main layout ");
            }
        });

        ImageButton start_rec = (ImageButton) findViewById(R.id.start_rec);
        start_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(getApplicationContext(), RecordActivity.class);
                startActivity(intentMain);
                Log.i("Content "," Record layout ");
            }
        });

    }
}
