package com.example.dongzhiyong.kotlintest.extensions

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import java.io.Serializable
import java.util.*

/**
 * 统一存放扩展函数
 *
 * Created by dongzhiyong on 16/9/25.
 */

fun Context.toast(msg: String, time: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, time).show()
}




