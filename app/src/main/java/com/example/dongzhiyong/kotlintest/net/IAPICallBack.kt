package com.example.dongzhiyong.kotlintest.net

import org.json.JSONObject

/**
 * Created by dongzhiyong on 16/9/25.
 */
interface IAPICallBack {
    fun onSuccess(data: JSONObject)
    fun onFailed(code: Int, msg: String)
}