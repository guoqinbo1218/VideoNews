package com.feicuiedu.videonews.bombapi.other;

/**
 * 作者：yuanchao on 2016/8/18 0018 11:42
 * 邮箱：yuanchao@feicuiedu.com
 */
public class InQuery {
//    {
//        "news": {
//        "$inQuery": {
//            "className": "News"
//            "where": {
//                "objectId": 新闻Id
//            },
//        }
//    }
//    }

    private final String className;  // 表名(查询哪一个表)
    private final String field;      // 字段名(查询哪一个字段)
    private final String objectId;   // id(查询哪一个表的哪一个id)

    public InQuery(String className, String field, String objectId) {
        this.field = field;
        this.objectId = objectId;
        this.className = className;
    }

    final String LIKES_IN_QUERY =
            "{ \"%s\": { \"$inQuery\": {\"where\": {\"objectId\":\"%s\"}, \"className\": \"%s\"}}}";

    @Override public String toString() {
        return String.format(LIKES_IN_QUERY, field,objectId,className);
    }
}