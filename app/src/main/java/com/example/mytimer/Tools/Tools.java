package com.example.mytimer.Tools;

import android.content.Context;

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
        mMedia.playSound(mContext,mSoundType);
    }
}
