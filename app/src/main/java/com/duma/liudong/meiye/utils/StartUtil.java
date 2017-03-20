package com.duma.liudong.meiye.utils;

import android.app.Activity;
import android.content.Intent;

import com.duma.liudong.meiye.view.start.login.LoginActivity;

/**
 * Created by liudong on 17/3/20.
 */

public class StartUtil {
    public static void toLogin(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);

        activity.startActivity(intent);
    }
}
