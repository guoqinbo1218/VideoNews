package com.feicuiedu.videonews.ui.news;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.feicuiedu.videonews.R;
import com.feicuiedu.videonews.bombapi.entity.NewsEntity;
import com.feicuiedu.videonews.commons.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 实现将VideoEntity Bind 视图上
 * <p/>
 * 作者：yuanchao on 2016/8/16 0016 17:36
 * 邮箱：yuanchao@feicuiedu.com
 */
public class NewsItemView extends FrameLayout {
    public NewsItemView(Context context) {
        this(context, null);
    }

    public NewsItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @BindView(R.id.ivPreview) ImageView ivPreview;
    @BindView(R.id.tvNewsTitle) TextView tvNewsTitle;
    @BindView(R.id.tvCreatedAt) TextView tvCreatedAt;
    @BindView(R.id.textureView) TextureView textureView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.ivPlay) ImageView ivPlay;

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_news, this, true);
        ButterKnife.bind(this);
    }

    public void bindModel(NewsEntity entity) {
        // 初始视图状态
        tvNewsTitle.setVisibility(View.VISIBLE);
        ivPreview.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        ivPlay.setVisibility(View.VISIBLE);
        // 设置标题,创建时间和预览图
        tvNewsTitle.setText(entity.getNewsTitle());
        tvNewsTitle.setText(entity.getNewsTitle());
        tvCreatedAt.setText(CommonUtils.format(entity.getCreatedAt()));
    }
}