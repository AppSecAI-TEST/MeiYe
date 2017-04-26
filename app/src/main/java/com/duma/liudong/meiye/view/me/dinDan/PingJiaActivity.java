package com.duma.liudong.meiye.view.me.dinDan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.Ts;
import com.hedgehog.ratingbar.RatingBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import me.iwf.photopicker.widget.MultiPickResultView;

/**
 * Created by liudong on 17/4/14.
 */

public class PingJiaActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.img_head_pic)
    ImageView imgHeadPic;
    @BindView(R.id.xx_pinfen)
    RatingBar xxPinfen;
    @BindView(R.id.layout_jiesuan)
    LinearLayout layoutJiesuan;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.recycler_view)
    MultiPickResultView recyclerView;
    @BindView(R.id.xx_store_desccredit)
    RatingBar xxStoreDesccredit;
    @BindView(R.id.xx_store_servicecredit)
    RatingBar xxStoreServicecredit;
    @BindView(R.id.xx_store_deliverycredit)
    RatingBar xxStoreDeliverycredit;

    private int num1, num2, num3, num4;
    private String goods_id, order_id;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pingjia);
    }

    @Override
    protected void initData() {
        goods_id = getIntent().getStringExtra("goods_id");
        order_id = getIntent().getStringExtra("order_id");
        ImageLoader.with(getIntent().getStringExtra("img"), imgHeadPic);
        recyclerView.init(this, MultiPickResultView.ACTION_SELECT, null);
        xxPinfen.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                num1 = (int) Double.parseDouble(String.valueOf(RatingCount));
            }
        });
        xxStoreDesccredit.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                num2 = (int) Double.parseDouble(String.valueOf(RatingCount));
            }
        });
        xxStoreServicecredit.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                num3 = (int) Double.parseDouble(String.valueOf(RatingCount));
            }
        });
        xxStoreDeliverycredit.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                num4 = (int) Double.parseDouble(String.valueOf(RatingCount));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recyclerView.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.layout_back, R.id.layout_other})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_other:
                if (num1 == 0 || num2 == 0 || num3 == 0 || num4 == 0) {
                    Ts.setText("请给商品评分~");
                    return;
                }
                TijiaoHttp();
                break;
        }
    }

    private void TijiaoHttp() {
        DialogUtil.show(mActivity);
        OkHttpUtils.getInstance().cancelTag(this);
        PostFormBuilder postFormBuilder = OkHttpUtils
                .post()
                .tag(this)
                .url(Api.comment)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("order_id", order_id)
                .addParams("goods_id", goods_id)
                .addParams("content", editText.getText().toString())
                .addParams("goods_rank", num1 + "")
                .addParams("describe_score", xxStoreDesccredit + "")
                .addParams("seller_score", xxStoreServicecredit + "")
                .addParams("logistics_score", xxStoreDeliverycredit + "");
        File file;
        for (int i = 0; i < recyclerView.getPhotos().size(); i++) {
            file = new File(recyclerView.getPhotos().get(i));
            postFormBuilder.addFile("img_file[" + i + "]", file.getName(), file);
        }
        postFormBuilder.build().execute(new MyStringCallback() {
            @Override
            public void onMySuccess(String result) {
                DialogUtil.hide();
                Ts.setText("提交成功!");
                finish();
            }
        });
    }

//    private String getNum() {
//        switch (num1) {
//            case 1:
//            case 2:
//                return "1";
//            case 3:
//            case 4:
//                return "2";
//        }
//        return "3";
//    }
}
