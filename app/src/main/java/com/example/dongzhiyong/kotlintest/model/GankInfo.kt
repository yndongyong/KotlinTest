package com.example.dongzhiyong.kotlintest.model

/**
 * Created by Dong on 2016/9/28.
 */
data class GankInfo(
        var _id: String,
        var createdAt: String,
        var desc: String,
        var images: List<String>,
        var publishedAt: String,
        var source: String,
        var type: String,
        var url: String,
        var used: Boolean = false,
        var who: String)

/*{
    "_id": "57eb0100421aa95de3b8ab00",
    "createdAt": "2016-09-28T07:30:08.163Z",
    "desc": "Android 下雪效果",
    "images": [
    "http://img.gank.io/cf2f253d-a7d7-453e-a948-3dda9d9984ae"
    ],
    "publishedAt": "2016-09-28T11:35:12.91Z",
    "source": "chrome",
    "type": "Android",
    "url": "https://github.com/HelloVass/SnowingView",
    "used": true,
    "who": "代码家"
}
*/   