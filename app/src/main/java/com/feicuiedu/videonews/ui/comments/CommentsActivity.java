package com.feicuiedu.videonews.ui.comments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.feicuiedu.videonews.R;
import com.feicuiedu.videonews.bombapi.entity.NewsEntity;
import com.feicuiedu.videonews.commons.CommonUtils;
import com.feicuiedu.videoplayer.part.SimpleVideoView;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 新闻评论页面,主要包括三个部分:
 * <ul>
 * <li/>上: 用MediaPlayer +SurfaceView实现的视频播放, {@link SimpleVideoView}
 * <li/>下: 评论列表视图 {@link com.feicuiedu.videonews.ui.base.BaseResourceView}
 * <li/>选项菜单中有评论和收藏
 * </ul>
 */
public class CommentsActivity extends AppCompatActivity {

    private static final String KEY_NEWS = "KEY_NEWS";

    public static void open(Context context, NewsEntity newsEntity) {
        // NewsEntity --> json字符串
        Gson gson = new Gson();
        String news = gson.toJson(newsEntity);
        //
        Intent intent = new Intent(context, CommentsActivity.class);
        intent.putExtra(KEY_NEWS, news);
        context.startActivity(intent);
    }

    private NewsEntity newsEntity; // 视频新闻实体

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取传入当前页面的数据
        String news = getIntent().getStringExtra(KEY_NEWS);
        Gson gson = new Gson();
        newsEntity = gson.fromJson(news, NewsEntity.class);
        setContentView(R.layout.activity_comments);
    }

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tvTitle) TextView tvTitle; //
    @BindView(R.id.simpleVideoPlayer) SimpleVideoView simpleVideoView;
    @BindView(R.id.commentsListView) CommentsListView commentsListView;

    @Override public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        //
        tvTitle.setText(newsEntity.getNewsTitle());
        // 告诉评论列表视图，针对哪一条新闻去获取评论数据
        commentsListView.setNewsId(newsEntity.getObjectId());
        commentsListView.autoRefresh();
        // 设置播放源
        String videoPath = CommonUtils.encodeUrl(newsEntity.getVideoUrl());
        simpleVideoView.setVideoPath(videoPath);
    }

    @Override protected void onResume() {
        super.onResume();
        simpleVideoView.onResume();
    }

    @Override protected void onPause() {
        super.onPause();
        simpleVideoView.onPasuse();
    }
}
