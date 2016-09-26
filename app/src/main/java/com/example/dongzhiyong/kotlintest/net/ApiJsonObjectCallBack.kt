package com.example.dongzhiyong.kotlintest.net

import com.zhy.http.okhttp.callback.Callback
import okhttp3.Call
import okhttp3.Response
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import org.json.JSONObject
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by dongzhiyong on 16/9/25.
 */
class ApiJsonObjectCallBack(call: IAPICallBack) : Callback<JSONObject>() {

    val callback = call

    var errorCode = -999 //-999:我也不知道是什么错误

    companion object {
        private val SUCCEESS_TOKEN: Int = 0
        private val ERROR_TOKEN: Int = -1
    }

    override fun onError(call: Call, e: Exception, id: Int) {
        var msg: String = when (e) {
            is ConnectException -> "访问服务器失败"
            is ConnectTimeoutException -> "访问超时"
            is UnknownHostException -> " unable to resolve host || network not available"
            is SocketException -> "网络连接出错"
            is SocketTimeoutException -> "连接超时"
            is JSONException -> "反序列化字符串为JSON对象出错"
            else -> e.message ?: "访问失败"
        }

        callback.onFailed(errorCode, msg)
    }

    override fun onResponse(response: JSONObject, id: Int) {
        /**
         * {
         *    "succeed": true,
         *    "code": 0,
         *    "msg": "成功",
         *    "data":""
         * }
         */

        if (response.optInt("code", ERROR_TOKEN) == ERROR_TOKEN) {
            callback.onFailed(ERROR_TOKEN, response.optString("msg", "访问失败"))
        } else if (response.optInt("code", SUCCEESS_TOKEN) == SUCCEESS_TOKEN) {
            if (response.optJSONObject("data") != null || response.optJSONArray("data") != null) {
                callback.onSuccess(response)
            } else {
                callback.onFailed(ERROR_TOKEN, response.optString("msg", "访问失败"))
            }
        }


    }

    override fun parseNetworkResponse(response: Response, id: Int): JSONObject {
        return JSONObject(response.body().string())
    }
}

