package com.wyksofts.saveone.util;

import android.content.Context;
import android.content.Intent;

import com.wyksofts.saveone.R;

public class ShareApp {

    public void shareApp(Context context){
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT",
                "Download:-\t" + context.getString(R.string.app_name));
        intent.putExtra("android.intent.extra.TEXT",
                "https://twitter.com/DscTtu?t=nLFp2oGleW6Tpu3XpzbugQ&s=09");
        context.startActivity(Intent.createChooser(intent, "Share via"));
    }
}
