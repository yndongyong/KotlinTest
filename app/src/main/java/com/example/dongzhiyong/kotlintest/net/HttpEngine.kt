package com.example.dongzhiyong.kotlintest.net

import com.example.dongzhiyong.kotlintest.SecondActivity
import com.example.dongzhiyong.kotlintest.model.GankInfo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.Callback
import com.zhy.http.okhttp.callback.StringCallback
import com.zhy.http.okhttp.log.LoggerInterceptor
import okhttp3.*
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Dong on 2016/11/4.
 */
open class HttpEngine<D> {

    companion object {
        val HTTP_METHOD_GET = 0
        val HTTP_METHOD_POST = 1
    }

    var url: String = ""
    var requestMethod: Int = HTTP_METHOD_POST

    private var params: MutableMap<String, Any>? = HashMap()

    private var headers: MutableMap<String, Any>? = HashMap()

    private var _onBefore: ((Request) -> Unit) = {}

    private var _onResponse: (response: String) -> Unit = {}

    private var _onError: (Exception) -> Unit = {}

    private var _onAfter: (Int) -> Unit = {}


    fun onBefore(onBefore: (Request) -> Unit) {
        _onBefore = onBefore
    }

    fun onResponse(onResponse: (String) -> Unit) {
        _onResponse = onResponse
    }

    fun onError(onFailed: (Exception) -> Unit) {
        _onError = onFailed
    }

    fun onAfter(onAfter: (Int) -> Unit) {
        _onAfter = onAfter
    }

    fun onParams(onParam: RequestParam.() -> Unit) {
        val pair = RequestParam()
        pair.onParam()
        params?.putAll(pair.param)

    }

    fun onHeaders(onHeaders: RequestParam.() -> Unit) {
        val pair = RequestParam()
        pair.onHeaders()
        headers?.putAll(pair.param)
    }

    internal fun execute() {
        val callback = object : StringCallback() {
            override fun onBefore(request: Request, id: Int) {
                super.onBefore(request, id)
                _onBefore.invoke(request)
            }

            override fun onResponse(response: String, id: Int) {
                _onResponse.invoke(response)
            }

            override fun onError(call: Call, e: Exception, id: Int) {
                _onError.invoke(e)
            }

            override fun onAfter(id: Int) {
                super.onAfter(id)
                _onAfter(id)
            }
        }

        val callbackObjc = object : Callback<D>() {
            override fun parseNetworkResponse(response: Response?, id: Int): D {
                val temp = response?.body()?.string()
                val type = object : TypeToken<List<GankInfo>>() {}.type
                val response = GsonBuilder().create().fromJson<List<D>>(temp, type)
                return response as D
            }

            override fun onResponse(response: D?, id: Int) {
                throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(call: Call?, e: Exception?, id: Int) {
                throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }


        if (requestMethod == HTTP_METHOD_GET) {
            val builder = OkHttpUtils.get()
            builder.url(url)
            params?.let {
                for ((key, value) in it) {
                    builder.addParams(key, value.toString())
                }
            }
            headers?.let {
                for ((key, value) in it) {
                    builder.addHeader(key, value.toString())
                }
            }
            builder.build().execute(callback)

        } else if (requestMethod == HTTP_METHOD_POST) {
            /**
             *  OkHttpUtils
            .postString()
            .url(url)
            .mediaType(MediaType.parse("application/json"))
            .content(Gson().toJson(paramPost))
            .build()
             */

            val builder = OkHttpUtils.postString()
            builder.url(url)
            builder.mediaType(MediaType.parse("application/json"))
            headers?.let {
                for ((key, value) in it) {
                    builder.addHeader(key, value.toString())
                }
            }
            params?.let {
                //如果有默认在这里添加
//                params!!.put("","")
                builder.content(Gson().toJson(params))
            }


            builder.build().execute(callback)
        }

    }

}

class RequestParam {
    val param = HashMap<String, Any>()

    operator fun String.minus(b: String): Unit {
        param.put(this, b)
    }

    operator fun String.minus(b: Int): Unit {
        param.put(this, b.toString())
    }
}

object Http {

    fun init() {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(LoggerInterceptor("OkHttp", false))
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build()//other setting
        OkHttpUtils.initClient(okHttpClient)
    }

    /* private val command: (Int, HttpEngine.() -> Unit) -> Unit = { method, req ->
         val httpEngine = HttpEngine()
         httpEngine.requestMethod = method
         httpEngine.req()
         httpEngine.execute()
     }
 
     fun get(request: HttpEngine.() -> Unit): Unit = command(HttpEngine.HTTP_METHOD_GET, request)
 
     fun post(request: HttpEngine.() -> Unit): Unit = command(HttpEngine.HTTP_METHOD_POST, request)*/


    fun <D> post2(request: HttpEngine<D>.() -> Unit): Unit {
        val httpEngine = HttpEngine<D>()
        httpEngine.request()
        httpEngine.execute()
    }
}

