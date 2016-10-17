
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

8.   待续。。。

9.   待续。。。
        


