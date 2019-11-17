package com.example.projectthursday.Activities.StartActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.projectthursday.Activities.MainActivity.MainActivity;
import com.example.projectthursday.R;
import com.example.projectthursday.Retrofit2.Items.ColorItem;
import com.example.projectthursday.ServerRequests.Requests;
import com.example.projectthursday.Utils.Colors;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.Response;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Requests.INSTANCE.getColors().subscribe(new SingleObserver<Response<List<ColorItem>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Response<List<ColorItem>> listResponse) {
                int responseCode = listResponse.code();

                switch (responseCode) {
                    case 200: {
                        List<ColorItem> list = listResponse.body();
                        if (list != null) {
                            if (!list.isEmpty()) {
                                for (ColorItem color : list) {
                                    Colors.setColor(color.getKey(), color.getValue());
                                }
                                startActivity(new Intent(StartActivity.this, MainActivity.class));
                            }
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
//
//        Requests.INSTANCE.getColors().mergeWith(Requests.INSTANCE.getCategories(5, "ru", null))
//                .subscribe(new FlowableSubscriber<Response<List<ColorItem>>>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//
//                    }
//
//                    @Override
//                    public void onNext(Response<List<ColorItem>> listResponse) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

    }

}
