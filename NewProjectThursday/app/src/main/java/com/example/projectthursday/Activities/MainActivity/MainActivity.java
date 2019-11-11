package com.example.projectthursday.Activities.MainActivity;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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


//        Requests.INSTANCE.getHeaders().subscribe(new SingleObserver<Response<HeadersTest>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.i(TAG, "getHeaders() onSubscribe");
//            }
//
//            @Override
//            public void onSuccess(Response<HeadersTest> headersResponse) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.i(TAG, "getHeaders() onError");
//            }
//        });

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
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_star_bnv_1:
                presenter.startFragment(new BlankFragment1(), Constants.FRAGMENT_1);
                break;
            case R.id.toolbar_star_bnv_2:
                presenter.startFragment(new BlankFragment2(), Constants.FRAGMENT_2);
                break;
            case R.id.toolbar_star_bnv_3:
                presenter.startFragment(new BlankFragment3(), Constants.FRAGMENT_3);
                break;
        }
        return true;
    }
}
