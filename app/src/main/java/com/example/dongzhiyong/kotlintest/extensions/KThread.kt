package com.example.dongzhiyong.kotlintest.extensions

import android.os.Handler
import android.os.Looper


/**
 * 关于 thread 扩展属性、扩展函数集合
 * Created by Dong on 2016/10/8.
 */

fun runAsync(action: () -> Unit) {
    Thread(Runnable(action)).start()
}

fun runAsyncDelay(delayMillis: Long, action: () -> Unit) {
    //TODO 要使用excutorSerivers
}

fun runUiThread(action: () -> Unit) {
    Handler(Looper.getMainLooper()).post(Runnable(action))
}

fun runUIThread(delayMillis: Long, action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(Runnable(action), delayMillis)
}




