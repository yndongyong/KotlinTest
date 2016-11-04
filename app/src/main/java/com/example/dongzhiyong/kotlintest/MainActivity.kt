package com.example.dongzhiyong.kotlintest

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Button
import com.example.dongzhiyong.kotlintest.delegates.ShareData
import com.example.dongzhiyong.kotlintest.extensions.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ToolBarWrapper {

    companion object {
        var REQUEST_CODE = 1001
    }

    override val toolBar: Toolbar by lazy { findV<Toolbar>(R.id.toolbar) }

    //将一个属性，委托 给一个方法 ：属性委托
    var password: Int by ShareData.prefrence(this, ShareData.PASSWORD_SP_KEY, ShareData.PASSWORD_SP_DEFAULT)
    var usernmae: String by ShareData.prefrence(this, ShareData.PASSWORD_SP_KEY, "")

    val btn_button1: Button by lazy { findV<Button>(R.id.button1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolBarTitle = "custom title"

        setupNavigationIcon(R.mipmap.ic_launcher) {
            toast("右上角点击")
        }

        initEvent(savedInstanceState)

    }

    fun initEvent(savedInstanceState: Bundle?) {
        var name: String? = "Hello Dong"
//        tv_title.text = name
        button1.text = "获取网络数据"


        with(tv_title) {
            text = name
        }

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

//            btn_button1.toast("toast")
//            btn_button1.toast("toast",Toast.LENGTH_LONG)

//            btn_button1.snack("msg")
//            btn_button1.snack("msg", Snackbar.LENGTH_SHORT)
            /*btn_button1.snack("msg", Snackbar.LENGTH_SHORT) {
                action("action") {
                    this@MainActivity.toast("action toast")
                }
            }*/

            /* runAsync {
                 var response = URL("https://www.baidu.com").readText()
                 runUiThread { toast(response) }
             }*/
            /* runUiThread {
                 //do work
             }*/

            runUiThread(2000) {
                //do work delay 2's
                d("错误消息")
            }


        }

        /*button1.onClick { 
            //do work
        }*/

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
        button2.onClick {
            this@MainActivity.toast(password.toString())
            password++
        }
        //打开 kotlin activity
        button3.onClick {
            /*val intent = IntentFor<SecondActivity>()
            val bundle = BundleWrapper {
                putString("param1", "1")

                putString("param2", "2")
            }
            intent.putExtras(bundle)
            startActivity(intent)*/

            val params = "1"

            // startActivity
//            readyGo<SecondActivity>()

            readyGo<SecondActivity>("param1" to params, "param2" to "2")

//            readyGoForResult<SecondActivity>(REQUEST_CODE)

//            readyGoForResult<SecondActivity>(REQUEST_CODE, "param1" to "1", "param2" to "2")

//            readyGoThenKill<SecondActivity>("param1" to "1", "param2" to "2")

        }
        //打开 原生activity
        button4.onClick {
            /*val intent = Intent(this, NativeActivity::class.java).apply { 
                putExtra("","")
                putExtra("",false)
            }
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
        button5.onClick {
            AlertDialog.Builder(this@MainActivity).apply {
                setTitle(R.string.app_name)
                setCancelable(false)
                setPositiveButton(R.string.btn_ensure) {
                    dialog, which ->
                    dialog.dismiss()
                    this@MainActivity.toast("dialog positive dismiss")
                }
                setNegativeButton(R.string.btn_cancel) {
                    dialog, which ->
                    dialog.dismiss()
                    this@MainActivity.toast("dialog negative dismiss")
                }
            }.show()
        }

    }


}
