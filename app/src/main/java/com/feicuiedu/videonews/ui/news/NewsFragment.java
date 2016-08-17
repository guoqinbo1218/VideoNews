package com.feicuiedu.videonews.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicuiedu.videonews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：yuanchao on 2016/8/16 0016 16:46
 * 邮箱：yuanchao@feicuiedu.com
 */
public class NewsFragment extends Fragment {

    @BindView(R.id.newsListView) NewsListView newsListView;

    private View view;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_news, container, false);
        }
        return view;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup)view.getParent()).removeView(view);
    }
}