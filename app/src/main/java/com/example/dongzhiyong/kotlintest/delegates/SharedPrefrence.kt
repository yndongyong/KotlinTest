package com.example.dongzhiyong.kotlintest.delegates

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 *
 * Created by Dong on 2016/9/27.
 */
object ShareData {

    val PASSWORD_SP_KEY = "key_password"
    val PASSWORD_SP_DEFAULT = 0

    /**
     * 定义一个函数使用委托
     */
    fun <W> prefrence(context: Context, key: String, default: W)
            = SharedPrefrenceDelegate(context, key, default)
}

/**
 * 自定义委托 扩展sheraedPrefrence
 */
class SharedPrefrenceDelegate<T>(val context: Context, val key: String, val default: T) :
        ReadWriteProperty<Any, T> {

    val sp by lazy { context.getSharedPreferences("shared_default", Context.MODE_PRIVATE) }

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return findPrefrences(key, default)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        putPrefrences(key, value)
    }

    private fun <U> findPrefrences(key: String, default: U) = with(sp) {
        var result: Any = when (default) {
            is String -> getString(key, default)
            is Int -> getInt(key, default)
            is Long -> getLong(key, default)
            is Float -> getFloat(key, default)
            is Boolean -> getBoolean(key, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }

        result as U
    }

    private fun <U> putPrefrences(key: String, value: U) = with(sp.edit()) {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            is Boolean -> putBoolean(key, value)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply()
    }

}

