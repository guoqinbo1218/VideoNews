package com.feicuiedu.videonews.ui.likes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicuiedu.videonews.R;

import butterknife.ButterKnife;

/**
 * 作者：yuanchao on 2016/8/18 0018 17:27
 * 邮箱：yuanchao@feicuiedu.com
 */
public class LikesFragment extends Fragment{

    private View view;
    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_likes, container, false);
            ButterKnife.bind(this, view);
        }
        return view;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup)view.getParent()).removeView(view);
    }
}
