package com.example.dongzhiyong.kotlintest.extensions

import android.util.Log

/**
 * Created by Dong on 2016/10/21.
 */

private val Any.tag: String
    get() = javaClass.simpleName

fun Any.d(msg: String)
        = Log.d(tag, msg)


