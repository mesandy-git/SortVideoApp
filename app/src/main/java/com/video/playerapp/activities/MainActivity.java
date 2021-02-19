package com.video.playerapp.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.video.playerapp.R;
import com.video.playerapp.adapters.MenuPagerAdapter;
import com.video.playerapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    int pos = 0;
    private ActivityMainBinding binding;
    private MenuPagerAdapter menuPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        menuPagerAdapter = new MenuPagerAdapter(this);
        binding.pager.setAdapter(menuPagerAdapter);
        binding.pager.setOffscreenPageLimit(5);
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this);
        binding.pager.addOnPageChangeListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.video: {
                binding.pager.setCurrentItem(0, true);
                return true;
            }
            case R.id.camera: {
                binding.pager.setCurrentItem(1, true);
                return true;
            }
            case R.id.posts: {
                binding.pager.setCurrentItem(2, true);
                return true;
            }
            case R.id.profile: {
                binding.pager.setCurrentItem(3, true);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        pos = position;
        switch (position) {
            case 0:
                binding.bottomNavigationView.setSelectedItemId(R.id.video);
                break;
            case 1:
                binding.bottomNavigationView.setSelectedItemId(R.id.camera);
                break;
            case 2:
                binding.bottomNavigationView.setSelectedItemId(R.id.posts);
                break;
            case 3:
                binding.bottomNavigationView.setSelectedItemId(R.id.profile);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {
        if (pos != 0) {
            binding.pager.setCurrentItem(0);
        } else
            super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (menuPagerAdapter != null) {
            menuPagerAdapter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (menuPagerAdapter != null) {
            menuPagerAdapter.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (menuPagerAdapter != null) {
            menuPagerAdapter.onStop();
        }
    }
}

