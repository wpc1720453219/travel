package cn.wpc.dao;

import java.util.List;

import cn.wpc.entiy.Route;

public interface RouteDao {

	List<Route> findByPage(int cid, int start, int pageSize,String rname);

	int findTotalCount(int cid,String rname);

	Route findOne(int rid);

}
