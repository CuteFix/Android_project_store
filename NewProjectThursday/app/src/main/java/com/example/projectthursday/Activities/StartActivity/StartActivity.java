package com.example.projectthursday.Activities.StartActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectthursday.Activities.MainActivity.MainActivity;
import com.example.projectthursday.R;
import com.example.projectthursday.Retrofit2.Items.ColorItem;
import com.example.projectthursday.Retrofit2.Items.GetStringsItem;
import com.example.projectthursday.Retrofit2.ServerRequests.RequestsComplete;
import com.example.projectthursday.Utils.ParsData.Colors;
import com.example.projectthursday.Utils.ParsData.Strings;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        getColors();
    }

    private void getColors() {
        RequestsComplete.INSTANCE.getColors(list -> {
            if (list != null) {
                if (!list.isEmpty()) {
                    for (ColorItem item : list) {
                        Colors.setColor(item.getKey(), item.getValue());
                    }
                    getStrings();
                }
            }
        });
    }

    private void getStrings() {
        RequestsComplete.INSTANCE.getStrings(list -> {
            if (list != null) {
                if (!list.isEmpty()) {
                    for (GetStringsItem item : list) {
                        Strings.setByKey(item.getKey(), item.getValue());
                    }
                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                }
            }
        });
    }

}
