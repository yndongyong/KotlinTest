package com.example.dongzhiyong.kotlintest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * 统一存放扩展函数
 *
 * Created by dongzhiyong on 16/9/25.
 */
val View.ctx: Context
    get() = context

fun Context.toast(msg: String, time: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, time).show()
}

fun ViewGroup.inflate(layoutId: Int): View = LayoutInflater.from(this.context).inflate(layoutId, this, false)