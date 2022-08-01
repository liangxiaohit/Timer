package com.example.mytimer.Tools;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

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
    public void hideStateBar(Window window) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            try {
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                field.setInt(window.getDecorView(), Color.TRANSPARENT);  //change to transparent
            } catch (Exception e) {}
        }

        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    public MediaC getmMedia() {
        return mMedia;
    }

    public void setmMedia(MediaC mMedia) {
        this.mMedia = mMedia;
    }
}
