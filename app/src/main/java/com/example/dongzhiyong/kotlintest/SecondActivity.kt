package com.example.dongzhiyong.kotlintest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.dongzhiyong.kotlintest.adapters.GankIOAdapter
import com.example.dongzhiyong.kotlintest.model.GankInfo
import com.example.dongzhiyong.kotlintest.net.Api
import com.example.dongzhiyong.kotlintest.net.IAPICallBack
import com.example.dongzhiyong.kotlintest.net.UrlUtils
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_second.*
import org.json.JSONObject

class SecondActivity : AppCompatActivity() {

    private val mAdapter: GankIOAdapter by lazy { GankIOAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initView()
    }

    private fun initView() {
        mAdapter.onItemClickListener = {
            toast(it.toString())
        }

        rv_gank_list.adapter = mAdapter
        rv_gank_list.layoutManager = LinearLayoutManager(this)
        loadDataFromNet()
    }

    private fun loadDataFromNet() {
        Api.get(UrlUtils.gankIOUrl, object : IAPICallBack {
            override fun onSuccess(data: JSONObject) {
//                this@SecondActivity.toast(data.toString())
                val optJSONArray = data.optJSONArray("results")
                val type = object : TypeToken<List<GankInfo>>() {}.type
                val temp = optJSONArray.toString()
                val response = GsonBuilder().create().fromJson<List<GankInfo>>(temp, type)
                mAdapter.datas = response
            }

            override fun onFailed(code: Int, msg: String) {
                this@SecondActivity.toast("code: $code, msg: $msg")
            }

        })
    }
}
