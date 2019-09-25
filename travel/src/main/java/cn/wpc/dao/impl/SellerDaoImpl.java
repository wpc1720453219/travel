package cn.wpc.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.wpc.dao.SellerDao;
import cn.wpc.entiy.Seller;
import cn.wpc.util.JDBCUtils;

public class SellerDaoImpl implements SellerDao {

 private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
		@Override
		public Seller findById(int sid) {	
	String sql = "select * from tab_seller where sid = ? ";
return   template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),sid);
	}

}
