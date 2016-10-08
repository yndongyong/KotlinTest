package com.example.dongzhiyong.kotlintest.extensions

import android.content.Context
import android.content.DialogInterface
import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import com.bumptech.glide.Glide

/**
 * 关于 View的 扩展属性、扩展函数 集合
 * Created by Dong on 2016/10/8.
 */
val View.ctx: Context
    get() = context


operator fun ViewGroup.get(pos: Int) = getChildAt(pos)

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View
        = LayoutInflater.from(this.context).inflate(layoutId, this, attachToRoot)

fun View.snack(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    val snackbar = Snackbar.make(this, message, duration)
    snackbar.show()
}

fun View.snack(message: String, duration: Int = Snackbar.LENGTH_SHORT, f: (Snackbar.() -> Unit)?) {
    val snackbar = Snackbar.make(this, message, duration)
    if (f != null) {
        snackbar.f()
    }
    snackbar.show()
}


fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}


fun View.onClick(action: (View?) -> Unit) {
    setOnClickListener {
        action(this)
    }
}

fun View.onLongClick(action: (View?) -> Boolean) {
    setOnLongClickListener {
        action(this)
    }
}

fun ImageView.loadByUrl(url: String) {
    Glide.with(this.ctx).load(url).into(this)
}