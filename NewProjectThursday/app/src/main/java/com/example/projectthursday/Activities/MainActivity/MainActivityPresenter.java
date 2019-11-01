package com.example.projectthursday.Activities.MainActivity;

import androidx.fragment.app.Fragment;

import com.example.projectthursday.Activities.MainActivity.MainActivity;
import com.example.projectthursday.R;

public class MainActivityPresenter {

    private MainActivity mainActivity;

    public MainActivityPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void startFragment(Fragment fragment, String tag) {
        mainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, tag)
                .commit();
    }

}
