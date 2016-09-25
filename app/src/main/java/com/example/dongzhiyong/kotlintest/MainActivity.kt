package com.example.dongzhiyong.kotlintest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.dongzhiyong.kotlintest.data.Api
import com.example.dongzhiyong.kotlintest.data.UrlUtils
import kotlinx.android.synthetic.main.activity_main.*

import com.example.dongzhiyong.kotlintest.net.IAPICallBack
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEvent(savedInstanceState)
    }

    fun initEvent(savedInstanceState: Bundle?) {
        var name: String? = "Hello Dong"
        tv_title.text = name
        button.text = "获取网络数据"

        // bind listener way 1 匿名函数
        /* button.setOnClickListener (object :View.OnClickListener{
             override fun onClick(v: View?) {


             }
         })*/
        //bind listen way 2 lambda表达式
        /* button.setOnClickListener {
             //load data form net
             Thread{
                 var response = URL("https://www.baidu.com").readText()
                 runOnUiThread { toast(response) }
             }.start()

         }*/

        button.setOnClickListener {
            Api.get(UrlUtils.schoolNewsUrl, object : IAPICallBack {
                override fun onSuccess(data: JSONObject) {
                    this@MainActivity.toast(data.toString())
                }

                override fun onFailed(code: Int, msg: String) {
                    this@MainActivity.toast("code = $code ;msg = $msg")
                }

            })
        }

    }

    class myrunnable : Runnable {
        override fun run() {

        }
    }
}
