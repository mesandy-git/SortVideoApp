package com.video.playerapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.video.playerapp.R;

public class VideoFragment extends Fragment implements Player.EventListener, View.OnTouchListener {
    private static final String TAG = "VideoFragment";
    private PlayerView playerView;
    private View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_video, container, false);
        playerView = v.findViewById(R.id.video_player);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        playerView.setPlayer();
        SimpleExoPlayer player = new SimpleExoPlayer.Builder(getContext()).build();
        player.addMediaItem(MediaItem.fromUri("https://firebasestorage.googleapis.com/v0/b/large-storage-9ea8c.appspot.com/o/150021382_414771289588250_2019407489563652971_n.mp4?alt=media&token=95df2953-e31c-486b-882f-587772804324"));
        player.addMediaItem(MediaItem.fromUri("https://firebasestorage.googleapis.com/v0/b/large-storage-9ea8c.appspot.com/o/video.mp4?alt=media&token=f75bcaa2-7c7c-4713-8700-58780d41df98"));
        player.prepare();
        player.play();
        playerView.setPlayer(player);
        playerView.setUseController(false);
        playerView.setOnTouchListener(this);
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        Log.d(TAG, "onPlayerError: " + error);
    }

    @Override
    public void onPlaybackStateChanged(int state) {
        Log.d(TAG, "onPlayerError: " + state);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.video_player)
            if (playerView.getPlayer().isPlaying()) {
                playerView.getPlayer().pause();
            } else {
                playerView.getPlayer().play();
            }
        return false;
    }
}