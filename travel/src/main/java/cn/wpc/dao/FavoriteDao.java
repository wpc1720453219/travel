package cn.wpc.dao;

import cn.wpc.entiy.Favorite;

public interface FavoriteDao {

	public Favorite findByRidAndUid(int rid, int uid);

	public int findCountByRid(int rid);

	public void add(int rid, int uid);

}
