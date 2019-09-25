package cn.wpc.dao;

import cn.wpc.entiy.User;

public interface UserDao {
	
	public User findByUsername(String username);
	
	public void save(User user);

	public User findByCode(String code);

	public void updateStatus(User user);

	public User findByUsernameAndPassword(String username, String password);

}
