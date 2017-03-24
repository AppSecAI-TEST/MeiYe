package com.duma.liudong.meiye.presenter;

import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.io.File;

/**
 * Created by liudong on 17/3/24.
 */

public class UserDataPresenter {

    private ChangeUserDataListeren changeUserDataListeren;

    public UserDataPresenter(ChangeUserDataListeren changeUserDataListeren) {
        this.changeUserDataListeren = changeUserDataListeren;
    }

    public interface ChangeUserDataListeren {
        void changeSuccess();

        void changPhono(String res);
    }

    public void changeUserData(final String type, final String data) {
        OkHttpUtils.getInstance().cancelTag("changeUserData");
        final PostFormBuilder builder = OkHttpUtils.post().tag("changeUserData").url(Api.setting);
        builder.addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id));
        builder.addParams("token", MyApplication.getSpUtils().getString(Constants.token));
        switch (type) {
            case Constants.nickname:
                builder.addParams("nickname", data);
                break;
            case Constants.birthday:
                builder.addParams("birthday", data);
                break;
            case Constants.sex:
                builder.addParams("sex", data);
                break;
            case Constants.head_pic:
                builder.addParams("head_pic", data);
                break;
        }
        builder.build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        change(type, data);
                    }
                });
    }

    public void change(String type, String data) {
        switch (type) {
            case Constants.nickname:
                StartUtil.setNickName(data);
                break;
            case Constants.birthday:
                StartUtil.setBirthday(data);
                break;
            case Constants.sex:
                StartUtil.setSex(data);
                break;
            case Constants.head_pic:
                MyApplication.getSpUtils().put(Constants.head_pic, data);
                break;
        }
        changeUserDataListeren.changeSuccess();
    }

    public void addPhono(final File file) {
        OkHttpUtils.getInstance().cancelTag("addPhono");
        OkHttpUtils
                .post()
                .tag("addPhono")
                .url(Api.alterHead)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addFile("head_pic", file.getName(), file)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        changeUserDataListeren.changPhono(result);
                    }
                });
    }

}
