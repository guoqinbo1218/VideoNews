package com.feicuiedu.videonews.bombapi.result;


import com.feicuiedu.videonews.bombapi.entity.NewsEntity;

import java.util.List;

/**
 * 所有视频新闻查询结果
 *
 * 作者：yuanchao on 2016/8/16 0016 11:48
 * 邮箱：yuanchao@feicuiedu.com
 */
public class NewsResult {

    private List<NewsEntity> results;

    public List<NewsEntity> getResults() {
        return results;
    }

//     {
//        "results": [{...},{...}]
//    }
}
