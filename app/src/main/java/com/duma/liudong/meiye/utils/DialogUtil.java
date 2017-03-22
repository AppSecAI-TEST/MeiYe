package com.duma.liudong.meiye.utils;

import android.app.Activity;
import android.view.Window;

import com.duma.liudong.meiye.view.dialog.LoadineDialog;

/**
 * Created by 79953 on 2016/9/30.
 */

public class DialogUtil {
    private static LoadineDialog LoadineDialog;

    public static void show(Activity activity) {
        LoadineDialog = new LoadineDialog(activity);
        LoadineDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LoadineDialog.show();
    }

    public static void show(Activity activity, boolean isBack) {
        LoadineDialog = new LoadineDialog(activity, isBack);
        LoadineDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LoadineDialog.show();
    }

    public static void hide() {
        if (LoadineDialog != null)
            if (LoadineDialog.isShowing()) {
                LoadineDialog.dismiss();
            }
    }
}
