package com.duma.liudong.meiye.view.classift;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.model.SlideBus;
import com.duma.liudong.meiye.utils.Constants;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.liudong.meiye.R.id.tv_zonghe;

/**
 * Created by liudong on 17/3/30.
 */

public class PaiXuFragment extends BaseFragment {
    @BindView(tv_zonghe)
    TextView tvZonghe;
    @BindView(R.id.img_zonghe)
    ImageView imgZonghe;
    @BindView(R.id.layout_zonghe)
    LinearLayout layoutZonghe;
    @BindView(R.id.tv_juli)
    TextView tvJuli;
    @BindView(R.id.img_juli)
    ImageView imgJuli;
    @BindView(R.id.layout_juli)
    LinearLayout layoutJuli;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.img_xiaoliang)
    ImageView imgXiaoliang;
    @BindView(R.id.layout_xiaoliang)
    LinearLayout layoutXiaoliang;
    @BindView(R.id.layout_shaixuan)
    LinearLayout layoutShaixuan;

    public String paixuName = "";
    public String paixu = "";

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_paixu;
    }

    @Override
    protected void initData() {
        refresh();
    }


    @OnClick({R.id.layout_zonghe, R.id.layout_juli, R.id.layout_xiaoliang, R.id.layout_shaixuan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_zonghe:
                paixuName = "";
                paixu = "";
                refresh();
                break;
            case R.id.layout_juli:
                if (paixuName.equals(Constants.juLi)) {
                    setPaixu();

                } else {
                    paixu = Constants.zhenXu;
                    paixuName = Constants.juLi;
                }
                refresh();
                break;
            case R.id.layout_xiaoliang:
                if (paixuName.equals(Constants.xiaoLiang)) {
                    setPaixu();
                } else {
                    paixuName = Constants.xiaoLiang;
                    paixu = Constants.zhenXu;
                }
                refresh();
                break;
            case R.id.layout_shaixuan:
                EventBus.getDefault().post(new SlideBus(1));
                break;
        }
    }

    private void setPaixu() {
        if (paixu.equals(Constants.zhenXu)) {
            paixu = Constants.daoXu;
        } else {
            paixu = Constants.zhenXu;
        }
    }

    private void refresh() {
        setMoRen(tvJuli, imgJuli);
        setMoRen(tvXiaoliang, imgXiaoliang);
        setMoRen(tvZonghe, imgZonghe);

        switch (paixuName) {
            case Constants.xiaoLiang:
                setClick(tvXiaoliang, imgXiaoliang);
                break;
            case Constants.juLi:
                setClick(tvJuli, imgJuli);
                break;
            default:
                setClick(tvZonghe, imgZonghe);
        }
        EventBus.getDefault().post(new SlideBus(0));
    }

    private void setMoRen(TextView tv, ImageView img) {
        tv.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
        img.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.l2q));
    }

    private void setClick(TextView tv, ImageView img) {
        tv.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
        if (paixu.equals(Constants.zhenXu) || paixuName.equals("")) {
            img.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.l3q));
        } else {
            img.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.l1q));
        }

    }
}
