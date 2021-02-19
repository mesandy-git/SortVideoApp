package com.video.playerapp.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.video.playerapp.R;
import com.video.playerapp.fragments.CameraFragment;
import com.video.playerapp.fragments.PostsFragment;
import com.video.playerapp.fragments.ProfileFragment;
import com.video.playerapp.fragments.VideoFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setBottomNavigationSelection();
    }

    @SuppressLint("NonConstantResourceId")
    private void setBottomNavigationSelection() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.video);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.camera:
                    loadFragment(new CameraFragment());
                    return true;

                case R.id.video:
                    loadFragment(new VideoFragment());
                    return true;

                case R.id.posts:
                    loadFragment(new PostsFragment());
                    return true;

                case R.id.profile:
                    loadFragment(new ProfileFragment());
                    return true;
            }
            return false;
        });

        bottomNavigationView.setOnNavigationItemReselectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.camera:
                case R.id.video:
                case R.id.posts:
                case R.id.profile:
                    break;
            }
        });
    }

    public void loadFragment(Fragment fragment) {
        String tagFragmentName = fragment.getClass().getName();
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        Fragment currentFragment = mFragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragmentTemp = mFragmentManager.findFragmentByTag(tagFragmentName);
        if (fragmentTemp == null) {
            fragmentTemp = fragment;
            fragmentTransaction.add(R.id.main_fragment, fragmentTemp, tagFragmentName);
        } else {
            fragmentTransaction.show(fragmentTemp);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}