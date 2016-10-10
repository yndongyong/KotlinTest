package com.example.dongzhiyong.kotlintest.extensions

import android.os.Bundle

/**
 * Created by Dong on 2016/10/10.
 */
inline fun BundleWrapper(body: Bundle.() -> Unit): Bundle {
    val bundle = Bundle()
    bundle.body()
    return bundle
}

