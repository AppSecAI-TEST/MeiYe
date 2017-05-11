package com.duma.liudong.meiye.presenter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.luck.picture.lib.model.FunctionOptions;
import com.luck.picture.lib.model.PictureConfig;
import com.yalantis.ucrop.entity.LocalMedia;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liudong on 17/5/9.
 */

public class PhotoSelectUtil {
    private Activity mActivity;
    private RecyclerView mRecyclerView;
    private CommonAdapter<LocalMedia> mAdapter;
    private List<LocalMedia> mList;//选择的图片 负责和选择图片控件交互
    private List<LocalMedia> mList_adapter;//负责adapter的图片
    private FunctionOptions options;
    private PictureConfig.OnSelectResultCallback resultCall;
    private int max = 9;

    public PhotoSelectUtil(Activity activity, RecyclerView recyclerView) {
        this.mActivity = activity;
        this.mRecyclerView = recyclerView;
        mList = new LinkedList<>();
        mList_adapter = new LinkedList<>();
        initPhotoView();
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 4));
        mRecyclerView.setFocusable(false);
        mRecyclerView.setNestedScrollingEnabled(false);
        initAdapter();
        mRecyclerView.setAdapter(mAdapter);


        setPhotoList(new ArrayList<LocalMedia>());
    }

    public List<LocalMedia> getmList() {
        return mList;
    }

    private void initPhotoView() {
        options = new FunctionOptions.Builder()
                .setMaxSelectNum(max)
                .setCheckNumMode(true)
                .setThemeStyle(ContextCompat.getColor(mActivity, R.color.main_red))
                .setSelectMedia(mList)
                .setCompress(true)
                .setCompressFlag(2)
                .create();
        resultCall = new PictureConfig.OnSelectResultCallback() {
            @Override
            public void onSelectSuccess(List<LocalMedia> list) {
                setPhotoList(list);
            }

            @Override
            public void onSelectSuccess(LocalMedia localMedia) {

            }
        };
    }

    private void setPhotoList(List<LocalMedia> list) {
        mList.clear();
        mList.addAll(list);

        mList_adapter.clear();
        mList_adapter.addAll(list);

        notifyData();
    }

    private void notifyData() {
        if (mList.size() < max) {
            for (int i = 0; i < mList_adapter.size(); i++) {
                if (mList_adapter.get(i).getPath().equals("0")) {
                    mList_adapter.remove(i);
                }
            }
            LocalMedia e = new LocalMedia();
            e.setPath("0");
            mList_adapter.add(e);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void initAdapter() {
        mAdapter = new CommonAdapter<LocalMedia>(mActivity, R.layout.rv_photo, mList_adapter) {
            @Override
            protected void convert(ViewHolder holder, final LocalMedia localMedia, final int position) {
                final ImageView imageView = holder.getView(R.id.img_photo);
                FrameLayout layout = holder.getView(R.id.layout_finsh);

                if (localMedia.getPath().equals("0")) {
                    ImageLoader.with(R.drawable.img_87, imageView);
                    layout.setVisibility(View.GONE);
                } else {
                    ImageLoader.with(localMedia.getPath(), imageView);
                    layout.setVisibility(View.VISIBLE);
                }

                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mList.remove(position);
                        mList_adapter.remove(position);
                        notifyData();
                    }
                });

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (localMedia.getPath().equals("0")) {
                            //跳转
                            PictureConfig.getInstance().init(options).openPhoto(mActivity, resultCall);
                        } else {
                            PictureConfig.getInstance().externalPicturePreview(mActivity, position, mList);
                        }
                    }
                });
            }
        };
    }

}
