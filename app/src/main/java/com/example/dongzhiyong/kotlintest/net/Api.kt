package com.example.dongzhiyong.kotlintest.net

import com.google.gson.Gson
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.FileCallBack
import com.zhy.http.okhttp.log.LoggerInterceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

/**
 *
 * Created by dongzhiyong on 16/9/25.
 */
object Api {

   

    fun get(url: String, callback: IAPICallBack) {
        OkHttpUtils.get()
                .url(url)
                .tag(url)
                .build()
                .execute(ApiJsonObjectCallBack(callback))
    }

    fun post(url: String, params: HashMap<String, Any>?, callback: IAPICallBack) {
        var paramPost = HashMap<String, Any>()

        //if url contains login url add baseRequest as Object
        /*if (needLogin!!) {
            paramPost.put("baseRequest", "")
        }*/
        if (params != null) {
            for ((key, value) in params) {
                paramPost.put(key, value)
            }
        }

        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json"))
                .content(Gson().toJson(paramPost))
                .build()
                .execute(ApiJsonObjectCallBack(callback))
    }

    fun uploadFile(url: String, params: ArrayList<String>, callback: ApiFileCallBack) {
        val postFormBuilder = OkHttpUtils.post()
        postFormBuilder.url(url)

        params.forEach {
            val file = File(it)
            postFormBuilder.addFile(file.name, file.name, file)
        }
        postFormBuilder
                .build()
                .execute(callback)
    }

}