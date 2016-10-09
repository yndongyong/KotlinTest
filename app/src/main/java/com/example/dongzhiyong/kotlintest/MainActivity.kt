package com.example.dongzhiyong.kotlintest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.dongzhiyong.kotlintest.delegates.ShareData
import com.example.dongzhiyong.kotlintest.extensions.*
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL


class MainActivity : AppCompatActivity() {

    //将一个属性，委托 给一个方法 ：属性委托
    var password: Int by ShareData.prefrence(this, ShareData.PASSWORD_SP_KEY, ShareData.PASSWORD_SP_DEFAULT)
    var usernmae: String by ShareData.prefrence(this, ShareData.PASSWORD_SP_KEY, "")

    val btn_button1: Button by lazy { find<Button>(R.id.button1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEvent(savedInstanceState)
    }

    fun initEvent(savedInstanceState: Bundle?) {
        var name: String? = "Hello Dong"
        tv_title.text = name
        button1.text = "获取网络数据"

        // bind listener way 1 匿名函数
        /*button1.setOnClickListener (object : View.OnClickListener{
            override fun onClick(v: View?) {

            }
        })*/

        /*button1.setOnClickListener {
            View.OnClickListener({
                this@MainActivity.toast(password.toString())
            })
        }*/

        //bind listen way 2 lambda表达式
        /* button1.setOnClickListener {
             //load data form net
             Thread {
                 var response = URL("https://www.baidu.com").readText()
                 runOnUiThread { toast(response) }
             }.start()
         }*/




        button1.onClick {
//             this@MainActivity.toast("onClick")
            runAsync {
                var response = URL("https://www.baidu.com").readText()
                runUiThread { toast(response) }
            }

            /* runUIThread(3000) {
                 toast("toast")
             }*/

        }

        button1.onLongClick {
            this@MainActivity.toast("onLongClick")
            true
        }

        /*btn_button1.setOnClickListener {
            Api.get(UrlUtils.gankIOUrl, object : IAPICallBack {
                override fun onSuccess(data: JSONObject) {
                    this@MainActivity.toast(data.toString())
                }

                override fun onFailed(code: Int, msg: String) {
                    this@MainActivity.toast("code = $code ; msg = $msg")
                }

            })
        }*/

        button2.setOnClickListener {
            this@MainActivity.toast(password.toString())
            password++
        }
        //打开 kotlin activity
        button3.setOnClickListener {
            val intent = IntentFor<SecondActivity>()
            startActivity(intent)
        }
        //打开 原生activity
        button4.setOnClickListener {
            /* val intent = Intent(this, NativeActivity::class.java)
             startActivity(intent)*/

            readyGo<NativeActivity>()

            /* button4.snack("snackbar")
 
             button4.snack("snackbar", Snackbar.LENGTH_LONG)*/

            /* button4.snack("snackbar") {
                 action("acttion") {
                     this@MainActivity.toast("toast")
                 }
             }*/

        }

    }

    class myrunnable : Runnable {
        override fun run() {

        }
    }
}
