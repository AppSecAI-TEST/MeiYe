package com.duma.liudong.meiye.utils;

import android.app.Activity;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.duma.liudong.meiye.model.ProvinceBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by 79953 on 2016/8/11.
 */
public class ShenShiQuUtil implements Runnable {
    private OnGetDiZhi onGetDiZhi;
    private OptionsPickerView pvOptions;
    private Activity activity;

    ArrayList<ProvinceBean> options1Items;
    ArrayList<ArrayList<ProvinceBean>> options2Items;
    ArrayList<ArrayList<ArrayList<ProvinceBean>>> options3Items;
    ArrayList<ArrayList<ProvinceBean>> options3Items_01;


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
        try {
            String json = getText(activity);
            if (json.equals("")) {
                Ts.setText("读取省市区数据错误！");
                return;
            }
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("dataList"));
            for (int i = 0; i < jsonArray.length(); i++) {
                //获取省
                JSONObject options1 = jsonArray.getJSONObject(i);
                options1Items.add(new ProvinceBean(options1.getLong("id"), options1.getString("areaName"), options1.getString("areaCode")));
                //获取市
                JSONArray shiArray = new JSONArray(options1.getString("city"));
                ArrayList<ProvinceBean> options2Items_01 = new ArrayList<>();
                options3Items_01 = new ArrayList<>();
                for (int j = 0; j < shiArray.length(); j++) {
                    JSONObject options2 = shiArray.getJSONObject(j);
                    options2Items_01.add(new ProvinceBean(
                            options2.getLong("id"), options2.getString("areaName"), options2.getString("areaCode")));
                    //获取县
                    JSONArray xianArray = new JSONArray(options2.getString("country"));
                    //该市所有的县
                    ArrayList<ProvinceBean> options3Items_01_01 = new ArrayList<>();
                    for (int n = 0; n < xianArray.length(); n++) {
                        JSONObject options3 = xianArray.getJSONObject(n);
                        options3Items_01_01.add(new ProvinceBean(
                                options3.getLong("id"), options3.getString("areaName"), options3.getString("areaCode")));
                    }
                    options3Items_01.add(options3Items_01_01);
                }
                options3Items.add(options3Items_01);
                options2Items.add(options2Items_01);
            }
            //三级联动效果
            pvOptions.setPicker(options1Items, options2Items, options3Items);
            onGetDiZhi.hide();
        } catch (JSONException e) {
            Ts.JsonErroy();
        }
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
            InputStream is = activity.getAssets().open("ssqjson.txt");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            return new String(buffer, "GB2312");
        } catch (IOException e) {
            Ts.setText("读取省市区数据错误！");
        }
        return "";
    }
}
