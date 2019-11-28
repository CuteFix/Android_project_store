package com.example.projectthursday.Activities.FilterActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectthursday.Activities.FilterActivity.Adapter.FilterAdapter;
import com.example.projectthursday.Items.FilterItem;
import com.example.projectthursday.Items.FilterUnderItem;
import com.example.projectthursday.R;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity {

    private static final String TAG = FilterActivity.class.getCanonicalName();
    private FilterActivityPresenter presenter;

    @BindView(R.id.filter_main_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Filter");

        presenter = new FilterActivityPresenter(this);

        List<FilterItem> filterItems = Arrays.asList(
                new FilterItem("Материнская плата", Arrays.asList(new FilterUnderItem(true, "1"), new FilterUnderItem(true, "2"), new FilterUnderItem(true, "3"))),
                new FilterItem("Процессор", Arrays.asList(new FilterUnderItem(true, "1"), new FilterUnderItem(true, "2"), new FilterUnderItem(true, "3"))),
                new FilterItem("Видеокарта", Arrays.asList(new FilterUnderItem(true, "1"), new FilterUnderItem(true, "2"), new FilterUnderItem(true, "3"))),
                new FilterItem("Оперативная плата", Arrays.asList(new FilterUnderItem(true, "1"), new FilterUnderItem(true, "2"), new FilterUnderItem(true, "3"))),
                new FilterItem("Жесткий диск", Arrays.asList(new FilterUnderItem(true, "1"), new FilterUnderItem(true, "2"), new FilterUnderItem(true, "3"))),
                new FilterItem("Звуковая плата", Arrays.asList(new FilterUnderItem(true, "1"), new FilterUnderItem(true, "2"), new FilterUnderItem(true, "3"))));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FilterAdapter(filterItems));
//
//        Requests.INSTANCE.getColors().subscribe(new Consumer<Response<String>>() {
//            @Override
//            public void accept(Response<String> stringResponse) throws Exception {
//                String colors = stringResponse.body();
//                Log.i(TAG, "Colors answer = " + colors);
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                Log.i(TAG, "Colors answer error", throwable);
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_check_outline:
                finish();
                return true;
            default:
                return false;
        }
    }
}
