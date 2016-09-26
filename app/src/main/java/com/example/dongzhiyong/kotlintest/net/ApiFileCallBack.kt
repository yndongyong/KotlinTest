package com.example.dongzhiyong.kotlintest.net

import com.zhy.http.okhttp.callback.FileCallBack

/**
 * Created by dongzhiyong on 16/9/26.
 */
abstract class ApiFileCallBack(val filePathFolder:String,val fileName:String) :FileCallBack(filePathFolder,fileName){
}