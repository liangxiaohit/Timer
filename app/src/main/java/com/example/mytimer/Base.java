package com.example.mytimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mytimer.Tools.Tools;

public abstract class  Base extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tools.getInstance().hideStateBar(getWindow());
        supportRequestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.base_activity);
    }

    public abstract void getDataFromIntent();

    public abstract void initComponents();

    public abstract void initFeatures();

    public abstract void setListener();

}