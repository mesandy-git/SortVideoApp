package com.video.playerapp.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.PagerAdapter;

import com.video.playerapp.R;
import com.video.playerapp.databinding.PagerLayoutBinding;

import org.jetbrains.annotations.NotNull;

public class MenuPagerAdapter extends PagerAdapter {
    //            private final String[] videoUrls = {
//            "https://firebasestorage.googleapis.com/v0/b/large-storage-9ea8c.appspot.com/o/150021382_414771289588250_2019407489563652971_n.mp4?alt=media&token=95df2953-e31c-486b-882f-587772804324"
//            , "https://firebasestorage.googleapis.com/v0/b/large-storage-9ea8c.appspot.com/o/video.mp4?alt=media&token=f75bcaa2-7c7c-4713-8700-58780d41df98",
//            "https://firebasestorage.googleapis.com/v0/b/large-storage-9ea8c.appspot.com/o/150021382_414771289588250_2019407489563652971_n.mp4?alt=media&token=95df2953-e31c-486b-882f-587772804324"
//            , "https://firebasestorage.googleapis.com/v0/b/large-storage-9ea8c.appspot.com/o/video.mp4?alt=media&token=f75bcaa2-7c7c-4713-8700-58780d41df98"};
//
    private final Uri[] videoUrls;
    Context context;
    int currentPage = -1;
    private VideoRecyclerAdapter videoPagerAdapter;

    public MenuPagerAdapter(Context context) {
        this.context = context;
        videoUrls = new Uri[]{Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.video), Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.video2)};
    }

    @Override
    public int getCount() {
        return 4;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.pager_layout, container, false);
        container.addView(layout);
        PagerLayoutBinding binding = PagerLayoutBinding.bind(layout);

        if (position == 0) {
            videoPagerAdapter = new VideoRecyclerAdapter(context, videoUrls, binding.recyclerView);
            binding.recyclerView.setAdapter(videoPagerAdapter);
            SnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(binding.recyclerView);
            binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_SETTLING) {

                    } else if (newState == RecyclerView.NO_POSITION) {

                    }
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
                    videoPagerAdapter.onPlay();
                }
            });
        }

        return layout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    public void onResume() {
        if (videoPagerAdapter != null)
            videoPagerAdapter.onResume();
    }

    public void onPause() {
        if (videoPagerAdapter != null)
            videoPagerAdapter.onPause();
    }

    public void onStop() {
        if (videoPagerAdapter != null)
            videoPagerAdapter.onStop();
    }
}
