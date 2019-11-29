 # 天行旅游网

## 1.技术选型
	1. Web层
	    a)   	Servlet：前端控制器
	    b)   	html：视图
	    c)		Filter：过滤器   
	    d)   	BeanUtils：数据封装
	    e)   	Jackson：json序列化工具
	2. Service层
	    f)		Javamail：java发送邮件工具
        g)		Redis：nosql内存数据库
        h)		Jedis：java的redis客户端
	3. Dao层
	    i)		Mysql：数据库
	    j)		Druid：数据库连接池
	    k)		JdbcTemplate：jdbc的工具

## 2.项目介绍
       1.前后端分离，前端用html显示，运用JS进行数据校验，运用 Ajax，将数据以json的方式传到后端，后端运用Jackson将数据转换为json
         数据，返回到前端。主要功能包括 登录验证，分类数据展示、邮件激功能、订单管理、搜索查询、商品信息查看详情、旅游线路收藏等功
		 能，用户可以在线进行浏览旅游景点，选择喜欢的景点， 购买，查看订单，后景点收藏等操作。
	  
## 3.模块划分
	   1.注册功能
	     前端： 用户名、密码、邮箱、名字、电话号，运用js正则表达式验证,通过ajax回调函数异步提交，将数据到后端进行验证，注册和登录
		 都会有验证码，自己编写验证码代码通过，时间的方式让连接不重复让前验证码失效，并生成激活码到数据库中
						function changeCheckCode(img) {
							img.src="checkCode?"+new Date().getTime();
						}
						
		2.邮件激活
		 注册成功后发生邮件到用户邮箱，邮箱里有激活链接，点击链接跳转项目里，验证激活，激活成功会跳转登录页面
		 学会邮件工具类 MailUtils 的使用和代码编写
		 
		3.登录，退出
		 登录成功，将用户存储session中，退出，销毁session，调到登录界面
		 
		4.分类数据展示
         旅游分类：国外游，团购，自由行，几天游...这些分类
	     这些分类的信息，因为基本是不变的，所以，可以用Redis，存储，先到Redis查询，如果为空在从数据里查询，并储存到Redis里有序集合
		 
		5.分页+模糊查询
		  通过ajax回调函数，for循环，放到 js 变量中, 调用$("#route").html(route_lis);添加html中
		  模仿百度分页方式，一页10条数据
		    1.一共展示10个页码，能够达到前5后4的效果
            2.如果前边不够5个，后边补齐10个
            3.如果后边不足4个，前边补齐10个
		 6.7.....

## 3.收获
       1.独立完成整个项目，从数据库构建，到前端界面的爬取修改和部分编写，再到工具类的编写，后端代码的编写，熟悉Servlet3.0前端控
       制器的注解配置,Jquery+Bootstrap等网页技术,学会了Redis在项目中使用,邮件在项目中使用，验证码编写运用,过滤器的运用
## 4.项目亮点
       优化Servlt,减少Servlet的数量，现在是一个功能一个Servlet，将其优化为一个模块一个Servlet，相当于在数据库中一张表对应一个Servlet，在Servlet中提供不同的方法，完成用户的请求。
	   
	   //1.获取请求路径
        String uri = req.getRequestURI(); //   /travel/user/add
        System.out.println("请求uri:"+uri);//  /travel/user/add
        //2.获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        System.out.println("方法名称："+methodName);
        //3.获取方法对象Method
        //谁调用我？我代表谁
        System.out.println(this);//UserServlet的对象cn.itcast.travel.web.servlet.UserServlet@4903d97e
        try {
            //获取方法
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //4.执行方法
            //暴力反射
            //method.setAccessible(true);
            method.invoke(this,req,resp);
		

## 4 不足
       1.没用到框架，代码比较繁琐，但学习这个，再学习框架将会对框架有个更深的了解，而不是上来就是springBoot,直接套用,结果原理不知道
       2.没用到高并发,Redis没有到分布式锁，实际用处，只能算是一个练手项目
