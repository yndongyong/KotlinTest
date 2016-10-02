package com.example.dongzhiyong.kotlintest.net

/**
 * object 定义的是类,只会生成一个对象,如同Java中的静态类
 * Created by dongzhiyong on 16/9/25.
 */
object UrlUtils {

    private val BASE_HOST: String = "http://xxxxxxx"
    private val PROJECT_NAME = "/school/"

    private fun getUrl(functionName: String): String {
        return BASE_HOST + PROJECT_NAME + functionName
    }

    val gankIOUrl: String
        get() = "http://gank.io/api/data/Android/10/1"
}