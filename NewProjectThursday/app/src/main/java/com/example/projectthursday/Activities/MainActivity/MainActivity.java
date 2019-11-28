package com.example.projectthursday.Activities.MainActivity;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.projectthursday.Activities.FilterActivity.FilterActivity;
import com.example.projectthursday.Activities.MainActivity.Fragments.BlankFragment1;
import com.example.projectthursday.Activities.MainActivity.Fragments.BlankFragment2;
import com.example.projectthursday.Activities.MainActivity.Fragments.BlankFragment3;
import com.example.projectthursday.R;
import com.example.projectthursday.Utils.Constants;
import com.example.projectthursday.Utils.ParsData.Colors;
import com.example.projectthursday.Utils.ParsData.Strings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = MainActivity.class.getCanonicalName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    private MainActivityPresenter presenter;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        presenter = new MainActivityPresenter(this);
        presenter.startFragment(new BlankFragment1(), Constants.FRAGMENT_1);

        setStatusBarColor(Colors.getColorByName(getString(R.string.telegram_dark_light)));
        setActionBarColor(Colors.getColorByName(getString(R.string.telegram_dark_medium)));
        setBottomMenuColor(Colors.getColorByName(getString(R.string.telegram_dark_medium)));
        setBottomMenuItemTextColor();
        setBottomMenuItemIconColor();

        setBottomMenuItemText();

    }

    private void setStatusBarColor(int color) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
    }

    private void setActionBarColor(int color) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    private void setBottomMenuColor(int color) {
        bottomNavigationView.setItemBackground(new ColorDrawable(color));
    }

    private void setBottomMenuItemTextColor() {
        ColorStateList myColorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked}, //активна
                        new int[]{android.R.attr.state_pressed}, //нажата
                        new int[]{} //не активна
                },
                new int[]{
                        Colors.getColorByName(getString(R.string.telegram_white)),
                        Colors.getColorByName(getString(R.string.telegram_gray_light)),
                        Colors.getColorByName(getString(R.string.telegram_gray_medium)),
                }
        );

        bottomNavigationView.setItemTextColor(myColorStateList);
    }

    private void setBottomMenuItemIconColor() {

        ColorStateList myColorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked}, //активна
                        new int[]{android.R.attr.state_pressed}, //нажата
                        new int[]{} //не активна
                },
                new int[]{
                        Colors.getColorByName(getString(R.string.telegram_white)),
                        Colors.getColorByName(getString(R.string.telegram_gray_light)),
                        Colors.getColorByName(getString(R.string.telegram_gray_medium)),
                }
        );

        bottomNavigationView.setItemIconTintList(myColorStateList);
    }

    public void setBottomMenuItemText() {
        bottomNavigationView.getMenu().findItem(R.id.bnv_1)
                .setTitle(Strings.getByKey(getString(R.string.bnv_1)));
        bottomNavigationView.getMenu().findItem(R.id.bnv_2)
                .setTitle(Strings.getByKey(getString(R.string.bnv_2)));
        bottomNavigationView.getMenu().findItem(R.id.bnv_3)
                .setTitle(Strings.getByKey(getString(R.string.bnv_3)));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        MenuItem searchItem = menu.findItem(R.id.toolbar_search);
        if (searchItem != null) {
            SearchView searchView = (SearchView) searchItem.getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    Fragment fragment = fragmentManager.findFragmentByTag(Constants.FRAGMENT_1);

                    if (fragment != null) {
                        if (fragment instanceof BlankFragment1) {
                            BlankFragment1 blankFragment = (BlankFragment1) fragment;
                            blankFragment.searchText(newText);
                        }
                    }
                    return false;
                }
            });
        }


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (item.getItemId()) {
            case R.id.toolbar_item_delete:
                Fragment fragment1 = fragmentManager.findFragmentByTag(Constants.FRAGMENT_1);
                if (fragment1 != null) {
                    if (fragment1 instanceof BlankFragment1) {
                        BlankFragment1 blankFragment1 = (BlankFragment1) fragment1;
                        blankFragment1.deleteItems();
                    }
                }
                break;
            case R.id.toolbar_item_add:
                Fragment fragment = fragmentManager.findFragmentByTag(Constants.FRAGMENT_1);
                if (fragment != null) {
                    if (fragment instanceof BlankFragment1) {
                        BlankFragment1 blankFragment1 = (BlankFragment1) fragment;
                        blankFragment1.parseItems();
                    }
                }
                break;
            case R.id.toolbar_filter:
                Fragment fragment3 = fragmentManager.findFragmentByTag(Constants.FRAGMENT_1);
                if (fragment3 != null) {
                    if (fragment3 instanceof BlankFragment1) {
                        Intent intent = new Intent(this, FilterActivity.class);
                        startActivity(intent);
                    }
                }
                break;
            case android.R.id.home: {
                Fragment fragment11 = fragmentManager.findFragmentByTag(Constants.FRAGMENT_1);
                if (fragment11 != null) {
                    if (fragment11 instanceof BlankFragment1) {
                        ((BlankFragment1) fragment11).parseItems();
                    }
                }
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bnv_1:
                presenter.startFragment(new BlankFragment1(), Constants.FRAGMENT_1);
                break;
            case R.id.bnv_2:
                presenter.startFragment(new BlankFragment2(), Constants.FRAGMENT_2);
                break;
            case R.id.bnv_3:
                presenter.startFragment(new BlankFragment3(), Constants.FRAGMENT_3);
                break;
        }
        return true;
    }
}
