package com.example.user.spotify20;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.IOException;

public class SongStreamService extends Service {

    MediaPlayer player;
    String songDirectory;
    boolean streamingSong = false;

    @Override
    public void onCreate() {
        super.onCreate();

        player = new MediaPlayer();
    }
    private void initMediaPlayerPreference() {
        player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);

        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try{
            String uri = "android.resource://com.example.user.spotify20/raw/" + songDirectory;
            player.setDataSource(getApplicationContext(), Uri.parse(uri));
            player.prepare();
            player.setLooping(true);
            player.start();
        } catch(IOException ex) {

        }


    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        songDirectory = intent.getStringExtra("MP3 file of song");
        initMediaPlayerPreference();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(player != null)
            player.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
