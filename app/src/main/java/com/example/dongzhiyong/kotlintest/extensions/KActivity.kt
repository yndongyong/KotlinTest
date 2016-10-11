package com.example.dongzhiyong.kotlintest.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import java.io.Serializable

/**
 * 关于 Activity的 扩展属性、扩展函数集合
 * Created by Dong on 2016/10/8.
 */
inline fun <reified T : View> Activity.findV(viewId: Int): T = findViewById(viewId) as T


inline fun <reified T : Activity> Activity.readyGo(vararg params: Pair<String, Any>) {
    val intent = IntentFor<T>()
    params.let {
        intent.putExtras(BundleWrapper {
            bundleOf(this, params)
        })
    }
    startActivity(intent)
}


inline fun <reified T : Activity> Activity.readyGoThenKill(vararg params: Pair<String, Any>) {
    val intent = IntentFor<T>()
    params.let {
        intent.putExtras(BundleWrapper {
            bundleOf(this, params)
        })
    }

    startActivity(intent)
    this.finish()
}

inline fun <reified T : Activity> Activity.readyGoForResult(requestCode: Int, vararg params: Pair<String, Any>) {
    val intent = IntentFor<T>()
    params.let {
        intent.putExtras(BundleWrapper {
            bundleOf(this, params)
        })
    }
    startActivityForResult(intent, requestCode)
}




inline fun <reified T : Activity> Activity.IntentFor(vararg params: Pair<String, Any>): Intent {
    val intent = Intent(this, T::class.java)
    params.let {
        intent.putExtras(BundleWrapper {
            bundleOf(this, params)
        })
    }

    return intent
}


fun bundleOf(b: Bundle, params: Array<out Pair<String, Any>>) {
    for ((k, v) in params) {
        when (v) {
            is Boolean -> b.putBoolean(k, v)
            is Byte -> b.putByte(k, v)
            is Char -> b.putChar(k, v)
            is Short -> b.putShort(k, v)
            is Int -> b.putInt(k, v)
            is Long -> b.putLong(k, v)
            is Float -> b.putFloat(k, v)
            is Double -> b.putDouble(k, v)
            is String -> b.putString(k, v)
            is CharSequence -> b.putCharSequence(k, v)
            is Parcelable -> b.putParcelable(k, v)
            is Serializable -> b.putSerializable(k, v)
            is BooleanArray -> b.putBooleanArray(k, v)
            is ByteArray -> b.putByteArray(k, v)
            is CharArray -> b.putCharArray(k, v)
            is DoubleArray -> b.putDoubleArray(k, v)
            is FloatArray -> b.putFloatArray(k, v)
            is IntArray -> b.putIntArray(k, v)
            is LongArray -> b.putLongArray(k, v)
            is Array<*> -> {
                @Suppress("UNCHECKED_CAST")
                when {
                    v.isArrayOf<Parcelable>() -> b.putParcelableArray(k, v as Array<out Parcelable>)
                    v.isArrayOf<CharSequence>() -> b.putCharSequenceArray(k, v as Array<out CharSequence>)
                    v.isArrayOf<String>() -> b.putStringArray(k, v as Array<out String>)
                    else -> throw UnsupportedOperationException("Unsupported bundle component (${v.javaClass})")
                }
            }
            is ShortArray -> b.putShortArray(k, v)
            is Bundle -> b.putBundle(k, v)
            else -> throw UnsupportedOperationException("Unsupported bundle component (${v.javaClass})")
        }
    }
}

