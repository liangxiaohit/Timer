package com.example.mytimer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mytimer.Tools.CheckAll;
import com.example.mytimer.Tools.Constants;

public class MainActivity extends Base {

    private EditText mHourStudy,mMinStudy,mSecStudy,mHourRest,mMinRest,mSecRest = null;

    private Button mStart = null;

    private CountDownTimer mTimer = null;

    private int mNumberHourStudy,mNumberMinStudy,mNumberSecStudy,mNumberHourRest,mNumberMinRest,mNumberSecRest = 0;

    private boolean isStudyRun = true;

    private boolean isRunning = false;

    private AlertDialog.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFromIntent();
        initComponents();
        initFeatures();
        setListener();
    }

    @Override
    public void getDataFromIntent() {

    }

    @Override
    public void initComponents() {

        mHourStudy = findViewById(R.id.mytimer_main_et_hour_study);
        mMinStudy = findViewById(R.id.mytimer_main_et_min_study);
        mSecStudy = findViewById(R.id.mytimer_main_et_sec_study);
        mHourRest = findViewById(R.id.mytimer_main_et_hour_rest);
        mMinRest = findViewById(R.id.mytimer_main_et_min_rest);
        mSecRest = findViewById(R.id.mytimer_main_et_sec_rest);
        mStart = findViewById(R.id.mytimer_main_bt_start);

        createBuilder();

    }

    @Override
    public void initFeatures() {


    }

    @Override
    public void setListener() {

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = !isRunning;
                if(isRunning){
                    mStart.setText("STOP");
                    getAllTime();
                    startTimer(mNumberHourStudy,mNumberMinStudy,mNumberSecStudy);
                }else {
                    mStart.setText("START");
                    mTimer.cancel();
                }

            }
        });
    }

    public void getAllTime(){
        mNumberHourStudy = CheckAll.isZero(mHourStudy.getText().toString());
        mNumberMinStudy = CheckAll.isZero(mMinStudy.getText().toString());
        mNumberSecStudy = CheckAll.isZero(mSecStudy.getText().toString());
        mNumberHourRest = CheckAll.isZero(mHourRest.getText().toString());
        mNumberMinRest = CheckAll.isZero(mMinRest.getText().toString());
        mNumberSecRest = CheckAll.isZero(mSecRest.getText().toString());
    }

    public void createBuilder(){

        builder = new AlertDialog.Builder(this);

        builder.setTitle(Constants.TIME_UP).setIcon(android.R.drawable.ic_dialog_info);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                mTimer = null;
                if(isStudyRun){
                    mHourStudy.setText(mNumberHourStudy+"");
                    mMinStudy.setText(mNumberMinStudy+"");
                    mSecStudy.setText(mNumberSecStudy+"");
                    startTimer(mNumberHourRest,mNumberMinRest,mNumberSecRest);
                }else {
                    mHourRest.setText(mNumberHourRest+"");
                    mMinRest.setText(mNumberMinRest+"");
                    mSecRest.setText(mNumberSecRest+"");
                    startTimer(mNumberHourStudy,mNumberMinStudy,mNumberSecStudy);
                }
            }
        });
    }

    public void startTimer(int hour, int min, int sec){

        mTimer = new CountDownTimer((hour * 60 * 60 + min * 60 + sec) * 1000,1000) {
            @Override
            public void onTick(long l) {
                if(isStudyRun){
                    mMinStudy.setText((Integer.parseInt(mMinStudy.getText().toString())-1)+"");
                }else {
                    mMinRest.setText((Integer.parseInt(mMinStudy.getText().toString())-1)+"");
                }
            }

            @Override
            public void onFinish() {
                builder.show();
            }
        };

        mTimer.start();
    }
}