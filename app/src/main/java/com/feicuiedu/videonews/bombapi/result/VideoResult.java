package com.feicuiedu.videonews.bombapi.result;


import com.feicuiedu.videonews.bombapi.entity.VideoEntity;

import java.util.List;

/**
 * 所有视频新闻查询结果
 *
 * 作者：yuanchao on 2016/8/16 0016 11:48
 * 邮箱：yuanchao@feicuiedu.com
 */
public class VideoResult {

    private List<VideoEntity> results;

    public List<VideoEntity> getResults() {
        return results;
    }

//     {
//        "results": [{...},{...}]
//    }
}
