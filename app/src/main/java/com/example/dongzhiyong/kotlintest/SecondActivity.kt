package com.example.dongzhiyong.kotlintest

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.dongzhiyong.kotlintest.adapters.GankIOAdapter
import com.example.dongzhiyong.kotlintest.extensions.toast
import com.example.dongzhiyong.kotlintest.model.GankInfo
import com.example.dongzhiyong.kotlintest.net.Http
import com.example.dongzhiyong.kotlintest.net.UrlUtils
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_second.*
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

    private val mAdapter: GankIOAdapter by lazy {
        GankIOAdapter {
            item, position ->
            this@SecondActivity.toast(item.toString())
        }
    }

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


        rv_gank_list.adapter = mAdapter
        rv_gank_list.layoutManager = LinearLayoutManager(this)
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
            url = UrlUtils.gankIOUrl //reqeust url

	    onHeaders {
                "Content-type" - "application/json" //http header
            }
	    //设置参数
            onParams {
                "username" - "dong"
                "password" - 123
            }
           //请求开始请调用
            onBefore {
                progressDialog.show()
            }
	   //请求成功	
            onResponse {
	    //resStr 返回值
                resStr ->
                var data = JSONObject(resStr)
                val optJSONArray = data.optJSONArray("results")
                val type = object : TypeToken<List<GankInfo>>() {}.type
                val temp = optJSONArray.toString()
                val response = GsonBuilder().create().fromJson<List<GankInfo>>(temp, type)
                mAdapter.datas = response
                this@SecondActivity.toast("success :" + resStr)

            }
	    //请求失败	
            onError {
                e ->
		progressDialog.dismiss()
                this@SecondActivity.toast("onError :" + e.toString())
            }
	   //请求结束，onError和onAfter之后只有一个执行	
            onAfter {
                progressDialog.dismiss()
            }

        }
    }
}
