package com.feicuiedu.videonews.ui.news;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.feicuiedu.videonews.bombapi.entity.NewsEntity;

import java.util.Collection;
import java.util.LinkedList;

/**
 * 作者：yuanchao on 2016/8/16 0016 17:45
 * 邮箱：yuanchao@feicuiedu.com
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {

    private final LinkedList<NewsEntity> dataSet = new LinkedList<>();

    public final void clear(){
        dataSet.clear();
        notifyDataSetChanged();
    }

    public final void addData(Collection<NewsEntity> data){
        dataSet.addAll(data);
        notifyDataSetChanged();
    }

    @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsItemView newsItemView = new NewsItemView(parent.getContext());
        return new MyViewHolder(newsItemView);
    }

    @Override public int getItemCount() {
        return dataSet.size();
    }

    @Override public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.bindModel(dataSet.get(position));
    }

    static final class MyViewHolder extends RecyclerView.ViewHolder {
        private NewsItemView itemView;

        public MyViewHolder(NewsItemView itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
