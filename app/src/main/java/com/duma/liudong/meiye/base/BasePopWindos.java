package com.duma.liudong.meiye.base;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by liudong on 17/4/18.
 */

public class BasePopWindos {
    private View popipWindow_view;
    private PopupWindow popupWindow;
    private View viewToumin;

    public BasePopWindos(Activity activity, @LayoutRes int resource) {
        popipWindow_view = activity.getLayoutInflater().inflate(resource, null, false);
        popupWindow = new PopupWindow(popipWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                disMiss();
            }
        });
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    public void setViewToumin(View viewToumin) {
        this.viewToumin = viewToumin;
    }

    public void Show(View view) {
        popupWindow.showAsDropDown(view);
        if (viewToumin != null)
            viewToumin.setVisibility(View.VISIBLE);
    }

    public View getView() {
        return popipWindow_view;
    }

    public void disMiss() {
        if (viewToumin != null)
            viewToumin.setVisibility(View.GONE);
        popupWindow.dismiss();
    }
}
