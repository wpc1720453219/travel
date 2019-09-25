package cn.wpc.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.wpc.dao.CategoryDao;
import cn.wpc.entiy.Category;
import cn.wpc.util.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao {

	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category ";
        return template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
	}

}
