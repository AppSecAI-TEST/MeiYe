package com.duma.liudong.meiye.utils;

import android.app.Activity;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.duma.liudong.meiye.model.ProvinceBean;
import com.duma.liudong.meiye.model.ShenShiQuBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 79953 on 2016/8/11.
 */
public class ShenShiQuUtil implements Runnable {
    private OnGetDiZhi onGetDiZhi;
    private OptionsPickerView pvOptions;
    private Activity activity;

    List<ProvinceBean> options1Items;
    List<List<ProvinceBean>> options2Items;
    List<List<List<ProvinceBean>>> options3Items;
    List<List<ProvinceBean>> options3Items_01;

    private ShenShiQuBean bean;

    public interface OnGetDiZhi {
        void getDiZhi(String province, String city, String district);

        void hide();
    }


    public ShenShiQuUtil(OnGetDiZhi onGetDiZhi, OptionsPickerView pvOptions, Activity activity) {
        this.onGetDiZhi = onGetDiZhi;
        this.pvOptions = pvOptions;
        this.activity = activity;
        options1Items = new ArrayList<>();
        options2Items = new ArrayList<>();
        options3Items = new ArrayList<>();
        initOption();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        Lg.e("1");
        String json = getText(activity);
        if (json.equals("")) {
            Ts.setText("读取省市区数据错误！");
            return;
        }
        bean = new Gson().fromJson(json, ShenShiQuBean.class);
        pvOptions.setPicker(bean.getProvinceList(), bean.getCityList(), bean.getCountryList());
        onGetDiZhi.hide();
    }

    //三级联动效果
    private void initOption() {
        pvOptions = new OptionsPickerView.Builder(activity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                onGetDiZhi.getDiZhi(options1Items.get(options1).getName(),
                        options2Items.get(options1).get(options2).getName(),
                        options3Items.get(options1).get(options2).get(options3).getName());
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("选择城市")//标题
                .setCyclic(false, false, false)//循环与否
                .build();
    }

    public void Show() {
        pvOptions.show();
    }


    private String getText(Activity activity) {
        try {
            InputStream is = activity.getAssets().open("cityjson.txt");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            return new String(buffer, "UTF-8");
        } catch (IOException e) {
            Ts.setText("读取省市区数据错误！");
        }
        return "";
    }
}
