package com.example.mytimer.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.mytimer.Tools.Constants;

public class TimerService extends Service {

//    private MyBinder binder = new MyBinder();

    private CountDownTimer mTimer = null;

    private int hour,min,sec = 0;

    private int mTotalTime = 0;

    private Intent mIntent = new Intent();

    public TimerService() {
    }

//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
////        throw new UnsupportedOperationException("Not yet implemented");
//        return binder;
//    }
//
//    public class MyBinder extends Binder{
//
//        public void setMins(int mHour, int mMin, int mSec){
//            hour = mHour;
//            min = mMin;
//            sec = mSec;
//        }
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("--------Start service");
        initFeature();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        hour = intent.getIntExtra("HOUR",0);
        min = intent.getIntExtra("MIN",0);
        sec = intent.getIntExtra("SEC",0);
        mTotalTime = hour * 60 * 60 + min * 60 + sec;
        System.out.println("--------onStartCommand "+hour+" "+min+" "+sec);
        mIntent.setAction(Constants.NAME_RECEIVER);
//        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        countdown();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void initFeature(){

    }

    public void countdown(){

        System.out.println("-------------countdown "+hour+" "+min+" "+sec);
        mTimer = new CountDownTimer((mTotalTime) * 1000,1000) {
            @Override
            public void onTick(long l) {
                //发送广播
                System.out.println("-------------"+mTotalTime);
                mTotalTime =mTotalTime - 1;


            }

            @Override
            public void onFinish() {
                //发送广播
                sendBroadcast(mIntent);
            }
        };

        mTimer.start();
    }
}