package com.duma.liudong.meiye.view.me.shiWuDinDan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.hedgehog.ratingbar.RatingBar;

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

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pingjia);
    }

    @Override
    protected void initData() {
        recyclerView.init(this, MultiPickResultView.ACTION_SELECT, null);
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
                break;
        }
    }
}
