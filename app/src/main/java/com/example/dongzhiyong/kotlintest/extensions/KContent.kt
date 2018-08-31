package com.example.dongzhiyong.kotlintest.extensions

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast

/**
 * 统一存放扩展函数
 *
 * Created by Dong on 16/9/25.
 */

fun Context.toast(msg: String, time: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, time).show()
}

fun Context.inflate(resId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this).inflate(resId, null, attachToRoot)
}

val Fragment.act: Activity
    get() = activity as Activity

val Fragment.ctx: Context
    get() = activity as Context

fun Fragment.toast(msg: String, time: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.act, msg, time).show()
}

fun <T : Fragment> T.withArguments(vararg params: Pair<String, Any>): T {
    arguments = BundleWrapper {
        bundleOf(this, params)
    }
    return this

}

        




