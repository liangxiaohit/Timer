package com.example.mytimer;

import androidx.appcompat.app.AlertDialog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytimer.Service.TimerService;
import com.example.mytimer.Tools.CheckAll;
import com.example.mytimer.Tools.Constants;
import com.example.mytimer.Tools.Tools;

public class MainActivity extends Base {

    private EditText mHourStudy,mMinStudy,mSecStudy,mHourRest,mMinRest,mSecRest = null;

    private TextView mReset = null;

    private Button mStart = null;

    private int mNumberHourStudy,mNumberMinStudy,mNumberSecStudy,mNumberHourRest,mNumberMinRest,mNumberSecRest = 0;

    private boolean isRunning = false;

    private AlertDialog.Builder builder = null;

    private TimerReceiver mMyReceiver = null;

    private ChooseAddressWheel mWheel = null;

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

        View mTitleView = findViewById(R.id.title_in_base_main);
        mReset =mTitleView.findViewById(R.id.BaseActivity_TV_reset);

        createBuilder();

    }

    @Override
    public void initFeatures() {

        initReceiver();
    }

    @Override
    public void setListener() {

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = !isRunning;
                getAllTime();
                int hour,min,sec = 0;
                if(isRunning){
                    hour = mNumberHourStudy;
                    min = mNumberMinStudy;
                    sec = mNumberSecStudy;
                }else {
                    hour = mNumberHourRest;
                    min = mNumberMinRest;
                    sec = mNumberSecRest;
                }

                mStart.setText("STOP");
                mStart.setEnabled(false);
                Toast.makeText(MainActivity.this,"Time countdown start!",Toast.LENGTH_LONG).show();
                setEditTextAvalible(false);


                Intent intent = new Intent(MainActivity.this,TimerService.class);
                intent.putExtra("HOUR",hour);
                intent.putExtra("MIN",min);
                intent.putExtra("SEC",sec);
                startService(intent);

            }
        });


        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEditTextAvalible(true);
                mStart.setText("START");
                mStart.setEnabled(true);
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

    public void initReceiver(){
        mMyReceiver = new TimerReceiver();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(Constants.NAME_RECEIVER);
        registerReceiver(mMyReceiver,mFilter);
    }

    public void createBuilder(){

        builder = new AlertDialog.Builder(this);

        builder.setTitle(Constants.TIME_UP).setIcon(android.R.drawable.ic_dialog_info);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                mStart.setText("START");
                mStart.setEnabled(true);
                setEditTextAvalible(true);
                Tools.getInstance().getmMedia().stopPlaying();
            }
        });

    }

    public void setEditTextAvalible(boolean isAvalible){
        mHourStudy.setEnabled(isAvalible);
        mMinStudy.setEnabled(isAvalible);
        mSecStudy.setEnabled(isAvalible);
        mHourRest.setEnabled(isAvalible);
        mMinRest.setEnabled(isAvalible);
        mSecRest.setEnabled(isAvalible);
    }

    public class TimerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String mAction = intent.getAction();
            if(Constants.NAME_RECEIVER.equals(mAction)){
                System.out.println("-------Receiver get");
                builder.show();
                Tools.getInstance().showAlertToUser(MainActivity.this,"",Constants.SOUND_CORRECT);
            }
        }
    }

}