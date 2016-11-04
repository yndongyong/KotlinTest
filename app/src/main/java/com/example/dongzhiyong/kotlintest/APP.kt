package com.example.dongzhiyong.kotlintest

import android.app.Application
import com.example.dongzhiyong.kotlintest.net.Api
import com.example.dongzhiyong.kotlintest.net.Http
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
        initCrash()
        initOkHttp()
    }

    private fun initOkHttp() {
        // init okhttp
        Http.init()
    }

    private fun initCrash() {
        val crashHandler = CrashHandler.getInstance()
        crashHandler.init(this.applicationContext)
    }
}