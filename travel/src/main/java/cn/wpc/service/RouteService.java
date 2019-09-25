package cn.wpc.service;

import cn.wpc.entiy.PageBean;
import cn.wpc.entiy.Route;

public interface RouteService {

	public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname);

	public Route findOne(String rid);
		

   
}
