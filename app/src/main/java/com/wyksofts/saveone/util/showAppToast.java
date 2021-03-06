package com.wyksofts.saveone.util;

import android.content.Context;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class showAppToast {


    //show success toast
    public void showSuccess(Context context, String message) {
        Toasty.success(
                context, message,
                Toast.LENGTH_LONG,
                true).show();
    }

    //show warning failure toast

    public void showFailure(Context context, String message) {
        Toasty.error(
                context, message,
                Toast.LENGTH_LONG
                , true).show();
    }


    //show success toast short time
    public void showShortSuccess(Context context, String message) {
        Toasty.success(
                context, message,
                Toast.LENGTH_SHORT,
                true).show();
    }

    //show warning failure toast short time
    public void showShortFailure(Context context, String message) {
        Toasty.error(
                context, message,
                Toast.LENGTH_SHORT
                , true).show();
    }

}
