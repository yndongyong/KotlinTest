package com.example.dongzhiyong.kotlintest

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

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

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View
        = LayoutInflater.from(this.context).inflate(layoutId, this, attachToRoot)

fun ImageView.loadByUrl(url: String) {
    Glide.with(this.ctx).load(url).into(this)
}

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

inline fun <reified T : View> Activity.find(viewId: Int): T = findViewById(viewId) as T

operator fun ViewGroup.get(pos: Int) = getChildAt(pos)
