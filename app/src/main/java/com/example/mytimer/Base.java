package com.example.mytimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public abstract class  Base extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
    }

    public abstract void getDataFromIntent();

    public abstract void initComponents();

    public abstract void initFeatures();

    public abstract void setListener();

}