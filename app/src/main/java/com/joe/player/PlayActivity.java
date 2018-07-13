package com.joe.player;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.joe.player.widget.media.AndroidMediaController;
import com.joe.player.widget.media.IjkVideoView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class PlayActivity extends AppCompatActivity {

    private IjkVideoView videoView;

    private TableLayout hud_view;

    private AndroidMediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        mediaController = new AndroidMediaController(this, false);
        hud_view = findViewById(R.id.hud_view);
        videoView = findViewById(R.id.videoView);
        videoView.setMediaController(mediaController);
        videoView.setHudView(hud_view);
        videoView.setVideoURI(Uri.parse("http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/08/17/63bf8bcc706a46b58ee5c821edaee661_h264818000nero_aac32-5.mp4"));
        videoView.start();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (!videoView.isBackgroundPlayEnabled()) {
            videoView.stopPlayback();
            videoView.release(true);
            videoView.stopBackgroundPlay();
        } else {
            videoView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }
}
