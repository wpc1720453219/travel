package cn.wpc.dao.impl;

import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.wpc.dao.FavoriteDao;
import cn.wpc.entiy.Favorite;
import cn.wpc.util.JDBCUtils;


public class FavoriteDaoImpl implements FavoriteDao {

	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
	@Override
	public Favorite findByRidAndUid(int rid, int uid) {
		
		  Favorite favorite = null;
	        try {
	            String sql = " select * from tab_favorite where rid = ? and uid = ?";
	            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
	        } catch (DataAccessException e) {
	           
	        }
	        return favorite;
	}

	@Override
	public int findCountByRid(int rid) {
	
		 String sql = "SELECT COUNT(*) FROM tab_favorite WHERE rid = ?";

		    return template.queryForObject(sql,Integer.class,rid);
	}

	@Override
	public void add(int rid, int uid) {
		
		String sql = "insert into tab_favorite values(?,?,?)";

	    template.update(sql,rid,new Date(),uid);
	}

}
