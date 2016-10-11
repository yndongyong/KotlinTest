package com.example.dongzhiyong.kotlintest.extensions

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * 统一存放扩展函数
 *
 * Created by dongzhiyong on 16/9/25.
 */

fun Context.toast(msg: String, time: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, time).show()
}

val Fragment.act: Activity
    get() = activity

val Fragment.ctx: Context
    get() = activity

fun Fragment.toast(msg: String, time: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.act, msg, time).show()
}

fun <T : Fragment> T.withArguments(vararg params: Pair<String, Any>): T {
    arguments = BundleWrapper {
        bundleOf(this, params)
    }
    return this

}

        




