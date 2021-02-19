package com.video.playerapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

import org.jetbrains.annotations.NotNull;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> implements Player.EventListener, View.OnTouchListener, ControllerCallBack {

    private final String[] videoUrls;
    private final Context parentActivity;
    private RecyclerView recyclerView;
    private int currentPage = -1;
    SimpleExoPlayer player;
    VideoAdapter(Context parentActivity, String[] videoUrls, RecyclerView recyclerView) {
        this.videoUrls = videoUrls;
        this.parentActivity = parentActivity;
        this.recyclerView = recyclerView;
        player = new SimpleExoPlayer.Builder(parentActivity).build();
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.video_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        String url = videoUrls[position];
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final int scrollOffset = recyclerView.computeVerticalScrollOffset();
                final int height = recyclerView.getHeight();
                int page_no = scrollOffset / height;

                if (page_no != currentPage) {
                    currentPage = page_no;
                }
            }
        });
        setPlayer(ViewHolder.videoViewBinding.videoPlayer, url, position);
    }

    private void setPlayer(PlayerView playerView, String url, int pos) {
        player.addMediaItem(MediaItem.fromUri(url));
        player.prepare();
        if (pos == currentPage)
            player.play();
        playerView.setPlayer(player);
        playerView.setUseController(false);
        playerView.setOnTouchListener(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        if (v.getId() == R.id.video_player)
//            if (ViewHolder.videoViewBinding.videoPlayer.getPlayer().isPlaying()) {
//                ViewHolder.videoViewBinding.videoPlayer.getPlayer().pause();
//            } else {
//                ViewHolder.videoViewBinding.videoPlayer.getPlayer().play();
//            }
        return false;
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
        if (player != null && !player.isPlaying()) {
            player.play();
        }
    }

    @Override
    public void onStop() {
        if (player != null) {
            player.stop(true);
            player.release();
        }
    }

    @Override
    public void onResume() {
        if (player != null) {
            player.play();
        }
    }

    @Override
    public void onPause() {
        if (player != null && player.isPlaying()) {
            player.pause();
        }
    }
}
