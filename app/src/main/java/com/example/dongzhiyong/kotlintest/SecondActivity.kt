package com.example.dongzhiyong.kotlintest

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.dongzhiyong.kotlintest.extensions.bind
import com.example.dongzhiyong.kotlintest.extensions.update
import com.example.dongzhiyong.kotlintest.extensions.loadByUrl
import com.example.dongzhiyong.kotlintest.extensions.toast
import com.example.dongzhiyong.kotlintest.model.GankInfo
import com.example.dongzhiyong.kotlintest.net.Http
import com.example.dongzhiyong.kotlintest.net.UrlUtils
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.list_item_gank_layout.view.*
import org.json.JSONObject
import kotlin.properties.Delegates

class SecondActivity : AppCompatActivity() {

    var pageIndex: Int by Delegates.observable(0) {
        d, old, new ->
        if (pageIndex == 0) {
            //调用刷新

        } else if (pageIndex > 0) {
            //调用加载
        }
    }

    var items: List<GankInfo> = arrayListOf();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initView()
    }

    private fun initView() {
        /* mAdapter.onItemClickListener = {
             toast(it.toString())
         }*/

        var param1 = intent?.getStringExtra("param1")
        toast("param1:" + param1)

        rv_gank_list.bind(items,R.layout.list_item_gank_layout){
            iv_imageView.loadByUrl(it.images?.get(0))
            tv_desc.text = it.desc
            tv_publishedAt.text = it.publishedAt
            tv_who.text = it.who
        }

        loadDataFromNet()

    }

    private fun loadDataFromNet() {
        /*Api.get(UrlUtils.gankIOUrl, object : IAPICallBack {
            override fun onSuccess(data: JSONObject) {
//                this@SecondActivity.toast(data.toString())
                val optJSONArray = data.optJSONArray("results")
                val type = object : TypeToken<List<GankInfo>>() {}.type
                val temp = optJSONArray.toString()
                val response = GsonBuilder().create().fromJson<List<GankInfo>>(temp, type)
                mAdapter.datas = response
                this@SecondActivity.toast("success  ${getString(R.string.app_name)}")
            }

            override fun onFailed(code: Int, msg: String) {
                this@SecondActivity.toast("code: $code, msg: $msg")
            }
        })*/


        val progressDialog = ProgressDialog(this@SecondActivity)

        Http.get {
            url = UrlUtils.gankIOUrl

            onParams {
                "username" - "dong"
                "password" - 123
            }
            onHeaders {
                "Content-type" - "application/json"
            }
            onBefore {
                progressDialog.show()
            }

            onResponse {
                resStr ->
                var data = JSONObject(resStr)
                val optJSONArray = data.optJSONArray("results")
                val type = object : TypeToken<List<GankInfo>>() {}.type
                val temp = optJSONArray.toString()
                val response = GsonBuilder().create().fromJson<List<GankInfo>>(temp, type)
                rv_gank_list.update(response)
                this@SecondActivity.toast("success :" + resStr)

            }
            onError {
                e ->
                this@SecondActivity.toast("onError :" + e.toString())
            }

            onAfter {
                progressDialog.dismiss()
            }

        }
    }
}
