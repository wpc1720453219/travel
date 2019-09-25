package cn.wpc.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.wpc.entiy.ResultInfo;
import cn.wpc.entiy.User;
import cn.wpc.service.UserService;
import cn.wpc.service.impl.UserServiceImpl;;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

	private UserService service=new UserServiceImpl();
	
	/*
	 * 注册功能
	 */
	 public void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 
	        //验证校验
	        String check = request.getParameter("check");
	        //从sesion中获取验证码
	        HttpSession session = request.getSession();
	        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
	        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
	        //比较
	        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
	            //验证码错误
	            ResultInfo info = new ResultInfo();
	            //注册失败
	            info.setFlag(false);
	            info.setErrorMsg("验证码错误");
	            //将info对象序列化为json
	            ObjectMapper mapper = new ObjectMapper();
	            String json = mapper.writeValueAsString(info);
	            response.setContentType("application/json;charset=utf-8");
	            response.getWriter().write(json);
	            return;
	        }
			//1.获取数据
	        Map<String, String[]> map = request.getParameterMap();

	        //2.封装对象
	        User user = new User();
	        
	        try {
				BeanUtils.populate(user, map);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	      //3.调用service完成注册
	       
	        boolean flag = service.regist(user);
	        
	        ResultInfo info =new ResultInfo();
	        
	        if(flag) {
	        	info.setFlag(true);
	        }else {
	        	info.setFlag(false);
	        	info.setErrorMsg("注册失败");
	        }
	        
	        ObjectMapper mapper=new ObjectMapper();
	       String json = mapper.writeValueAsString(info);
	        response.setContentType("application/json;charset=utf-8");
	        response.getWriter().write(json);

	 }
	 
	 /*
	  * 登录功能
	  */
	 public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//验证校验
	        String check = request.getParameter("check");
	        //从sesion中获取验证码
	        HttpSession session = request.getSession();
	        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
	        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
	        //比较
	        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
	            //验证码错误
	            ResultInfo info = new ResultInfo();
	            //注册失败
	            info.setFlag(false);
	            info.setErrorMsg("验证码错误");
	            //将info对象序列化为json
	            ObjectMapper mapper = new ObjectMapper();

		        response.setContentType("application/json;charset=utf-8");
		        mapper.writeValue(response.getOutputStream(),info);
		        return;
	        }
			 Map<String,String[]> map=request.getParameterMap();
			   //2.封装User对象
		        User user = new User();
		        try {
		            BeanUtils.populate(user,map);
		        } catch (IllegalAccessException e) {
		            e.printStackTrace();
		        } catch (InvocationTargetException e) {
		            e.printStackTrace();
		        }
		        
		      //3.调用Service查询
		        UserService service = new UserServiceImpl();
		        User u  = service.login(user);

		        ResultInfo info = new ResultInfo();
		        
		        //4.判断用户对象是否为null
		        if(u == null){
		            //用户名密码或错误
		            info.setFlag(false);
		            info.setErrorMsg("用户名密码或错误");
		        }
		        if(u!=null && !"Y".equals(u.getStatus())) {
		        	info.setFlag(false);
		        	info.setErrorMsg("您尚未激活，请激活");
		        }
		        if(u!=null && "Y".equals(u.getStatus())) {
		        	request.getSession().setAttribute("user",u);
		        	info.setFlag(true);
		        }
		      //响应数据
		        ObjectMapper mapper = new ObjectMapper();

		        response.setContentType("application/json;charset=utf-8");
		        mapper.writeValue(response.getOutputStream(),info);

	 }
	 
	 /*
	  * 查询单个对象,是
	  */
	 public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 
		 Object user=request.getSession().getAttribute("user");
		 	ObjectMapper mapper = new ObjectMapper();
	        response.setContentType("application/json;charset=utf-8");
	        mapper.writeValue(response.getOutputStream(),user);
		 
	 }
	 
	 /*
	  * 退出功能
	  */
	 public void  exit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 	request.getSession().invalidate();
			response.sendRedirect(request.getContextPath()+"/login.html");
		 
	 }
	 
	 /*
	  * 激活功能
	  */
	 public void  active(HttpServletRequest request, HttpServletResponse response) throws IOException{
			String code=request.getParameter("code");
			if(code!=null) {
				 //2.调用service完成激活
	            boolean flag = service.active(code);

	            //3.判断标记
	            String msg = null;
	            if(flag){
	                //激活成功
	                msg = "激活成功，请<a href='login.html'>登录</a>";
	            }else{
	                //激活失败
	                msg = "激活失败，请联系管理员!";
	            }
	            response.setContentType("text/html;charset=utf-8");
	            response.getWriter().write(msg);
			
			}
	 }
	 

	 
	 
	 
}
