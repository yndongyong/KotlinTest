package com.example.dongzhiyong.kotlintest.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

/**
 * 关于 Activity的 扩展属性、扩展函数集合
 * Created by Dong on 2016/10/8.
 */
inline fun <reified T : View> Activity.find(viewId: Int): T = findViewById(viewId) as T

inline fun <reified T : Activity> Activity.IntentFor(): Intent = Intent(this, T::class.java)


inline fun <reified T : Activity> Activity.readyGo() {
    startActivity(IntentFor<T>())
}

inline fun <reified T : Activity> Activity.readyGo(bundle: Bundle) {
    val intent = IntentFor<T>()
    intent.putExtras(bundle)
    startActivity(intent)
}

inline fun <reified T : Activity> Activity.readyGoThenKill() {
    val intent = IntentFor<T>()
    startActivity(intent)
    this.finish()
}

inline fun <reified T : Activity> Activity.readyGoThenKill(bundle: Bundle) {
    val intent = IntentFor<T>()
    intent.putExtras(bundle)
    startActivity(intent)
    this.finish()
}

inline fun <reified T : Activity> Activity.readyGoForResult(requestCode: Int) {
    val intent = IntentFor<T>()
    startActivityForResult(intent, requestCode)
}

inline fun <reified T : Activity> Activity.readyGoForResult(requestCode: Int, bundle: Bundle) {
    val intent = IntentFor<T>()
    intent.putExtras(bundle)
    startActivityForResult(intent, requestCode)
}

