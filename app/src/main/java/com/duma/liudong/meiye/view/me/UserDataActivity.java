package com.duma.liudong.meiye.view.me;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.presenter.UserDataPresenter;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.GetFileUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.Lg;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.PaiZhaoDialog;
import com.duma.liudong.meiye.view.dialog.XingBieDialog;
import com.duma.liudong.meiye.view.dialog.XiuGaiNiChengDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.duma.liudong.meiye.R.id.tv_xingbie;

/**
 * Created by liudong on 17/3/23.
 */

public class UserDataActivity extends BaseActivity implements XiuGaiNiChengDialog.ClickListenerInterface,
        UserDataPresenter.ChangeUserDataListeren, XingBieDialog.ClickListenerInterface, TimePickerView.OnTimeSelectListener, PaiZhaoDialog.ClickListenerInterface {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_touxiang)
    ImageView imgTouxiang;
    @BindView(R.id.layout_touxiang)
    LinearLayout layoutTouxiang;
    @BindView(R.id.tv_yonghuming)
    TextView tvYonghuming;
    @BindView(R.id.tv_niCheng)
    TextView tvNiCheng;
    @BindView(R.id.layout_nicheng)
    LinearLayout layoutNicheng;
    @BindView(tv_xingbie)
    TextView tvXingbie;
    @BindView(R.id.layout_xingbie)
    LinearLayout layoutXingbie;
    @BindView(R.id.tv_shengri)
    TextView tvShengri;
    @BindView(R.id.layout_shengri)
    LinearLayout layoutShengri;
    @BindView(R.id.layout_dizhi)
    LinearLayout layoutDizhi;
    @BindView(R.id.layout_password)
    LinearLayout layoutPassword;
    @BindView(R.id.layout_anquan)
    LinearLayout layoutAnquan;
    @BindView(R.id.btn_cancel_login)
    Button btnCancelLogin;

    private XiuGaiNiChengDialog niChenDialog;
    private UserDataPresenter userDataPresenter;
    private XingBieDialog xingBieDialog;
    private TimePickerView pvTime;
    private PaiZhaoDialog paiZhaoDialog;
    private Uri uri;
    private String path;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_userdata);
    }

    @Override
    protected void initData() {
        tvTitle.setText("账户设置");
        tvYonghuming.setText(MyApplication.getSpUtils().getString(Constants.phone));
        niChenDialog = new XiuGaiNiChengDialog(mActivity);
        niChenDialog.setClicklistener(this);
        userDataPresenter = new UserDataPresenter(this);
        xingBieDialog = new XingBieDialog(mActivity);
        xingBieDialog.setClicklistener(this);
        paiZhaoDialog = new PaiZhaoDialog(mActivity);
        paiZhaoDialog.setClicklistener(this);
        File file = GetFileUtil.getFile();
        path = file.getPath();
        uri = Uri.fromFile(file);
        //时间选择器
        pvTime = new TimePickerView.Builder(this, this).setType(TimePickerView.Type.YEAR_MONTH_DAY).build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        ImageLoader.withYuan(Api.url + MyApplication.getSpUtils().getString(Constants.head_pic), imgTouxiang);
        tvNiCheng.setText(MyApplication.getSpUtils().getString(Constants.nickname));
        tvXingbie.setText(MyApplication.getSpUtils().getString(Constants.sex));
        tvShengri.setText(MyApplication.getSpUtils().getString(Constants.birthday));
        if (StartUtil.isEmpty(MyApplication.getSpUtils().getString(Constants.head_pic))) {
            ImageLoader.withYuan(R.drawable.touxiang, imgTouxiang);
        }
    }

    @OnClick({R.id.layout_back, R.id.layout_touxiang, R.id.layout_nicheng, R.id.layout_xingbie, R.id.layout_shengri, R.id.layout_dizhi, R.id.layout_password, R.id.layout_anquan, R.id.btn_cancel_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_touxiang:
                paiZhaoDialog.show();
                break;
            case R.id.layout_nicheng:
                niChenDialog.show();
                break;
            case R.id.layout_xingbie:
                xingBieDialog.show();
                break;
            case R.id.layout_shengri:
                pvTime.show();
                break;
            case R.id.layout_dizhi:
                break;
            case R.id.layout_password:
                break;
            case R.id.layout_anquan:
                break;
            case R.id.btn_cancel_login:
                StartUtil.cancleLogin();
                finish();
                break;
        }
    }

    //修改昵称
    @Override
    public void onRes(String res) {
        DialogUtil.show(mActivity);
        userDataPresenter.changeUserData(Constants.nickname, res);
    }

    @Override
    public void changeSuccess() {
        DialogUtil.hide();
        refresh();
    }

    //修改头像
    @Override
    public void changPhono(String res) {
        try {
            JSONObject j = new JSONObject(res);
            userDataPresenter.change(Constants.head_pic, j.getString("head_pic"));
        } catch (JSONException e) {
            Ts.JsonErroy();
        }
    }

    //修改性别
    @Override
    public void onXinBie(String type) {
        DialogUtil.show(mActivity);
        userDataPresenter.changeUserData(Constants.sex, type);
    }

    //修改生日
    @Override
    public void onTimeSelect(Date date, View v) {
        DialogUtil.show(mActivity);
        userDataPresenter.changeUserData(Constants.birthday, date.getTime() / 1000 + "");
    }

    @Override
    public void paiZhao() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 200);
    }

    @Override
    public void xiangce() {
        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
        getAlbum.setType("image/*");
        startActivityForResult(getAlbum, 100);
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        DialogUtil.show(mActivity);
        File f;
        //得到bitmap图片 没有压缩的
        if (requestCode == 100) {
            // 相册
            Lg.e(data.getData() + "");
            try {
                f = new File(getRealPathFromURI(data.getData()));
            } catch (Exception e) {
                Ts.setText("请选取相册照片!");
                DialogUtil.hide();
                return;
            }
        } else {
            f = new File(getRealPathFromURI(uri));
            Lg.e(uri + "");
        }
        YaSuoFile(f);
    }

    private void YaSuoFile(File file) {
        Luban.get(this)
                .load(file)
                .putGear(Luban.THIRD_GEAR)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        userDataPresenter.addPhono(file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Message message = new Message();
                        message.what = 2;
                        handler.sendMessage(message);
                        Ts.setText("图片压缩错误！请稍后再试！");
                    }
                }).launch();
    }

    //获取uri真实的地址
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    DialogUtil.show(mActivity);
                    break;
                case 2:
                    DialogUtil.hide();
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
