package cn.wpc.service.impl;

import java.util.List;

import cn.wpc.dao.FavoriteDao;
import cn.wpc.dao.RouteDao;
import cn.wpc.dao.RouteImgDao;
import cn.wpc.dao.SellerDao;
import cn.wpc.dao.impl.FavoriteDaoImpl;
import cn.wpc.dao.impl.RouteDaoImpl;
import cn.wpc.dao.impl.RouteImgDaoImpl;
import cn.wpc.dao.impl.SellerDaoImpl;
import cn.wpc.entiy.PageBean;
import cn.wpc.entiy.Route;
import cn.wpc.entiy.RouteImg;
import cn.wpc.entiy.Seller;
import cn.wpc.service.RouteService;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    
    private RouteImgDao routeImgDao=new RouteImgDaoImpl();
    
    private SellerDao sellerDao=new SellerDaoImpl();
    
    private FavoriteDao favoritDao=new FavoriteDaoImpl();
 

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname ) {
        //封装PageBean
        PageBean<Route> pb = new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);  //1
        //设置每页显示条数
        pb.setPageSize(pageSize);       //5
        
        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        List<Route> list = routeDao.findByPage(cid,start,pageSize,rname);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);


        return pb;
    }



	@Override
	public Route findOne(String rid) {
		
		Route route=routeDao.findOne(Integer.parseInt(rid));
		
		List<RouteImg> routeImgList= routeImgDao.findByid(route.getRid());
		
		route.setRouteImgList(routeImgList);
		
		Seller seller= sellerDao.findById(route.getSid());
		
		route.setSeller(seller);
		
		//4.查询收藏次数
		int count = favoritDao.findCountByRid(route.getRid());
		route.setCount(count);
		return route;
		
		
	}
	
	
	
}
