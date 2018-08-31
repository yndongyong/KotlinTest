
##Kotlin library of Android

一些使用示例

1. Content/View 相关
	- 扩展view，使任意View的子类都将具有content属性

			view.ctx
	- findViewById
	
			 rootView.findV<Button>(R.id.button1)
	
	- 扩展view，使任意View的子类都可以调用show snackbar 的方法
	
			view.snack("msg")
            view.snack("msg", Snackbar.LENGTH_SHORT)
			//同时设置action,以及action的回调
			view.snack("msg", Snackbar.LENGTH_SHORT) {
                action("action") {
                    this@MainActivity.toast("action toast")
                }
            }
			
	- 使任意的View的子类都可以调用show toast方法
	
				view.toast("toast")
			    view.toast("toast",Toast.LENGTH_LONG)	
	
	- ViewGroup直接使用LayoutInflate的方法
	
			viewgroup.inflate(R.layout.activity_second)
            viewgroup.inflate(R.layout.activity_second,attachToRoot = false)	

	- view click事件
	
			view.onClick { 
            	//do work
        	}		
	- View long Click事件

			button1.onLongClick {
            	this@MainActivity.toast("onLongClick")
            	true
        	}			

2. Intent 相关

	- 获取Intent
	
		 `val intent = IntentFor<SecondActivity>()`
	


3. Bundle 相关
	- bundle 赋值1

			val bundle = BundleWrapper {
                putString("param1", "1")
                putString("param2", "2")
            }	
	- bundle 赋值2
			
			//TODO


4. Activity 相关
	
	- startActivity
	
			readyGo<SecondActivity>() 

 	- startActivity with bundle 
 			
			readyGo<SecondActivity>("param1" to params, "param2" to "2")

	- startActivityForResult
		
			readyGoForResult<SecondActivity>(REQUEST_CODE)

	- startActivityForResult with bundle

			readyGoForResult<SecondActivity>(REQUEST_CODE, "param1" to "1", "param2" to "2")

	- startActivity and then finish self

			readyGoThenKill<SecondActivity>()

	- startaActivity with bundle and then finish self
	
			readyGoThenKill<SecondActivity>( "param1" to "1", "param2" to "2")

	- findViewById

			findV<Toolbar>(R.id.toolbar)	

	- activity 中调用show toast方法
	
				toast("toast")
	

5.   Fragment相关

	- 扩展fragment，使其具有activity的实例
			
			fragment.act
	
	- 扩展fragment，使其具有Content的实例
			
			fragment.ctx

	- toast方法
	
			toast("toast")
	- 生成Fragment实例的方法

			 val fragment = XxxFragment.withArguments("param1" to 1, "param2" to "2")
  
6.   线程相关

	- 在子线程中执行
	
			runAsync {
                //do work
            }

	- 在子线程中执行 带延时 ，单位毫秒 （还没有）
	
	
	- 在UI线程中执行
	
			runUiThread {
                //do work
            }
	- 在UI线程中执行，延时多少毫秒
	
			runUiThread(2000) {
                //do work delay 2's
            }		
			
	
7.   SharedPreferences相关，使用属性委托的思想封装SharedPreferences
	
		用法：
		var password: String by ShareData.prefrence(this, "key_password", "DEFAULT_vlaue")
		当在代码中对password重新赋值时，会自动更新SharedPreferences中的值。


8.   kotlin实现配置式的网络请求访问

		用法：
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
                this@SecondActivity.toast("success :" + resStr)

            }
	    	//请求失败	
	        onError {
	            e ->
				progressDialog.dismiss()
	            this@SecondActivity.toast("onError :" + e.toString())
	        }
	   		//请求结束，onError和onAfter只有一个会执行	
	        onAfter {
	            progressDialog.dismiss()
	        }

        }

9.   FastAdaper
	-  绑定单布局
	
	```
	rl_list.bind(datas, R.layout.item_category_1){
            Glide.with(context).load(it.getUrl()).into(iv_icon)
            tv_category_name.text = it.description
        }
	```	
	
   -  对应多种布局
	
	```
		rl_list.bind(datas)
                .map(layoutId = R.layout.item_category_1, predicate = { it.type == 1 }) {
                    Glide.with(context).load(it.getUrl()).into(iv_icon)
                    tv_category_name.text = it.description
                    setOnClickListener {
                        Toast.makeText(context, "click 1", Toast.LENGTH_SHORT).show()
                    }
                    iv_icon.setOnClickListener {
                        Toast.makeText(context, "click image", Toast.LENGTH_SHORT).show()
                    }
                }.map(layoutId = R.layout.item_category_2, predicate = { it.type == 2 }) {
                    Glide.with(context).load(it.getUrl()).into(c2_iv_icon)
                    c2_tv_category_name.text = it.description
                    setOnClickListener {
                        Toast.makeText(context, "click 2", Toast.LENGTH_SHORT).show()
                    }
                }
	```

10.   待续。。。


11. 待续。。。   
        


