package com.sfotakos.puppeteer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StageActivity extends AppCompatActivity {

    public static String JOKE_STAGE = "JOKE_STAGE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);

        TextView jokeStage = findViewById(R.id.jokeStage_tv);

        if (getIntent() != null && getIntent().getExtras() != null) {
            jokeStage.setText(getIntent().getExtras().getString(JOKE_STAGE));
        }
    }
}
