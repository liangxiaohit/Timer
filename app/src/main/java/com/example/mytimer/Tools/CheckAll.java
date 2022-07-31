package com.example.mytimer.Tools;

import android.app.Activity;
import android.util.Log;

public class CheckAll {

    public static int isZero(Object mObj){

        int isZero = 0;

        if(null == mObj){
            isZero = 0;
        }else {
            String mStr = mObj.toString();

            if(null == mStr || "".equals(mStr) || "[]".equals(mStr)){
                isZero = 0;
            }else {
                isZero = Integer.parseInt(mStr);
            }
        }

        return isZero;

    }
}
