package com.example.mytimer.Tools;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;

import com.example.mytimer.R;

public class MediaC {

    private AssetManager mAM = null;
    private MediaPlayer player = null;
    private AssetFileDescriptor fileDescriptor = null;


    /**
     * voice
     * @param soundType
     */
    private MediaPlayer mMediaPlayer = null;
    private MediaPlayer.OnCompletionListener CompleteLisner = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            System.out.println("----------- in OnCompletionListener");
            releastMedia();
        }
    };

    private void releastMedia(){

        if(null != mMediaPlayer){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void playSound(Context activity, int soundType,boolean isLoop) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                if(soundType == Constants.SOUND_CORRECT){
                    mMediaPlayer = MediaPlayer.create(activity, R.raw.success_sound);
                }else if(soundType == Constants.SOUND_ERROR) {
                    mMediaPlayer = MediaPlayer.create(activity, R.raw.error_sound);
                }else if(soundType == Constants.SOUND_INFO){
                    mMediaPlayer = MediaPlayer.create(activity, R.raw.alert_sound);
                }else {
                    mMediaPlayer = MediaPlayer.create(activity, R.raw.alert_sound);
                }

                mMediaPlayer.start();

                mMediaPlayer.setOnCompletionListener(CompleteLisner);

                if(isLoop){
                    mMediaPlayer.setLooping(true);
                }
            }
        }).start();
    }

    public void stopPlaying(){
        if(mMediaPlayer.isPlaying() || mMediaPlayer.isLooping()){
            mMediaPlayer.setLooping(false);
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
        }
    }
}
