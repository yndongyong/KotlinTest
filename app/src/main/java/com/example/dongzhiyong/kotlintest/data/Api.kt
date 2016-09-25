package com.example.dongzhiyong.kotlintest.data

import com.example.dongzhiyong.kotlintest.net.ApiJsonObjectCallBack
import com.example.dongzhiyong.kotlintest.net.IAPICallBack
import com.google.gson.Gson
import com.zhy.http.okhttp.OkHttpUtils
import okhttp3.MediaType
import java.util.*

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

    fun post(url: String, params: HashMap<String, Any>, callback: IAPICallBack,needLogin:Boolean? = true) {
        var paramPost = HashMap<String, Any>()

        //if url contains login url add baseRequest as Object
        if (needLogin!!) {
            paramPost.put("baseRequest","")

            for ((key ,value) in params){
                paramPost.put(key,value)
            }
        }else{
            paramPost = params
        }
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json"))
                .content(Gson().toJson(paramPost))
                .build()
                .execute(ApiJsonObjectCallBack(callback))
    }

}