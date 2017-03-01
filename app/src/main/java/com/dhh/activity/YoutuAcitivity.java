package com.dhh.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by D on 28/02/2017.
 */

public class YoutuAcitivity extends YouTubeBaseActivity {
    private Button btn;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.view_youtobe);
        youTubePlayerView=(YouTubePlayerView)findViewById(R.id.youtobe_view);
        btn =(Button)findViewById(R.id.btn_see);
        onInitializedListener= new YouTubePlayer.OnInitializedListener(){

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("6_n7ru1e-rg");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              youTubePlayerView.initialize("AIzaSyDzJk1BW9YX0SvH1jpEkMVeDqvhXfNLkR4",onInitializedListener);
            }
        });
    }





}
