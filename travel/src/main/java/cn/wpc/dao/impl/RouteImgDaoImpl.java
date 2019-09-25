package cn.wpc.dao.impl;

import java.util.List;

import javax.xml.transform.Templates;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.wpc.dao.RouteImgDao;
import cn.wpc.entiy.RouteImg;
import cn.wpc.util.JDBCUtils;

public class RouteImgDaoImpl implements RouteImgDao {

	private JdbcTemplate template= new JdbcTemplate(JDBCUtils.getDataSource());
	@Override
	public List<RouteImg> findByid(int rid) {
		
		String sql="select * from tab_route_img where rid= ?";
		
		return template.query(sql,new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
	}

}
