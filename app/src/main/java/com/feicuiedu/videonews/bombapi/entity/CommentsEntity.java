package com.feicuiedu.videonews.bombapi.entity;

import com.feicuiedu.videonews.bombapi.other.UserPointer;

/**
 * 作者：yuanchao on 2016/8/18 0018 14:57
 * 邮箱：yuanchao@feicuiedu.com
 */
public class CommentsEntity extends BaseEntity{

    // 评论内容
    private String content;
    // 评论作者
    private UserPointer author;

    public String getContent() {
        return content;
    }

    public UserPointer getAuthor() {
        return author;
    }

//    {
//        "content": "我是愤青！谁敢惹我！",
//        "author": {
//                "__type": "Object",
//                "className": "_User",
//                "objectId": "D5vlAAAJ",
//                "createdAt": "2016-07-11 12:20:07",
//                "updatedAt": "2016-07-11 12:20:09",
//                "username": "飞翔的猪头"
//        },
//        "news": {
//                "__type": "Pointer",
//                "className": "News",
//                "objectId": "sfd3Dd3s",
//    },

}
