package cn.wpc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wpc.entiy.Category;
import cn.wpc.service.CategoryService;
import cn.wpc.service.impl.CategoryServiceImpl;


/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
  
	 private CategoryService service = new CategoryServiceImpl();
	/*
	 * 查询所有
	 */
	 public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 
		 List<Category> cs =service.findAll();
		 
		 writeValue(cs,response);
		 
	 }     
  

}
