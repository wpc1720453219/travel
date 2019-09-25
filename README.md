 # 天行旅游网

## 1.技术选型
	1. Web层
	    a)   	Servlet：前端控制器
	    b)		html：视图
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
       1.前后端分离，前端用html显示，运用JS进行数据校验，运用 Ajax，
          将数据以json的方式传到后端，后端运用Jackson将数据转换为json
         数据，返回到前端。主要功能包括 登录验证，分类数据展示、邮件激
         功能、订单管理、搜索查询、商品信息查看详情、旅游线路收藏等功能，
         用户可以在线进行浏览旅游景点，选择喜欢的景点， 购买，查看订单
       ，后景点收藏等操作。
  
  ## 3.收获
       1.独立完成整个项目，从数据库构建，到前端界面的爬取，再
       到后来工具类的编写，后端代码的编写，熟悉Servlet3.0前端控
       制器的注解配置，html+Jquery+Bootstrap等网页技术，自己有
       独立思考和构建小型项目的能力
  ## 4 不足
       1.没用到框架，代码比较繁琐，但学习了这个，再学习了框架，
        知道了框架方便了什么具体问题
       2.没用到高并发，实际用处，只能算是一个练手项目
       3.有待学习
