package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.managers.DataRequestManager;
import com.gpc.meinvxiupro.models.ImageResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            DataRequestManager.getInstance().getImageResult(tag, 0, new Callback<ImageResult>() {
                @Override
                public void onResponse(Call<ImageResult> call, Response<ImageResult> response) {
                    Log.e("homeActivity", tag + " onResponse imageResult == " + response.body().getImgs().get(0).getTitle());
                }

                @Override
                public void onFailure(Call<ImageResult> call, Throwable t) {
                    Log.e("homeActivity", tag + " onFailure");
                }
            });
        }
    }

}
