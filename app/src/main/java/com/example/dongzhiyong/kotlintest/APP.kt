package com.example.dongzhiyong.kotlintest

import android.app.Application
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.log.LoggerInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

/**
 * Created by Dong on 2016/9/28.
 */
class APP : Application() {

    companion object {
        var instance: Application by Delegates.notNull<Application>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        val crashHandler = CrashHandler.getInstance()
        crashHandler.init(this.applicationContext)

        initOkHttp()
    }

    private fun initOkHttp() {
        // init okhttp
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(LoggerInterceptor("OkHttp", false))
                .connectTimeout(4, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build()//other setting
        OkHttpUtils.initClient(okHttpClient)
    }
}