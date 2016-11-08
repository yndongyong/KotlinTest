package com.example.dongzhiyong.kotlintest.extensions

import android.os.Handler
import android.os.Looper


/**
 * 关于 thread 扩展属性、扩展函数集合
 * Created by Dong on 2016/10/8.
 */

fun runAsync(action: () -> Unit) {
    TaskEngine.runOnThread { action() }
}

fun runAsync(delayMillis: Long, action: () -> Unit) {
    //TODO 还未实现
    throw UnsupportedOperationException("runAsync delayMillis unSupport ")
}

fun runUiThread(action: () -> Unit) {
    Handler(Looper.getMainLooper()).post(Runnable(action))
}

fun runUiThread(delayMillis: Long, action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(Runnable(action), delayMillis)
}




