package com.gpc.meinvxiupro.managers;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pcgu on 16-4-13.
 */
public class UiThreadManager {
    Observable uiThreadObservable = Observable.create(new Observable.OnSubscribe() {
        @Override
        public void call(Object o) {
            o.
        }
    })
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread());
}
