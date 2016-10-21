package com.example.dongzhiyong.kotlintest.extensions

import android.content.Context
import android.os.Build

/**
 * Created by Dong on 2016/10/21.
 */

private val version: Int
    get() = Build.VERSION.SDK_INT

fun upCompact(apiLevel: Int, action: () -> Unit) {
    if (version > apiLevel) action()
}

fun downCompact(apiLevel: Int, action: () -> Unit) {
    if (version < apiLevel) action()
}

fun Int.dpTosp(context: Context): Int
        = (this * context.resources.displayMetrics.density).toInt()

fun Float.dpTosp(context: Context): Int
        = (this * context.resources.displayMetrics.density).toInt()

fun Int.spTodp(context: Context): Int
        = (this * context.resources.displayMetrics.scaledDensity).toInt()

fun Float.spTodp(context: Context): Int
        = (this * context.resources.displayMetrics.scaledDensity).toInt()

