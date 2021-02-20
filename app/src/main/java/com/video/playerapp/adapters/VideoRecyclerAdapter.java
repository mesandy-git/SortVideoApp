package com.video.playerapp.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.video.playerapp.R;
import com.video.playerapp.databinding.VideoViewBinding;
import com.video.playerapp.interfaces.ControllerCallBack;

public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.ViewHolder> implements Player.EventListener, ControllerCallBack {

    private final Uri[] videoUrls;
    private final Context parentActivity;
    private final RecyclerView recyclerView;
    private final int currentPage = -1;

    VideoRecyclerAdapter(Context parentActivity, Uri[] videoUrls, RecyclerView recyclerView) {
        this.videoUrls = videoUrls;
        this.parentActivity = parentActivity;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public VideoRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.video_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoRecyclerAdapter.ViewHolder holder, int position) {
        Uri url = videoUrls[position];
        setPlayer(ViewHolder.videoViewBinding.videoPlayer, url);

    }

    private void setPlayer(PlayerView playerView, Uri url) {
        SimpleExoPlayer player = new SimpleExoPlayer.Builder(parentActivity).build();
        player.addMediaItem(MediaItem.fromUri(url));
        player.prepare();
        playerView.setPlayer(player);
    }


    @Override
    public int getItemCount() {
        return videoUrls.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        static VideoViewBinding videoViewBinding;

        public ViewHolder(View view) {
            super(view);
            videoViewBinding = VideoViewBinding.bind(view);
        }
    }

    @Override
    public void onPlay() {

//        if (player != null && !player.isPlaying()) {
//            player.play();
//        }
    }

    @Override
    public void onStop() {
//        if (player != null) {
//            player.stop(true);
//            player.release();
//        }
    }

    @Override
    public void onResume() {
//        if (player != null) {
//            player.play();
//        }
    }

    @Override
    public void onPause() {
//        if (player != null && player.isPlaying()) {
//            player.pause();
//        }
    }
}
