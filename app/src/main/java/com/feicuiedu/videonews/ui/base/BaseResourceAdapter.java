package com.feicuiedu.videonews.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collection;

/**
 * 作者：yuanchao on 2016/8/17 0017 10:43
 * 邮箱：yuanchao@feicuiedu.com
 */
public class BaseResourceAdapter<Model> extends RecyclerView.Adapter {

    public void clear() {

    }

    public void addData(Collection<Model> data) {

    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override public int getItemCount() {
        return 0;
    }
}