package com.example.mytimer.Tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytimer.R;

import java.lang.reflect.Field;

public class Tools {

    private MediaC mMedia = new MediaC();

    public static class ToolsHolder{

        private static Tools mTools = new Tools();
    }

    private Tools(){
    }

    public static Tools getInstance(){

        return ToolsHolder.mTools;
    }

    public void showAlertToUser(Context mContext, String mMsg, int mSoundType){

//        mAlert.showInfo(mContext,mMsg);
        mMedia.playSound(mContext,mSoundType,true);
    }

    /**
     * Hide the status bar
     * @param window
     */
    public void hideStateBar(Window window, Activity mActivity) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && Build.VERSION_CODES.R > Build.VERSION.SDK_INT){
            try {
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                field.setInt(window.getDecorView(), Color.TRANSPARENT);  //change to transparent
                window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            } catch (Exception e) {}
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){

            WindowInsetsController mControl = mActivity.getWindow().getDecorView().getWindowInsetsController();

            mControl.hide(WindowInsets.Type.statusBars());
        }

        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    /**
     * 自定义toast提示功能
     * @param mActivity
     * @param mContent
     */
    public void showToast(Activity mActivity,String mContent) {
        Toast toast = new Toast(mActivity);
        LayoutInflater layoutInflater  = LayoutInflater.from(mActivity);;
        View v = layoutInflater.inflate(R.layout.toast_style, null);
        TextView mTV = v.findViewById(R.id.toast_tv_show_content);
        mTV.setText(mContent);
        toast.setView(v);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public MediaC getmMedia() {
        return mMedia;
    }

    public void setmMedia(MediaC mMedia) {
        this.mMedia = mMedia;
    }
}
