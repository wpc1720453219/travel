package cn.wpc.service.impl;

import cn.wpc.dao.FavoriteDao;
import cn.wpc.dao.impl.FavoriteDaoImpl;
import cn.wpc.entiy.Favorite;
import cn.wpc.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
	
	private FavoriteDao favoriteDao = new FavoriteDaoImpl();
	@Override
	public boolean isFavorite(String rid, int uid) {
		
	Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);
		
		return  favorite != null;
	}
	@Override
	public void add(String rid, int uid) {
		
		  favoriteDao.add(Integer.parseInt(rid),uid);
	}

}
