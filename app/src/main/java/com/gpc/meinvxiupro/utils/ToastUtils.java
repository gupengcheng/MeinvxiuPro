package com.gpc.meinvxiupro.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gpc.meinvxiupro.MeinvxiuApplication;
import com.gpc.meinvxiupro.R;

/**
 * Created by pcgu on 16-4-12.
 */
public class ToastUtils {
    public static void showShortToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showShortToast(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }


    public static void showLongToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void showLongToast(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    public static void showShortSnakeBar(View parentView, String toast) {
        Snackbar snackbar = Snackbar.make(parentView, toast, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public static void showShortSnakeBar(View parentView, String toast, int backgroundColor, int textColor) {
        Snackbar snackbar = Snackbar.make(parentView, toast, Snackbar.LENGTH_SHORT);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(backgroundColor);
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(textColor);
        snackbar.show();
    }

    public static void showShortSnakeBar(View parentView, String toast, String actionName) {
        final Snackbar snackbar = Snackbar.make(parentView, toast, Snackbar.LENGTH_SHORT);
        snackbar.setAction(actionName, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    public static void showLongSnakeBar(View parentView, String toast) {
        Snackbar snackbar = Snackbar.make(parentView, toast, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static void showLongSnakeBar(View parentView, String toast, int backgroundColor) {
        Snackbar snackbar = Snackbar.make(parentView, toast, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(backgroundColor);
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(MeinvxiuApplication.getInstance().getApplicationContext(), R.color.rgb_ffffff));
        snackbar.show();
    }

    public static void showLongSnakeBar(View parentView, String toast, String actionName) {
        final Snackbar snackbar = Snackbar.make(parentView, toast, Snackbar.LENGTH_LONG);
        snackbar.setAction(actionName, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }
}
