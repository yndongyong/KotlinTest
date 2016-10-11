package com.example.dongzhiyong.kotlintest

import android.support.v7.widget.Toolbar
import com.example.dongzhiyong.kotlintest.extensions.ctx

/**
 * Created by Dong on 2016/10/11.
 */
interface ToolBarWrapper {

    val toolBar: Toolbar

    var toolBarTitle: String
        get() = toolBar.title.toString()
        set(value) {
            toolBar.title = value
        }

    fun setupNavigationIcon(resId: Int, action: () -> Unit) {
        toolBar.navigationIcon = toolBar.ctx.getDrawable(resId)
        toolBar.setNavigationOnClickListener {
            action()
        }
    }

}