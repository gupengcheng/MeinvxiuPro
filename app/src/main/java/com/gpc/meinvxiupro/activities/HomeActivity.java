package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.managers.DataRequestManager;
import com.gpc.meinvxiupro.models.ImageResult;

import rx.Subscriber;
import rx.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loadData();
    }

    private void loadData() {
        String[] mLovelyTags = this.getResources().getStringArray(R.array.tag_lovely_array);
        for (final String tag : mLovelyTags) {
            DataRequestManager.getInstance().getImageResult(tag, 0, Schedulers.computation(), subscriber);
        }
    }

    Subscriber<ImageResult> subscriber = new Subscriber<ImageResult>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onNext(ImageResult imageResult) {
            Toast.makeText(HomeActivity.this, imageResult.getImgs().get(0).getTitle(), Toast.LENGTH_SHORT).show();
        }
    };

}
